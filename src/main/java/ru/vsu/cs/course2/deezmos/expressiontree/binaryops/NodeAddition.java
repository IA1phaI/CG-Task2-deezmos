package ru.vsu.cs.course2.deezmos.expressiontree.binaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETreeNode;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeBinaryOperator;

/**
 * NodeAddition
 */
public class NodeAddition extends NodeBinaryOperator {

  public NodeAddition(ETreeNode left, ETreeNode right) {
    super(left, right);
  }

  @Override
  public double getValue() {
    return getLeft().getValue() + getRight().getValue();
  }

}
