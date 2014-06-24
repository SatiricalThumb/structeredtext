package edu.kit.iti.structuredtext.plcopenxml;

import edu.kit.iti.structuredtext.ast.*;
import edu.kit.iti.structuredtext.plcopenxml.model.SFCGraph;

/**
 * @author weigla
 * @date 24.06.2014
 */
public class Graph2Ast {
    public static final String STATE_VARIABLE = "_state";
    private static final String EXIT_VARIABLE = "_exit";
    private static final String ENTRY_VARIABLE = "_entry";

    SFCGraph graph;
    EnumerationTypeDeclaration enumStates;

    public Graph2Ast(SFCGraph graph) {
        this.graph = graph;
    }


    private String getStatesTypeName() {
        return graph.getName() + "_states_t";
    }

    public FunctionBlockDeclaration getFunctionBlock() {
        FunctionBlockDeclaration fbd = new FunctionBlockDeclaration();
        graph.getScope().clear(VariableDeclaration.LOCAL);
        graph.getScope().create(getStatesTypeName(), STATE_VARIABLE);
        graph.getScope().create("BOOL", ENTRY_VARIABLE, EXIT_VARIABLE);

        fbd.setFunctionBlockName(graph.getName());
        fbd.setScope(graph.getScope());

        enumStates = new EnumerationTypeDeclaration();
        enumStates.setTypeName(getStatesTypeName());

        for (SFCGraph.Node n : graph.getNodes()) {
            if (n instanceof SFCGraph.Step) {
                enumStates.addValue(n.name);
                if (((SFCGraph.Step) n).initial) {
                    enumStates.setInitializationValue(n.name);

                }
            }
        }

        CaseStatement theBigCase = new CaseStatement();
        theBigCase.setExpression(parseExpression("_states"));

        for (SFCGraph.Node n : graph.getNodes()) {
            if (n instanceof SFCGraph.Step)
                theBigCase.addCase(caseFor((SFCGraph.Step) n));


        }

        fbd.getFunctionBody().
                add(assignment(STATE_VARIABLE, "FALSE"));
        fbd.getFunctionBody().add(theBigCase);
        return fbd;
    }


    private CaseStatement.Case caseFor(SFCGraph.Step n) {
        CaseStatement.Case esac = new CaseStatement.Case();

        esac.addCondition(new CaseConditions.Enumeration("state_t#" + n.name));
        StatementList sl = esac.getStatements();


        sl.add(new CommentStatement("begin(onEntry): " + n.name));

        if (n.onEntry != null) {
            IfStatement ifEntry = ifstatement(ENTRY_VARIABLE, assignment(ENTRY_VARIABLE, "FALSE"));
            ifEntry.getConditionalBranches().get(0).getStatements().addAll(n.onEntry);
            sl.add(ifEntry);
        } else {
            sl.add(assignment(ENTRY_VARIABLE, "FALSE"));
        }
        sl.add(new CommentStatement("end(onEntry): " + n.name));

        //build in active
        sl.add(new CommentStatement("begin(onWhile): " + n.name));

        if (n.onWhile != null)
            sl.addAll(n.onWhile);

        sl.add(new CommentStatement("end(onWhile):" + n.name));

        sl.add(new CommentStatement("begin(transition)"));
        IfStatement transitions = new IfStatement();
        StatementList assignExitTrue = parseStatements(
                String.format("%s := TRUE;", EXIT_VARIABLE));

        for (SFCGraph.Transition t : n.outgoing) {
            StatementList assignNextState = parseStatements(String.format("%s := %s#%s;",
                    STATE_VARIABLE, getStatesTypeName(), t.to.name));
            GuardedStatement gc = new GuardedStatement();
            gc.setCondition(t.conditions.get(0)); //TODO include all?
            gc.getStatements().addAll(assignExitTrue);
            gc.getStatements().addAll(assignNextState);
            transitions.getConditionalBranches().add(gc);
        }
        sl.add(transitions);
        sl.add(new CommentStatement("end(transition)"));

        sl.add(new CommentStatement("begin(onExit)"));
        IfStatement ifExit = ifstatement(EXIT_VARIABLE, assignment(ENTRY_VARIABLE, "TRUE"));
        if (n.onExit != null) {
            ifExit.getConditionalBranches().get(0).getStatements().addAll(n.onExit);
        }
        sl.add(ifExit);
        sl.add(new CommentStatement("begin(onExit)"));

        return esac;
    }

    private IfStatement ifstatement(String condigtion, Statement then) {
        IfStatement _if = new IfStatement();
        _if.addGuardedCommand(parseExpression(condigtion), new StatementList(then));
        return _if;
    }

    private Statement assignment(String var, String expression) {
        AssignmentStatement a = new AssignmentStatement(new SymbolicReference(var, null),
                parseExpression(expression));
        return a;
    }

    private StatementList parseStatements(String format) {
        return SFCModelBuilderCodeSys.getStructuredTextParser(format).statement_list().ast;
    }


    private Expression parseExpression(String states) {
        return SFCModelBuilderCodeSys.getStructuredTextParser(states).expression().ast;
    }
}
