package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.binaryops;

import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.NodeBinaryOperator;

/**
 * NodeMultiplication
 */
public class NodeMultiplication extends NodeBinaryOperator {

  @Override
  public double compute() {
    return getLeft().getValue() * getRight().getValue();
  }

}
