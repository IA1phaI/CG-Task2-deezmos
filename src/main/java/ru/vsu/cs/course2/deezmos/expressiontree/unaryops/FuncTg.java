package ru.vsu.cs.course2.deezmos.expressiontree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.Evaluator;
import ru.vsu.cs.course2.deezmos.expressiontree.NodeUnaryOperator;

/**
 * FuncTg
 */
public class FuncTg implements Evaluator {

  @Override
  public double evaluate(ETNode left, ETNode right) {
    return Math.tan(left.evaluate());
  }
}
