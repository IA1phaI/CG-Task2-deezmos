package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.NodeUnaryOperator;

/**
 * NodeAcos
 */
public class NodeAcos extends NodeUnaryOperator {

  @Override
  public double compute() {
    return Math.acos(getArgument().getValue());
  }

}
