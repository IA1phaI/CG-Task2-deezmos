package ru.vsu.cs.course2.deezmos.expressiontree;

/**
 * NodeOperator
 */
public abstract class NodeUnaryOperator implements ETreeNode {

  private ETreeNode argument;

  public ETreeNode(ETreeNode left) {
    this.argument = left;
  }

  public ETreeNode() {
    this(null);
  }

  public ETreeNode getArgument() {
    return argument;
  }

  public void setArgument(ETreeNode left) {
    this.argument = left;
  }

  @Override
  public void pushChild(ETreeNode child) {
    setArgument(child);
  }
}
