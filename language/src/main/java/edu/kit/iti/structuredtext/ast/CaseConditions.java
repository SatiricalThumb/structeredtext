package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.Visitor;
import edu.kit.iti.structuredtext.datatypes.AnyInt;
import edu.kit.iti.structuredtext.datatypes.EnumerateType;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

public abstract class CaseConditions {
    public static class Range extends CaseConditions {
        private ScalarValue<? extends AnyInt, Integer> start, stop;

        public Range(ScalarValue<? extends AnyInt, Integer> start, ScalarValue<? extends AnyInt, Integer> stop) {
            this.start = start;
            this.stop = stop;
        }

        public Range(edu.kit.iti.structuredtext.ast.Range ast) {
            super(); //TODO
        }


        public ScalarValue<? extends AnyInt, Integer> getStart() {
            return start;
        }

        public void setStart(ScalarValue<? extends AnyInt, Integer> start) {
            this.start = start;
        }

        public ScalarValue<? extends AnyInt, Integer> getStop() {
            return stop;
        }

        public void setStop(ScalarValue<? extends AnyInt, Integer> stop) {
            this.stop = stop;
        }


        public void visit(Visitor visitor) {
            visitor.visit(this);
        }
    }

    public static class IntegerCondition extends CaseConditions {
        private ScalarValue<? extends AnyInt, Long> value;

        public IntegerCondition(ScalarValue<? extends AnyInt, Long> value) {
            this.value = value;
        }

        public ScalarValue<? extends AnyInt, Long> getValue() {
            return value;
        }

        public void setValue(ScalarValue<? extends AnyInt, Long> value) {
            this.value = value;
        }


        public void visit(Visitor visitor) {
            visitor.visit(this);
        }

    }

    public static class Enumeration extends CaseConditions {
        private String start;
        private String stop;

        public Enumeration(ScalarValue<EnumerateType, String> start) {

        }

        public Enumeration(String start, String stop) {
            this.start = start;
            this.stop = stop;
        }

        public Enumeration(String s) {
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getStop() {
            return stop;
        }

        public void setStop(String stop) {
            this.stop = stop;
        }


        public void visit(Visitor visitor) {
            visitor.visit(this);
        }
    }
}