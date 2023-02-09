package edu.westminstercollege.cmpt355.eval.node;

import java.util.List;

public record VariableAccess(String variableName) implements Expression {

    @Override
    public String getNodeDescription() {
        return String.format("VariableAccess [variableName: %s]", variableName);
    }

    @Override
    public List<? extends Node> children() {
        return List.of();
    }
}
