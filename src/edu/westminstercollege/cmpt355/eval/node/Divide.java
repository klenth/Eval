package edu.westminstercollege.cmpt355.eval.node;

import java.util.List;

public record Divide(Expression left, Expression right) implements Expression {

    @Override
    public List<Expression> children() {
        return List.of(left, right);
    }
}
