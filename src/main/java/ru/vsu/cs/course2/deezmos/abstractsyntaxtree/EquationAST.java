package ru.vsu.cs.course2.deezmos.abstractsyntaxtree;

import java.util.HashMap;
import java.util.List;

import ru.vsu.cs.course2.deezmos.EquationTokenizer.Token;

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

  public void parse(List<Token> tokens, HashMap<String, Double> params) {
    for (Token token : tokens) {
    }
  }
}
