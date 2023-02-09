package edu.westminstercollege.cmpt355.eval.node;

import java.util.List;

public record Print(List<PrintArgument> arguments) implements Statement {

    @Override
    public List<? extends Node> children() {
        return arguments;
    }
}
