package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.ASTNode;
import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.NodeUnaryOperator;
import ru.vsu.cs.course2.deezmos.expressiontree.ETreeNode;

/**
 * NodeCos
 */
public class NodeCos extends NodeUnaryOperator {

  public NodeCos(ETreeNode argument) {
    super(argument);
  }

  @Override
  public double getValue() {
    return Math.cos(getArgument().getValue());
  }

}
