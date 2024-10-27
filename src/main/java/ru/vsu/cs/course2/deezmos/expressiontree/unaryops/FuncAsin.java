package ru.vsu.cs.course2.deezmos.expressiontree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.Evaluator;

/**
 * FuncAsin
 */
public class FuncAsin implements Evaluator {

  @Override
  public double evaluate(ETNode left, ETNode right) {
    return Math.asin(left.evaluate());
  }
}
