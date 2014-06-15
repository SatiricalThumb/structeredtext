package edu.kit.iti.structuredtext;

import edu.kit.iti.structuredtext.datatypes.values.*;
import org.junit.Test;

import static edu.kit.iti.structuredtext.ValueFactory.*;
import static org.junit.Assert.assertEquals;


/**
 * Created by weigla on 15.06.2014.
 */
public class ValueFactoryTest {
    @Test
    public void postiveParseTimeLiteral() {
        assertEquals(new TimeValue(5, 0, 0, 0, 0), parseTimeLiteral("TIME#5d").getValue());
        assertEquals(new TimeValue(5, 1, 1, 1, 1), parseTimeLiteral("TIME#5d1h1m1s1ms").getValue());
        assertEquals(new TimeValue(0, 0, 0, 0, 1), parseTimeLiteral("TIME#1ms").getValue());
        assertEquals(new TimeValue(0, 0, 0, 1, 0), parseTimeLiteral("TIME#1s").getValue());
        assertEquals(new TimeValue(0, 10.2, 0, 0, 0), parseTimeLiteral("TIME#10.2h").getValue());
    }

    @Test
    public void positiveParseTimeOfDayLiteral() {
        assertEquals(new TimeOfDayValue(5,32,30), parseTimeOfDayLiteral("TOD#05:32:30").getValue());
        assertEquals(new TimeOfDayValue(5,32,30), parseTimeOfDayLiteral("TOD#5:32:30").getValue());
        assertEquals(new TimeOfDayValue(0,32,30), parseTimeOfDayLiteral("TOD#0:32:30").getValue());
        assertEquals(new TimeOfDayValue(0,32,0), parseTimeOfDayLiteral("TOD#0:32:00").getValue());
        assertEquals(new TimeOfDayValue(0,0,30), parseTimeOfDayLiteral("TOD#0:00:30").getValue());
    }

    @Test public void positiveParseDateLiteral() {
        assertEquals(new DateValue(2012,8,6), parseDateLiteral("2012-08-06").getValue());
    }


    @Test public void positiveParseDateAndTimeLiteral() {
        assertEquals(new DateAndTimeValue(2012,6,8,10,22,12), ValueFactory.parseDateAndTimeLiteral("DT#2012-06-08-10:22:12").getValue());
    }

    @Test public void positiveParseIntegerLiteral() {
        assertEquals(0b101110101L, (long) parseIntegerLiteral("2#101110101").getValue());
        assertEquals(06161L, (long) parseIntegerLiteral("8#6161").getValue());
        assertEquals(0xABDEFL, (long) parseIntegerLiteral("16#ABDEF").getValue());
        assertEquals(201614L, (long) parseIntegerLiteral("201614").getValue());



    }

    @Test public void positiveParseBitLiteral() {
        assertEquals(new Bits(0b1111, 1).getRegister(), parseBitLiteral("BOOL#2#1").getValue().getRegister());
        assertEquals(new Bits(0b1111, 8), parseBitLiteral("BYTE#2#1111").getValue());
        assertEquals(new Bits(0b1111, 16), parseBitLiteral("WORD#2#1111").getValue());
        assertEquals(new Bits(0b1111, 32), parseBitLiteral("DWORD#2#1111").getValue());
        assertEquals(new Bits(0b1111, 64), parseBitLiteral("LWORD#2#1111").getValue());
    }
}
