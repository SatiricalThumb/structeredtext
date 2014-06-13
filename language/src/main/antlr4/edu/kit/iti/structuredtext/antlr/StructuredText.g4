grammar StructuredText;


@header {
import java.util.*;
import edu.kit.iti.structuredtext.*;
import edu.kit.iti.structuredtext.ast.*;
import edu.kit.iti.structuredtext.datatypes.*;
import edu.kit.iti.structuredtext.datatypes.values.*;
}



fragment
FIXED_POINT                             : NUMBER (DOT NUMBER)?;

fragment
LETTER                                  : 'a'..'z' | 'A'..'Z';

fragment
DIGIT                                   : '0'..'9';

fragment
HEX_DIGIT                               : DIGIT | 'A'..'F';

fragment
OCTAL_DIGIT                             : '0'..'7';

fragment
Underscores
: '_'+
;


/******
 * Literal
 */

fragment
BIT_TYPES               : (BOOL|BYTE|WORD|DWORD|LWORD) '#';

fragment
INT_TYPES               : (SINT|INT|DINT|LINT) '#' (MINUS|PLUS)?;

fragment
UINT_TYPES               : (USINT|UINT|UDINT|ULINT) '#';

fragment
REAL_TYPES              : (REAL|LREAL) '#';

fragment
NUMBER                  : DIGIT (DIGIT | Underscores DIGIT)*;

fragment
NUMBER_BASE             : ('2'|'8'|'16') '#';

fragment
OCTAL_LITERAL           : '8#' OCTAL_DIGIT (OCTAL_DIGIT | Underscores OCTAL_DIGIT)*;

fragment
BINARY_LITERAL          : '2#' BIT (BIT | Underscores BIT)*;

fragment
HEX_LITERAL             : '16#' HEX_DIGIT (Underscores | Underscores HEX_DIGIT)*;


INTEGER_LITERAL         : (UINT_TYPES|INT_TYPES|BIT_TYPES)? (OCTAL_DIGIT|BINARY_LITERAL|HEX_LITERAL|NUMBER);

REAL_LITERAL            : (REAL_TYPES [+-]?)? FIXED_POINT ([eE] FIXED_POINT+)?;

TIME_LITERAL            : (TIME|'T') '#' (FIXED_POINT ('d'|'h'|'m'|'s'|'ms'))+;

fragment
DATE_VALUE              :  NUMBER MINUS NUMBER MINUS NUMBER;

fragment
TwoDigit                : DIGIT? DIGIT;

fragment
TOD_VALUE               : TwoDigit COLON TwoDigit COLON TwoDigit (DOT TwoDigit)?;


DATE_LITERAL             : (DATE|'D')'#' DATE_VALUE;
TOD_LITERAL             : (TIME_OF_DAY|'TOD')'#' TOD_VALUE;

DATETIME                : (DATE_AND_TIME|'DT')'#' DATE_VALUE MINUS TOD_VALUE;

INCOMPL_LOCATION_LITERAL: 'AT%'[IQM]'*';

