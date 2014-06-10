package edu.kit.iti.structuredtext.functions;

import edu.kit.iti.structuredtext.datatypes.AnyNum;

/**
 * Created by weigl on 10.06.14.
 */
public abstract class Operator<A,B,R>
{
    public abstract R invoke(A a,B b);


    public static class ADD extends Operator<AnyNum,AnyNum,AnyNum> {
        @Override
        public AnyNum invoke(AnyNum anyNum, AnyNum anyNum2) {

            return null;
        }
    }
}
