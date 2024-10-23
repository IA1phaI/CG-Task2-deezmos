package ru.vsu.cs.course2.deezmos.abstractsyntaxtree;

/**
 * NodeBinaryOperator
 */
public abstract class NodeBinaryOperator extends NodeOperator {

  private ASTNodeI left;
  private ASTNodeI right;

  public ASTNodeI getLeft() {
    return left;
  }

  public ASTNodeI getRight() {
    return right;
  }

}
