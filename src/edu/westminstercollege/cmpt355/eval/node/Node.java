package edu.westminstercollege.cmpt355.eval.node;

import java.util.List;

public sealed interface Node
    permits PrintArgument, Statement, Program {

    default String getNodeDescription() {
        String fullName = getClass().getSimpleName();
        int index = fullName.lastIndexOf('.');
        if (index >= 0)
            return fullName.substring(index + 1);
        return fullName;
    }

    List<? extends Node> children();
}
