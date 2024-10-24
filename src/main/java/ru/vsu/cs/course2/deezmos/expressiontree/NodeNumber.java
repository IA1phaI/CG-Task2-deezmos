package ru.vsu.cs.course2.deezmos.expressiontree;

/**
 * NodeNumber
 */
public class NodeNumber implements ETNode {

  private double value;

  public NodeNumber(double value) {
    this.value = value;
  }

  @Override
  public void pushChild(ETNode child) {
    throw new RuntimeException("NodeNumber cannot have child");
  }

  @Override
  public double getValue() {
    return value;
  }

}
