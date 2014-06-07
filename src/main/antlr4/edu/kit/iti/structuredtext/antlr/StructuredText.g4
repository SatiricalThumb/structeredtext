grammar StructuredText;


fragment LETTER : ('a'..'z' | 'A'..'Z');
fragment DIGIT : '0'..'9';
fragment HEX_DIGIT : DIGIT | 'A'..'F';
fragment OCTAL_DIGIT : '0'..'7';


CHARACTER_LITERAL_2BYTE : '$' HEX_DIGIT HEX_DIGIT;
CHARACTER_LITERAL_4BYTE : '$' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT;

INTEGER_LITERAL: DIGIT (DIGIT |'_')*;
OCTAL_LITERAL:   '8#' OCTAL_DIGIT (OCTAL_DIGIT |'_')*;
BINARY_LITERAL:  '2#' BIT (BIT | '_')*;
HEX_LITERAL:    '16#' HEX_DIGIT ('_' | HEX_DIGIT)*;
REAL_LITERAL: [+-]? FIXED_POINT ([eE] DIGIT+)?;


REALH:'REALH';
LREALH:'REALH';
RETURN:'RETURN';
COMMA:',';
ARROW_RIGHT : '=>';
NOT:'NOT';
END_CASE:'END_CASE';
OF:'OF';
CASE: 'CASE';
IF: 'IF';
ELSIF:'ELSIF';
ELSE:'ELSE';
THEN:'THEN';

//Cast Operators
BYTEH:'BYTE#';
BOOLH:'BOOL#';
WORDH:'WORD#';
DWORDH:'DWORD#';
LWORDH:'LWORD#';

WORD:'WORD';
DWORD:'DWORD';
LWORD:'LWORD';
HASH:'#';

TRUE  : 'TRUE';
FALSE : 'FALSE';
SQUOTE:'\'';
DQUOTE:'"';
DOLLAR:'$';
T:'T';
TIME:'TIME';
STRING  : 'STRING';
WSTRING : 'WSTRING';
INT:'INT';
SINT:'SINT';
DINT:'DINT';
LINT:'LINT';
UINT:'UINT';
USINT:'USINT';
UDINT:'UDINT';
ULINT:'ULINT';
REAL:'REAL';
BOOL:'BOOL';
ANY:'ANY';
ANY_DERIVED:'ANY_DERIVED';
ANY_MAGNITUDE:'ANY_MAGNITUDE';
ANY_ELEMENTARY:'ANY_ELEMENTARY';
ANY_NUM:'ANY_NUM';
ANY_REAL:'ANY_REAL';
ANY_INT:'ANY_INT';
ANY_BIT:'ANY_BIT';
ANY_STRING:'ANY_STRING';
ANY_DATE:'ANY_DATE';
ARRAY:'ARRAY';

//EXPONENT: [eE];
MULT:'*';
DIV:'/';
MOD:'MOD';
LPAREN: '(';
RPAREN: ')';

LBRACKET:'[';
RBRACKET:']';

SEMICOLON:';';
POWER:'**';
ASSIGN:':=';
FOR:'FOR';
END_FOR:'END_FOR';
TO:'TO';
BY:'BY';
DO:'DO';
WHILE:'WHILE';
END_WHILE:'END_WHILE';
UNTIL: 'UNTIL';
END_REPEAT: 'END_REPEAT';
REPEAT: 'REPEAT';
EXIT: 'EXIT';


NIL : 'NIL';
RETAIN : 'RETAIN';
NON_RETAIN : 'NON_RETAIN';

BIT : '1' | '0';


WS: [ \r\t\n] -> skip;

BYTE: 'BYTE';
MINUS: '-';
PLUS : '+';

LESS_THAN:'<';
GREATER_THAN:'>';
GREATER_EQUALS:'>=';
LESS_EQUALS: '<=';
EQUALS: '=';
NOT_EQUALS:'<>';

AMPERSAND:'&';
VAR_OUTPUT:'VAR_OUTPUT';
VAR_EXTERNAL:'VAR_EXTERNAL';
FUNCTION_BLOCK:'FUNCTION_BLOCK';
END_PROGRAM:'END_PROGRAM';
RIGHT_ARROW:'RIGHT_ARROW';
END_STRUCT:'END_STRUCT';
END_IF:'END_IF';
VAR_TEMP:'VAR_TEMP';
COLON:':';

