package edu.kit.iti.structuredtext.ast;


/**
 * Created by weigla on 09.06.2014.
 */
class Literal extends Expression {
    public static enum LiteralType {
        LREAL, REAL, STRING, WSTRING,
        SINT, UINT, BINARY, OCTAL, HEX,
        DURATION, TIME_OF_DAY, BIT_STRING, BOOLEAN;
    }


    LiteralType type;
    String cast;
    String literal;
    Object value;

    public Literal() {
    }

    public Literal(LiteralType type, String cast, String literal, Object value) {
        this.type = type;
        this.cast = cast;
        this.literal = literal;
        this.value = value;
    }

    public LiteralType getType() {
        return type;
    }

    public void setType(LiteralType type) {
        this.type = type;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getLiteral() {
        return literal;
    }

    public void setLiteral(String literal) {
        this.literal = literal;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
