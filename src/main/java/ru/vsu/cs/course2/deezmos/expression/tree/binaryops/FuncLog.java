package ru.vsu.cs.course2.deezmos.expression.tree.binaryops;

import ru.vsu.cs.course2.deezmos.expression.tree.ETNode;
import ru.vsu.cs.course2.deezmos.expression.tree.Evaluator;

/**
 * FuncLog
 */
public class FuncLog implements Evaluator {

  @Override
  public double evaluate(ETNode left, ETNode right) {
    return Math.log(right.evaluate()) / Math.log(left.evaluate());
  }
}
