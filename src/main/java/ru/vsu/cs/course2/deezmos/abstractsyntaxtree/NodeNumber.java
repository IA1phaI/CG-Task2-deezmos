package ru.vsu.cs.course2.deezmos.abstractsyntaxtree;

/**
 * NodeNumber
 */
public class NodeNumber extends ASTNode {

  private double value;

  public NodeNumber(double value) {
    this.value = value;
  }

  @Override
  public double getValue() {
    return value;
  }

}
