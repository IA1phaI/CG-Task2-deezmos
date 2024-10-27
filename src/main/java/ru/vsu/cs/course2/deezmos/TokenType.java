package ru.vsu.cs.course2.deezmos;

/** Tokens of equation. */
public enum TokenType {
  SPACE,
  NUMBER,
  VARIABLE,
  L_BRACKET,
  R_BRACKET,
  LINE,
  COMMA,
  PLUS,
  MINUS,
  MULT,
  POW,
  DIVISION,
  LOG,
  ABS,
  SIN,
  COS,
  TG,
  CTG,
  ASIN,
  ACOS,
  ATG,
  ACTG,
  LN,
  LG,
  EOL;

  public int precedence() {
    switch (this) {
      case SPACE, COMMA, EOL, L_BRACKET, R_BRACKET -> {
        return -1;
      }
      case PLUS, MINUS -> {
        return 0;
      }
      default -> {
        return 1;
      }
    }
  }
}
