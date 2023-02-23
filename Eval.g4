grammar Eval;

@parser::header {
import edu.westminstercollege.cmpt355.eval.node.*;
}

program
returns [Program n]
    : (stmts+=statement)* EOF {
        var statements = new ArrayList<Statement>();
        // Write code that iterates over $stmts (a List<StatementContext>)
        // pull out each one's .n, and add it to statements.
        // Method 1: for loop with counter
        /*
        for (int i = 0; i < $stmts.size(); ++i)
            statements.add($stmts.get(i).n);
        */

        // Method 2: for-each loop
        for (var stmt : $stmts)
            statements.add(stmt.n);

        // Method 3: using Java's Streams API (will require an additional import)
        //statements = $stmts.stream().map(stmt -> stmt.n).collect(Collectors.toList());

        $n = new Program(statements);
    }
    ;

statement
returns [Statement n]
    : print {
        $n = $print.n;
    }
    | assignment {
        $n = $assignment.n;
    }
    ;

print
returns [Print n]
    : 'print' '(' (args+=printArgument (',' args+=printArgument)*)? ')' {
        var arguments = new ArrayList<PrintArgument>();
        for (var arg : $args)
            arguments.add(arg.n);
        $n = new Print(arguments);
    }
    ;

printArgument
returns [PrintArgument n]
    : STRING {
        $n = new StringArgument($STRING.text);
    }
    | expr {
        $n = $expr.n;
    }
    ;

assignment
returns [Assignment n]
    : NAME '=' expr {
        $n = new Assignment($NAME.text, $expr.n);
    }
    ;

expr
returns [Expression n]
    : NAME {
        $n = new VariableAccess($NAME.text);
    }
    | NUMBER {
        $n = new Literal($NUMBER.text);
    }
    | '(' expr ')' {
        $n = $expr.n;
    }
    | <assoc=right> l=expr '^' r=expr {
        $n = new Power($l.n, $r.n);
    }
    | op=('+' | '-') expr {
        if ($op.text.equals("+"))
            $n = $expr.n;
        else
            $n = new Negate($expr.n);
    }
    | l=expr op=('*' | '/') r=expr {
        if ($op.text.equals("*"))
            $n = new Multiply($l.n, $r.n);
        else
            $n = new Divide($l.n, $r.n);
    }
    | l=expr op=('+' | '-') r=expr {
        if ($op.text.equals("+"))
            $n = new Add($l.n, $r.n);
        else
            $n = new Subtract($l.n, $r.n);
    }
    | 'input' '(' (args+=printArgument (',' args+=printArgument)*)? ')' {
        var arguments = new ArrayList<PrintArgument>();
        for (var arg : $args)
            arguments.add(arg.n);
        $n = new Input(arguments);
    }
    | '(' expr ')' {
        $n = $expr.n;
    }
    | 'sqrt' '(' expr ')' {
        $n = new SquareRoot($expr.n);
    }
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