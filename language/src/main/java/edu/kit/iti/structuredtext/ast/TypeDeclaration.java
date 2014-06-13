package edu.kit.iti.structuredtext.ast;

/**
 * Created by weigl on 13.06.14.
 */
public class TypeDeclaration<T> {
    protected String typeName;
    protected String baseTypeName;
    protected T initializationValue;

    public TypeDeclaration() {
    }

    public TypeDeclaration(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setBaseTypeName(String baseTypeName) {
        this.baseTypeName = baseTypeName;
    }

    public String getBaseTypeName() {
        return baseTypeName;
    }

    public T getInitializationValue() {
        return initializationValue;
    }

    public void setInitializationValue(T initializationValue) {
        this.initializationValue = initializationValue;
    }
}
