package ru.vsu.cs.course2.deezmos.expression.tree.unaryops;

import java.util.HashMap;

import ru.vsu.cs.course2.deezmos.expression.tree.ETNode;

/**
 * FuncNegativeVariable
 */
public class FuncNegativeVariable extends FuncPositiveVariable {

  public FuncNegativeVariable(String variable, HashMap<String, Double> variables) {
    super(variable, variables);
  }

  @Override
  public double evaluate(ETNode left, ETNode right) {
    return -getVariables().get(getVariable());
  }
}
