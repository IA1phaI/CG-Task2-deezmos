package ru.vsu.cs.course2.deezmos.expressiontree.unaryops;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.Evaluator;

/**
 * FuncNumber
 */
public class FuncNumber implements Evaluator {

  private double value;

  public FuncNumber(double value) {
    this.value = value;
  }

  @Override
  public double evaluate(ETNode left, ETNode right) {
    return this.value;
  }
}
