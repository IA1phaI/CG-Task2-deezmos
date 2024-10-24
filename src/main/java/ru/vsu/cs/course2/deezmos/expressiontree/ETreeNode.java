package ru.vsu.cs.course2.deezmos.expressiontree;

/**
 * ETreeNode
 */
public interface ETreeNode {

  double getValue();

  ETreeNode getLeft();
  void setLeft(ETreeNode left);
  boolean hasLeft();

  ETreeNode getRight();
  void setRight(ETreeNode right);
  boolean hasRight();
}
