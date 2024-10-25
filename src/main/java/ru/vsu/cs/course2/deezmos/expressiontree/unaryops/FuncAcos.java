package ru.vsu.cs.course2.deezmos.expressiontree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.Evaluator;

/**
 * FuncAcos
 */
public class FuncAcos implements Evaluator {

  @Override
  public double evaluate(ETNode left, ETNode right) {
    return Math.acos(left.evaluate());
  }
}
