package edu.westminstercollege.cmpt355.eval.node;

import java.util.List;

public record Program(List<Statement> statements) implements Node {

    @Override
    public List<? extends Node> children() {
        return statements;
    }
}