DT:'DT';
LREAL:'LREAL';
PERCENT:'%';
PROGRAM: 'PROGRAM';
OR:'OR';
AND:'AND';
XOR:'XOR';
END_CONFIGURATION:'END_CONFIGURATION';

DATE:'DATE';
STRUCT: 'STRUCT';
DOT: '.';
WITH:'WITH';
SINGLE: 'SINGLE';
END_TYPE:'END_TYPE';
VAR_INPUT: 'VAR_INPUT';
VAR_GLOBAL:'VAR_GLOBAL';
TASK:'TASK';
TYPE:'TYPE';
FUNCTION:'FUNCTION';
DOLLAR_DQUOTE: '$"';
VAR_IN_OUT:'VAR_IN_OUT';
END_FUNCTION:'END_FUNCTION';
END_FUNCTION_BLOCK:'END_FUNCTION_BLOCK';
INTERVAL:'INTERVAL';
CONSTANT:'CONSTANT';
VAR:'VAR';
VAR_CONFIG:'VAR_CONFIG';
END_VAR:'END_VAR';
PRIORITY:'PRIORITY';
READ_WRITE:'READ_WRITE';
READ_ONLY:'READ_ONLY';
VAR_ACCESS:'VAR_ACCESS';
RANGE: '..';
END_RESOURCE:'END_RESOURCE';
CONFIGURATION:'CONFIGURATION';
RESOURCE:'RESOURCE';
ON:'ON';
R_EDGE:'R_EDGE';
F_EDGE:'F_EDGE';
AT:'AT';
I:'I';
Q:'Q';
M:'M';
X:'X';
B:'B';
W:'W';
D:'D';
L:'L';
DOLLAR_SQUOTE:'$\'';
DOLLAR_DOLLAR: '$$';           
DOLLAR_L: '$L';
DOLLAR_N: '$N';
DOLLAR_P: '$P';
DOLLAR_R: '$R';
DOLLAR_T: '$T';
DOLLAR_l: '$l';
DOLLAR_n: '$n';
DOLLAR_p: '$p';
DOLLAR_r: '$r';
DOLLAR_t: '$t';
UNDERSCORE:'_';
TIME_OF_DAY:'TIME_OF_DAY#';
TOD:'TOD#';
DATE_AND_TIME:'DATE_AND_TIME';


IDENTIFIER: [a-zA-Z] [a-zA-Z0-9_]*;


/*******************************************************************************

*******************************************************************************/
    
start : library_element_declaration+;

library_element_name    
    : data_type_name
    | function_name
    | function_block_type_name
    | program_type_name
    | resource_type_name
    | configuration_name
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
    : (integer_type_name HASH)
      ( signed_integer
      | binary_integer
      | octal_integer
      | hex_integer )
    ;

unsigned_integer 
    : (PLUS | MINUS)? INTEGER_LITERAL
    ;

signed_integer 
    : (PLUS | MINUS)? INTEGER_LITERAL
    ;

integer 
    : INTEGER_LITERAL
    ;

binary_integer 
    : BINARY_LITERAL
    ;

octal_integer 
    : OCTAL_LITERAL
    ;

hex_integer 
    : HEX_LITERAL
    ;

real_literal 
    : real_type_name? REAL_LITERAL
    ;

bit_string_literal
    : (BYTEH | WORDH | DWORDH | LWORDH)?
      ( unsigned_integer
      | binary_integer 
      | octal_integer 
      | hex_integer )
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
    : (T | TIME ) HASH (MINUS)? interval
    ;

interval 
    : days 
    | hours 
    | minutes 
    | seconds 
    | milliseconds
    ;

fragment FIXED_POINT: DIGIT+ (DOT DIGIT+)?;

DAY_LITERAL : FIXED_POINT 'd';
HOURS_LITERAL : FIXED_POINT 'h';
MINUTES_LITERAL : FIXED_POINT 'm';
SECONDS_LITERAL : FIXED_POINT 's';
MILLIESECONDS_LITERAL : FIXED_POINT 'ms';

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
    : (TIME_OF_DAY | TOD) daytime
    ;

