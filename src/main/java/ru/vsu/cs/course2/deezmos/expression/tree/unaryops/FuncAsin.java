package ru.vsu.cs.course2.deezmos.expression.tree.unaryops;

import ru.vsu.cs.course2.deezmos.expression.tree.ETNode;
import ru.vsu.cs.course2.deezmos.expression.tree.Evaluator;

/**
 * FuncAsin
 */
public class FuncAsin implements Evaluator {

  @Override
  public double evaluate(ETNode left, ETNode right) {
    return Math.asin(left.evaluate());
  }
}
