package ru.vsu.cs.course2.deezmos.expressiontree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.NodeUnaryOperator;

/**
 * NodeParenteses
 */
public class NodeParentheses extends NodeUnaryOperator {

  public NodeParentheses() {
    super();
  }

  @Override
  public double getValue() {
    return getArgument().getValue();
  }

}
