package ru.vsu.cs.course2.deezmos.expressiontree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.Evaluator;

/**
 * FuncCos
 */
public class FuncCos implements Evaluator {

  @Override
  public double evaluate(ETNode left, ETNode right) {
    return Math.cos(left.evaluate());
  }
}
