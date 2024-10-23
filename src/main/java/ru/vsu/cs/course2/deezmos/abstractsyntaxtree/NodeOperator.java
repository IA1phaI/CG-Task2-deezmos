package ru.vsu.cs.course2.deezmos.abstractsyntaxtree;

/**
 * NodeOperator
 */
public abstract class NodeOperator implements ASTNodeI {
  private Double value;

  @Override
  public double getValue() {
    if (this.value != null) {
      return this.value;
    }

    return compute();
  }

  abstract double compute();
}
