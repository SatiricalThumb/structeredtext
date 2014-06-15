package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.Visitor;
import edu.kit.iti.structuredtext.datatypes.IECString;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by weigl on 13.06.14.
 */
public class EnumerationTypeDeclaration extends TypeDeclaration<String> {
    private List<String> allowedValues = new LinkedList<>();


    @Override
    public <S> S visit(Visitor<S> visitor) {
        return visitor.visit(this);
    }

    public void addValue(String text) {
        allowedValues.add(text);
    }

    public List<String> getAllowedValues() {
        return allowedValues;
    }

    public void setAllowedValues(List<String> allowedValues) {
        this.allowedValues = allowedValues;
    }

}
