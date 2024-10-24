package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.binaryops;

import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.NodeBinaryOperator;

/**
 * NodeLog
 */
public class NodeLog extends NodeBinaryOperator {

  @Override
  public double compute() {
    return Math.log(getRight().getValue()) / Math.log(getLeft()getValue());
  }

}
