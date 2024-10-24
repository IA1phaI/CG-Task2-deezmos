package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.NodeUnaryOperator;

/**
 * NodeTg
 */
public class NodeTg extends NodeUnaryOperator {

  @Override
  public double compute() {
    return Math.tan(this.getArgument().getValue());
  }

}
