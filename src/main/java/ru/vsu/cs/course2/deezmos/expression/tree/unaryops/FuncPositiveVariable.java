package ru.vsu.cs.course2.deezmos.expression.tree.unaryops;

import java.util.HashMap;

import ru.vsu.cs.course2.deezmos.expression.tree.ETNode;
import ru.vsu.cs.course2.deezmos.expression.tree.Evaluator;

/**
 * FuncVariable
 */
public class FuncPositiveVariable implements Evaluator {

  private String variable;
  private HashMap<String, Double> variables;

  public FuncPositiveVariable(String variable, HashMap<String, Double> variables) {
    this.variable = variable;
    this.variables = variables;
  }

  protected HashMap<String, Double> getVariables() {
    return variables;
  }

  protected String getVariable() {
    return variable;
  }

  @Override
  public double evaluate(ETNode left, ETNode right) {
    return getVariables().get(getVariable());
  }
}