daytime 
    : day_hour COLON day_minute COLON day_second
    ;

day_hour 
    : integer
    ;

day_minute 
    : integer
    ;

day_second 
    : integer
    ;

date 
    : (DATE | 'D#') date_literal
    ;

date_literal 
    : year MINUS month MINUS day
    ;

year 
    : integer
    ;

month 
    : integer
    ;

day 
    : integer
    ;

date_and_time 
    : (DATE_AND_TIME | DT) HASH date_literal MINUS daytime
    ;

data_type_name 
    : non_generic_type_name | generic_type_name
    ;

non_generic_type_name 
    : elementary_type_name
    | derived_type_name
    ;

elementary_type_name
    : numeric_type_name
    | date_type_name
    | bit_string_type_name
    | STRING
    | WSTRING
    | TIME
    ;

numeric_type_name : integer_type_name | real_type_name;
integer_type_name : signed_integer_type_name | unsigned_integer_type_name;

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
    : REALH
    | LREALH
    ;

date_type_name  
    : DATE
    | TIME_OF_DAY
    | TOD
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

derived_type_name   
    : single_element_type_name
    | array_type_name
    | structure_type_name
    | string_type_name
    ;

single_element_type_name    
    : simple_type_name
    | subrange_type_name
    | enumerated_type_name
    ;

simple_type_name        
    : IDENTIFIER
    ;
subrange_type_name      
    : IDENTIFIER
    ;

enumerated_type_name    
    : IDENTIFIER
    ;

array_type_name         
    : IDENTIFIER
    ;

structure_type_name     
    : IDENTIFIER
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
    : simple_type_name COLON simple_spec_init
    ;

simple_spec_init 
    : simple_specification (ASSIGN constant)?
    ;

simple_specification 
    : elementary_type_name 
    | simple_type_name
    ;

subrange_type_declaration 
    : subrange_type_name COLON subrange_spec_init
    ;

subrange_spec_init 
    : subrange_specification(ASSIGN signed_integer)?
    ;

subrange_specification 
    : integer_type_name LPAREN subrange RPAREN 
    | subrange_type_name
    ;

subrange 
    : signed_integer RANGE  signed_integer
    ;

enumerated_type_declaration 
    : enumerated_type_name COLON enumerated_spec_init
    ;

enumerated_spec_init 
    : enumerated_specification (ASSIGN enumerated_value)?
    ;

enumerated_specification 
    : (LPAREN enumerated_value (COMMA enumerated_value)* RPAREN)
    | enumerated_type_name
    ;

enumerated_value 
    : (enumerated_type_name HASH)? IDENTIFIER
    ;

array_type_declaration 
    : array_type_name COLON array_spec_init
    ;

array_spec_init 
    : array_specification (ASSIGN array_initialization)?
    ;

array_specification 
    : array_type_name
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
    : structure_type_name COLON structure_specification
    ;

structure_specification
    : structure_declaration 
    | initialized_structure
    ;

initialized_structure 
    : structure_type_name (ASSIGN structure_initialization)?
    ;

structure_declaration 
    : STRUCT structure_element_declaration SEMICOLON 
      (structure_element_declaration SEMICOLON)* 
      END_STRUCT
    ;

structure_element_declaration 
    : structure_element_name COLON
        ( simple_spec_init
        | subrange_spec_init
        | enumerated_spec_init
        | array_spec_init
        | initialized_structure)
    ;    

structure_element_name 
    : IDENTIFIER
    ;

structure_initialization 
    : LPAREN structure_element_initialization
        (COMMA structure_element_initialization)* RPAREN
    ;

structure_element_initialization 
    : structure_element_name ASSIGN
        ( constant
        | enumerated_value
        | array_initialization
        | structure_initialization )
    ;

string_type_name 
    : IDENTIFIER
    ;

string_type_declaration 
    : string_type_name COLON 
      (STRING|WSTRING) 
      ( LBRACKET integer RBRACKET)?
      (ASSIGN character_string)?
    ;

variable 
    : direct_variable
    | symbolic_variable
    ;

