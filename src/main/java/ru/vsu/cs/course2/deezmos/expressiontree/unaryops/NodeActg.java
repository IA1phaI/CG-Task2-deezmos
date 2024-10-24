package ru.vsu.cs.course2.deezmos.expressiontree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeUnaryOperator;

/**
 * NodeActg
 */
public class NodeActg extends NodeUnaryOperator {

  public NodeActg(ETNode argument) {
    super(argument);
  }

  @Override
  public double getValue() {
    return 1 / Math.atan(this.getArgument().getValue());
  }

}
