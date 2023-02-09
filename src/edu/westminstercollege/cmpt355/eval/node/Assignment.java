package edu.westminstercollege.cmpt355.eval.node;

import java.util.List;

public record Assignment(String variableName, Expression expression) implements Statement {

    @Override
    public List<Node> children() {
        return List.of(expression);
    }

    @Override
    public String getNodeDescription() {
        return String.format("Assignment [variableName: %s]", variableName);
    }
}
