grammar StructuredText;

fragment
FIXED_POINT                             : DIGIT+ (DOT DIGIT+)?;

fragment
LETTER                                  : 'a'..'z' | 'A'..'Z';

fragment
DIGIT                                   : '0'..'9';

fragment
HEX_DIGIT                               : DIGIT | 'A'..'F';

fragment
OCTAL_DIGIT                             : '0'..'7';

/******
 * Literal
 */

CHARACTER_LITERAL_2BYTE : '$' HEX_DIGIT HEX_DIGIT;
CHARACTER_LITERAL_4BYTE : '$' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT;

UINTEGER_LITERAL        : DIGIT (DIGIT |'_')*;
//SINTEGER_LITERAL        : (PLUS|MINUS) DIGIT (DIGIT |'_')*;
OCTAL_LITERAL           : '8#' OCTAL_DIGIT (OCTAL_DIGIT |'_')*;
BINARY_LITERAL          : '2#' BIT (BIT | '_')*;
HEX_LITERAL             : '16#' HEX_DIGIT ('_' | HEX_DIGIT)*;

REAL_LITERAL            : FIXED_POINT ([eE] DIGIT+)?;

DATE_LIERAL             : UINTEGER_LITERAL MINUS UINTEGER_LITERAL MINUS UINTEGER_LITERAL;

INCOMPL_LOCATION_LITERAL: 'AT%'[IQM]'*';

DAY_LITERAL             : FIXED_POINT 'd';
HOURS_LITERAL           : FIXED_POINT 'h';
MINUTES_LITERAL         : FIXED_POINT 'm';
SECONDS_LITERAL         : FIXED_POINT 's';
MILLIESECONDS_LITERAL   : FIXED_POINT 'ms';

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
 * region Casts
 */

BOOLH                   : 'BOOL#';
BYTEH                   : 'BYTE#';
DWORDH                  : 'DWORD#';
LREALH                  : 'LREALH';
REALH                   : 'REALH';
TIME_OF_DAYH            : 'TIME_OF_DAY'|'TOD' '#';
TODH                    : 'TOD#';
TIMEH                   : 'TIME'|'T' '#';
WORDH                   : 'WORD#';
DINTH                   : DINT '#';
INTH                    : INT '#';
LINTH                   : LINT '#';
LWORDH                  : LWORD '#';
SINTH                   : SINT '#';
UDINTH                  : UDINT '#';
UINTH                   : UINT '#';
ULINTH                  : ULINT '#';
USINTH                  : USINT '#';
DATE_AND_TIMEH          : 'DATE_AND_TIME'|'DT' '#';


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
DATEH                   : 'DATE#' | 'D#';
DO                      : 'DO';
DOLLAR_DQUOTE           : '$"';
DOT                     : '.';
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
RANGE                   :  '..';
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
UNDERSCORE              :'_';
WS                      : [ \r\t\n] -> skip;

//PERCENT               :'%';
DOLLAR_SQUOTE           :'$\'';
DOLLAR_DOLLAR           : '$$';
DOLLAR_L                : '$L';
DOLLAR_N                : '$N';
DOLLAR_P                : '$P';
DOLLAR_R                : '$R';
DOLLAR_T                : '$T';
DOLLAR_l                : '$l';
DOLLAR_n                : '$n';
DOLLAR_p                : '$p';
DOLLAR_r                : '$r';
DOLLAR_t                : '$t';


IDENTIFIER              : [a-zA-Z] [a-zA-Z0-9_]*;
CAST                    : IDENTIFIER '#';

/************************************************/

start : library_element_declaration+;

library_element_name    
    : data_type_name
    | function_block_type_name
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
    : numeric_literal 
    | character_string 
    | time_literal
    | bit_string_literal 
    | boolean_literal
    ;

numeric_literal 
    : integer_literal 
    | real_literal
    ;

integer_literal 
    : integer_type_cast?
      ( UINTEGER_LITERAL
      | BINARY_LITERAL
      | OCTAL_LITERAL
      | HEX_LITERAL)
    ;

integer_type_cast
    : signed_integer_type_cast
    | unsigned_integer_type_cast
    ;

signed_integer_type_cast
    : SINTH
    | INTH
    | DINTH
    | LINTH
    ;

unsigned_integer_type_cast
    : USINTH
    | UINTH
    | UDINTH
    | ULINTH
    ;

integer
    : UINTEGER_LITERAL
    | MINUS UINTEGER_LITERAL
    ;

real_literal 
    : real_type_cast? MINUS? REAL_LITERAL
    ;

bit_string_literal
    : (BYTEH | WORDH | DWORDH | LWORDH)?
      ( UINTEGER_LITERAL
      | BINARY_LITERAL
      | OCTAL_LITERAL
      | HEX_LITERAL )
    ;

boolean_literal     
    : BOOLH? BIT
    | TRUE 
    | FALSE
    ;

character_string
    : single_byte_character_string
    | double_byte_character_string
    ;

single_byte_character_string 
    : SQUOTE (single_byte_character_representation)* SQUOTE
    ;

double_byte_character_string 
    : DQUOTE (double_byte_character_representation)* DQUOTE
    ;

