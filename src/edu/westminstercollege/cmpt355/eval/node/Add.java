package edu.westminstercollege.cmpt355.eval.node;

import java.util.List;

public record Add(Expression left, Expression right) implements Expression {

    @Override
    public List<Node> children() {
        return List.of(left, right);
    }
}
