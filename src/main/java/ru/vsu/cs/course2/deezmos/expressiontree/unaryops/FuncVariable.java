package ru.vsu.cs.course2.deezmos.expressiontree.unaryops;

import java.util.HashMap;

import ru.vsu.cs.course2.deezmos.expressiontree.ETNode;
import ru.vsu.cs.course2.deezmos.expressiontree.Evaluator;

/**
 * FuncVariable
 */
public class FuncVariable implements Evaluator {

  private String variable;
  private HashMap<String, Double> variables;

  public FuncVariable(String variable, HashMap<String, Double> variables) {
    this.variable = variable;
    this.variables = variables;
  }

  @Override
  public double evaluate(ETNode left, ETNode right) {
    return variables.get(variable);
  }
}
