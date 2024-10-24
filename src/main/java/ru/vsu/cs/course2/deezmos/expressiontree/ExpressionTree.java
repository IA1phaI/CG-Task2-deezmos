package ru.vsu.cs.course2.deezmos.expressiontree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

import ru.vsu.cs.course2.deezmos.ExpressionTokenizer;
import ru.vsu.cs.course2.deezmos.TokenType;
import ru.vsu.cs.course2.deezmos.ExpressionTokenizer.Token;
import ru.vsu.cs.course2.deezmos.expressiontree.unaryops.NodeCos;
import ru.vsu.cs.course2.deezmos.expressiontree.unaryops.NodeParentheses;

/**
 * EpressionTree
 */
public class ExpressionTree {

  private HashMap<String, Double> paramValues;

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

    Stack<ETNode> stack = new Stack<>();
    ETNode currentNode = new ETreeNode();

    for (Token token : tokens) {
      if (token.type() == TokenType.L_PAREN) {
        ETNode child = new NodeParentheses();
        stack.push(currentNode);
        currentNode = child;
      } else if (token.type() == TokenType.NUMBER) {
        ETNode child = new NodeNumber(token.value());
        currentNode.pushChild(child);
        currentNode = stack.pop();
      } else if (token.type() == TokenType.PARAM) {
        ETNode child = new NodeNumber(paramValues.get(token.value()));
        stack.push(currentNode);
        currentNode = child;
      } else if (token.type() == TokenType.R_PAREN) {
        currentNode = stack.pop();
      }
      if (token.type() == TokenType.PARAM) {
        // TODO:
        ETNode child = recognizeOperator(token.type());
      } else if (token.type() == TokenType.R_PAREN) {
        currentNode = stack.pop();
      }
    }

  }

  public ETNode recognizeOperator(TokenType tokenType) {
    NodeCos node = new NNo
    return new Nod;
  }
}
