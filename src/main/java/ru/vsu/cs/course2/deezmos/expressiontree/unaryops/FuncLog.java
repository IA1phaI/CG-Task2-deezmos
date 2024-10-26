package ru.vsu.cs.course2.deezmos.expressiontree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.Evaluator;

/**
 * FuncLog
 */
public class FuncLog implements Evaluator {

  @Override
  public double evaluate(ETNode left, ETNode right) {
    return Math.log10(left.evaluate());
  }
}
