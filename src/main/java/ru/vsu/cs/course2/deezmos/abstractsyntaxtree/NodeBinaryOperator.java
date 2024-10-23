package ru.vsu.cs.course2.deezmos.abstractsyntaxtree;

/**
 * NodeBinaryOperator
 */
public abstract class NodeBinaryOperator extends NodeOperator {

  private ASTNode left;
  private ASTNode right;

  public NodeBinaryOperator(ASTNode left, ASTNode right) {
    this.left = left;
    this.right = right;
  }

  public ASTNode getLeft() {
    return left;
  }

  public ASTNode getRight() {
    return right;
  }
}
