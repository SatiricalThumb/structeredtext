package edu.kit.iti.structuredtext.datatypes;

public class AnyInt extends AnyNum {

    public static final Integer DEFAULT = 0;

    public final static class SInt extends AnyInt {
        private SInt() {
            super(8, false);
        }
    }

    public final static class Int extends AnyInt {
        private Int() {
            super(16, false);
        }
    }

    public final static class DInt extends AnyInt {
        private DInt() {
            super(32, false);
        }
    }

    public final static class LInt extends AnyInt {
        private LInt() {
            super(64, false);
        }
    }

    public static class AnyUInt extends AnyInt {
        public AnyUInt(int bitLength) {
            super(bitLength);
        }

        private AnyUInt(int bitLength, boolean signed) {
            super(bitLength, signed);
        }
    }


    public final static class USInt extends AnyInt {
        private USInt() {
            super(8, true);
        }
    }

    public final static class UInt extends AnyInt {
        private UInt() {
            super(16, true);
        }
    }

    public final static class UDInt extends AnyInt {
        private UDInt() {
            super(32, true);
        }
    }

    public final static class ULInt extends AnyInt {
        private ULInt() {
            super(64, true);
        }
    }


    public static final SInt SINT = new SInt();
    public static final USInt SUInt = new USInt();
    public static final Int INT = new Int();
    public static final AnyInt UINT = new UInt();
    public static final UDInt UDINT = new UDInt();
    public static final DInt DINT = new DInt();
    public static final ULInt ULINT = new ULInt();
    public static final LInt LINT = new LInt();


    protected int bitLength = 0;
    private boolean signed = false;

    public AnyInt(int bitLength) {
        this.bitLength = bitLength;
    }

    public AnyInt(int bitLength, boolean signed) {
        this.bitLength = bitLength;
        this.signed = signed;
    }

    public boolean isValid(int value) {
        long max = (2 << bitLength) - 1;
        long min = -(2 << bitLength);
        return value <= max && value >= min;
    }
}