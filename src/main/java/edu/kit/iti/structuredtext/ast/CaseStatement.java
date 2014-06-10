package edu.kit.iti.structuredtext.ast;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weigla on 09.06.2014.
 */
@Data
public class CaseStatement extends Statement {
    Expression expression;
    List<Case> cases = new ArrayList<>();
    StatementList elseCase = new StatementList();

    public class Case extends Top {
        List<CaseElement> conditions = new ArrayList<>();
        StatementList statements = new StatementList();
    }

    public abstract class CaseElement {}

    @Data
    public class RangeCaseElement {
        int start, stop;
    }

    @Data
    public class IntegerCaseElement {
        int value;
    }

    @Data
    public class EnumeratedCaseElement {
        Enumerated enumerated;
    }
}
