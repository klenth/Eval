package edu.westminstercollege.cmpt355.eval;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.nio.file.Path;

public class Main {

    public static void main(String... args) throws IOException, SyntaxException {
        var lexer = new EvalLexer(CharStreams.fromFileName("test_programs/hello.eval"));
        var parser = new EvalParser(new CommonTokenStream(lexer));

        // Uncomment after AST building is done
        /*
        var program = parser.program().n;
        AST.print(program);
         */
    }
}
