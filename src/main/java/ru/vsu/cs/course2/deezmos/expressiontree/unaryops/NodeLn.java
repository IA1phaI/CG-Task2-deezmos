package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeUnaryOperator;

/**
 * NodeLn
 */
public class NodeLn extends NodeUnaryOperator {

  public NodeLn(ETNode argument) {
    super(argument);
  }

  @Override
  public double getValue() {
    return Math.log(getArgument().getValue());
  }

}
