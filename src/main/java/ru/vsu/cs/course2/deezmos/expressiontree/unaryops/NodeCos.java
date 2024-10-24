package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeUnaryOperator;

/**
 * NodeCos
 */
public class NodeCos extends NodeUnaryOperator {

  public NodeCos(ETNode argument) {
    super(argument);
  }

  @Override
  public double getValue() {
    return Math.cos(getArgument().getValue());
  }

}
