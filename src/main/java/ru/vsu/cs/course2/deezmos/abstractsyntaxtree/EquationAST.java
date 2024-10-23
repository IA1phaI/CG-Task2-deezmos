package ru.vsu.cs.course2.deezmos.abstractsyntaxtree;

import java.util.HashMap;
import java.util.List;

import ru.vsu.cs.course2.deezmos.EquationTokenizer.Token;

/**
 * EquationAST
 */
public class EquationAST {

  private ASTNodeI head;

  public EquationAST(ASTNodeI head) {
    this.head = head;
  }

  public ASTNodeI getHead() {
    return head;
  }

  public void parse(List<Token> tokens, HashMap<String, Double> params) {
    for (Token token : tokens) {
      switch (token.type()) {
        case TokenType:
          
          break;

        default:
          break;
      }
    }
  }
}
