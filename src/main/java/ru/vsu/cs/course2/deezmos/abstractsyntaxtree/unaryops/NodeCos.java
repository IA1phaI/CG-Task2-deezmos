package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.NodeUnaryOperator;

/**
 * NodeCos
 */
public class NodeCos extends NodeUnaryOperator {

  @Override
  public double compute() {
    return Math.cos(getArgument().getValue());
  }

}
