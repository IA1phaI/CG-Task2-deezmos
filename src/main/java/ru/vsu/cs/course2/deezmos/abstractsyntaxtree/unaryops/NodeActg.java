package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.NodeUnaryOperator;

/**
 * NodeActg
 */
public class NodeActg extends NodeUnaryOperator {

  @Override
  public double compute() {
    return 1 / Math.atan(this.getArgument().getValue());
  }

}
