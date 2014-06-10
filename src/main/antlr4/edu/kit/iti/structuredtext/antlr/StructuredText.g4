grammar StructuredText;

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
WS                      : [ \r\t\n] -> skip;
COMMENT                 : '(*' ~[] '*)';


IDENTIFIER              : [a-zA-Z] [a-zA-Z0-9_]*;
CAST_LITERAL                    : IDENTIFIER '#';

/************************************************/

start : library_element_declaration+;

library_element_name    
    : data_type_name
    | IDENTIFIER
    ;

library_element_declaration 
    : data_type_declaration
    | function_declaration
    | function_block_declaration
    | program_declaration
    | configuration_declaration
    ;

constant    
    : integer
    | real
    | string
    | time
    | timeofday
    | date
    | FALSE
    | TRUE
    | datetime
    | cast
    ;

cast : CAST_LITERAL;
integer  : INTEGER_LITERAL;
real     : REAL_LITERAL;
string   : WSTRING_LITERAL | STRING_LITERAL;
time     : TIME_LITERAL;
timeofday: TOD_LITERAL;
date     : DATE_LITERAL;
datetime : DATETIME;

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
    : IDENTIFIER COLON simple_spec_init
    ;

simple_spec_init
    : simple_specification (ASSIGN constant)?
    ;

simple_specification
    : elementary_type_name
    | IDENTIFIER
    ;

subrange_type_declaration
    :IDENTIFIER COLON subrange_spec_init
    ;

subrange_spec_init 
    : subrange_specification (ASSIGN integer)?
    ;

subrange_specification 
    : integer_type_name LPAREN subrange RPAREN 
    | IDENTIFIER
    ;

subrange 
    : MINUS? integer RANGE  MINUS? integer
    ;

enumerated_type_declaration 
    :IDENTIFIER COLON enumerated_specification (ASSIGN IDENTIFIER|CAST_LITERAL )?
    ;


/* removed: casting of identifiers */
enumerated_specification 
    : (LPAREN IDENTIFIER (COMMA IDENTIFIER)* RPAREN)
    | IDENTIFIER
    ;

array_type_declaration 
    :IDENTIFIER COLON array_spec_init
    ;

array_spec_init 
    : array_specification (ASSIGN array_initialization)?
    ;

array_specification 
    :IDENTIFIER
    | ARRAY LBRACKET subrange (COMMA subrange)* RBRACKET
        OF non_generic_type_name
    ;

array_initialization
    : LBRACKET array_initial_elements 
      (COMMA array_initial_elements)* RBRACKET
    ;

array_initial_elements 
    : array_initial_element 
    | integer LPAREN(array_initial_element)? RPAREN
    ;

array_initial_element 
    : constant
    | structure_initialization
    | array_initialization
    ;

structure_type_declaration 
    :IDENTIFIER COLON structure_specification
    ;

structure_specification
    : structure_declaration 
    | initialized_structure
    ;

initialized_structure 
    :IDENTIFIER (ASSIGN structure_initialization)?
    ;

structure_declaration 
    : STRUCT structure_element_declaration SEMICOLON 
      (structure_element_declaration SEMICOLON)* 
      END_STRUCT
    ;

structure_element_declaration 
    :IDENTIFIER COLON
        ( simple_spec_init
        | subrange_spec_init
        | cast
        | array_spec_init
        | initialized_structure)
    ;    


structure_initialization 
    : LPAREN structure_element_initialization
        (COMMA structure_element_initialization)* RPAREN
    ;

structure_element_initialization 
    :IDENTIFIER ASSIGN
        ( constant
        | array_initialization
        | structure_initialization )
    ;


string_type_declaration 
    :IDENTIFIER COLON
      (STRING|WSTRING) 
      ( LBRACKET integer RBRACKET)?
      (ASSIGN (WSTRING_LITERAL|STRING_LITERAL))?
    ;

variable 
    : direct_variable
    | symbolic_variable
    ;

symbolic_variable 
    : IDENTIFIER subscript_list? ( DOT symbolic_variable)?
    ;

subscript_list
    : LBRACKET expression (COMMA expression)* RBRACKET
    ;


direct_variable 
    : DIRECT_VARIABLE_LITERAL
    ;

DIRECT_VARIABLE_LITERAL: '%' [IQM] [XBWDL]? FIXED_POINT;


input_declarations 
    : VAR_INPUT (RETAIN | NON_RETAIN)? 
      (input_declaration SEMICOLON)+      
      END_VAR
    ;

