package edu.kit.iti.structuredtext.ast;


/**
 * Created by weigla on 09.06.2014.
 */
public class VariableDeclaration {
    public static final int INPUT = 1;
    public static final int OUTPUT = 2;
    public static final int INOUT = 4;
    public static final int LOCAL = 8;
    public static final int GLOBAL = 16;
    public static final int CONSTANT = 32;
    public static final int RETAIN = 64;
    public static final int LOCATED = 128;
    public static final int EXTERNAL = 256;
    public static final int TEMP = 512;


    private String name;
    private Initialization init;
    private TypeDeclaration<?> declaredType;
    private String dataType;
    private int type;

    public VariableDeclaration() {

    }

    public VariableDeclaration(String name) {
        this();
        this.name = name;
    }

    public VariableDeclaration(String name, int type, Initialization i) {
        this(name, type);
        init = i;
    }

    public VariableDeclaration(String name, int type, TypeDeclaration<?> i) {
        this(name, type);
        declaredType = i;
    }

    public VariableDeclaration(String name, Integer type) {
        this(name);
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Initialization getInit() {
        return init;
    }

    public void setInit(Initialization init) {
        this.init = init;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public boolean is(int i) {
        return (type & i) != 0;
    }

    public boolean isRetain() {
        return is(RETAIN);
    }

    public boolean isConstant() {
        return is(CONSTANT);
    }


    public boolean isExternal() {
        return is(EXTERNAL);
    }


    public boolean isTemp() {
        return is(TEMP);
    }


    public boolean isLocated() {
        return is(LOCATED);
    }


    public boolean isLocal() {
        return is(LOCAL);
    }


    public boolean isOutput() {
        return is(OUTPUT);
    }


    public boolean isInput() {
        return is(INPUT);
    }

    public boolean isInOut() {
        return is(INOUT);
    }

    public boolean isGlobal() {
        return is(GLOBAL);
    }

    public TypeDeclaration<?> getDeclaredType() {
        return declaredType;
    }

    public void setDeclaredType(TypeDeclaration<?> declaredType) {
        this.declaredType = declaredType;
    }
}
