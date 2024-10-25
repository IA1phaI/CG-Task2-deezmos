package ru.vsu.cs.course2.deezmos.expressiontree.binaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.Evaluator;

/**
 * FuncPow
 */
public class FuncPow implements Evaluator {

  @Override
  public double evaluate(ETNode left, ETNode right) {
    return Math.pow(left.evaluate(), right.evaluate());
  }
}
