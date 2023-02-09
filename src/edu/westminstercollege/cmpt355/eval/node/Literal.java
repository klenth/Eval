package edu.westminstercollege.cmpt355.eval.node;

import java.util.List;

public record Literal(String text) implements Expression {

    @Override
    public String getNodeDescription() {
        return String.format("Literal [text: %s]", text);
    }

    @Override
    public List<? extends Node> children() {
        return List.of();
    }
}