single_byte_character_representation
    : common_character_representation
    | DOLLAR_SQUOTE 
    | DQUOTE
    | CHARACTER_LITERAL_2BYTE
    ;

double_byte_character_representation 
    : common_character_representation
    | DOLLAR_DQUOTE 
    | SQUOTE
    | CHARACTER_LITERAL_4BYTE
    ;

common_character_representation 
    : ~( DQUOTE | SQUOTE | DOLLAR)
    | DOLLAR_DOLLAR
    | DOLLAR_L
    | DOLLAR_N
    | DOLLAR_P
    | DOLLAR_R
    | DOLLAR_T
    | DOLLAR_l
    | DOLLAR_n
    | DOLLAR_p
    | DOLLAR_r
    | DOLLAR_t
    ;

time_literal 
    : duration 
    | time_of_day
    ;

duration 
    : TIMEH (MINUS)? interval
    ;

interval 
    : days 
    | hours 
    | minutes 
    | seconds 
    | milliseconds
    ;


days 
    : DAY_LITERAL (UNDERSCORE)? hours
    ;

hours 
    : HOURS_LITERAL UNDERSCORE? minutes
    ;

minutes 
    : MINUTES_LITERAL UNDERSCORE? seconds
    ;

seconds 
    : SECONDS_LITERAL UNDERSCORE? milliseconds
    ;

milliseconds 
    : MILLIESECONDS_LITERAL
    ;

time_of_day 
    : TIME_OF_DAYH daytime
    ;

daytime 
    : integer COLON integer COLON integer
    ;

date 
    : DATEH date_literal
    ;

date_literal 
    : integer MINUS integer MINUS integer
    ;

date_and_time 
    : DATE_AND_TIMEH date_literal MINUS daytime
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

real_type_cast
    : REALH
    | LREALH
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
    : TYPE type_declaration SEMICOLON
        (type_declaration SEMICOLON)* 
      END_TYPE
    ;

type_declaration    
    : single_element_type_declaration
    | array_type_declaration
    | structure_type_declaration
    | string_type_declaration
    ;

single_element_type_declaration
    : simple_type_declaration
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
    |IDENTIFIER
    ;

subrange_type_declaration 
    :IDENTIFIER COLON subrange_spec_init
    ;

subrange_spec_init 
    : subrange_specification(ASSIGN integer)?
    ;

subrange_specification 
    : integer_type_name LPAREN subrange RPAREN 
    |IDENTIFIER
    ;

subrange 
    : integer RANGE  integer
    ;

enumerated_type_declaration 
    :IDENTIFIER COLON enumerated_spec_init
    ;

enumerated_spec_init 
    : enumerated_specification (ASSIGN enumerated_value)?
    ;

enumerated_specification 
    : (LPAREN enumerated_value (COMMA enumerated_value)* RPAREN)
    | IDENTIFIER
    ;

enumerated_value 
    : CAST IDENTIFIER
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
    | enumerated_value
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
        | enumerated_spec_init
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
        | enumerated_value
        | array_initialization
        | structure_initialization )
    ;


string_type_declaration 
    :IDENTIFIER COLON
      (STRING|WSTRING) 
      ( LBRACKET integer RBRACKET)?
      (ASSIGN character_string)?
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
    : identifier_list COLON ( simple_spec_init 
                      | subrange_spec_init 
                      | enumerated_spec_init)
    ;

array_var_init_decl 
    : identifier_list COLON array_spec_init
    ;

structured_var_init_decl 
    : identifier_list COLON initialized_structure
    ;

fb_name_decl 
    : identifier_list COLON function_block_type_name 
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
      |IDENTIFIER
      | function_block_type_name
      )
    ;


global_var_declarations 
    : VAR_GLOBAL (CONSTANT | RETAIN)? global_var_decl SEMICOLON 
      (global_var_decl SEMICOLON)* END_VAR
    ;

global_var_decl 
    : global_var_spec COLON 
      ( located_var_spec_init 
      | function_block_type_name )?
    ;

global_var_spec 
    : identifier_list 
    | (IDENTIFIER)? location
    ;

located_var_spec_init   
    : simple_spec_init
    | subrange_spec_init
    | enumerated_spec_init
    | array_spec_init
    | initialized_structure
    | single_byte_string_spec
    | double_byte_string_spec
    ;

location 
    : AT direct_variable
    ;

identifier_list
    : IDENTIFIER (COMMA IDENTIFIER)*
    ;

string_var_declaration 
    : single_byte_string_var_declaration 
    | double_byte_string_var_declaration
    ;

single_byte_string_var_declaration 
    : identifier_list COLON single_byte_string_spec
    ;

single_byte_string_spec 
    : STRING (LBRACKET integer RBRACKET)? 
      (ASSIGN single_byte_character_string)?
    ;

double_byte_string_var_declaration 
    : identifier_list COLON double_byte_string_spec
    ;

double_byte_string_spec 
    : WSTRING (LBRACKET integer RBRACKET)? 
      (ASSIGN double_byte_character_string)?
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

function_block_type_name 
    :IDENTIFIER
    |IDENTIFIER
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
    | enumerated_value
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
                (IDENTIFIER COLON function_block_type_name ASSIGN
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
    | enumerated_value
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
    | enumerated_value
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
