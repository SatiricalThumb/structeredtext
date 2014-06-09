package edu.kit.iti.structuredtext.ast;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weigla on 09.06.2014.
 */
@Data
public class FunctionBlockInvocation extends Statement {
    String functionName;
    Map<String, Expression> inputParameters = new HashMap<>();
    Map<String, Expression> outputParameters = new HashMap<>();
}
