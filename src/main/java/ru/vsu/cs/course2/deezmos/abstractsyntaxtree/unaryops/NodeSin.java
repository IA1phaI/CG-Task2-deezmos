package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.NodeUnaryOperator;

/**
 * NodeSin
 */
public class NodeSin extends NodeUnaryOperator {

  @Override
  public double compute() {
    return Math.sin(this.getArgument().getValue());
  }

}
