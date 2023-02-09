package edu.westminstercollege.cmpt355.eval.node;

import java.util.List;

public record SquareRoot(Expression child) implements Expression {

    @Override
    public List<? extends Node> children() {
        return List.of(child);
    }
}
