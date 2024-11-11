package ru.vsu.cs.course2.deezmos.expression.tree.unaryops;

import ru.vsu.cs.course2.deezmos.expression.tree.ETNode;
import ru.vsu.cs.course2.deezmos.expression.tree.Evaluator;

/**
 * FuncTg
 */
public class FuncTg implements Evaluator {

  @Override
  public double evaluate(ETNode left, ETNode right) {
    double value = left.evaluate();
    if (Math.abs(Math.abs(value) % Math.PI - (Math.PI / 2)) < 0.05) {
      return Double.NaN;
    }

    return Math.tan(value);
  }
}
