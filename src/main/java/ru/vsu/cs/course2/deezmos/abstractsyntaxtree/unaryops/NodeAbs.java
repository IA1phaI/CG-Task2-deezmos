package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.NodeUnaryOperator;

/**
 * NodeAbs
 */
public class NodeAbs extends NodeUnaryOperator {

  @Override
  public double compute() {
    return Math.abs(getArgument().getValue());
  }

}
