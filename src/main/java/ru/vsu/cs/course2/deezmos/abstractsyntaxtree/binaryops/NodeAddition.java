package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.binaryops;

import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.ASTNode;
import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.NodeBinaryOperator;

/**
 * NodeAddition
 */
public class NodeAddition extends NodeBinaryOperator {

  public NodeAddition(ASTNode left, ASTNode right) {
    super(left, right);
  }

  @Override
  public double compute() {
    return getLeft().getValue() + getRight().getValue();
  }

}
