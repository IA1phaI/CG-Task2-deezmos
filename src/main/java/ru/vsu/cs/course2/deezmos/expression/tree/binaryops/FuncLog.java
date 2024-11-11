package ru.vsu.cs.course2.deezmos.expression.tree.binaryops;

import ru.vsu.cs.course2.deezmos.expression.tree.ETNode;
import ru.vsu.cs.course2.deezmos.expression.tree.Evaluator;

/**
 * FuncLog
 */
public class FuncLog implements Evaluator {

  @Override
  public double evaluate(ETNode left, ETNode right) {
    double leftArg = left.evaluate();
    if (Math.abs(leftArg) < 0.01) {
      leftArg = 0;
    }

    double rightArg = right.evaluate();
    if (Math.abs(rightArg) < 0.01) {
      rightArg = 0;
    }
    return Math.log(rightArg) / Math.log(leftArg);
  }
}
