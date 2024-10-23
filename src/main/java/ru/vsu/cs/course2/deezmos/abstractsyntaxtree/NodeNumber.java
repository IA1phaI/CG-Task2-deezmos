package ru.vsu.cs.course2.deezmos.abstractsyntaxtree;

/**
 * NodeNumber
 */
public class NodeNumber implements ASTNodeI {

  private double value;

  public NodeNumber(double value) {
    this.value = value;
  }

  @Override
  public double getValue() {
    return value;
  }

}
