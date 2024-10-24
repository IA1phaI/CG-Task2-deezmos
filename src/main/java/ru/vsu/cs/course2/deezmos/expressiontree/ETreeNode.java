package ru.vsu.cs.course2.deezmos.expressiontree;

/**
 * ETreeNode
 */
public interface ETreeNode {

  double getValue();

  void pushChild(ETreeNode child);
}
