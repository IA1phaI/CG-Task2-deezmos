package ru.vsu.cs.course2.deezmos.expressiontree;

import java.util.LinkedList;
import java.util.Stack;

import ru.vsu.cs.course2.deezmos.ExpressionTokenizer;
import ru.vsu.cs.course2.deezmos.TokenType;
import ru.vsu.cs.course2.deezmos.ExpressionTokenizer.Token;

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

    Stack<ETreeNode> operandStack = new Stack<>();
    ETreeNode currentNode = new ETreeNode();
      
    for (Token token : tokens) {
      switch (token.type()) {
        case L_PAREN -> {
          ETreeNode childNode = new ETreeNode();
          currentNode.setLeft(left);
        }
        case R_PAREN -> {
          while (condition) {
            
          }
          
        }
          
          break;

        default:
          break
      }
    }

  }

  private popOperator(Stack<NodeOperator> operatorStack, Stack<ETreeNode> operandStack) {
    ETreeNode node = operatorStack.pop();
    node.setRight(operandStack.pop());
    node.setLeft(operandStack.pop());
    operandStack.push(node);
  }

  public double evaluate() {
    return 0;
  }
}
