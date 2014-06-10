package edu.kit.iti.structuredtext.datatypes;

/**
 * Created by weigl on 10.06.14.
 */
public abstract class AnyBit{

    public static class Bool extends AnyBit {}
    public static class Word extends AnyBit {}
    public static class DWord extends AnyBit {}
    public static class LWord extends AnyBit {}

}
