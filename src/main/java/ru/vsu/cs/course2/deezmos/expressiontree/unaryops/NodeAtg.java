package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETreeNode;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeUnaryOperator;

/**
 * NodeAtg
 */
public class NodeAtg extends NodeUnaryOperator {

  public NodeAtg(ETreeNode argument) {
    super(argument);
  }

  @Override
  public double getValue() {
    return Math.atan(this.getArgument().getValue());
  }

}
