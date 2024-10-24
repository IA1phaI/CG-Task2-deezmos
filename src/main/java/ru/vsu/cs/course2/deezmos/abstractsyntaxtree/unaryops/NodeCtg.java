package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.NodeUnaryOperator;

/**
 * NodeCtg
 */
public class NodeCtg extends NodeUnaryOperator {

  @Override
  public double compute() {
    return 1 / Math.tan(this.getArgument().getValue());
  }

}
