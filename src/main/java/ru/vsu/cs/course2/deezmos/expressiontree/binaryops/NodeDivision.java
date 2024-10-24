package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.binaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETreeNode;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeBinaryOperator;

/**
 * NodeDivision
 */
public class NodeDivision extends NodeBinaryOperator {

  public NodeDivision(ETreeNode left, ETreeNode right) {
    super(left, right);
  }

  @Override
  public double getValue() {
    return getLeft().getValue() / getRight().getValue();
  }

}