package edu.kit.iti.structuredtext;

import edu.kit.iti.structuredtext.ast.*;
import edu.kit.iti.structuredtext.datatypes.AnyBit;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;
import edu.kit.iti.structuredtext.runtime.ExpressionVisitor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by weigl on 13.06.14.
 */
public class ASTVisitor<T> {

    Scope scope = new Scope();

    ExpressionVisitor exprEval = new ExpressionVisitor(scope);

    public Object deeper(Object obj) {
        try {
            Method methods = getClass().getMethod("visit", obj.getClass());
            return methods.invoke(this, obj);
        } catch (NoSuchMethodException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new AssertionError(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }

    public void visit(ArrayList<Top> l) {
        for (Top t : l) deeper(t);
    }

    public void visit(LinkedList<Top> l) {
        for (Top t : l) deeper(t);
    }


    public void visit(TopLevelElement t) {
        if (t instanceof ProgramDeclaration) {
            deeper((ProgramDeclaration) t);
        } else if (t instanceof TypeDeclarations) {
            deeper((TypeDeclarations) t);

        }
    }


    public void visit(ProgramDeclaration pd) {
        System.out.println("Executing " + pd.getProgramName());
        deeper(pd.getProgramBody());

    }

    public void visit(FunctionBlockDeclaration fbd) {
        deeper(fbd.getFunctionBody());
    }

    public void visit(TypeDeclarations td) {
        for (TypeDeclaration d : td) {
            deeper(d);
        }
    }

    public void visit(EnumerationTypeDeclaration etd) {
    }

    public void visit(ArrayTypeDeclaration atd) {
    }

    public void visit(StructureTypeDeclaration atd) {
    }

    public void visit(SubRangeDataType atd) {
    }

    public void visit(SimpleTypeDeclaration atd) {
    }

    public void visit(StringTypeDeclaration atd) {
    }

    public void visit(TypeDeclaration d) {
    }

    public void visit(StatementList statements) {
        for (Statement stmt : statements) {
            deeper(stmt);
        }
    }

    public void visit(IfStatement ifstmt) {
        boolean exec = false;
        for (GuardedStatement guardedStatement : ifstmt.getConditionalBranches()) {
            exec = (boolean) deeper(guardedStatement);
            if (exec) break;
        }
        if (!exec)
            deeper(ifstmt.getElseBranch());
    }


    public void visit(FunctionCallStatement fcs)
    {

    }

    public boolean visit(GuardedStatement gs) {
        Expression cond = gs.getCondition();

        ScalarValue<AnyBit.Bool, Boolean> bool = (ScalarValue<AnyBit.Bool, Boolean>) exprEval.eval(cond);

        if (bool.getValue()) {
            deeper(gs.getStatements());
            return true;
        } else {
            return false;
        }
    }


}
