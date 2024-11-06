package ru.vsu.cs.course2.deezmos.expression.tree.binaryops;

import ru.vsu.cs.course2.deezmos.expression.tree.ETNode;
import ru.vsu.cs.course2.deezmos.expression.tree.Evaluator;

/**
 * FuncPow
 */
public class FuncPow implements Evaluator {

  @Override
  public double evaluate(ETNode left, ETNode right) {
    return Math.pow(left.evaluate(), right.evaluate());
  }
}
