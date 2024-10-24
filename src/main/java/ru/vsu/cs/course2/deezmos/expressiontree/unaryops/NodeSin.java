package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETreeNode;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeUnaryOperator;

/**
 * NodeSin
 */
public class NodeSin extends NodeUnaryOperator {

  public NodeSin(ETreeNode argument) {
    super(argument);
  }

  @Override
  public double getValue() {
    return Math.sin(this.getArgument().getValue());
  }

}
