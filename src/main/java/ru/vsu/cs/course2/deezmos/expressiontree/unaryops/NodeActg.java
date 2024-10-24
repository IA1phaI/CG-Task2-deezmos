package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETreeNode;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeUnaryOperator;

/**
 * NodeActg
 */
public class NodeActg extends NodeUnaryOperator {

  public NodeActg(ETreeNode argument) {
    super(argument);
  }

  @Override
  public double getValue() {
    return 1 / Math.atan(this.getArgument().getValue());
  }

}
