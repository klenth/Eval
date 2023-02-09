package edu.westminstercollege.cmpt355.eval.node;

public sealed interface Statement extends Node
    permits Print, Assignment {
}
