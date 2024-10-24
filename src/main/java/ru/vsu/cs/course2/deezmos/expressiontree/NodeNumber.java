package ru.vsu.cs.course2.deezmos.expressiontree;

/**
 * NodeNumber
 */
public class NodeNumber extends ETreeNode {

  public NodeNumber(double value) {
    super(null, null, value);
  }

  public NodeNumber() {
    super();
  }

  @Override
  public void setLeft(ETreeNode left) {
    throw new RuntimeException("NodeNumber cannot have child");
  }

  @Override
  public void setRight(ETreeNode right) {
    throw new RuntimeException("NodeNumber cannot have child");
  }
}
