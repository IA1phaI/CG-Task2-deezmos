package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETreeNode;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeUnaryOperator;

/**
 * NodeLg
 */
public class NodeLg extends NodeUnaryOperator {

  public NodeLg(ETreeNode argument) {
    super(argument);
  }

  @Override
  public double getValue() {
    return Math.log10(getArgument().getValue());
  }

}
