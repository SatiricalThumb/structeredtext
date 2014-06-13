package edu.kit.iti.structuredtext.datatypes.values;

/**
 * Created by weigl on 11.06.14.
 * Immutable
 */
public class Bits {
    private final long register;
    private final long nbits;

    public Bits(long nbits) {
        this(0, nbits);
    }

    public Bits(long register, long nbits) {
        this.register = register & allMask(); // trunc
        this.nbits = nbits;
    }

    private long allMask() {
        if (nbits == 64) {
            return -1L;
        } else {
            return (1L << nbits) - 1;
        }
    }

    public Bits shl(int n) {
        return new Bits(register << n, nbits);
    }

    public Bits shr(int n) {
        return new Bits(register >>> n, nbits);
    }


    public Bits rol(int n) {
        assert n <= nbits;

        if (n == nbits) {
            return this;
        }


        long maskAll = allMask();
        long maskRetain = (2 << (nbits - n)) - 1;
        long maskLoss = ~maskRetain & maskAll;

        long loss = register & maskRetain;
        long last = (loss >> (nbits - n));
        return new Bits((register << n) | last, nbits);
    }


    public Bits ror(int n) {
        long maskAll = allMask();
        long maskLoss = (2 << n) - 1;

        long loss = maskLoss & register;
        long first = loss << n;

        return new Bits((register >>> n) | first, nbits);
    }

    public long getRegister() {
        return register;
    }

    public Bits and(Bits other)
    {
        return new Bits(register & other.register, nbits);
    }

    public Bits or(Bits other)
    {
        return new Bits(register | other.register, nbits);
    }

    public Bits xor(Bits other)
    {
        return new Bits(register ^ other.register, nbits);
    }

}
