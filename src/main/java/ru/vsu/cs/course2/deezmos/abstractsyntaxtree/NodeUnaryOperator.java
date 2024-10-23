package ru.vsu.cs.course2.deezmos.abstractsyntaxtree;

/**
 * NodeUnaryOperator
 */
public abstract class NodeUnaryOperator extends NodeOperator {

  private ASTNode argument;

  public NodeUnaryOperator(ASTNode argument) {
    this.argument = argument;
  }

  public ASTNode getArgument() {
    return argument;
  }
}
