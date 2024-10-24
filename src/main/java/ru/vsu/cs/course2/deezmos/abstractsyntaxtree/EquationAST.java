package ru.vsu.cs.course2.deezmos.abstractsyntaxtree;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import ru.vsu.cs.course2.deezmos.EquationTokenizer;
import ru.vsu.cs.course2.deezmos.TokenType;
import ru.vsu.cs.course2.deezmos.EquationTokenizer.Token;
import ru.vsu.cs.course2.deezmos.abstractsyntaxtree.binaryops.NodeAddition;

/**
 * EquationAST
 */
public class EquationAST {

  private ASTNode head;

  public EquationAST(ASTNode head) {
    this.head = head;
  }

  public ASTNode getHead() {
    return head;
  }

  public void parse(String equation) {
    EquationTokenizer tokenizer = new EquationTokenizer();
    tokenizer.setData(equation);

    Token token = tokenizer.next();
    Stack<ASTNode> parenStack = new Stack<>();
    Stack<ASTNode> localStack = new Stack<>();

    while (token.type() != TokenType.EOL) {
      switch (token.type()) {
        case NUMBER -> {
          
          
        }
        case PLUS -> {
          
          head = new NodeAddition(left, right)
        }

        default -> {}
      }
      
      token = tokenizer.next();
    }
    for (Token token : tokens) {
      TokenType
      switch (token.type()) {
        case TokenType.:
          
          break;

        default:
          break;
      }
    }
  }
}