input_declaration 
    : var_init_decl 
    | edge_declaration
    ;

edge_declaration 
    : identifier_list COLON BOOL (R_EDGE | F_EDGE)
    ;

var_init_decl 
    : var1_init_decl 
    | array_var_init_decl 
    | structured_var_init_decl 
    | fb_name_decl 
    | string_var_declaration
    ;

var1_init_decl 
    : identifier_list COLON ( simple_spec_init | subrange_spec_init | cast )
    ;


array_var_init_decl 
    : identifier_list COLON array_spec_init
    ;

structured_var_init_decl 
    : identifier_list COLON initialized_structure
    ;

fb_name_decl 
    : identifier_list COLON IDENTIFIER
      ( ASSIGN structure_initialization )?
    ;


output_declarations 
    : VAR_OUTPUT (RETAIN | NON_RETAIN)? var_init_decl SEMICOLON
      (var_init_decl SEMICOLON)* 
      END_VAR
    ;

input_output_declarations 
    : VAR_IN_OUT var_declaration SEMICOLON 
      (var_declaration SEMICOLON)* 
      END_VAR
    ;

var_declaration 
    : temp_var_decl 
    | fb_name_decl
    ;

temp_var_decl 
    : var1_declaration 
    | array_var_declaration 
    | structured_var_declaration 
    | string_var_declaration
    ;

var1_declaration 
    : identifier_list COLON ( simple_specification 
                      | subrange_specification 
                      | enumerated_specification)
    ;

array_var_declaration 
    : identifier_list COLON array_specification
    ;

structured_var_declaration 
    : identifier_list COLON IDENTIFIER
    ;

var_declarations 
    : VAR CONSTANT? var_init_decl SEMICOLON ((var_init_decl SEMICOLON))*
      END_VAR
    ;

retentive_var_declarations 
    : VAR RETAIN var_init_decl SEMICOLON (var_init_decl SEMICOLON)*
      END_VAR
    ;

located_var_declarations 
    : VAR (CONSTANT | RETAIN | NON_RETAIN)?
      located_var_decl SEMICOLON (located_var_decl SEMICOLON)* 
      END_VAR
    ;

located_var_decl : (IDENTIFIER)? location COLON located_var_spec_init;

external_var_declarations 
    : VAR_EXTERNAL CONSTANT? external_declaration SEMICOLON 
      (external_declaration SEMICOLON)*
      END_VAR
    ;

external_declaration 
    :IDENTIFIER COLON
      ( simple_specification 
      | subrange_specification
      | enumerated_specification 
      | array_specification 
      | IDENTIFIER
      )
    ;



global_var_declarations 
    : VAR_GLOBAL (CONSTANT | RETAIN)? global_var_decl SEMICOLON 
      (global_var_decl SEMICOLON)* END_VAR
    ;

global_var_decl 
    : global_var_spec COLON 
      ( located_var_spec_init )?
    ;

global_var_spec 
    : identifier_list 
    | (IDENTIFIER)? location
    ;

located_var_spec_init   
    : simple_spec_init
    | subrange_spec_init
    | cast
    | array_spec_init
    | initialized_structure
    | string_var_declaration
    ;

location 
    : AT direct_variable
    ;

identifier_list
    : IDENTIFIER (COMMA IDENTIFIER)*
    ;

string_var_declaration 
    : identifier_list COLON (WSTRING|STRING)
      (LBRACKET integer RBRACKET)?
      (ASSIGN (WSTRING_LITERAL | STRING_LITERAL))?
    ;



incompl_located_var_declarations 
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
    : FUNCTION IDENTIFIER COLON
        (elementary_type_name | IDENTIFIER)
        (io_var_declarations | function_var_decls )*
        function_body
      END_FUNCTION
    ;
     
io_var_declarations 
    : input_declarations
    | output_declarations
    | input_output_declarations
    ;

function_var_decls 
    : VAR (CONSTANT)? var2_init_decl SEMICOLON 
      (var2_init_decl SEMICOLON)* 
      END_VAR
    ;

function_body   
    : statement_list
    ;

/*ladder_diagram
                | function_block_diagram
                | instruction_list
                | statement_list /*| <other languages>;*/

var2_init_decl 
    : var1_init_decl 
    | array_var_init_decl 
    | structured_var_init_decl 
    | string_var_declaration
    ;


function_block_declaration 
    : FUNCTION_BLOCK name=IDENTIFIER
      ( io_var_declarations | other_var_declarations )*
      body=function_block_body
      END_FUNCTION_BLOCK
    ;

