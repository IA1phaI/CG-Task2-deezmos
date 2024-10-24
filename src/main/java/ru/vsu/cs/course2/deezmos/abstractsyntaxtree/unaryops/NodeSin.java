package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.ASTNode;
import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.NodeUnaryOperator;

/**
 * NodeSin
 */
public class NodeSin extends NodeUnaryOperator {

  public NodeSin(ASTNode argument) {
    super(argument);
  }

  @Override
  public double compute() {
    return Math.sin(this.getArgument().getValue());
  }

}
