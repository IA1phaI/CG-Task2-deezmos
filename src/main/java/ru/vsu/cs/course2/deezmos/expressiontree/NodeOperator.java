package ru.vsu.cs.course2.deezmos.expressiontree;

/**
 * NodeOperator
 */
public abstract class NodeOperator implements ETreeNode {
  private ETreeNode left;
  private ETreeNode right;

  public NodeOperator(ETreeNode left, ETreeNode right) {
    this.left = left;
    this.right = right;
  }

  public NodeOperator() {
    this(null, null);
  }

  @Override
  public ETreeNode getLeft() {
    return left;
  }

  @Override
  public void setLeft(ETreeNode left) {
    this.left = left;
  }

  @Override
  public ETreeNode getRight() {
    return right;
  }

  @Override
  public void setRight(ETreeNode right) {
    this.right = right;
  }

  @Override
  abstract double getValue();

  @Override
  public boolean hasLeft() {
    return left != null;
  }

  @Override
  public boolean hasRight() {
    return right != null;
  }

}
