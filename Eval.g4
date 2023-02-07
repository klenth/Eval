grammar Eval;

program
    : statement* EOF
    ;

statement
    : print
    | assignment
    ;

print
    : 'print' '(' (printArgument (',' printArgument)*)? ')'
    ;

printArgument
    : STRING
    | expr
    ;

assignment
    : NAME '=' expr
    ;

expr
    : NAME
    | NUMBER
    | '(' expr ')'
    | expr '^' expr
    | ('+' | '-') expr
    | expr ('*' | '/') expr
    | expr ('+' | '-') expr
    | 'input' '(' (printArgument (',' printArgument)*)? ')'
    | NAME '(' expr ')'
    ;

NAME
    : [a-zA-Z_$] [a-zA-Z0-9_$]*
    ;

STRING
    : ["] .*? ["]
    | ['] .*? [']
    ;

NUMBER
    : [0-9]+ ('.' [0-9]*)?
    | [0-9]* '.' [0-9]+
    ;

WHITESPACE
    : [ \r\n\t]+    -> skip
    ;