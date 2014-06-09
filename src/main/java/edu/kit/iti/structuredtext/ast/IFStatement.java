package edu.kit.iti.structuredtext.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weigla on 09.06.2014.
 */
public class IFStatement extends Top{
    protected List<GuardedStatement> conditionalBranches = new ArrayList<>();
    protected List<Statement> elseBranch = new ArrayList<>();
}
