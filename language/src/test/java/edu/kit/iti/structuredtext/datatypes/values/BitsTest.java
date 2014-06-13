package edu.kit.iti.structuredtext.datatypes.values;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BitsTest {

    private Bits bits1, bits8, bits16, bits32, bits64;
    private long init;

    @Before
    public void setUp() {
        this.init = 0xAA_BB_CC_DD_EE_FF_88_11L;
        this.bits1 = new Bits(init, 1);
        this.bits8 = new Bits(init, 8);
        this.bits16 = new Bits(init, 16);
        this.bits32 = new Bits(init, 32);
        this.bits64 = new Bits(init, 64);
    }


    @Test
    public void testTrunc() throws Exception {
        assertEqualsBits(1, bits1.getRegister());

        assertEqualsBits(0x11L, bits8.getRegister());

        assertEqualsBits(0x88_11L, bits16.getRegister());

        assertEqualsBits(0xEE_FF_88_11L, bits32.getRegister());

        assertEqualsBits(0xAA_BB_CC_DD_EE_FF_88_11L, bits64.getRegister());


    }

    private static String binary(long v) {
        return String.format("%64s", Long.toBinaryString(v)).replace(' ', '0');
    }

    public static void assertEqualsBits(long expected, long got) {
        assertEquals(binary(expected), binary(got));
    }

    public static void assertEqualsBits(long expected, Bits got) {
        assertEqualsBits(expected, got.getRegister());
    }

    @Test
    public void testShl() throws Exception {
        assertEqualsBits(0, this.bits1.shl(1));

        assertEqualsBits(0x10L, this.bits8.shl(4));
        assertEqualsBits(0x00L, this.bits8.shl(4));

        assertEqualsBits(0x11_00L, bits16.shl(8));
        assertEqualsBits(0x44_00L, bits16.shl(2));
        assertEqualsBits(0x88_00L, bits16.shl(1));

        assertEqualsBits(0xE_FF_88_11_0L, this.bits32.shl(4));
        assertEqualsBits(0xFF_88_11_00L, this.bits32.shl(4));
        assertEqualsBits(0xF_88_11_00_0L, this.bits32.shl(4));

        assertEqualsBits(0xA_BB_CC_DD_EE_FF_88_11_0L, bits64.shl(4));
        assertEqualsBits(0xBB_CC_DD_EE_FF_88_11_00L, bits64.shl(4));

        assertEqualsBits(0xCC_DD_EE_FF_88_11_00_00L, bits64.shl(8));
        assertEqualsBits(0xDD_EE_FF_88_11_00_00_00L, bits64.shl(8));
        assertEqualsBits(0xFF_88_11_00_00_00_00_00L, bits64.shl(16));
    }

    @Test
    public void testRhl() throws Exception {
        assertEquals(0, this.bits1.shr(1));

        assertEqualsBits(0x04L, this.bits8.shr(2));
        assertEqualsBits(0x04L, this.bits8.shr(0));
        assertEqualsBits(0x02L, this.bits8.shr(1));
        assertEqualsBits(0x01L, this.bits8.shr(1));

        assertEqualsBits(0x00_88L, bits16.shr(8));
        assertEqualsBits(0x00_22L, bits16.shr(2));
        assertEqualsBits(0x00_11L, bits16.shr(1));

        assertEqualsBits(0x0_EE_FF_88_1L, this.bits32.shr(4));
        assertEqualsBits(0x00_EE_FF_88L, this.bits32.shr(4));
        assertEqualsBits(0x0_00_EE_FF_8L, this.bits32.shr(4));

        assertEqualsBits(0x0AABBCCDDEEFF881L, bits64.shr(4));
        assertEqualsBits(0x00AABBCCDDEEFF88L, bits64.shr(4));

        assertEqualsBits(0x0000AABBCCDDEEFFL, bits64.shr(8));
        assertEqualsBits(0x000000AABBCCDDEEL, bits64.shr(8));
        assertEqualsBits(0x0000000000AABBCCL, bits64.shr(16));
    }

    @Test
    public void testRol() throws Exception {
        assertEqualsBits(1L, this.bits1.rol(1));

        assertEqualsBits(0x11L, this.bits8.rol(4));
        assertEqualsBits(0b0100_0100L, this.bits8.rol(2));

        assertEqualsBits(0x88_11_EE_FFL, bits32.rol(16));

        assertEqualsBits(0x1_AA_BB_CC_DD_EE_FF_88_1L, bits32.rol(60));

        //TODO
    }

    @Test
    public void testRor() throws Exception {
        //TODO
    }
}