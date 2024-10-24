package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETreeNode;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeUnaryOperator;

/**
 * NodeAbs
 */
public class NodeAbs extends NodeUnaryOperator {

  public NodeAbs(ETreeNode argument) {
    super(argument);
  }

  @Override
  public double getValue() {
    return Math.abs(getArgument().getValue());
  }

}
