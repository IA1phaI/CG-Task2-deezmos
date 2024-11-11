package ru.vsu.cs.course2.deezmos.expression.tree.unaryops;

import ru.vsu.cs.course2.deezmos.expression.tree.ETNode;
import ru.vsu.cs.course2.deezmos.expression.tree.Evaluator;

/**
 * FuncLg
 */
public class FuncLg implements Evaluator {

  @Override
  public double evaluate(ETNode left, ETNode right) {
    double argument = left.evaluate();
    if (Math.abs(argument) < 0.01) {
      argument = 0;
    }
    return Math.log10(argument);
  }
}
