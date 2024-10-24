package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETreeNode;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeUnaryOperator;

/**
 * NodeCtg
 */
public class NodeCtg extends NodeUnaryOperator {

  public NodeCtg(ETreeNode argument) {
    super(argument);
  }

  @Override
  public double getValue() {
    return 1 / Math.tan(this.getArgument().getValue());
  }

}
