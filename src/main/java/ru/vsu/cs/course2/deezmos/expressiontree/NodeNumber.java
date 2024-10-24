package ru.vsu.cs.course2.deezmos.expressiontree;

/**
 * NodeNumber
 */
public class NodeNumber extends ETNode {

  public NodeNumber(double value) {
    super(null, null, value);
  }

  public NodeNumber() {
    super();
  }

  @Override
  public void setLeft(ETNode left) {
    throw new RuntimeException("NodeNumber cannot have child");
  }

  @Override
  public void setRight(ETNode right) {
    throw new RuntimeException("NodeNumber cannot have child");
  }
}
