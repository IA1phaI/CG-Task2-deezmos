package ru.vsu.cs.course2.deezmos.abstractsyntaxtree;

/**
 * NodeOperator
 */
public abstract class NodeOperator extends ASTNode implements Computable {
  private Double value;

  @Override
  public double getValue() {
    if (value == null) {
      value = compute();
    }

    return value;
  }

}
