package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.binaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeBinaryOperator;

/**
 * NodePow
 */
public class NodePow extends NodeBinaryOperator {

  public NodePow(ETNode argument, ETNode pow) {
    super(argument, pow);
  }

  @Override
  public double getValue() {
    return Math.pow(getLeft().getValue(), getRight().getValue());
  }

}
