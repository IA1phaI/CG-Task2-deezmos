package ru.vsu.cs.course2.deezmos.expressiontree;

/**
 * Evaluator
 */
public interface Evaluator {
  double evaluate(ETNode left, ETNode right);
}
