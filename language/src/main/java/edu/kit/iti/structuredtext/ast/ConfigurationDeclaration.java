package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.Visitor;

/**
 * Created by weigl on 13.06.14.
 */
public class ConfigurationDeclaration extends TopLevelScopeElement {

    public <T> T visit(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
