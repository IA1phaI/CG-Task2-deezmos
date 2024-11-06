package ru.vsu.cs.course2.deezmos.expression.tree;

/**
 * Evaluator
 */
public interface Evaluator {
  double evaluate(ETNode left, ETNode right);
}
