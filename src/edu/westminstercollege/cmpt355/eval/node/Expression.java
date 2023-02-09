package edu.westminstercollege.cmpt355.eval.node;

public sealed interface Expression extends PrintArgument
    permits Add, Subtract, Multiply, Divide, Power,
        Negate, SquareRoot,
        Literal, VariableAccess,
        Input {
}
