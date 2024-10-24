package ru.vsu.cs.course2.deezmos.abstractsyntaxtree.binaryops;

import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.ASTNode;
import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.NodeBinaryOperator;

/**
 * NodeLog
 */
public class NodeLog extends NodeBinaryOperator {

  public NodeLog(ASTNode argument, ASTNode base) {
    super(argument, base);
  }

  @Override
  public double compute() {
    return Math.log(getRight().getValue()) / Math.log(getLeft().getValue());
  }

}
