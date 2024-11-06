package ru.vsu.cs.course2.deezmos.expression.tree.binaryops;

import ru.vsu.cs.course2.deezmos.expression.tree.ETNode;
import ru.vsu.cs.course2.deezmos.expression.tree.Evaluator;

/**
 * FuncDivide
 */
public class FuncDivide implements Evaluator {

  @Override
  public double evaluate(ETNode left, ETNode right) {
    return left.evaluate() / right.evaluate();
  }
}
