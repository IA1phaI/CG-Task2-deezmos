package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeUnaryOperator;

/**
 * NodeAcos
 */
public class NodeAcos extends NodeUnaryOperator {

  public NodeAcos(ETNode argument) {
    super(argument);
  }

  @Override
  public double getValue() {
    return Math.acos(getArgument().getValue());
  }

}
