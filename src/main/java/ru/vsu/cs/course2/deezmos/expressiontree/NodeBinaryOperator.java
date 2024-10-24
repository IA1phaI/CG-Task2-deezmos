package ru.vsu.cs.course2.deezmos.expressiontree;

/**
 * NodeOperator
 */
public abstract class NodeBinaryOperator implements ETreeNode {

  private ETreeNode left;
  private ETreeNode right;

  public ETreeNode(ETreeNode left, ETreeNode right) {
    this.left = left;
    this.right = right;
  }

  public ETreeNode() {
    this(null, null);
  }

  public ETreeNode getLeft() {
    return left;
  }

  public ETreeNode getRight() {
    return right;
  }

  public void setLeft(ETreeNode left) {
    this.left = left;
  }

  public void setRight(ETreeNode right) {
    this.right = right;
  }

  public boolean hasLeft() {
    return this.left != null;
  }

  public boolean hasRight() {
    return this.right != null;
  }

  @Override
  public void pushChild(ETreeNode child) {
    if (!this.hasLeft()) {
      this.setLeft(child);
      return;
    }
    this.setRight(child);
  }
}
