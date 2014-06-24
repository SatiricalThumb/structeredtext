package edu.kit.iti.structuredtext.plcopenxml.model;

import edu.kit.iti.structuredtext.ast.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author weigla
 * @date 24.06.2014
 */
public class SfcXmlElement {
    public static final String DURING = "700a583f-b4d4-43e4-8c14-629c7cd3bec8";
    public static final String ENTRY = "a6b08bd8-b696-47e3-9cbf-7408b61c9ff8";
    public static final String EXIT = "a2621e18-7de3-4ea6-ae6d-89e9e0b7befd";
    //public static final String CONDITION = "38391c6d-6d4a-42f8-8ee7-9f45e5adafa8";


    public int localId;
    public List<Integer> incomingReferenceIds;
    public List<SfcXmlElement> incomingReferences = new ArrayList<>(), outgoingReference = new ArrayList<>();

    public void bind(Map<Integer, SfcXmlElement> nodes, List<SFCAction> actions) {
        incomingReferences = new ArrayList<>();
        for (Integer i : incomingReferenceIds) {
            incomingReferences.add(nodes.get(i));
            nodes.get(i).outgoingReference.add(this);
        }
    }

    public static class XmlTransition extends SfcXmlElement {
        public Expression expression;
    }

    public static class Transition extends SfcXmlElement {
        public List<Integer> conditionReferences = new ArrayList<>();
        public List<Expression> conditions = new ArrayList<>();

        public void bind(Map<Integer, SfcXmlElement> nodes, List<SFCAction> actions) {
            super.bind(nodes, actions);
            for (Integer i : conditionReferences) {
                XmlTransition var = (XmlTransition) nodes.get(i);
                conditions.add(var.expression);
            }
        }
    }

    public static class Step extends SfcXmlElement {
        public String name;
        public String name_exit, name_entry, name_while;
        public boolean initial;
    }


    public static class JumpStep extends SfcXmlElement {
        public String targetName;
        public Step jumpTo;

        public void bind(Map<Integer, SfcXmlElement> nodes, List<SFCAction> actions) {
            super.bind(nodes, actions);

            for (SfcXmlElement e : nodes.values()) {
                if (e instanceof Step) {
                    Step step = (Step) e;
                    if (targetName.equals(step.name))
                        jumpTo = step;
                }
            }
        }

    }

    public static class Divergence extends SfcXmlElement {
        public boolean parallel;
    }

    public static class Convergence extends SfcXmlElement {
        public boolean parallel;
    }
}
