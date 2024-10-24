package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.binaryops;

import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.NodeBinaryOperator;

/**
 * NodePow
 */
public class NodePow extends NodeBinaryOperator {

  @Override
  public double compute() {
    return Math.pow(getLeft().getValue(), getRight().getValue());
  }

}
