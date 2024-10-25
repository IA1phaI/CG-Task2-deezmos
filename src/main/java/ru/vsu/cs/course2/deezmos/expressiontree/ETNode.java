package ru.vsu.cs.course2.deezmos.expressiontree;

/**
 * ETreeNode
 */
public class ETNode {

  private ETNode left;
  private ETNode right;
  private Evaluator evaluator;

  public double evaluate() {
    return evaluator.evaluate(left, right);
  };

  public ETNode getLeft() {
    return left;
  }

  public ETNode getRight() {
    return right;
  }

  public void setEvaluator(Evaluator evaluator) {
    this.evaluator = evaluator;
  }

  public void pushChild(ETNode child) {

    if (!this.hasLeft()) {
      this.setLeft(child);
      return;
    }
    this.setRight(child);
  }

  public boolean hasLeft() {
    return this.left != null;
  }

  public boolean hasRight() {
    return this.right != null;
  }

  public void setLeft(ETNode left) {
    this.left = left;
  }

  public void setRight(ETNode right) {
    this.right = right;
  }
}