symbolic_variable 
    : variable_name subscript_list?
      ( DOT symbolic_variable)?
    ;

variable_name 
    : IDENTIFIER
    ;

direct_variable 
    : PERCENT location_prefix size_prefix integer (DOT integer)*
    ;

location_prefix 
    : I 
    | Q 
    | M
    ;

size_prefix 
    : NIL /* maybe wrong, paper unclear */
    | X 
    | B 
    | W 
    | D 
    | L
    ;

subscript_list 
    : LBRACKET subscript (COMMA subscript)* RBRACKET
    ;

subscript 
    : expression
    ;

field_selector 
    : IDENTIFIER
    ;

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
    : var1_list COLON BOOL (R_EDGE | F_EDGE)
    ;

var_init_decl 
    : var1_init_decl 
    | array_var_init_decl 
    | structured_var_init_decl 
    | fb_name_decl 
    | string_var_declaration
    ;

var1_init_decl 
    : var1_list COLON ( simple_spec_init 
                      | subrange_spec_init 
                      | enumerated_spec_init)
    ;

var1_list 
    : variable_name (COMMA variable_name)*
    ;

array_var_init_decl 
    : var1_list COLON array_spec_init
    ;

structured_var_init_decl 
    : var1_list COLON initialized_structure
    ;

fb_name_decl 
    : fb_name_list COLON function_block_type_name 
      ( ASSIGN structure_initialization )?
    ;

fb_name_list 
    : fb_name (COMMA fb_name)*
    ;

fb_name
    : IDENTIFIER
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
    : var1_list COLON ( simple_specification 
                      | subrange_specification 
                      | enumerated_specification)
    ;

array_var_declaration 
    : var1_list COLON array_specification
    ;

structured_var_declaration 
    : var1_list COLON structure_type_name
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

located_var_decl : (variable_name)? location COLON located_var_spec_init;

external_var_declarations 
    : VAR_EXTERNAL CONSTANT? external_declaration SEMICOLON 
      (external_declaration SEMICOLON)*
      END_VAR
    ;

external_declaration 
    : global_var_name COLON 
      ( simple_specification 
      | subrange_specification
      | enumerated_specification 
      | array_specification 
      | structure_type_name 
      | function_block_type_name
      )
    ;

global_var_name 
    : IDENTIFIER
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
    : global_var_list 
    | (global_var_name)? location
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

global_var_list 
    : global_var_name (COMMA global_var_name)*
    ;

string_var_declaration 
    : single_byte_string_var_declaration 
    | double_byte_string_var_declaration
    ;

single_byte_string_var_declaration 
    : var1_list COLON single_byte_string_spec
    ;

single_byte_string_spec 
    : STRING (LBRACKET integer RBRACKET)? 
      (ASSIGN single_byte_character_string)?
    ;

double_byte_string_var_declaration 
    : var1_list COLON double_byte_string_spec
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
    : variable_name incompl_location COLON var_spec
    ;

incompl_location 
    : AT PERCENT (I | Q | M ) MULT
    ;

var_spec 
    : simple_specification 
    | subrange_specification 
    | enumerated_specification 
    | array_specification 
    | structure_type_name 
    | (STRING | WSTRING) ( LBRACKET integer RBRACKET)? 
    ;

function_name 
    : standard_function_name 
    | derived_function_name
    ;

standard_function_name 
    : IDENTIFIER /*<as defined in clause 2.5.1.5 of the standard>*/
    ;

derived_function_name 
    : IDENTIFIER
    ;

function_declaration 
    : FUNCTION derived_function_name COLON
        (elementary_type_name | derived_type_name)
        ( io_var_declarations | function_var_decls )* function_body
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
    : standard_function_block_name
    | derived_function_block_name
    ;

standard_function_block_name 
    : IDENTIFIER /* <as defined in clause 2.5.2.3 of the standard>*/
    ;

derived_function_block_name 
    : IDENTIFIER
    ;

function_block_declaration 
    : FUNCTION_BLOCK derived_function_block_name
      ( io_var_declarations | other_var_declarations )*
                                function_block_body
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
program_type_name 
    : IDENTIFIER
    ;

