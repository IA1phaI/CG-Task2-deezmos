package ru.vsu.cs.course2.deezmos.expressiontree;

/**
 * NodeOperator
 */
public abstract class NodeUnaryOperator implements ETNode {

  private ETNode argument;

  public NodeUnaryOperator(ETNode child) {
    this.argument = child;
  }

  public NodeUnaryOperator() {
    this(null);
  }

  public ETNode getArgument() {
    return argument;
  }

  public void setArgument(ETNode child) {
    this.argument = child;
  }

  @Override
  public void pushChild(ETNode child) {
    setArgument(child);
  }
}
