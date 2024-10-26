package ru.vsu.cs.course2.deezmos.expressiontree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.Evaluator;

/**
 * FuncSin
 */
public class FuncSin implements Evaluator {

  @Override
  public double evaluate(ETNode left, ETNode right) {
    return Math.sin(left.evaluate());
  }
}
