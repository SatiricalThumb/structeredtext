package edu.kit.iti.structuredtext;

import edu.kit.iti.structuredtext.datatypes.*;
import edu.kit.iti.structuredtext.ast.Expression;
import edu.kit.iti.structuredtext.datatypes.values.*;

import java.util.BitSet;

/**
 * Created by weigl on 11.06.14.
 */
public class ValueFactory {
    public static ScalarValue<AnyInt.Int, Integer> makeInt(int value) {
        return new ScalarValue<>(AnyInt.INT, value);
    }

    public static ScalarValue<AnyInt.SInt.SInt, Integer> makeSInt(int value) {
        return new ScalarValue<>(AnyInt.SINT, value);
    }

    public static ScalarValue<AnyInt.DInt, Integer> makeLInt(int value) {
        return new ScalarValue<>(AnyInt.DINT, value);
    }

    public static ScalarValue<AnyInt.LInt, Integer> makeDInt(int value) {
        return new ScalarValue<>(AnyInt.LINT, value);
    }


    public static ScalarValue<AnyInt.Int, Integer> makeInt() {
        return new ScalarValue<>(AnyInt.INT, AnyInt.DEFAULT);
    }

    public static ScalarValue<AnyInt.SInt.SInt, Integer> makeSInt() {
        return new ScalarValue<>(AnyInt.SINT, AnyInt.DEFAULT);
    }

    public static ScalarValue<AnyInt.DInt, Integer> makeLInt() {
        return new ScalarValue<>(AnyInt.DINT, AnyInt.DEFAULT);
    }

    public static ScalarValue<AnyInt.LInt, Integer> makeDInt() {
        return new ScalarValue<>(AnyInt.LINT, AnyInt.DEFAULT);
    }


    public static <T extends AnyBit> ScalarValue<T, BitSet> makeAnyBit(T dataType) {
        return new ScalarValue<>(dataType, new BitSet(dataType.getBitLength()));
    }

    public static ScalarValue<AnyBit.Bool, BitSet> makeBool() {
        return makeAnyBit(AnyBit.BOOL);
    }

    public static ScalarValue<AnyBit.Word, BitSet> makeWord() {
        return makeAnyBit(AnyBit.WORD);
    }

    public static ScalarValue<AnyBit.DWord, BitSet> makeDWord() {
        return makeAnyBit(AnyBit.DWORD);
    }

    public static ScalarValue<AnyBit.LWord, BitSet> makeLWord() {
        return makeAnyBit(AnyBit.LWORD);
    }

    public static Expression parseLiteral(String text) {
        return null;
    }

    public static ScalarValue<? extends AnyInt,Integer> parseIntegerLiteral(String s) {
        return null;//TODO
    }

    public static ScalarValue<EnumerateType,String> makeEnumeratedValue(String s) {
        return null;//TODO

    }

    public static ScalarValue<? extends IECString,String> parseStringLiteral(String s) {
        return null;//TODO

    }

    public static ScalarValue<TimeType,TimeValue> parseTimeLiteral(String s) {
        return null;//TODO

    }

    public static ScalarValue<AnyBit.Bool,Bits> makeBool(String text) {
        return new ScalarValue<AnyBit.Bool, Bits>(AnyBit.BOOL, new Bits(text.equals("TRUE")?1:0, 1));//TODO

    }

    public static ScalarValue<? extends AnyReal, Double> parseRealLiteral(String s) {
        return null;//TODO
    }

    public static ScalarValue<AnyDate.DateAndTime, DateAndTimeValue> parseDateTime(String s) {
        return null;
    }

    public static ScalarValue<AnyDate.Date, DateValue> parseDateLiteral(String s) {
        return null;
    }

    public static ScalarValue<AnyDate.TimeOfDay, TimeOfDayValue> parseTimeOfDayLiteral(String s) {
        return null;
    }
}