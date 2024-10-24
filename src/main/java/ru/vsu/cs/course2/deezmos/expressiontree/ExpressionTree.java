package ru.vsu.cs.course2.deezmos.expressiontree;

import java.util.LinkedList;
import java.util.Stack;

import ru.vsu.cs.course2.deezmos.ExpressionTokenizer;
import ru.vsu.cs.course2.deezmos.TokenType;
import ru.vsu.cs.course2.deezmos.ExpressionTokenizer.Token;
import ru.vsu.cs.course2.deezmos.expressiontree.unaryops.NodeParentheses;

/**
 * EpressionTree
 */
public class ExpressionTree {

  private ETreeNode root;
  private String expession;

  public ExpressionTree(String expression) {
    this.expession = expession;
  }

  public void parse() {
    ExpressionTokenizer tokenizer = new ExpressionTokenizer();
    tokenizer.setData(expession);
    LinkedList<Token> tokens = new LinkedList<>();
    while (tokenizer.hasNext()) {
      tokens.add(tokenizer.next());
    }

    Stack<ETreeNode> stack = new Stack<>();
    ETreeNode currentNode = new ETreeNode();

    for (Token token : tokens) {
      switch (token.type()) {
        case L_PAREN -> {
          ETreeNode child = new NodeParentheses();
          stack.push(currentNode);
          currentNode = childNode;
        }
        case NUMBER -> {
          ETreeNode child = new NodeNumber(token.value());
          currentNode.pushChild(child);
          currentNode = stack.pop();
        }
        case WORD -> {
          ETreeNode child = recognizeOperator
        }
        case R_PAREN -> {
          currentNode = stack.pop();
        }
      }
    }
  }

  private void popOperator(Stack<NodeBinaryOperator> operatorStack, Stack<ETreeNode> operandStack) {
    ETreeNode node = operatorStack.pop();
    node.setRight(operandStack.pop());
    node.setArgument(operandStack.pop());
    operandStack.push(node);
  }

  public double evaluate() {
    return 0;
  }
}
