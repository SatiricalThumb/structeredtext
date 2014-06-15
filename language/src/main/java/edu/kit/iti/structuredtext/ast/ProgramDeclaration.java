package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.Visitor;

/**
 * Created by weigl on 13.06.14.
 */
public class ProgramDeclaration extends TopLevelScopeElement {
    private StatementList programBody;
    private String programName;

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public StatementList getProgramBody() {
        return programBody;
    }

    public void setProgramBody(StatementList programBody) {
        this.programBody = programBody;
    }

    @Override
    public <T> T visit(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
