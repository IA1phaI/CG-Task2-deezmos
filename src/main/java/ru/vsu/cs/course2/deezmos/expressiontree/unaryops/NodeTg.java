package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETreeNode;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeUnaryOperator;

/**
 * NodeTg
 */
public class NodeTg extends NodeUnaryOperator {

  public NodeTg(ETreeNode argument) {
    super(argument);
  }

  @Override
  public double getValue() {
    return Math.tan(this.getArgument().getValue());
  }

}
