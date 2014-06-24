package edu.kit.iti.structuredtext.plcopenxml;

import edu.kit.iti.structuredtext.StructuredTextPrinter;
import edu.kit.iti.structuredtext.antlr.StructuredTextLexer;
import edu.kit.iti.structuredtext.antlr.StructuredTextParser;
import edu.kit.iti.structuredtext.ast.*;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;
import edu.kit.iti.structuredtext.plcopenxml.model.SFCAction;
import edu.kit.iti.structuredtext.plcopenxml.model.SFCGraph;
import edu.kit.iti.structuredtext.plcopenxml.model.SfcXmlElement;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.jdom2.*;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weigl on 23/06/14.
 */
public class SFCModelBuilderCodeSys {
    private static final Namespace NAMESPACE = Namespace.getNamespace("http://www.plcopen.org/xml/tc6_0201");
    private static final Namespace NS_XHTML = Namespace.getNamespace("http://www.w3.org/1999/xhtml");
    private File filename;


    public SFCModelBuilderCodeSys(String s) {
        filename = new File(s);
    }

    public static Element child(Element d, String... name) {

        for (String s : name) {
            if (d == null)
                break;

            Namespace ns = NAMESPACE;

            if ("xhtml".equals(s))
                ns = NS_XHTML;

            d = d.getChild(s, ns);
        }

        return d;

    }

    public static List<Element> children(Element d, String name) {
        if (d == null)
            return null;
        return d.getChildren(name, NAMESPACE);
    }

    public static StructuredTextParser getStructuredTextParser(String s) {
        StructuredTextLexer lexer = new StructuredTextLexer(new ANTLRInputStream(s));
        return new StructuredTextParser(new CommonTokenStream(lexer));
    }

    protected Document loadXml() throws JDOMException, IOException {
        SAXBuilder saxBuilder = new SAXBuilder();
        return saxBuilder.build(filename);
    }

