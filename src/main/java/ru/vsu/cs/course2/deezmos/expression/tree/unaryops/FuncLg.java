package ru.vsu.cs.course2.deezmos.expression.tree.unaryops;

import ru.vsu.cs.course2.deezmos.expression.tree.ETNode;
import ru.vsu.cs.course2.deezmos.expression.tree.Evaluator;

/**
 * FuncLg
 */
public class FuncLg implements Evaluator {

  @Override
  public double evaluate(ETNode left, ETNode right) {
    return Math.log10(left.evaluate());
  }
}
