package edu.kit.iti.structuredtext.ast;

import lombok.Data;

/**
 * Created by weigla on 09.06.2014.
 */
@Data
public class ForStatement  extends Statement {
    String variable;
    Expression start, stop, increase;
    StatementList statements = new StatementList();
}