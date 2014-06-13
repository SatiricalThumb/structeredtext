package edu.kit.iti.structuredtext;

/**
 * Created by weigl on 13.06.14.
 */
public class Tuple<S,T>
{
    public T a;
    public S b;

    public Tuple() {
    }

    public Tuple(T a, S b) {
        this.a = a;
        this.b = b;
    }

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    public S getB() {
        return b;
    }

    public void setB(S b) {
        this.b = b;
    }
}
