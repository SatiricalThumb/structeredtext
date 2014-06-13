package edu.kit.iti.structuredtext.ast;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by weigl on 13.06.14.
 */
public class EnumerationTypeDeclaration extends TypeDeclaration {
    private String initializationValue;
    private List<String> allowedValues = new LinkedList<>();

    public void setInitializationValue(String initializationValue) {
        this.initializationValue = initializationValue;
    }

    public String getInitializationValue() {
        return initializationValue;
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
