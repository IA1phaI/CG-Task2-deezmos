package ru.vsu.cs.course2.deezmos.expressiontree;

/**
 * NodeNumber
 */
public class NodeNumber implements ETreeNode {

  private double value;

  public NodeNumber(double value) {
    this.value = value;
  }

  @Override
  public ETreeNode getLeft() {
    return null;
  }

  @Override
  public ETreeNode getRight() {
    return null;
  }

  @Override
  public double getValue() {
    return value;
  }

  @Override
  public boolean hasLeft() {
    return false;
  }

  @Override
  public boolean hasRight() {
    return false;
  }

  @Override
  public void setLeft(ETreeNode left) throws RuntimeException {
    throw new RuntimeException("No child nodes in NodeNumber");
  }

  @Override
  public void setRight(ETreeNode right) throws RuntimeException {
    throw new RuntimeException("No child nodes in NodeNumber");
  }

}
