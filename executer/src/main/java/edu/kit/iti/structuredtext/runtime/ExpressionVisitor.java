package edu.kit.iti.structuredtext.runtime;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Operators;
import edu.kit.iti.structuredtext.Scope;
import edu.kit.iti.structuredtext.ast.BinaryExpression;
import edu.kit.iti.structuredtext.ast.Expression;
import edu.kit.iti.structuredtext.ast.SymbolicReference;
import edu.kit.iti.structuredtext.datatypes.Any;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;
import edu.kit.iti.structuredtext.functions.Operator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by weigl on 13.06.14.
 */
public class ExpressionVisitor {
    Scope scope = new Scope();


    public ExpressionVisitor() {
    }

    public ExpressionVisitor(Scope s) {
        this.scope = s;
    }

    public ScalarValue<?, ?> eval(Expression expression) {
        try {
            Method methods = getClass().getMethod("eval", expression.getClass());
            return (ScalarValue<?, ?>) methods.invoke(this, expression);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError(e);
        }
    }

    public ScalarValue<?,?> eval(SymbolicReference reference) {
        return scope.getVariable(reference.getIdentifier()).getValue();
    }

    public ScalarValue<?, ?> eval(BinaryExpression expression) {
        BinaryExpression.Operator operator = expression.getOperator();
        Expression left = expression.getLeftExpr(),
                right = expression.getRightExpr();

        ScalarValue<?, ?> leftValue = eval(left);
        ScalarValue<?, ?> rightValue = eval(right);
        Object value = Operator.invoke(operator.name(), leftValue.getValue(), rightValue.getValue());
        Any dt = (Any) Operator.invoke(operator.name(), leftValue.getDataType(), rightValue.getDataType());
        return new ScalarValue<>(dt, value);
    }


}
