package ru.vsu.cs.course2.deezmos.expressiontree.binaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.Evaluator;

/**
 * FuncLog
 */
public class FuncLog implements Evaluator {

  @Override
  public double evaluate(ETNode left, ETNode right) {
    // TODO Auto-generated method stub
    return Math.log(left.evaluate()) / Math.log(right.evaluate());
  }
}
