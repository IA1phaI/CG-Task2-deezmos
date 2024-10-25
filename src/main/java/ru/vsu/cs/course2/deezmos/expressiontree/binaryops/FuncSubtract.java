package ru.vsu.cs.course2.deezmos.expressiontree.binaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.Evaluator;

/**
 * FuncSubtract
 */
public class FuncSubtract implements Evaluator {

  @Override
  public double evaluate(ETNode left, ETNode right) {
    return left.evaluate() - right.evaluate();
  }
}
