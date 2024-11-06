package ru.vsu.cs.course2.deezmos.expression.tree.unaryops;

import ru.vsu.cs.course2.deezmos.expression.tree.ETNode;
import ru.vsu.cs.course2.deezmos.expression.tree.Evaluator;

/**
 * FuncActg
 */
public class FuncActg implements Evaluator {

  @Override
  public double evaluate(ETNode left, ETNode right) {
    return Math.PI / 2 - Math.atan(left.evaluate());
  }
}