fragment
StringCharacters        : ~["];

STRING_LITERAL          : ['] (StringCharacters| '$\'')* ['];
WSTRING_LITERAL         : ["] (StringCharacters| '$"')* ["];


/******
 * DATATYPES
 */


ANY                     : 'ANY';
ANY_BIT                 : 'ANY_BIT';
ANY_DATE                : 'ANY_DATE';
ANY_DERIVED             : 'ANY_DERIVED';
ANY_ELEMENTARY          : 'ANY_ELEMENTARY';
ANY_INT                 : 'ANY_INT';
ANY_MAGNITUDE           : 'ANY_MAGNITUDE';
ANY_NUM                 : 'ANY_NUM';
ANY_REAL                : 'ANY_REAL';
ANY_STRING              : 'ANY_STRING';
ARRAY                   : 'ARRAY';
BOOL                    : 'BOOL';
BYTE                    : 'BYTE';
DATE_AND_TIME           : 'DATE_AND_TIME';
DINT                    : 'DINT';
DWORD                   : 'DWORD';
INT                     : 'INT';
LINT                    : 'LINT';
LREAL                   : 'LREAL';
LWORD                   : 'LWORD';
REAL                    : 'REAL';
SINT                    : 'SINT';
STRING                  : 'STRING';
TIME                    : 'TIME';
TIME_OF_DAY             : 'TIME_OF_DAY'|'TOD';
UDINT                   : 'UDINT';
UINT                    : 'UINT';
ULINT                   : 'ULINT';
USINT                   : 'USINT';
WORD                    : 'WORD';
WSTRING                 : 'WSTRING';


/******
 * Keywords
 */

VAR_OUTPUT               : 'VAR_OUTPUT';
AT                      : 'AT';
BY                      : 'BY';
CASE                    : 'CASE';
COLON                   : ':';
CONFIGURATION           : 'CONFIGURATION';
CONSTANT                : 'CONSTANT';
DATE                    : 'DATE';
DO                      : 'DO';
DT                      : 'DT';
ELSE                    : 'ELSE';
ELSEIF                  : 'ELSEIF';
END_CASE                : 'END_CASE';
END_CONFIGURATION       : 'END_CONFIGURATION';
END_FOR                 : 'END_FOR';
END_FUNCTION            : 'END_FUNCTION';
END_FUNCTION_BLOCK      : 'END_FUNCTION_BLOCK';
END_IF                  : 'END_IF';
END_PROGRAM             : 'END_PROGRAM';
END_REPEAT              : 'END_REPEAT';
END_RESOURCE            : 'END_RESOURCE';
END_STRUCT              : 'END_STRUCT';
END_TYPE                : 'END_TYPE';
END_VAR                 : 'END_VAR';
END_WHILE               : 'END_WHILE';
EXIT                    : 'EXIT';
FOR                     : 'FOR';
FUNCTION                : 'FUNCTION';
FUNCTION_BLOCK          : 'FUNCTION_BLOCK';
F_EDGE                  : 'F_EDGE';
IF                      : 'IF';
INTERVAL                : 'INTERVAL';
NIL                     : 'NIL';
NON_RETAIN              : 'NON_RETAIN';
OF                      : 'OF';
ON                      : 'ON';
PRIORITY                : 'PRIORITY';
PROGRAM                 : 'PROGRAM';
READ_ONLY               : 'READ_ONLY';
READ_WRITE              : 'READ_WRITE';
REPEAT                  : 'REPEAT';
RESOURCE                : 'RESOURCE';
RETAIN                  : 'RETAIN';
RETURN                  : 'RETURN';
RIGHT_ARROW             : 'RIGHT_ARROW';
R_EDGE                  : 'R_EDGE';
SINGLE                  : 'SINGLE';
STRUCT                  : 'STRUCT';
TASK                    : 'TASK';
THEN                    : 'THEN';
TO                      : 'TO';
TYPE                    : 'TYPE';
UNTIL                   : 'UNTIL';
VAR                     : 'VAR';
VAR_ACCESS              : 'VAR_ACCESS';
VAR_CONFIG              : 'VAR_CONFIG';
VAR_EXTERNAL            : 'VAR_EXTERNAL';
VAR_GLOBAL              : 'VAR_GLOBAL';
VAR_INPUT               : 'VAR_INPUT';
VAR_IN_OUT              : 'VAR_IN_OUT';
VAR_TEMP                : 'VAR_TEMP';
WHILE                   : 'WHILE';
WITH                    : 'WITH';


/******
 * Operators
 */

AND                     :'AND';
ARROW_RIGHT             : '=>';
ASSIGN                  :':=';
COMMA                   : ',';
DIV                     :'/';
EQUALS                  : '=';
GREATER_EQUALS          :'>=';
GREATER_THAN            :'>';
LBRACKET                :'[';
LESS_EQUALS             : '<=';
LESS_THAN               :'<';
LPAREN                  : '(';
MINUS                   : '-';
MOD                     :'MOD';
MULT                    :'*';
NOT                     : 'NOT';
NOT_EQUALS              :'<>';
OR                      :'OR';
PLUS                    : '+';
POWER                   :'**';
RBRACKET                :']';
RPAREN                  : ')';
XOR                     :'XOR';


/*******
 * Constants
 */

AMPERSAND               :'&';
BIT                     : '1' | '0';
DOLLAR                  :'$';
DQUOTE                  :'"';
FALSE                   : 'FALSE';
SEMICOLON               :';';
SQUOTE                  :'\'';
TRUE                    : 'TRUE';
DOT                     : '.';
RANGE                   :  '..';


//Ignore
WS                      : [ \r\t\n]+      -> channel(HIDDEN);
COMMENT                 : '(*' ~[] '*)'  -> channel(HIDDEN);


IDENTIFIER              : [a-zA-Z] [a-zA-Z0-9_]*;
CAST_LITERAL            : IDENTIFIER '#' IDENTIFIER;
DIRECT_VARIABLE_LITERAL: '%' [IQM] [XBWDL]? FIXED_POINT;

/************************************************/

testtest: WS; // <- avoid an antlr error

start
    locals [ List<TopLevelElement> ast = new LinkedList<>(); ]
    : library_element_declaration +
    {
        System.out.println("Parsers gonna parse");
    }
    ;


//TODO
library_element_declaration
    : data_type_declaration
      { $start::ast.add($data_type_declaration.ctx.ast); }

    | function_declaration
      { $start::ast.add($function_declaration.ctx.ast); }

    | function_block_declaration
      { $start::ast.add($function_block_declaration.ctx.ast); }

    | program_declaration
      { $start::ast.add($program_declaration.ctx.ast); }

    | configuration_declaration
       // { $start::ast.add($configuration_declaration.ctx.ast); }
    ;

library_element_name
    locals []
    : data_type_name
    | IDENTIFIER
    ;


constant
    locals [ ScalarValue<?,?> ast; ]
    : integer   { $ast= $integer.ctx.ast; }
    | real      { $ast= $real.ctx.ast; }
    | string    { $ast= $string.ctx.ast; }
    | time      { $ast= $time.ctx.ast; }
    | timeofday { $ast= $timeofday.ctx.ast; }
    | date      { $ast= $date.ctx.ast; }
    | boolc     { $ast= $boolc.ctx.ast; }
    | datetime  { $ast= $datetime.ctx.ast; }
    | cast      { $ast= $cast.ctx.ast; }
    ;

boolc
    locals [ ScalarValue<AnyBit.Bool, Bits> ast]
    : (FALSE | TRUE)
    {  $ast = ValueFactory.makeBool($boolc.text); }
    ;

cast
    locals [ ScalarValue<EnumerateType, String> ast;]
    : CAST_LITERAL
    { $ast = ValueFactory.makeEnumeratedValue($CAST_LITERAL.text);}
    ;

integer
    locals [ ScalarValue<? extends AnyInt, Integer> ast; ]
    : INTEGER_LITERAL
    { $ast = ValueFactory.parseIntegerLiteral($INTEGER_LITERAL.text);}
    ;

real
    locals [ ScalarValue<? extends AnyReal, Double> ast ]
    : REAL_LITERAL
    { $ast = ValueFactory.parseRealLiteral($REAL_LITERAL.text); }

    ;

string
    locals [ ScalarValue<? extends IECString, String> ast;]

    : tok=(WSTRING_LITERAL | STRING_LITERAL)
    {$ast = ValueFactory.parseStringLiteral($tok.text);}
    ;

time
     locals [ ScalarValue<TimeType, TimeValue> ast]
    : TIME_LITERAL
    {$ast = ValueFactory.parseTimeLiteral($TIME_LITERAL.text);}
    ;

timeofday
    locals [ ScalarValue<AnyDate.TimeOfDay, TimeOfDayValue> ast]
    : TOD_LITERAL
    { $ast =ValueFactory.parseTimeOfDayLiteral($TOD_LITERAL.text);}
    ;

date
    locals [ ScalarValue<AnyDate.Date, DateValue> ast]
    : DATE_LITERAL
    {$ast = ValueFactory.parseDateLiteral($DATE_LITERAL.text);}
    ;

datetime
    locals [ ScalarValue<AnyDate.DateAndTime, DateAndTimeValue> ast;]
     : DATETIME
     { $ast = ValueFactory.parseDateTime($DATETIME.text);}
     ;

data_type_name
    : non_generic_type_name | generic_type_name
    ;

non_generic_type_name 
    : elementary_type_name
    | IDENTIFIER
    ;

elementary_type_name
    : numeric_type_name
    | date_type_name
    | bit_string_type_name
    | STRING
    | WSTRING
    | TIME
    ;

numeric_type_name
    : integer_type_name
    | real_type_name
    ;

integer_type_name
    : signed_integer_type_name
    | unsigned_integer_type_name
    ;

signed_integer_type_name 
    : SINT
    | INT
    | DINT
    | LINT
    ;

unsigned_integer_type_name
    : USINT
    | UINT
    | UDINT
    | ULINT
    ;

real_type_name
    : REAL
    | LREAL
    ;

date_type_name  
    : DATE
    | TIME_OF_DAY
    | DATE_AND_TIME
    | DT
    ;

bit_string_type_name 
    : BOOL
    | BYTE 
    | WORD 
    | DWORD 
    | LWORD
    ;

generic_type_name 
    : ANY 
    | ANY_DERIVED 
    | ANY_ELEMENTARY 
    | ANY_MAGNITUDE
    | ANY_NUM 
    | ANY_REAL 
    | ANY_INT 
    | ANY_BIT 
    | ANY_STRING 
    | ANY_DATE
    ;

data_type_declaration
   locals [ TypeDeclarations ast = new TypeDeclarations();  ]
    : TYPE (type_declaration SEMICOLON)+ END_TYPE
    ;

type_declaration    
    : array_type_declaration
    | structure_type_declaration
    | string_type_declaration
    | simple_type_declaration
    | subrange_type_declaration
    | enumerated_type_declaration
    ;

simple_type_declaration
    locals [ SimpleTypeDeclaration ast; ]
    : IDENTIFIER COLON simple_spec_init
    {
        $ast = $simple_spec_init.ctx.ast;
        $ast.setTypeName($IDENTIFIER.text);
        $data_type_declaration::ast.add($ast);
    }
    ;

simple_spec_init
    locals [ SimpleTypeDeclaration ast = new SimpleTypeDeclaration()]
    : simple_specification (ASSIGN c=constant {$ast.setInitializationValue($c.ctx.ast);})?
    {
        $ast.setBaseTypeName($simple_specification.text);
    }
    ;

simple_specification
    : elementary_type_name
    | IDENTIFIER
    ;

subrange_type_declaration
    locals [ SubRangeDataType ast; ]
    :IDENTIFIER COLON subrange_spec_init
    {
        $ast = $subrange_spec_init.ctx.ast;
        $ast.setTypeName($IDENTIFIER.text);
        $data_type_declaration::ast.add($ast);
    }
    ;

subrange_spec_init
    locals [ SubRangeDataType ast; ]
    : subrange_specification (ASSIGN integer {$ast.setInitializationValue($integer.ctx.ast);} )?
    {
        $ast = $subrange_specification.ctx.ast;
    }
    ;

subrange_specification
    locals [ SubRangeDataType ast = new SubRangeDataType(); ]
    : integer_type_name LPAREN subrange RPAREN
    {
        $ast.setBaseTypeName($integer_type_name.text);
        $ast.setRange($subrange.ctx.ast);
    }
    | IDENTIFIER
    {
        $ast.setBaseTypeName($IDENTIFIER.text);
    }
    ;

subrange
    locals [ Range ast; ]
    : a=MINUS? c=integer RANGE  b=MINUS? d=integer
    {
        //TODO signs
        $ast = new Range($c.ctx.ast, $d.ctx.ast);
    }
    ;

enumerated_type_declaration
    locals [ EnumerationTypeDeclaration  ast; ]
    :name=IDENTIFIER COLON enumerated_specification (ASSIGN value=(IDENTIFIER|CAST_LITERAL) )?
    {
        $ast = $enumerated_specification.ctx.ast;
        $ast.setTypeName($name.text);

        if($value != null) {
            $ast.setInitializationValue($value.text);
        }
        $data_type_declaration::ast.add($ast);
    }
    ;


/* removed: casting of identifiers */
enumerated_specification
    locals [ EnumerationTypeDeclaration  ast = new EnumerationTypeDeclaration(); ]
    : (LPAREN names+=IDENTIFIER (COMMA names+=IDENTIFIER)* RPAREN)
    {
        for(Token tok : $names)
            $ast.addValue(tok.getText());
    }
    | IDENTIFIER
    {
        $ast.setBaseTypeName($IDENTIFIER.text);
    }
    ;

array_type_declaration
 locals [ ArrayTypeDeclaration ast = new ArrayTypeDeclaration() ]
    :IDENTIFIER COLON array_spec_init
    {
        $ast.setTypeName($IDENTIFIER.text);
        $data_type_declaration::ast.add($ast);
    }
    ;

array_spec_init
    locals [ ArrayTypeDeclaration ast;]
    : array_specification
            (ASSIGN array_initialization
              {$ast.setInitializationValue($array_initialization.ctx.ast);}
             )?
    ;

array_specification
    locals [ ArrayTypeDeclaration ast = new ArrayTypeDeclaration() ]
    : IDENTIFIER
    { $ast.setBaseTypeName($IDENTIFIER.text);}
    | ARRAY LBRACKET ranges+=subrange (COMMA ranges+=subrange)* RBRACKET
        OF non_generic_type_name
    {
        $ast.setBaseTypeName($non_generic_type_name.text);

        for(SubrangeContext src : $ranges) {
            $ast.addSubRange(src.ast);
        }
    }
    ;

array_initialization
    locals [ ArrayInitialization ast = new ArrayInitialization(); ]
    : LBRACKET array_initial_elements 
      (COMMA array_initial_elements)* RBRACKET
    ;

array_initial_elements 
    : array_initial_element 
    | integer LPAREN (array_initial_element)? RPAREN
    ;

array_initial_element 
    : constant                       { $array_initialization::ast.add($constant.ctx.ast); }
    | structure_initialization       { $array_initialization::ast.add($structure_initialization.ctx.ast); }
    | array_initialization           { $array_initialization::ast.add($array_initialization.ctx.ast); }
    ;

structure_type_declaration
    locals [ StructureTypeDeclaration ast = new StructureTypeDeclaration(); ]
    :IDENTIFIER COLON structure_specification
     {  $ast.setTypeName($IDENTIFIER.text);
        $data_type_declaration::ast.add($ast);
     }
    ;

structure_specification
    : structure_declaration 
    | initialized_structure
    ;

initialized_structure
    locals [ StructureInitialization ast = new StructureInitialization(); ]
    :IDENTIFIER (ASSIGN structure_initialization)?
    { $ast = $structure_initialization.ctx.ast;
      $ast.setStructureName($IDENTIFIER.text);
    }
    ;

structure_declaration
    locals []
    : STRUCT structure_element_declaration SEMICOLON 
      (structure_element_declaration SEMICOLON)* 
      END_STRUCT
    ;

structure_element_declaration
    locals [
    ]
    :IDENTIFIER COLON
        ( simple_spec_init
          { $structure_type_declaration::ast.addField($IDENTIFIER.text, $simple_spec_init.ctx.ast);}
        | subrange_spec_init
          { $structure_type_declaration::ast.addField($IDENTIFIER.text, $subrange_spec_init.ctx.ast);}
        | cast
          { $structure_type_declaration::ast.addField($IDENTIFIER.text, $cast.ctx.ast);}
        | array_spec_init
          { $structure_type_declaration::ast.addField($IDENTIFIER.text, $array_spec_init.ctx.ast);}
        | initialized_structure
          { $structure_type_declaration::ast.addField($IDENTIFIER.text, $initialized_structure.ctx.ast);}
        )
    ;    


structure_initialization
    locals [ StructureInitialization ast = new StructureInitialization(); ]
    : LPAREN structure_element_initialization
        (COMMA structure_element_initialization)* RPAREN
    ;

structure_element_initialization 
    :n=IDENTIFIER ASSIGN
        ( constant              {$structure_initialization::ast.addField($n.text, $constant.ctx.ast);}
        | array_initialization  {$structure_initialization::ast.addField($n.text, $array_initialization.ctx.ast);}
        | structure_initialization
          {$structure_initialization::ast.addField($n.text, $structure_initialization.ctx.ast);}
        )
    ;


string_type_declaration
    locals [ StringTypeDeclaration ast = new StringTypeDeclaration(); ]
    :IDENTIFIER COLON
      base=(STRING|WSTRING)
      (LBRACKET integer { $ast.setSize($integer.ctx.ast);} RBRACKET)?
      (ASSIGN value=string {$ast.setInitializationValue($value.ctx.ast);} )?
    {
        $ast.setTypeName($IDENTIFIER.text);
        $ast.setBaseTypeName($base.text);
        $data_type_declaration::ast.add($ast);
    }
    ;

variable
    locals [ Reference ast; ]
    : direct_variable   { $ast = $direct_variable.ctx.ast; }
    | symbolic_variable { $ast = $symbolic_variable.ctx.ast; }
    ;

symbolic_variable
    locals [ SymbolicReference ast;]
    : IDENTIFIER subscript_list? ( DOT other=symbolic_variable)?
    { $ast = new SymbolicReference($IDENTIFIER.text,
        $DOT.text != null ? $other.ctx.ast : null);}
    ;

subscript_list
    : LBRACKET expression (COMMA expression)* RBRACKET
      {
        $symbolic_variable::ast.addSubscript($expression.ctx.ast);
      }
    ;


direct_variable
    locals [ DirectVariable ast; ]
    : DIRECT_VARIABLE_LITERAL
    { $ast=new DirectVariable($DIRECT_VARIABLE_LITERAL.text); }
    ;


input_declarations[VariableScope gather]
    @init {
        $gather.push(Variable.INPUT);
    }
    : VAR_INPUT (RETAIN {$gather.mix(Variable.RETAIN);} | NON_RETAIN)?
      (input_declaration[gather] SEMICOLON)+
      END_VAR
    ;

input_declaration[VariableScope gather]
    : var_init_decl[gather]
    | edge_declaration[gather]
    ;

edge_declaration[VariableScope gather]
    : identifier_list COLON BOOL (R_EDGE | F_EDGE)
    {   $gather.addBoolEdge($identifier_list.ctx.ast, $R_EDGE!=null); }
    ;

var_init_decl[VariableScope gather]
    : var1_init_decl              [gather]
    | array_var_init_decl         [gather]
    | structured_var_init_decl    [gather]
    | fb_name_decl                [gather]
    | string_var_declaration      [gather]
    ;

var1_init_decl[VariableScope gather]
    : identifier_list COLON
        ( simple_spec_init
          {$gather.create($identifier_list.ctx.ast, $simple_spec_init.ctx.ast);}
        | subrange_spec_init
          {$gather.create($identifier_list.ctx.ast, $subrange_spec_init.ctx.ast);}
        | cast
          {$gather.create($identifier_list.ctx.ast, $cast.ctx.ast);}
        )
    ;


array_var_init_decl[VariableScope gather]
    : identifier_list COLON array_spec_init
    {$gather.create($identifier_list.ctx.ast, $array_spec_init.ctx.ast);}
    ;

structured_var_init_decl[VariableScope gather]
    : identifier_list COLON initialized_structure
    {$gather.create($identifier_list.ctx.ast, $initialized_structure.ctx.ast);}
    ;

fb_name_decl[VariableScope gather]
    : identifier_list COLON IDENTIFIER
      ( ASSIGN structure_initialization)?
      { $gather.createFBName($identifier_list.ctx.ast, $IDENTIFIER.text, $structure_initialization.ctx.ast);}
    ;


output_declarations[VariableScope gather]
    @init{$gather.clear(Variable.OUTPUT);}
    : VAR_OUTPUT ( RETAIN {$gather.mix(Variable.RETAIN);}
                 | NON_RETAIN)?
      (var_init_decl[gather] SEMICOLON)+
      END_VAR
    ;

input_output_declarations[VariableScope gather]
    @init{$gather.clear(Variable.INOUT);}
    : VAR_IN_OUT
      (var_declaration[gather] SEMICOLON)+
      END_VAR
    ;

var_declaration[VariableScope gather]
    : temp_var_decl[gather]
    | fb_name_decl[gather]
    ;

temp_var_decl[VariableScope gather]
    : var1_declaration                  [gather]
    | array_var_declaration             [gather]
    | structured_var_declaration        [gather]
    | string_var_declaration            [gather]
    ;

var1_declaration[VariableScope gather]
    : identifier_list COLON
            ( simple_specification
                 { $gather.create($identifier_list.ctx.ast, $simple_specification.text); }

            | subrange_specification
                 { $gather.create($identifier_list.ctx.ast, $subrange_specification.ctx.ast); }

            | enumerated_specification
                 { $gather.create($identifier_list.ctx.ast, $enumerated_specification.ctx.ast); }
            )
    ;

array_var_declaration[VariableScope gather]
    : identifier_list COLON array_specification
    ;

structured_var_declaration[VariableScope gather]
    : identifier_list COLON IDENTIFIER
    ;

var_declarations[VariableScope gather]
    : VAR CONSTANT? (var_init_decl[gather] SEMICOLON)+
      END_VAR
    ;

retentive_var_declarations[VariableScope gather]
    : VAR RETAIN  (var_init_decl[gather] SEMICOLON)+
      END_VAR
    ;

located_var_declarations[VariableScope gather]
    @init { $gather.clear(Variable.LOCATED); }
    : VAR ( CONSTANT   {$gather.mix(Variable.CONSTANT);}
          | RETAIN     {$gather.mix(Variable.RETAIN);}
          | NON_RETAIN
          )?
      (located_var_decl[gather] SEMICOLON)+
      END_VAR
    ;

located_var_decl[VariableScope gather]
    : (IDENTIFIER)? location COLON located_var_spec_init[gather]
    ;

external_var_declarations[VariableScope gather]
    @init{ $gather.clear(Variable.EXTERNAL);}
    : VAR_EXTERNAL (CONSTANT {$gather.mix(Variable.CONSTANT);})?
      (external_declaration[gather] SEMICOLON)+
      END_VAR
    ;

external_declaration[VariableScope gather]
    :IDENTIFIER COLON
      ( simple_specification 
      | subrange_specification
      | enumerated_specification 
      | array_specification 
      | IDENTIFIER
      )
    ;

global_var_declarations[VariableScope gather]
@init {$gather.push(Variable.GLOBAL);}
    : VAR_GLOBAL    ( CONSTANT {$gather.mix(Variable.CONSTANT);}
                    | RETAIN {$gather.mix(Variable.RETAIN);}
                    )?
        (global_var_decl[gather] SEMICOLON)+
      END_VAR
    ;

global_var_decl[VariableScope gather]
    : global_var_spec[gather] COLON
      ( located_var_spec_init[gather] )?
    ;

global_var_spec[VariableScope gather]
    : identifier_list 
    | (IDENTIFIER)? location
    {
        //TODO
    }
    ;

located_var_spec_init[VariableScope gather]
    : simple_spec_init
    | subrange_spec_init
    | cast
    | array_spec_init
    | initialized_structure
    | string_var_declaration[gather]
    ;

location 
    : AT direct_variable
    ;

identifier_list
    locals [ List<String> ast = new ArrayList<>(); ]
    : names+=IDENTIFIER (COMMA names+=IDENTIFIER)*
    {
        for(Token t : $names)
            $ast.add(t.getText());
    }
    ;

string_var_declaration[VariableScope gather]
    locals [ ScalarValue<? extends AnyInt,Integer> length = null, ScalarValue<? extends IECString,String> def = null ]
    : identifier_list COLON type=(WSTRING|STRING)
      (LBRACKET integer {$length=$integer.ctx.ast;} RBRACKET)?
      (ASSIGN string {$def=$string.ctx.ast;} )?
      { $gather.create($identifier_list.ctx.ast, $length, $def); }
    ;



incompl_located_var_declarations[VariableScope gather]
    @init{ $gather.clear(Variable.RETAIN);}
    : VAR (RETAIN|NON_RETAIN)?
      incompl_located_var_decl SEMICOLON 
      (incompl_located_var_decl SEMICOLON)* 
      END_VAR
    ;

incompl_located_var_decl 
    :IDENTIFIER INCOMPL_LOCATION_LITERAL COLON var_spec
    ;

var_spec 
    : simple_specification 
    | subrange_specification 
    | enumerated_specification 
    | array_specification 
    | IDENTIFIER
    | (STRING | WSTRING) (LBRACKET integer RBRACKET)?
    ;

function_declaration
    locals [ FunctionDeclaration ast = new FunctionDeclaration() ]
    : FUNCTION name=IDENTIFIER COLON
        ( elementary_type_name {$ast.setReturnType($elementary_type_name.text);}
        | IDENTIFIER           {$ast.setReturnType($IDENTIFIER.text);}
        )
        ( io_var_declarations[$ast.getScope()]
        | function_var_decls[$ast.getScope()]
        )*

        body=statement_list

      END_FUNCTION
      {
        $ast.setFunctionName($name.text);
      }
    ;
     
io_var_declarations[VariableScope gather]
    : input_declarations[gather]
    | output_declarations[gather]
    | input_output_declarations[gather]
    ;

function_var_decls[VariableScope gather]
    @init {$gather.push(Variable.LOCAL);}
    : VAR
      ( CONSTANT  {$gather.mix(Variable.CONSTANT);} )?
      (var2_init_decl[gather] SEMICOLON)+
      END_VAR
    {$gather.pop();}
    ;

/*ladder_diagram
                | function_block_diagram
                | instruction_list
                | statement_list /*| <other languages>;*/

var2_init_decl[VariableScope gather]
    : var1_init_decl            [gather]
    | array_var_init_decl       [gather]
    | structured_var_init_decl  [gather]
    | string_var_declaration    [gather]
    ;


function_block_declaration
    locals [ FunctionBlockDeclaration ast = new FunctionBlockDeclaration(); ]
    : FUNCTION_BLOCK name=IDENTIFIER
      ( io_var_declarations[$ast.getScope()]
      | other_var_declarations[$ast.getScope()] )*
      body=statement_list
      END_FUNCTION_BLOCK
      {
        $ast.setFunctionBlockName($name.text);
        $ast.setFunctionBody($body.ctx.ast);
      }
    ;

other_var_declarations[VariableScope gather]
    : external_var_declarations         [gather]
    | var_declarations                  [gather]
    | retentive_var_declarations        [gather]
    | non_retentive_var_declarations    [gather]
    | temp_var_decls                    [gather]
    | incompl_located_var_declarations  [gather]
    ;

temp_var_decls[VariableScope gather]
    @init { $gather.clear(Variable.TEMP); }
    : VAR_TEMP
      (temp_var_decl[gather] SEMICOLON)+
      END_VAR
    { $gather.pop(); }
    ;

non_retentive_var_declarations [VariableScope gather]
    @init { $gather.clear(); }
    : VAR NON_RETAIN
      (var_init_decl[gather] SEMICOLON)+
      END_VAR
    { $gather.pop(); }
    ;


program_declaration
    locals [ ProgramDeclaration ast = new ProgramDeclaration(); ]
    : PROGRAM name=IDENTIFIER
      ( io_var_declarations[$ast.getScope()]
      | other_var_declarations[$ast.getScope()]
      | located_var_declarations[$ast.getScope()]
      | program_access_decls
      )*
      body=statement_list
      END_PROGRAM
      {
        $ast.setProgramName($name.text);
        $ast.setProgramBody($body.ctx.ast);

      }
    ;

program_access_decls    
    : VAR_ACCESS
        program_access_decl SEMICOLON
        (program_access_decl SEMICOLON )*
      END_VAR
    ;

program_access_decl 
    :IDENTIFIER COLON symbolic_variable COLON
        non_generic_type_name (direction)?
    ;


configuration_declaration
    locals [ ConfigurationDeclaration ast = new ConfigurationDeclaration(); ]
    : CONFIGURATION name=IDENTIFIER
        (global_var_declarations[$ast.getScope()])?
        ( single_resource_declaration
        | (resource_declaration (resource_declaration)*)
        )
        (access_declarations)?
        (instance_specific_initializations[$ast.getScope()])?
      END_CONFIGURATION
    ;

resource_declaration
    locals [ ResourceDeclaration ast = new ResourceDeclaration(); ]
    : RESOURCE IDENTIFIER ON IDENTIFIER
      (global_var_declarations[$ast.getScope()])?
      single_resource_declaration
      END_RESOURCE
    ;

single_resource_declaration 
    : (task_configuration SEMICOLON)*
      program_configuration SEMICOLON
      (program_configuration SEMICOLON)*
    ;


access_declarations 
    : VAR_ACCESS
      access_declaration SEMICOLON
      (access_declaration SEMICOLON)*
      END_VAR
    ;

access_declaration 
    :IDENTIFIER COLON access_path COLON
      non_generic_type_name (direction)?;

access_path 
    : (IDENTIFIER DOT)? direct_variable
    | (IDENTIFIER DOT)* symbolic_variable
    ;

global_var_reference 
    : (IDENTIFIER DOT)? IDENTIFIER (DOT IDENTIFIER)?
    ;

program_output_reference 
    : IDENTIFIER DOT symbolic_variable
    ;


direction 
    : READ_WRITE 
    | READ_ONLY
    ;

task_configuration 
    : TASK IDENTIFIER task_initialization
    ;


task_initialization 
    : LPAREN (SINGLE ASSIGN data_source COMMA)?
             (INTERVAL ASSIGN data_source COMMA)?
             PRIORITY ASSIGN integer 
      RPAREN
    ;

data_source 
    : constant
    | global_var_reference
    | program_output_reference
    | direct_variable
    ;

program_configuration   
    : PROGRAM (RETAIN | NON_RETAIN)?
        IDENTIFIER (WITH IDENTIFIER)? COLON IDENTIFIER
        ( LPAREN prog_conf_elements RPAREN)?
    ;

prog_conf_elements 
    : prog_conf_element (COMMA prog_conf_element)*
    ;

prog_conf_element 
    : fb_task 
    | prog_cnxn
    ;

fb_task 
    : IDENTIFIER WITH IDENTIFIER
    ;

prog_cnxn 
    : symbolic_variable ASSIGN prog_data_source
    | symbolic_variable RIGHT_ARROW data_sink
    ;

prog_data_source 
    : constant
    | global_var_reference
    | direct_variable
    ;

data_sink   
    : global_var_reference
    | direct_variable
    ;

instance_specific_initializations[VariableScope gather]
    : VAR_CONFIG
        (instance_specific_init[gather] SEMICOLON)+
      END_VAR
    ;

instance_specific_init[VariableScope gather]
    : IDENTIFIER DOT (IDENTIFIER DOT)+
      ((IDENTIFIER (location)? COLON located_var_spec_init[gather]) |
                (IDENTIFIER COLON IDENTIFIER ASSIGN
                   structure_initialization))
    ;


expression
    locals [
        Expression ast = new Expression();
    ]

    : MINUS sub=expression
      {
        $ast = new UnaryExpression(
                    UnaryExpression.Operator.MINUS,
                    $sub.ctx.ast);
      }
    | NOT sub=expression
      {
        $ast = new UnaryExpression(
                    UnaryExpression.Operator.NEGATE,
                    $sub.ctx.ast);
      }
    | LPAREN sub=expression RPAREN
      {
        $ast = $sub.ctx.ast;
      }
    | left=expression   op=POWER                right=expression
    { $ast = new BinaryExpression($left.ctx.ast, $right.ctx.ast, BinaryExpression.Operator.POWER); }

    |<assoc=right>
      left=expression   op=(MOD|DIV)            right=expression
    { $ast = new BinaryExpression($left.ctx.ast, $right.ctx.ast, $op.text); }

    | left=expression   op=MULT                 right=expression
    { $ast = new BinaryExpression($left.ctx.ast, $right.ctx.ast, BinaryExpression.Operator.POWER); }

    | left=expression   op=(PLUS|MINUS)         right=expression
    { $ast = new BinaryExpression($left.ctx.ast, $right.ctx.ast, $op.text); }

    | left=expression   op=( LESS_THAN
                           | GREATER_THAN
                           | GREATER_EQUALS
                           | LESS_EQUALS )      right=expression
    { $ast = new BinaryExpression($left.ctx.ast, $right.ctx.ast, $op.text); }

    | left=expression   op= ( EQUALS
                            | NOT_EQUALS )      right=expression
    { $ast = new BinaryExpression($left.ctx.ast, $right.ctx.ast, $op.text); }

    | left=expression   op=AND                  right=expression
    { $ast = new BinaryExpression($left.ctx.ast, $right.ctx.ast, BinaryExpression.Operator.AND); }

    | left=expression   op=OR                   right=expression
    { $ast = new BinaryExpression($left.ctx.ast, $right.ctx.ast, BinaryExpression.Operator.OR); }

    | left=expression   op=XOR                  right=expression
    { $ast = new BinaryExpression($left.ctx.ast, $right.ctx.ast, BinaryExpression.Operator.XOR); }

    //BASE CASE
    | primary_expression
    { $ast = $primary_expression.ctx.ast; }
    ;

primary_expression
    locals [ Expression ast;]
    : constant      { $ast = $constant.ctx.ast; }
    | variable      { $ast = $variable.ctx.ast; }
    | functioncall  { $ast = $functioncall.ctx.ast; }
    ;

functioncall
    locals [ FunctionCall ast = new FunctionCall(); ]
    :   IDENTIFIER
        LPAREN
            param_assignment (COMMA param_assignment)*
        RPAREN
    {
        $ast.setFunctionName($IDENTIFIER.text);
    }
    ;

param_assignment
    : (id=IDENTIFIER ASSIGN)? expression
      {
        $functioncall::ast.addInputParameter($id.text, $expression.ctx.ast);
      }
    | IDENTIFIER ARROW_RIGHT variable
      {
        $functioncall::ast.addOutputParameter($IDENTIFIER.text, $variable.ctx.ast);
      }
    ;

statement_list
    locals [ StatementList ast = new StatementList(); ]
    : statement SEMICOLON
      { $ast.add($statement.ctx.ast); }
      (statement SEMICOLON  { $ast.add($statement.ctx.ast);}  )*
    ;

statement
    locals [ Statement ast = new Statement(); ]
    : /*empty*/
    { } //empty statement
    |  assignment_statement
    { $ast = $assignment_statement.ctx.ast; }
    | subprogram_control_statement
    { $ast = $subprogram_control_statement.ctx.ast; }

    | selection_statement
    { $ast = $selection_statement.ctx.ast; }

    | iteration_statement
    { $ast = $iteration_statement.ctx.ast; }
    ;

assignment_statement
     locals [ Statement ast; ]
    : variable ASSIGN expression
    {
        $ast = new AssignmentStatement($variable.ctx.ast, $expression.ctx.ast);
    }
    ;

subprogram_control_statement
     locals [ Statement ast = new Statement(); ]
    : functioncall {$ast = new FunctionCallStatement($functioncall.ctx.ast);}
    | RETURN       {$ast = new ReturnStatement();}
    ;

selection_statement
     locals [ Statement ast = new Statement(); ]
    : if_statement   {$ast = $if_statement.ctx.ast;}
    | case_statement {$ast = $case_statement.ctx.ast;}
    ;

if_statement
     locals [ IfStatement ast = new IfStatement(); ]
    : IF cond=expression THEN thenlist=statement_list
        { $ast.addGuardedCommand($cond.ctx.ast, $thenlist.ctx.ast); }
      ( ELSEIF cond=expression THEN thenlist=statement_list
        { $ast.addGuardedCommand($cond.ctx.ast, $thenlist.ctx.ast); }
      )*
      (ELSE elselist=statement_list)?
      END_IF
      {
        if($ELSE.text != null)
            $ast.setElseBranch($elselist.ctx.ast);
      }
    ;

case_statement
     locals [ CaseStatement ast = new CaseStatement(); ]
    : CASE cond=expression OF
      case_element (case_element)* (ELSE statement_list)+
      END_CASE
      {
        $ast.setExpression($cond.ctx.ast);
      }
    ;

case_element
    locals [ CaseStatement.Case cs = new CaseStatement.Case(); ]
    : case_list COLON statement_list
    {
        $cs.setStatements($statement_list.ctx.ast);
        $case_statement::ast.addCase($cs);
    }
    ;

case_list 
    : case_list_element (COMMA case_list_element)*
    ;

case_list_element 
    : subrange   { $case_element::cs.addCondition(new CaseConditions.Range($subrange.ctx.ast)); }
    | integer    { $case_element::cs.addCondition(
                        new CaseConditions.IntegerCondition($integer.ctx.ast)); }
    | cast       { $case_element::cs.addCondition(
                    new CaseConditions.Enumeration($cast.ctx.ast)); }
    | IDENTIFIER { $case_element::cs.addCondition(
                    new CaseConditions.Enumeration($IDENTIFIER.text)); }
    ;

iteration_statement
    locals [ Statement ast = new Statement(); ]
    : for_statement    { $ast = $for_statement.ctx.ast; }
    | while_statement  { $ast = $while_statement.ctx.ast; }
    | repeat_statement { $ast = $repeat_statement.ctx.ast; }
    | exit_statement   { $ast = $exit_statement.ctx.ast; }
    ;

for_statement
    locals [ ForStatement ast = new ForStatement(); ]
    : FOR var=IDENTIFIER ASSIGN for_list DO
        statement_list
      END_FOR
      {
        $ast.setVariable($var.text);
        $ast.setStatements($statement_list.ctx.ast);
      }
    ;

for_list 
    : begin=expression TO end=expression (BY step=expression)?
      { $for_statement::ast.setStep($step.ctx.ast);
        $for_statement::ast.setStop($end.ctx.ast);
        $for_statement::ast.setStart($begin.ctx.ast);
      }
    ;

while_statement
    locals [WhileStatement ast = new WhileStatement();]
    : WHILE expression DO statement_list END_WHILE
    {
        $ast.setCondition($expression.ctx.ast);
        $ast.setStatements($statement_list.ctx.ast);
    }
    ;

repeat_statement
    locals [RepeatStatement ast = new RepeatStatement();]
    : REPEAT statement_list UNTIL expression END_REPEAT
     {
            $ast.setCondition($expression.ctx.ast);
            $ast.setStatements($statement_list.ctx.ast);
     }
    ;

exit_statement
     locals [ExitStatement ast = new ExitStatement();]
    : EXIT
    ;