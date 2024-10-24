package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.ASTNode;
import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.NodeUnaryOperator;

/**
 * NodeActg
 */
public class NodeActg extends NodeUnaryOperator {

  public NodeActg(ASTNode argument) {
    super(argument);
  }

  @Override
  public double compute() {
    return 1 / Math.atan(this.getArgument().getValue());
  }

}
