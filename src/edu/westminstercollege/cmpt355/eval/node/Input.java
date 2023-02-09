package edu.westminstercollege.cmpt355.eval.node;

import java.util.List;

public record Input(List<PrintArgument> arguments) implements Expression {

    @Override
    public List<? extends Node> children() {
        return arguments;
    }
}
