package ru.vsu.cs.course2.deezmos.expressiontree.binaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeBinaryOperator;

/**
 * NodeLog
 */
public class NodeLog extends NodeBinaryOperator {

  public NodeLog(ETNode argument, ETNode base) {
    super(argument, base);
  }

  @Override
  public double getValue() {
    return Math.log(getRight().getValue()) / Math.log(getLeft().getValue());
  }

}