program_declaration     
    : PROGRAM program_type_name
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
    : access_name COLON symbolic_variable COLON 
        non_generic_type_name (direction)?
    ;

configuration_name 
    : IDENTIFIER
    ;

resource_type_name 
    : IDENTIFIER
    ;

configuration_declaration 
    : CONFIGURATION configuration_name
        (global_var_declarations)?
        ( single_resource_declaration
        | (resource_declaration (resource_declaration)*)
        )
        (access_declarations)?
        (instance_specific_initializations)?
      END_CONFIGURATION
    ;

resource_declaration 
    : RESOURCE resource_name ON resource_type_name
      (global_var_declarations)?
      single_resource_declaration
      END_RESOURCE
    ;

single_resource_declaration 
    : (task_configuration SEMICOLON)*
      program_configuration SEMICOLON
      (program_configuration SEMICOLON)*
    ;

resource_name 
    : IDENTIFIER
    ;

access_declarations 
    : VAR_ACCESS
      access_declaration SEMICOLON
      (access_declaration SEMICOLON)*
      END_VAR
    ;

access_declaration 
    : access_name COLON access_path COLON
      non_generic_type_name (direction)?;

access_path 
    : (resource_name DOT )? direct_variable
    | (resource_name DOT)? (program_name DOT)?
      (fb_name DOT)* symbolic_variable;

global_var_reference 
    : (resource_name DOT)? 
      global_var_name (DOT structure_element_name)?
    ;
access_name 
    : IDENTIFIER
    ;

program_output_reference 
    : program_name DOT symbolic_variable
    ;

program_name 
    : IDENTIFIER
    ;

direction 
    : READ_WRITE 
    | READ_ONLY
    ;

task_configuration 
    : TASK task_name task_initialization
    ;

task_name 
    : IDENTIFIER
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
        program_name (WITH task_name)? COLON program_type_name
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
    : fb_name WITH task_name
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
    : resource_name DOT program_name DOT (fb_name DOT)*
      ((variable_name (location)? COLON located_var_spec_init) |
                (fb_name COLON function_block_type_name ASSIGN
                   structure_initialization))
    ;

expression 
    : xor_expression (OR xor_expression)*
    ;

xor_expression 
    : and_expression (XOR and_expression)*
    ;

and_expression 
    : comparison ((AMPERSAND | AND) comparison)*
    ;

comparison 
    : equ_expression ( (EQUALS | NOT_EQUALS) equ_expression)*
    ;

equ_expression 
    : add_expression (comparison_operator add_expression)*
    ;

comparison_operator 
    : LESS_THAN
    | GREATER_THAN
    | GREATER_EQUALS
    | LESS_EQUALS
    ;

add_expression 
    : term (add_operator term)*;

add_operator 
    : PLUS 
    | MINUS
    ;

term 
    : power_expression (multiply_operator power_expression)*
    ;

multiply_operator 
    : MULT 
    | DIV 
    | MOD
    ;

power_expression 
    : unary_expression (POWER unary_expression)*
    ;

unary_expression 
    : (unary_operator)? primary_expression
    ;

unary_operator  
    : MINUS 
    | NOT
    ;
    
primary_expression  
    : constant
    | enumerated_value
    | variable
    | LPAREN expression RPAREN
    | function_name LPAREN param_assignment (COMMA param_assignment)* RPAREN
    ;

statement_list 
    : statement SEMICOLON (statement SEMICOLON)*
    ;

statement 
    : NIL
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
    : fb_name LPAREN (param_assignment (COMMA param_assignment)*)+ RPAREN
    ;

param_assignment 
    : ((variable_name ASSIGN)+ expression)
    | ((NOT)+ variable_name ARROW_RIGHT variable)
    ;

selection_statement 
    : if_statement
    | case_statement
    ;

if_statement    
    : IF expression THEN statement_list
      (ELSIF expression THEN statement_list)*
      (ELSE statement_list)+
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
    | signed_integer 
    | enumerated_value
    ;

iteration_statement 
    : for_statement
    | while_statement
    | repeat_statement
    | exit_statement
    ;

for_statement   
    : FOR control_variable ASSIGN for_list DO
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