package ru.vsu.cs.course2.deezmos.expressiontree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.Evaluator;

/**
 * NodeCtg
 */
public class FuncCtg implements Evaluator {

  @Override
  public double evaluate(ETNode left, ETNode right) {
    return 1 / Math.tan(left.evaluate());
  }
}