other_var_declarations  
    : external_var_declarations
    | var_declarations
    | retentive_var_declarations
    | non_retentive_var_declarations
    | temp_var_decls
    | incompl_located_var_declarations
    ;

temp_var_decls  
    : VAR_TEMP
      temp_var_decl SEMICOLON (temp_var_decl SEMICOLON)*
      END_VAR
    ;

non_retentive_var_declarations 
    : VAR NON_RETAIN
      var_init_decl SEMICOLON
      (var_init_decl SEMICOLON)*
      END_VAR
    ;

function_block_body 
    : statement_list
    ;
                        
/* ladder_diagram
| function_block_diagram
| instruction_list
| statement_list
/*| <other languages>
;*/

program_declaration     
    : PROGRAM name=IDENTIFIER
      ( io_var_declarations
      | other_var_declarations
      | located_var_declarations
      | program_access_decls
      )*
      function_block_body
      END_PROGRAM
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
    : CONFIGURATION name=IDENTIFIER
        (global_var_declarations)?
        ( single_resource_declaration
        | (resource_declaration (resource_declaration)*)
        )
        (access_declarations)?
        (instance_specific_initializations)?
      END_CONFIGURATION
    ;

resource_declaration 
    : RESOURCE IDENTIFIER ON IDENTIFIER
      (global_var_declarations)?
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

instance_specific_initializations 
    : VAR_CONFIG
        instance_specific_init SEMICOLON
        (instance_specific_init SEMICOLON)*
      END_VAR
    ;

instance_specific_init 
    : IDENTIFIER DOT IDENTIFIER DOT (IDENTIFIER DOT)*
      ((IDENTIFIER (location)? COLON located_var_spec_init) |
                (IDENTIFIER COLON IDENTIFIER ASSIGN
                   structure_initialization))
    ;


expression
    : unary_operator expression
    | LPAREN expression RPAREN
    | expression POWER expression
    |<assoc=right>
      expression (MOD|DIV) expression
    | expression (MULT) expression
    | expression (PLUS|MINUS) expression
    | expression comparison_operator expression
    | expression (EQUALS | NOT_EQUALS) expression
    | expression AND expression
    | expression OR expression
    | expression XOR expression
    | primary_expression
    ;

comparison_operator 
    : LESS_THAN
    | GREATER_THAN
    | GREATER_EQUALS
    | LESS_EQUALS
    ;

unary_operator
    : MINUS
    | NOT
    ;
    
primary_expression  
    : constant
    | variable
    | functioncall
    ;

functioncall
    :   IDENTIFIER LPAREN param_assignment (COMMA param_assignment)* RPAREN
    ;

statement_list 
    : statement SEMICOLON (statement SEMICOLON)*
    ;

statement 
    : /*empty*/
    | assignment_statement
    | subprogram_control_statement
    | selection_statement
    | iteration_statement
    ;

assignment_statement 
    : variable ASSIGN expression
    ;

subprogram_control_statement    
    : fb_invocation
    | RETURN
    ;

fb_invocation 
    : IDENTIFIER LPAREN (param_assignment (COMMA param_assignment)*)+ RPAREN
    ;

param_assignment 
    : (IDENTIFIER ASSIGN)+ expression
    | IDENTIFIER ARROW_RIGHT variable
    ;

selection_statement 
    : if_statement
    | case_statement
    ;

if_statement
    : IF expression THEN statement_list
      (ELSEIF expression THEN statement_list)*
      (ELSE statement_list)?
      END_IF
    ;

case_statement  
    : CASE expression OF
      case_element (case_element)* (ELSE statement_list)+
      END_CASE
    ;

case_element 
    : case_list COLON statement_list
    ;

case_list 
    : case_list_element (COMMA case_list_element)*
    ;

case_list_element 
    : subrange 
    | integer
    | cast
    | IDENTIFIER
    ;

iteration_statement 
    : for_statement
    | while_statement
    | repeat_statement
    | exit_statement
    ;

for_statement   
    : FOR IDENTIFIER ASSIGN for_list DO
        statement_list
      END_FOR
    ;

control_variable 
    : IDENTIFIER
    ;

for_list 
    : expression TO expression (BY expression)+
    ;

while_statement 
    : WHILE expression DO statement_list END_WHILE
    ;

repeat_statement 
    : REPEAT statement_list UNTIL expression END_REPEAT
    ;

exit_statement 
    : EXIT
    ;