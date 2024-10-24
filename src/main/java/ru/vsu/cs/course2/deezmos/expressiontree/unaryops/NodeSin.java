package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeUnaryOperator;

/**
 * NodeSin
 */
public class NodeSin extends NodeUnaryOperator {

  public NodeSin(ETNode argument) {
    super(argument);
  }

  @Override
  public double getValue() {
    return Math.sin(this.getArgument().getValue());
  }

}
