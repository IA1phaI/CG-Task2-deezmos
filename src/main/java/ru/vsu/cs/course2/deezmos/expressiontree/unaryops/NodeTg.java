package ru.vsu.cs.course2.deezmos.expressiontree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeUnaryOperator;

/**
 * NodeTg
 */
public class NodeTg extends NodeUnaryOperator {

  public NodeTg(ETNode argument) {
    super(argument);
  }

  @Override
  public double getValue() {
    return Math.tan(this.getArgument().getValue());
  }

}
