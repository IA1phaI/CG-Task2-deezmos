package ru.vsu.cs.course2.deezmos.expressiontree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeUnaryOperator;

/**
 * NodeCos
 */
public class NodeCos extends NodeUnaryOperator {

  @Override
  public double getValue() {
    return Math.cos(getArgument().getValue());
  }

}
