package ru.vsu.cs.course2.deezmos.expressiontree.binaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeBinaryOperator;

/**
 * NodeAddition
 */
public class NodeAddition extends NodeBinaryOperator {

  public NodeAddition(ETNode left, ETNode right) {
    super(left, right);
  }

  @Override
  public double getValue() {
    return getLeft().getValue() + getRight().getValue();
  }

}
