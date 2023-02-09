package edu.westminstercollege.cmpt355.eval.node;

import java.util.List;

public record Power(Expression left, Expression right) implements Expression {

    @Override
    public List<? extends Node> children() {
        return List.of(left, right);
    }
}
