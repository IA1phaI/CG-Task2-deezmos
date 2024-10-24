package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.ASTNode;
import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.NodeUnaryOperator;

/**
 * NodeCtg
 */
public class NodeCtg extends NodeUnaryOperator {

  public NodeCtg(ASTNode argument) {
    super(argument);
  }

  @Override
  public double compute() {
    return 1 / Math.tan(this.getArgument().getValue());
  }

}
