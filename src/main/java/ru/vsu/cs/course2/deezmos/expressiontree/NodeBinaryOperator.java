package ru.vsu.cs.course2.deezmos.expressiontree;

/**
 * NodeOperator
 */
public abstract class NodeBinaryOperator implements ETNode {

  private ETNode left;
  private ETNode right;

  public NodeBinaryOperator(ETNode left, ETNode right) {
    this.left = left;
    this.right = right;
  }

  public NodeBinaryOperator() {
    this(null, null);
  }

  public ETNode getLeft() {
    return left;
  }

  public ETNode getRight() {
    return right;
  }

  public void setLeft(ETNode left) {
    this.left = left;
  }

  public void setRight(ETNode right) {
    this.right = right;
  }

  public boolean hasLeft() {
    return this.left != null;
  }

  public boolean hasRight() {
    return this.right != null;
  }

  @Override
  public void pushChild(ETNode child) {
    if (!this.hasLeft()) {
      this.setLeft(child);
      return;
    }
    this.setRight(child);
  }
}
