package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.ASTNode;
import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.NodeUnaryOperator;

/**
 * NodeAtg
 */
public class NodeAtg extends NodeUnaryOperator {

  public NodeAtg(ASTNode argument) {
    super(argument);
  }

  @Override
  public double compute() {
    return Math.atan(this.getArgument().getValue());
  }

}
