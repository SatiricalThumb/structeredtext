package edu.kit.iti.structuredtext.plcopenxml.model;

import edu.kit.iti.structuredtext.ast.Expression;
import edu.kit.iti.structuredtext.ast.StatementList;
import edu.kit.iti.structuredtext.ast.VariableScope;

import java.util.*;

/**
 * @author weigla
 * @date 24.06.2014
 */
public class SFCGraph {
    private Set<Node> nodes = new HashSet<>();
    private VariableScope scope;
    private String name;

    public static SFCGraph buildGraph(List<SFCAction> actions, Map<Integer, SfcXmlElement> nodes) {
        for (SfcXmlElement e : nodes.values()) {
            e.bind(nodes, actions);
        }

        Map<String, SFCAction> actionMap = new HashMap<>();

        for (SFCAction a : actions) actionMap.put(a.getName(), a);

        SFCGraph graph = new SFCGraph();
        Factory f = new Factory(actionMap, nodes);


        for (SfcXmlElement e : nodes.values()) {
            if (e instanceof SfcXmlElement.Step) {
                SfcXmlElement.Step step = (SfcXmlElement.Step) e;
                graph.nodes.add(f.create(step));
            }

            if (e instanceof SfcXmlElement.Divergence) {
                SfcXmlElement.Divergence d = (SfcXmlElement.Divergence) e;
                graph.nodes.add(f.create(d));
            }

            if (e instanceof SfcXmlElement.Convergence) {
                SfcXmlElement.Convergence d = (SfcXmlElement.Convergence) e;
                graph.nodes.add(f.create(d));
            }
        }

        for (SfcXmlElement e : nodes.values()) {
            if (e instanceof SfcXmlElement.Transition) {
                SfcXmlElement.Transition d = (SfcXmlElement.Transition) e;
                Transition transition = f.create(d);
            }

        }
        return graph;
    }

    public void print() {
        for (Node n : nodes) {
            System.out.println(n.name);
        }
    }

    public VariableScope getScope() {
        return scope;
    }

    public void setScope(VariableScope scope) {
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public static class Factory {
        Map<Integer, SfcXmlElement> nodes;
        Map<String, SFCAction> actions;
        Map<Integer, Transition> memTransitions = new HashMap<>();
        Map<Integer, Node> memNodes = new HashMap<>();

        public Factory(Map<String, SFCAction> actions, Map<Integer, SfcXmlElement> nodes) {
            this.actions = actions;
            this.nodes = nodes;
        }

        private Node create(SfcXmlElement.Step step) {
            if (memNodes.containsKey(step.localId))
                return memNodes.get(step.localId);

            Step n = new Step();

            n.name = step.name;
            n.initial = step.initial;

            try {

                n.onEntry = actions.get(n.name + "_entry").getStatements();
            } catch (NullPointerException npe) {
            }
            try {
                n.onExit = actions.get(n.name + "_exit").getStatements();
            } catch (NullPointerException npe) {
            }
            try {
                n.onWhile = actions.get(n.name + "_active").getStatements();
            } catch (NullPointerException npe) {
            }

            memNodes.put(step.localId, n);
            return n;
        }

        public Transition create(SfcXmlElement.Transition transition) {
            if (memTransitions.containsKey(transition.localId))
                return memTransitions.get(transition.localId);
            Transition t = new Transition();
            t.conditions = transition.conditions;


            for (SfcXmlElement a : transition.outgoingReference) {
                int localId;
                if (a instanceof SfcXmlElement.JumpStep) {
                    localId = ((SfcXmlElement.JumpStep) a).jumpTo.localId;
                } else {
                    localId = a.localId;
                }
                Node from = memNodes.get(localId);
                from.incoming.add(t); // yeah this is right
                t.to = from;
            }

            for (SfcXmlElement a : transition.incomingReferences) {
                int localId;
                if (a instanceof SfcXmlElement.JumpStep) {
                    localId = ((SfcXmlElement.JumpStep) a).jumpTo.localId;
                } else {
                    localId = a.localId;
                }
                Node to = memNodes.get(localId);
                to.outgoing.add(t);
                t.from = to;

            }

            memTransitions.put(transition.localId, t);
            return t;
        }

        private Node create(SfcXmlElement.Divergence d) {
            if (memNodes.containsKey(d.localId))
                return memNodes.get(d.localId);
            Divergence div = new Divergence();
            div.parallel = d.parallel;
            memNodes.put(d.localId, div);
            return div;
        }

        private Node create(SfcXmlElement.Convergence d) {
            if (memNodes.containsKey(d.localId))
                return memNodes.get(d.localId);
            Divergence div = new Divergence();
            div.parallel = d.parallel;
            memNodes.put(d.localId, div);
            return div;
        }
    }

    public static class Node {
        public String name;
        public Set<Transition> outgoing = new HashSet<>();
        public Set<Transition> incoming = new HashSet<>();
    }

    public static class Step extends Node {
        public boolean initial;
        public StatementList onExit, onEntry, onWhile;
    }

    public static class Convergence extends Node {
        public boolean parallel;
    }

    public static class Divergence extends Node {
        public boolean parallel;
    }

    public static class Transition {
        public List<Expression> conditions;
        public Node from, to;
    }
}
