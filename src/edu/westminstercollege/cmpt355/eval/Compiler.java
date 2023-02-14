package edu.westminstercollege.cmpt355.eval;

import edu.westminstercollege.cmpt355.eval.node.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class Compiler {

    // Commented out until we have our AST nodes defined...
    private SymbolTable symbols = new SymbolTable();
    private PrintWriter out;
    private final Program program;
    private final String className;

    public Compiler(Program program, String className) {
        this.program = program;
        this.className = className;
    }

    public void compile(Path outputDir) throws IOException, SyntaxException {
        Path asmFilePath = outputDir.resolve(className + ".j");
        try (var out = new PrintWriter(Files.newBufferedWriter(asmFilePath))) {
            this.out = out;
            resolveSymbols(program);

            out.printf(".class public %s\n", className);
            out.printf(".super java/lang/Object\n");
            out.println();
            out.println(".field private static in Ljava/util/Scanner;");
            out.println();
            out.printf("""
                    .method static <clinit>()V
                    .limit stack 3
                    .limit locals 0
                    new java/util/Scanner
                    dup
                    getstatic java/lang/System/in Ljava/io/InputStream;
                    invokenonvirtual java/util/Scanner/<init>(Ljava/io/InputStream;)V
                    putstatic %s/in Ljava/util/Scanner;
                    return
                    .end method
                    
                    """, className);
            out.printf(".method public static main([Ljava/lang/String;)V\n");
            out.printf(".limit stack 100\n");
            //out.printf(".limit locals %d\n", symbols.getVariableCount() * 2 + 1); // + 1 because of args
            out.println();

            // Generate code for program here 🙂

            out.printf("return\n");
            out.printf(".end method\n");
        }
    }

     // Make sure that all symbols (in this case, names of variables) make sense,
     // i.e. we should not be using the value of a variable before we have assigned
     // to it (Eval does not have declarations).
     private void resolveSymbols(Program program) throws SyntaxException {
        AST.preOrder(program, node -> {
            switch (node) {
                case Assignment(String name, Expression e) -> symbols.registerVariable(name);
                case VariableAccess(String name) -> {
                    if (symbols.findVariable(name).isEmpty())
                        // No variable found!
                        throw new SyntaxException(String.format("Variable used before assignment: %s", name));
                }
                default -> {}
            }
        });
    }

    private void generateCode(Statement statement) {
        switch (statement) {
            default ->
                    throw new RuntimeException(String.format("Unimplemented: %s", statement.getNodeDescription()));
        }
    }

    private void generateCode(PrintArgument argument) {
        switch (argument) {
            default ->
                    throw new RuntimeException(String.format("Unimplemented: %s", argument.getNodeDescription()));
        }
    }

    private void generateCode(Expression expr) {
        switch (expr) {
            default ->
                    throw new RuntimeException(String.format("Unimplemented: %s", expr.getNodeDescription()));
        }
    }

    // */
}
