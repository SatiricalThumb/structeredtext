package edu.kit.iti.structuredtext.runtime;


import edu.kit.iti.structuredtext.Scope;
import edu.kit.iti.structuredtext.ast.BinaryExpression;
import edu.kit.iti.structuredtext.ast.Expression;
import edu.kit.iti.structuredtext.ast.Reference;
import edu.kit.iti.structuredtext.ast.SymbolicReference;
import edu.kit.iti.structuredtext.ast.UnaryExpression;
import edu.kit.iti.structuredtext.datatypes.Any;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;
import edu.kit.iti.structuredtext.functions.Operator;

import org.apache.commons.lang.NotImplementedException;

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
    	if(expression == null) throw new IllegalArgumentException("expression must be != null");
    	
    	if (expression instanceof BinaryExpression) {
			BinaryExpression new_name = (BinaryExpression) expression;
			return eval(new_name);
		}
    	
    	if (expression instanceof SymbolicReference) {
			return eval( (SymbolicReference) expression);
		}
    	
    	if (expression instanceof UnaryExpression) {
			return eval((UnaryExpression) expression);			
		}
    	
    	if (expression instanceof ScalarValue<?, ?>) {
    		return eval((ScalarValue<?, ?>) expression);
    	}
    	
    	if (expression instanceof Reference) {
			return eval((Reference) expression);
		}
    	
		throw new NotImplementedException("not implemented for " + expression);
    }
    
    public ScalarValue<?, ?> eval(UnaryExpression expression) {
    	throw new NotImplementedException();
    }	
    
    public ScalarValue<?, ?> eval(Reference expression) {
    	throw new NotImplementedException();
    }
    
    
    public ScalarValue<?, ?> eval(ScalarValue<?,?> a) { return a;}
    

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
