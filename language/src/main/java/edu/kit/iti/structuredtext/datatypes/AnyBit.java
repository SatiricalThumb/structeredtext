package edu.kit.iti.structuredtext.datatypes;

/**
 * Created by weigl on 10.06.14.
 */
public class AnyBit extends Any {
    protected int bitLength;

    public final static class Bool extends AnyBit {
        public Bool() {
            super(1);
        }
    }

    public final static class Byte extends AnyBit {
        public Byte() {
            super(8);
        }
    }

    public final static class Word extends AnyBit {
        public Word() {
            super(16);
        }
    }

    public final static class DWord extends AnyBit {
        public DWord() {
            super(32);
        }
    }

    public final static class LWord extends AnyBit {
        public LWord() {
            super(64);
        }
    }


    public final static Bool BOOL = new Bool();
    public final static Byte BYTE = new Byte();
    public final static Word WORD = new Word();
    public final static DWord DWORD = new DWord();
    public final static LWord LWORD = new LWord();


    private AnyBit(int bitLength) {
        this.bitLength = bitLength;
    }

    public int getBitLength() {
        return bitLength;
    }
}