    public List<SFCFunctionBlock> build() {
        try {
            Document p = loadXml();

            // search for: <data name="http://www.3s-software.com/plcopenxml/application" handleUnknown="implementation">

            XPathFactory xpf = XPathFactory.instance();
            XPathExpression<Element> xpath = xpf.compile("//ppx:pou",
                    Filters.element(), null,
                    Namespace.getNamespace("ppx", "http://www.plcopen.org/xml/tc6_0201"));
            List<Element> pous = xpath.evaluate(p);

            ArrayList<SFCFunctionBlock> blocks = new ArrayList<>();

            for (Element e : pous) {
                SFCGraph graph = parseSfcFB(e);
                Graph2Ast graph2Ast = new Graph2Ast(graph);
                FunctionBlockDeclaration fb = graph2Ast.getFunctionBlock();


                StructuredTextPrinter stp = new StructuredTextPrinter();
                stp.setPrintComments(false);

                new TypeDeclarations(graph2Ast.enumStates).visit(stp);
                System.out.println(stp.getString());

                stp.clear();
                fb.visit(stp);
                System.out.println(stp.getString());


            }


            return blocks;
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private SFCGraph parseSfcFB(Element e) {
        VariableScope vs = parseInterface(child(e, "interface"));
        List<SFCAction> actions = parseActions(child(e, "actions"));
        Map<Integer, SfcXmlElement> nodes = parseNodes(child(e, "body", "SFC"));
        SFCGraph graph = SFCGraph.buildGraph(actions, nodes);
        graph.setScope(vs);
        graph.setName(attrval(e, "name"));
        return graph;
    }

    private Map<Integer, SfcXmlElement> parseNodes(Element child) {
        Map<Integer, SfcXmlElement> list = new HashMap<>();

        if (child == null)
            return list;

        for (Element e : child.getChildren()) {
            SfcXmlElement element = null;
            switch (e.getName()) {
                case "transition":
                    SfcXmlElement.Transition t = new SfcXmlElement.Transition();
                    t.conditionReferences = parseConnections(
                            children(child(e, "condition"), "connectionPointIn"));
                    element = t;
                    break;

                case "step":
                    SfcXmlElement.Step s = new SfcXmlElement.Step();
                    s.name = attrval(e, "name");
                    s.initial = "true".equals(attrval(e, "initialStep"));
                    element = s;
                    break;

                case "inVariable":
                    SfcXmlElement.XmlTransition v = new SfcXmlElement.XmlTransition();
                    v.expression = parseExpression(child(e, "expression").getTextNormalize());
                    element = v;
                    break;

                case "jumpStep":
                    SfcXmlElement.JumpStep j = new SfcXmlElement.JumpStep();
                    j.targetName = attrval(e, "targetName");
                    element = j;
                    break;

                case "selectionDivergence":
                    SfcXmlElement.Divergence d = new SfcXmlElement.Divergence();
                    element = d;
                    break;

                case "simultaneousDivergence":
                    SfcXmlElement.Divergence g = new SfcXmlElement.Divergence();
                    g.parallel = true;
                    element = g;
                    break;

                case "selectionConvergence":
                    SfcXmlElement.Convergence c = new SfcXmlElement.Convergence();
                    element = c;
                    break;

                case "simultaneousConvergence":
                    SfcXmlElement.Convergence f = new SfcXmlElement.Convergence();
                    f.parallel = true;
                    break;
                default:
                    throw new IllegalStateException("no handling for tag: " + e.getName());
            }
            element.localId = Integer.parseInt(attrval(e, "localId"));
            element.incomingReferenceIds = parseConnections(children(e, "connectionPointIn"));
            list.put(element.localId, element);
        }
        return list;
    }

    private String attrval(Element child, String name) {
        for (Attribute a : child.getAttributes()) {
            if (name.equals(a.getName()))
                return a.getValue();
        }
        return null;
    }

    private Expression parseExpression(String expression) {
        return getStructuredTextParser(expression).expression().ast;
    }

    private List<Integer> parseConnections(List<Element> connectionPointIn) {
        List<Integer> i = new ArrayList<>();

        if (connectionPointIn != null)
            for (Element d : connectionPointIn)
                for (Element e : children(d, "connection")) {
                    i.add(Integer.parseInt(attrval(e, "refLocalId")));
                }
        return i;
    }

    private List<SFCAction> parseActions(Element actions) {
        ArrayList<SFCAction> l = new ArrayList<>();

        if (actions != null) {
            for (Element action : children(actions, "action"))
                l.add(parseAction(action));
        }
        return l;
    }

    private SFCAction parseAction(Element action) {
        String name = attrval(action, "name");

        Element st = child(action, "body", "ST");

        if (st == null) {
            throw new IllegalArgumentException("Only Actions with ST are support: Error in action: " + name);
        }

        StatementList sl = parseStatementList(child(st, "xhtml").getTextNormalize());


        return new SFCAction(name, sl);
    }

    private VariableScope parseInterface(Element e) {
        VariableScope vs = new VariableScope();

        vs.clear(VariableDeclaration.INPUT);
        parseVariables(vs, child(e, "inputVars"));

        vs.clear(VariableDeclaration.OUTPUT);
        parseVariables(vs, child(e, "outputVars"));

        vs.clear(VariableDeclaration.LOCAL);
        parseVariables(vs, child(e, "localVars"));

        return vs;
    }

    private void parseVariables(VariableScope vs, Element listOfVariable) {
        if (listOfVariable != null)
            for (Element e : children(listOfVariable, "variable")) {
                String typeName = child(e, "type").getChildren().get(0).getName();
                String name = attrval(e, "name");

                VariableDeclaration variableDeclaration = new VariableDeclaration(name, vs.peek());
                variableDeclaration.setDataType(typeName);

                if (child(e, "initialValue") != null) {
                    String initialValue = attrval(child(e, "initialValue", "simpleValue"), "value");
                    variableDeclaration.setInit(parseConstants(initialValue));
                }
                vs.add(variableDeclaration);
            }
    }

    private ScalarValue<?, ?> parseConstants(String s) {
        StructuredTextParser parser = getStructuredTextParser(s);
        ScalarValue<?, ?> constant = parser.constant().ast;
        return constant;
    }

    private StatementList parseStatementList(String xhtml) {
        return getStructuredTextParser(xhtml).statement_list().ast;
    }
}
