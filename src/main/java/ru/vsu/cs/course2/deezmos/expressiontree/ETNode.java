package ru.vsu.cs.course2.deezmos.expressiontree;

/**
 * ETreeNode
 */
public interface ETNode {

  double getValue();

  void pushChild(ETNode child);
}
