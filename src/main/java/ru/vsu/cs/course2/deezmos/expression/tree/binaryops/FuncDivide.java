package ru.vsu.cs.course2.deezmos.expression.tree.binaryops;

import ru.vsu.cs.course2.deezmos.expression.tree.ETNode;
import ru.vsu.cs.course2.deezmos.expression.tree.Evaluator;

/**
 * FuncDivide
 */
public class FuncDivide implements Evaluator {

  @Override
  public double evaluate(ETNode left, ETNode right) {
    double divider = right.evaluate();
    if (Math.abs(divider) < 0.05) {
      divider = 0;
    }

    return left.evaluate() / divider;
  }
}
