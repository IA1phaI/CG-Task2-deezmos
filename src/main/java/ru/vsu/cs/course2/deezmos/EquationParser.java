package ru.vsu.cs.course2.deezmos;

import java.io.IOException;

import ru.vsu.cs.course2.deezmos.ExpressionTokenizer.Token;

/** EquationParser. */
public class EquationParser {
  private ExpressionTokenizer tokenizer;
  private Token currentToken;

  public EquationParser(ExpressionTokenizer tokenizer) {
    this.tokenizer = tokenizer;
  }

  public void setData(String string) throws IOException {
    this.tokenizer.setData(string);
    this.currentToken = this.tokenizer.next();
    //return this.expression();
  }
}
