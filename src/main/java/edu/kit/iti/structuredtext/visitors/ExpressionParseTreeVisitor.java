package edu.kit.iti.structuredtext.visitors;

import edu.kit.iti.structuredtext.antlr.StructuredTextBaseVisitor;
import edu.kit.iti.structuredtext.antlr.StructuredTextParser;
import edu.kit.iti.structuredtext.antlr.StructuredTextVisitor;
import edu.kit.iti.structuredtext.ast.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.function.BinaryOperator;

public class ExpressionParseTreeVisitor extends StructuredTextBaseVisitor<Expression> {

    @Override
    public Expression visitResource_type_name(@NotNull StructuredTextParser.Resource_type_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitYear(@NotNull StructuredTextParser.YearContext ctx) {
        return null;
    }

    @Override
    public Expression visitArray_initial_element(@NotNull StructuredTextParser.Array_initial_elementContext ctx) {
        return null;
    }

    @Override
    public Expression visitGlobal_var_declarations(@NotNull StructuredTextParser.Global_var_declarationsContext ctx) {
        return null;
    }

    @Override
    public Expression visitFunction_var_decls(@NotNull StructuredTextParser.Function_var_declsContext ctx) {
        return null;
    }

    @Override
    public Expression visitGlobal_var_name(@NotNull StructuredTextParser.Global_var_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitIteration_statement(@NotNull StructuredTextParser.Iteration_statementContext ctx) {
        return null;
    }

    @Override
    public Expression visitLibrary_element_name(@NotNull StructuredTextParser.Library_element_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitComparison_operator(@NotNull StructuredTextParser.Comparison_operatorContext ctx) {
        return null;
    }

    @Override
    public Expression visitStructure_type_name(@NotNull StructuredTextParser.Structure_type_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitInstance_specific_init(@NotNull StructuredTextParser.Instance_specific_initContext ctx) {
        return null;
    }

    @Override
    public Expression visitPrimary_expression(@NotNull StructuredTextParser.Primary_expressionContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public Expression visitNumeric_literal(@NotNull StructuredTextParser.Numeric_literalContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public Expression visitConfiguration_declaration(@NotNull StructuredTextParser.Configuration_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitOutput_declarations(@NotNull StructuredTextParser.Output_declarationsContext ctx) {
        return null;
    }

    @Override
    public Expression visitStructured_var_declaration(@NotNull StructuredTextParser.Structured_var_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitSubrange_spec_init(@NotNull StructuredTextParser.Subrange_spec_initContext ctx) {
        return null;
    }

    @Override
    public Expression visitDate_type_name(@NotNull StructuredTextParser.Date_type_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitData_source(@NotNull StructuredTextParser.Data_sourceContext ctx) {
        return null;
    }

    @Override
    public Expression visitStandard_function_name(@NotNull StructuredTextParser.Standard_function_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitSingle_element_type_declaration(@NotNull StructuredTextParser.Single_element_type_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitProg_data_source(@NotNull StructuredTextParser.Prog_data_sourceContext ctx) {
        return null;
    }

    @Override
    public Expression visitDaytime(@NotNull StructuredTextParser.DaytimeContext ctx) {
        return null;
    }

    @Override
    public Expression visitConfiguration_name(@NotNull StructuredTextParser.Configuration_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitBoolean_literal(@NotNull StructuredTextParser.Boolean_literalContext ctx) {
        return null;
    }

    @Override
    public Expression visitFb_name(@NotNull StructuredTextParser.Fb_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitBit_string_type_name(@NotNull StructuredTextParser.Bit_string_type_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitOctal_integer(@NotNull StructuredTextParser.Octal_integerContext ctx) {
        return null;
    }

    @Override
    public Expression visitAccess_path(@NotNull StructuredTextParser.Access_pathContext ctx) {
        return null;
    }

    @Override
    public Expression visitData_sink(@NotNull StructuredTextParser.Data_sinkContext ctx) {
        return null;
    }

    @Override
    public Expression visitSimple_specification(@NotNull StructuredTextParser.Simple_specificationContext ctx) {
        return null;
    }

    @Override
    public Expression visitExternal_declaration(@NotNull StructuredTextParser.External_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitDouble_byte_string_var_declaration(@NotNull StructuredTextParser.Double_byte_string_var_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitExpression(@NotNull StructuredTextParser.ExpressionContext ctx) {

        //LPAREN expr RPAREN
        if (ctx.LPAREN() != null) {
            return visit(ctx.expression(0));
        }

        if (ctx.unary_operator() != null) {
            UnaryExpression.Operator op = null;
            if (ctx.unary_operator().MINUS() != null) {
                op = UnaryExpression.Operator.MINUS;
            }
            if (ctx.unary_operator().NOT() != null) {
                op = UnaryExpression.Operator.NEGATE;
            }
            UnaryExpression ue = new UnaryExpression(op, visit(ctx.expression(0)));
            return ue;
        }

        if (ctx.primary_expression() != null) {
            return visit(ctx.primary_expression());
        }
        //BinaryCase
        BinaryExpression.Operator op = null;

        for (BinaryExpression.Operator o : BinaryExpression.Operator.values()) {
            if (ctx.getToken(o.token, 0) != null) {
                op = o;
                break;
            }
        }

        StructuredTextParser.ExpressionContext left = ctx.expression(0);
        StructuredTextParser.ExpressionContext right = ctx.expression(1);


        BinaryExpression be = new BinaryExpression(visit(left), visit(right), op);
        return be;
    }

    @Override
    public Expression visitCharacter_string(@NotNull StructuredTextParser.Character_stringContext ctx) {
        return null;
    }

    @Override
    public Expression visitAccess_declaration(@NotNull StructuredTextParser.Access_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitLibrary_element_declaration(@NotNull StructuredTextParser.Library_element_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitInput_declarations(@NotNull StructuredTextParser.Input_declarationsContext ctx) {
        return null;
    }

    @Override
    public Expression visitFor_statement(@NotNull StructuredTextParser.For_statementContext ctx) {
        return null;
    }

    @Override
    public Expression visitDouble_byte_string_spec(@NotNull StructuredTextParser.Double_byte_string_specContext ctx) {
        return null;
    }

    @Override
    public Expression visitGlobal_var_decl(@NotNull StructuredTextParser.Global_var_declContext ctx) {
        return null;
    }

    @Override
    public Expression visitReal_literal(@NotNull StructuredTextParser.Real_literalContext ctx) {
        return new Literal();
    }

    @Override
    public Expression visitSigned_integer_type_name(@NotNull StructuredTextParser.Signed_integer_type_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitDays(@NotNull StructuredTextParser.DaysContext ctx) {
        return null;
    }

    @Override
    public Expression visitReal_type_name(@NotNull StructuredTextParser.Real_type_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitOther_var_declarations(@NotNull StructuredTextParser.Other_var_declarationsContext ctx) {
        return null;
    }

    @Override
    public Expression visitResource_name(@NotNull StructuredTextParser.Resource_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitGlobal_var_list(@NotNull StructuredTextParser.Global_var_listContext ctx) {
        return null;
    }

    @Override
    public Expression visitType_declaration(@NotNull StructuredTextParser.Type_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitProgram_name(@NotNull StructuredTextParser.Program_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitBit_string_literal(@NotNull StructuredTextParser.Bit_string_literalContext ctx) {
        return null;
    }

    @Override
    public Expression visitGeneric_type_name(@NotNull StructuredTextParser.Generic_type_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitStructure_declaration(@NotNull StructuredTextParser.Structure_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitInput_declaration(@NotNull StructuredTextParser.Input_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitIncompl_located_var_declarations(@NotNull StructuredTextParser.Incompl_located_var_declarationsContext ctx) {
        return null;
    }

    @Override
    public Expression visitEnumerated_type_declaration(@NotNull StructuredTextParser.Enumerated_type_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitTemp_var_decl(@NotNull StructuredTextParser.Temp_var_declContext ctx) {
        return null;
    }

    @Override
    public Expression visitSingle_byte_string_spec(@NotNull StructuredTextParser.Single_byte_string_specContext ctx) {
        return null;
    }

    @Override
    public Expression visitStructure_type_declaration(@NotNull StructuredTextParser.Structure_type_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitSingle_element_type_name(@NotNull StructuredTextParser.Single_element_type_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitVar2_init_decl(@NotNull StructuredTextParser.Var2_init_declContext ctx) {
        return null;
    }

    @Override
    public Expression visitInstance_specific_initializations(@NotNull StructuredTextParser.Instance_specific_initializationsContext ctx) {
        return null;
    }

    @Override
    public Expression visitUnary_operator(@NotNull StructuredTextParser.Unary_operatorContext ctx) {
        return null;
    }

    @Override
    public Expression visitDerived_function_block_name(@NotNull StructuredTextParser.Derived_function_block_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitUnsigned_integer_type_name(@NotNull StructuredTextParser.Unsigned_integer_type_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitReal_type_cast(@NotNull StructuredTextParser.Real_type_castContext ctx) {
        return null;
    }

    @Override
    public Expression visitInteger_type_name(@NotNull StructuredTextParser.Integer_type_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitMonth(@NotNull StructuredTextParser.MonthContext ctx) {
        return null;
    }

    @Override
    public Expression visitParam_assignment(@NotNull StructuredTextParser.Param_assignmentContext ctx) {
        return null;
    }

    @Override
    public Expression visitString_type_declaration(@NotNull StructuredTextParser.String_type_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitProgram_access_decls(@NotNull StructuredTextParser.Program_access_declsContext ctx) {
        return null;
    }

    @Override
    public Expression visitVar1_list(@NotNull StructuredTextParser.Var1_listContext ctx) {
        return null;
    }

    @Override
    public Expression visitTask_initialization(@NotNull StructuredTextParser.Task_initializationContext ctx) {
        return null;
    }

    @Override
    public Expression visitFunction_block_declaration(@NotNull StructuredTextParser.Function_block_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitProg_conf_element(@NotNull StructuredTextParser.Prog_conf_elementContext ctx) {
        return null;
    }

    @Override
    public Expression visitString_type_name(@NotNull StructuredTextParser.String_type_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitSubscript_list(@NotNull StructuredTextParser.Subscript_listContext ctx) {
        return null;
    }

    @Override
    public Expression visitVar1_init_decl(@NotNull StructuredTextParser.Var1_init_declContext ctx) {
        return null;
    }

    @Override
    public Expression visitStandard_function_block_name(@NotNull StructuredTextParser.Standard_function_block_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitStructure_element_declaration(@NotNull StructuredTextParser.Structure_element_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitVar_declarations(@NotNull StructuredTextParser.Var_declarationsContext ctx) {
        return null;
    }

    @Override
    public Expression visitData_type_declaration(@NotNull StructuredTextParser.Data_type_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitDuration(@NotNull StructuredTextParser.DurationContext ctx) {
        return null;
    }

    @Override
    public Expression visitLocated_var_decl(@NotNull StructuredTextParser.Located_var_declContext ctx) {
        return null;
    }

    @Override
    public Expression visitVar_spec(@NotNull StructuredTextParser.Var_specContext ctx) {
        return null;
    }

    @Override
    public Expression visitSubrange_type_declaration(@NotNull StructuredTextParser.Subrange_type_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitSingle_resource_declaration(@NotNull StructuredTextParser.Single_resource_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitSelection_statement(@NotNull StructuredTextParser.Selection_statementContext ctx) {
        return null;
    }

    @Override
    public Expression visitStatement(@NotNull StructuredTextParser.StatementContext ctx) {
        return null;
    }

    @Override
    public Expression visitDate_literal(@NotNull StructuredTextParser.Date_literalContext ctx) {
        return null;
    }

    @Override
    public Expression visitProg_conf_elements(@NotNull StructuredTextParser.Prog_conf_elementsContext ctx) {
        return null;
    }

    @Override
    public Expression visitHours(@NotNull StructuredTextParser.HoursContext ctx) {
        return null;
    }

    @Override
    public Expression visitStart(@NotNull StructuredTextParser.StartContext ctx) {
        return null;
    }

    @Override
    public Expression visitArray_type_declaration(@NotNull StructuredTextParser.Array_type_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitVar_declaration(@NotNull StructuredTextParser.Var_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitAccess_name(@NotNull StructuredTextParser.Access_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitBinary_integer(@NotNull StructuredTextParser.Binary_integerContext ctx) {
        return null;
    }

    @Override
    public Expression visitFunction_name(@NotNull StructuredTextParser.Function_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitLocation(@NotNull StructuredTextParser.LocationContext ctx) {
        return null;
    }

    @Override
    public Expression visitInput_output_declarations(@NotNull StructuredTextParser.Input_output_declarationsContext ctx) {
        return null;
    }

    @Override
    public Expression visitDay_second(@NotNull StructuredTextParser.Day_secondContext ctx) {
        return null;
    }

    @Override
    public Expression visitMilliseconds(@NotNull StructuredTextParser.MillisecondsContext ctx) {
        return null;
    }

    @Override
    public Expression visitFb_name_decl(@NotNull StructuredTextParser.Fb_name_declContext ctx) {
        return null;
    }

    @Override
    public Expression visitInteger_literal(@NotNull StructuredTextParser.Integer_literalContext ctx) {
        Literal l = new Literal();

        if (ctx.integer_type_cast() != null) {
            l.setCast(ctx.integer_type_cast().getText());
        }

        if(ctx.unsigned_integer() != null) {
            l.setLiteral(ctx.unsigned_integer().getText());
            l.setType(Literal.LiteralType.UINT);
        }

        if(ctx.binary_integer() != null) {
            l.setLiteral(ctx.binary_integer().getText());
            l.setType(Literal.LiteralType.BINARY);
        }

        if(ctx.octal_integer() != null) {
            l.setLiteral(ctx.octal_integer().getText());
        }

        if(ctx.hex_integer() != null) {
            l.setLiteral(ctx.hex_integer().getText());
        }

        return l;
    }

    @Override
    public Expression visitVar_init_decl(@NotNull StructuredTextParser.Var_init_declContext ctx) {
        return null;
    }

    @Override
    public Expression visitSingle_byte_string_var_declaration(@NotNull StructuredTextParser.Single_byte_string_var_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitSeconds(@NotNull StructuredTextParser.SecondsContext ctx) {
        return null;
    }

    @Override
    public Expression visitFb_name_list(@NotNull StructuredTextParser.Fb_name_listContext ctx) {
        return null;
    }

    @Override
    public Expression visitTemp_var_decls(@NotNull StructuredTextParser.Temp_var_declsContext ctx) {
        return null;
    }

    @Override
    public Expression visitDouble_byte_character_representation(@NotNull StructuredTextParser.Double_byte_character_representationContext ctx) {
        return null;
    }

    @Override
    public Expression visitEnumerated_specification(@NotNull StructuredTextParser.Enumerated_specificationContext ctx) {
        return null;
    }

    @Override
    public Expression visitSymbolic_variable(@NotNull StructuredTextParser.Symbolic_variableContext ctx) {
        return null;
    }

    @Override
    public Expression visitWhile_statement(@NotNull StructuredTextParser.While_statementContext ctx) {
        return null;
    }

    @Override
    public Expression visitProgram_configuration(@NotNull StructuredTextParser.Program_configurationContext ctx) {
        return null;
    }

    @Override
    public Expression visitHex_integer(@NotNull StructuredTextParser.Hex_integerContext ctx) {
        return null;
    }

    @Override
    public Expression visitElementary_type_name(@NotNull StructuredTextParser.Elementary_type_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitCase_element(@NotNull StructuredTextParser.Case_elementContext ctx) {
        return null;
    }

    @Override
    public Expression visitArray_var_init_decl(@NotNull StructuredTextParser.Array_var_init_declContext ctx) {
        return null;
    }

    @Override
    public Expression visitEnumerated_type_name(@NotNull StructuredTextParser.Enumerated_type_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitExternal_var_declarations(@NotNull StructuredTextParser.External_var_declarationsContext ctx) {
        return null;
    }

    @Override
    public Expression visitStructure_element_name(@NotNull StructuredTextParser.Structure_element_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitString_var_declaration(@NotNull StructuredTextParser.String_var_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitSigned_integer_type_cast(@NotNull StructuredTextParser.Signed_integer_type_castContext ctx) {
        return null;
    }

    @Override
    public Expression visitIf_statement(@NotNull StructuredTextParser.If_statementContext ctx) {
        return null;
    }

    @Override
    public Expression visitInterval(@NotNull StructuredTextParser.IntervalContext ctx) {
        return null;
    }

    @Override
    public Expression visitStructure_element_initialization(@NotNull StructuredTextParser.Structure_element_initializationContext ctx) {
        return null;
    }

    @Override
    public Expression visitTime_literal(@NotNull StructuredTextParser.Time_literalContext ctx) {
        return null;
    }

    @Override
    public Expression visitSubprogram_control_statement(@NotNull StructuredTextParser.Subprogram_control_statementContext ctx) {
        return null;
    }

    @Override
    public Expression visitStructure_initialization(@NotNull StructuredTextParser.Structure_initializationContext ctx) {
        return null;
    }

    @Override
    public Expression visitStructured_var_init_decl(@NotNull StructuredTextParser.Structured_var_init_declContext ctx) {
        return null;
    }

    @Override
    public Expression visitFb_invocation(@NotNull StructuredTextParser.Fb_invocationContext ctx) {
        return null;
    }

    @Override
    public Expression visitCase_statement(@NotNull StructuredTextParser.Case_statementContext ctx) {
        return null;
    }

    @Override
    public Expression visitField_selector(@NotNull StructuredTextParser.Field_selectorContext ctx) {
        return null;
    }

    @Override
    public Expression visitSingle_byte_character_representation(@NotNull StructuredTextParser.Single_byte_character_representationContext ctx) {
        return null;
    }

    @Override
    public Expression visitFb_task(@NotNull StructuredTextParser.Fb_taskContext ctx) {
        return null;
    }

    @Override
    public Expression visitProg_cnxn(@NotNull StructuredTextParser.Prog_cnxnContext ctx) {
        return null;
    }

    @Override
    public Expression visitDerived_type_name(@NotNull StructuredTextParser.Derived_type_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitSubrange_type_name(@NotNull StructuredTextParser.Subrange_type_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitProgram_declaration(@NotNull StructuredTextParser.Program_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitArray_var_declaration(@NotNull StructuredTextParser.Array_var_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitGlobal_var_spec(@NotNull StructuredTextParser.Global_var_specContext ctx) {
        return null;
    }

    @Override
    public Expression visitStatement_list(@NotNull StructuredTextParser.Statement_listContext ctx) {
        return null;
    }

    @Override
    public Expression visitDerived_function_name(@NotNull StructuredTextParser.Derived_function_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitStructure_specification(@NotNull StructuredTextParser.Structure_specificationContext ctx) {
        return null;
    }

    @Override
    public Expression visitLocated_var_spec_init(@NotNull StructuredTextParser.Located_var_spec_initContext ctx) {
        return null;
    }

    @Override
    public Expression visitAssignment_statement(@NotNull StructuredTextParser.Assignment_statementContext ctx) {
        return null;
    }

    @Override
    public Expression visitDay_minute(@NotNull StructuredTextParser.Day_minuteContext ctx) {
        return null;
    }

    @Override
    public Expression visitDate_and_time(@NotNull StructuredTextParser.Date_and_timeContext ctx) {
        return null;
    }

    @Override
    public Expression visitInteger_type_cast(@NotNull StructuredTextParser.Integer_type_castContext ctx) {
        return null;
    }

    @Override
    public Expression visitControl_variable(@NotNull StructuredTextParser.Control_variableContext ctx) {
        return null;
    }

    @Override
    public Expression visitRetentive_var_declarations(@NotNull StructuredTextParser.Retentive_var_declarationsContext ctx) {
        return null;
    }

    @Override
    public Expression visitDate(@NotNull StructuredTextParser.DateContext ctx) {
        return null;
    }

    @Override
    public Expression visitDouble_byte_character_string(@NotNull StructuredTextParser.Double_byte_character_stringContext ctx) {
        return null;
    }

    @Override
    public Expression visitConstant(@NotNull StructuredTextParser.ConstantContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public Expression visitSimple_type_name(@NotNull StructuredTextParser.Simple_type_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitGlobal_var_reference(@NotNull StructuredTextParser.Global_var_referenceContext ctx) {
        return null;
    }

    @Override
    public Expression visitArray_initial_elements(@NotNull StructuredTextParser.Array_initial_elementsContext ctx) {
        return null;
    }

    @Override
    public Expression visitTask_configuration(@NotNull StructuredTextParser.Task_configurationContext ctx) {
        return null;
    }

    @Override
    public Expression visitInteger(@NotNull StructuredTextParser.IntegerContext ctx) {
        return null;
    }

    @Override
    public Expression visitIo_var_declarations(@NotNull StructuredTextParser.Io_var_declarationsContext ctx) {
        return null;
    }

    @Override
    public Expression visitNon_retentive_var_declarations(@NotNull StructuredTextParser.Non_retentive_var_declarationsContext ctx) {
        return null;
    }

    @Override
    public Expression visitFunction_block_type_name(@NotNull StructuredTextParser.Function_block_type_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitProgram_type_name(@NotNull StructuredTextParser.Program_type_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitResource_declaration(@NotNull StructuredTextParser.Resource_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitArray_initialization(@NotNull StructuredTextParser.Array_initializationContext ctx) {
        return null;
    }

    @Override
    public Expression visitDay(@NotNull StructuredTextParser.DayContext ctx) {
        return null;
    }

    @Override
    public Expression visitLocated_var_declarations(@NotNull StructuredTextParser.Located_var_declarationsContext ctx) {
        return null;
    }

    @Override
    public Expression visitVar1_declaration(@NotNull StructuredTextParser.Var1_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitSubscript(@NotNull StructuredTextParser.SubscriptContext ctx) {
        return null;
    }

    @Override
    public Expression visitMinutes(@NotNull StructuredTextParser.MinutesContext ctx) {
        return null;
    }

    @Override
    public Expression visitDirect_variable(@NotNull StructuredTextParser.Direct_variableContext ctx) {
        return null;
    }

    @Override
    public Expression visitSingle_byte_character_string(@NotNull StructuredTextParser.Single_byte_character_stringContext ctx) {
        return null;
    }

    @Override
    public Expression visitInitialized_structure(@NotNull StructuredTextParser.Initialized_structureContext ctx) {
        return null;
    }

    @Override
    public Expression visitProgram_output_reference(@NotNull StructuredTextParser.Program_output_referenceContext ctx) {
        return null;
    }

    @Override
    public Expression visitCase_list(@NotNull StructuredTextParser.Case_listContext ctx) {
        return null;
    }

    @Override
    public Expression visitTime_of_day(@NotNull StructuredTextParser.Time_of_dayContext ctx) {
        return null;
    }

    @Override
    public Expression visitData_type_name(@NotNull StructuredTextParser.Data_type_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitDay_hour(@NotNull StructuredTextParser.Day_hourContext ctx) {
        return null;
    }

    @Override
    public Expression visitEnumerated_value(@NotNull StructuredTextParser.Enumerated_valueContext ctx) {
        return null;
    }

    @Override
    public Expression visitTask_name(@NotNull StructuredTextParser.Task_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitArray_specification(@NotNull StructuredTextParser.Array_specificationContext ctx) {
        return null;
    }

    @Override
    public Expression visitFunction_body(@NotNull StructuredTextParser.Function_bodyContext ctx) {
        return null;
    }

    @Override
    public Expression visitSubrange_specification(@NotNull StructuredTextParser.Subrange_specificationContext ctx) {
        return null;
    }

    @Override
    public Expression visitEdge_declaration(@NotNull StructuredTextParser.Edge_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitEnumerated_spec_init(@NotNull StructuredTextParser.Enumerated_spec_initContext ctx) {
        return null;
    }

    @Override
    public Expression visitNumeric_type_name(@NotNull StructuredTextParser.Numeric_type_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitArray_type_name(@NotNull StructuredTextParser.Array_type_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitIncompl_located_var_decl(@NotNull StructuredTextParser.Incompl_located_var_declContext ctx) {
        return null;
    }

    @Override
    public Expression visitCase_list_element(@NotNull StructuredTextParser.Case_list_elementContext ctx) {
        return null;
    }

    @Override
    public Expression visitUnsigned_integer_type_cast(@NotNull StructuredTextParser.Unsigned_integer_type_castContext ctx) {
        return null;
    }

    @Override
    public Expression visitNon_generic_type_name(@NotNull StructuredTextParser.Non_generic_type_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitFor_list(@NotNull StructuredTextParser.For_listContext ctx) {
        return null;
    }

    @Override
    public Expression visitProgram_access_decl(@NotNull StructuredTextParser.Program_access_declContext ctx) {
        return null;
    }

    @Override
    public Expression visitFunction_block_body(@NotNull StructuredTextParser.Function_block_bodyContext ctx) {
        return null;
    }

    @Override
    public Expression visitVariable_name(@NotNull StructuredTextParser.Variable_nameContext ctx) {
        return null;
    }

    @Override
    public Expression visitFunction_declaration(@NotNull StructuredTextParser.Function_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitDirection(@NotNull StructuredTextParser.DirectionContext ctx) {
        return null;
    }

    @Override
    public Expression visitSimple_spec_init(@NotNull StructuredTextParser.Simple_spec_initContext ctx) {
        return null;
    }

    @Override
    public Expression visitRepeat_statement(@NotNull StructuredTextParser.Repeat_statementContext ctx) {
        return null;
    }

    @Override
    public Expression visitCommon_character_representation(@NotNull StructuredTextParser.Common_character_representationContext ctx) {
        return null;
    }

    @Override
    public Expression visitAccess_declarations(@NotNull StructuredTextParser.Access_declarationsContext ctx) {
        return null;
    }

    @Override
    public Expression visitExit_statement(@NotNull StructuredTextParser.Exit_statementContext ctx) {
        return null;
    }

    @Override
    public Expression visitSimple_type_declaration(@NotNull StructuredTextParser.Simple_type_declarationContext ctx) {
        return null;
    }

    @Override
    public Expression visitSubrange(@NotNull StructuredTextParser.SubrangeContext ctx) {
        return null;
    }

    @Override
    public Expression visitArray_spec_init(@NotNull StructuredTextParser.Array_spec_initContext ctx) {
        return null;
    }

    @Override
    public Expression visitVariable(@NotNull StructuredTextParser.VariableContext ctx) {
        return null;
    }

    @Override
    public Expression visitUnsigned_integer(@NotNull StructuredTextParser.Unsigned_integerContext ctx) {
        return null;
    }
}
