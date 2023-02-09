package edu.westminstercollege.cmpt355.eval.node;

public sealed interface PrintArgument extends Node
    permits Expression, StringArgument {
}
