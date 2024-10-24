package ru.vsu.cs.course2.deezmos.expressiontree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeUnaryOperator;

/**
 * NodeAbs
 */
public class NodeAbs extends NodeUnaryOperator {

  public NodeAbs(ETNode argument) {
    super(argument);
  }

  @Override
  public double getValue() {
    return Math.abs(getArgument().getValue());
  }

}
