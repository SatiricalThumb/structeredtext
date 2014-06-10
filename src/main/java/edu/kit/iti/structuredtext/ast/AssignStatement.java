package edu.kit.iti.structuredtext.ast;

import lombok.Data;

@Data
public class AssignStatement extends Statement {
    String variable;
    Expression expr;
}
