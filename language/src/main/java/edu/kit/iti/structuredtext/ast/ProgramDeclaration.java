package edu.kit.iti.structuredtext.ast;

/**
 * Created by weigl on 13.06.14.
 */
public class ProgramDeclaration extends TopLevelScopeElement {
    private StatementList programBody;
    private String programName;

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramBody(StatementList programBody) {
        this.programBody = programBody;
    }

    public StatementList getProgramBody() {
        return programBody;
    }
}
