package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.binaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeBinaryOperator;

/**
 * NodeSubtraction
 */
public class NodeSubtraction extends NodeBinaryOperator {

  public NodeSubtraction(ETNode left, ETNode right) {
    super(left, right);
  }

  @Override
  public double getValue() {
    return getLeft().getValue() - getRight().getValue();
  }

}
