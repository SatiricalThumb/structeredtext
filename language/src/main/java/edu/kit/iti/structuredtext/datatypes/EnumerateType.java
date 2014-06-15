package edu.kit.iti.structuredtext.datatypes;

/**
 * Created by weigl on 10.06.14.
 */
public class EnumerateType extends Any {
    private String name;
    private String[] allowedValues;
    private String defValue;

    public EnumerateType(String name, String[] allowedValues) {
        this.name = name;
        this.allowedValues = allowedValues;
    }

    public EnumerateType(String name, String[] allowedValues, String defValue) {
        this.name = name;
        this.allowedValues = allowedValues;
        this.defValue = defValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAllowedValues() {
        return allowedValues;
    }

    public void setAllowedValues(String[] allowedValues) {
        this.allowedValues = allowedValues;
    }

    public String getDefValue() {
        return defValue;
    }

    public void setDefValue(String defValue) {
        this.defValue = defValue;
    }

    @Override
    public String repr(Object obj) {
        return name + "#" + obj;
    }
}
