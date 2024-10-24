package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.binaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETreeNode;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeBinaryOperator;

/**
 * NodeLog
 */
public class NodeLog extends NodeBinaryOperator {

  public NodeLog(ETreeNode argument, ETreeNode base) {
    super(argument, base);
  }

  @Override
  public double getValue() {
    return Math.log(getRight().getValue()) / Math.log(getLeft().getValue());
  }

}
