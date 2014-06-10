package edu.kit.iti.structuredtext.ast;

import lombok.Data;

import java.util.List;

/**
 * Created by weigla on 09.06.2014.
 */
@Data
public abstract  class GuardedStatement extends Statement{
    protected Expression condition;
    protected List<Statement> subcommands;
}
