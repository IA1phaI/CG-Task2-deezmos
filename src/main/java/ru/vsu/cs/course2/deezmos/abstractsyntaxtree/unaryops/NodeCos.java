package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.ASTNode;
import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.NodeUnaryOperator;

/**
 * NodeCos
 */
public class NodeCos extends NodeUnaryOperator {

  public NodeCos(ASTNode argument) {
    super(argument);
  }

  @Override
  public double compute() {
    return Math.cos(getArgument().getValue());
  }

}
