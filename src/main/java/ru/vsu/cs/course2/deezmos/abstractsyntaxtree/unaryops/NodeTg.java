package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.unaryops;

import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.ASTNode;
import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.NodeUnaryOperator;

/**
 * NodeTg
 */
public class NodeTg extends NodeUnaryOperator {

  public NodeTg(ASTNode argument) {
    super(argument);
  }

  @Override
  public double compute() {
    return Math.tan(this.getArgument().getValue());
  }

}
