package edu.westminstercollege.cmpt355.eval.node;

import java.util.List;

public record StringArgument(String text) implements PrintArgument {

    @Override
    public List<? extends Node> children() {
        return List.of();
    }

    @Override
    public String getNodeDescription() {
        return String.format("StringArgument [text: %s]", text);
    }
}
