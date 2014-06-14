package edu.kit.iti.structuredtext.datatypes;

public class IECString extends Any {
    public final static class WString extends IECString {}
    public final static class String  extends IECString {}


    public final static String STRING_8BIT = new String();
    public final static WString STRING_16BIT = new WString();


}