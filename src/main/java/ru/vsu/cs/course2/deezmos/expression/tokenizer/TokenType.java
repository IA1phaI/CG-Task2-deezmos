package ru.vsu.cs.course2.deezmos.expression.tokenizer;

/** Tokens of equation. */
public enum TokenType {
  SPACE,
  NUMBER,
  POSITIVE_VARIABLE,
  NEGATIVE_VARIABLE,
  EULER_CONST,
  PI_CONST,
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
  SQRT,
  EOL;

  public enum OperatorType {
    UNARY_OPERATOR,
    BINARY_OPERATOR,
    NON_OPERATOR;
  }

  public int precedence() {
    switch (this) {
      case SPACE, COMMA, EOL, L_BRACKET, R_BRACKET -> {
        return -1;
      }
      case PLUS, MINUS -> {
        return 0;
      }
      case MULT, DIVISION -> {
        return 1;
      }
      default -> {
        return 2;
      }
    }
  }

  public OperatorType getOperatorType() {
    switch (this) {
      case PLUS, MINUS, MULT, DIVISION, POW, LOG -> {
        return OperatorType.BINARY_OPERATOR;
      }
      case ABS, SIN, COS, TG, CTG, ASIN, ACOS, ATG, ACTG, LN, LG, SQRT -> {
        return OperatorType.UNARY_OPERATOR;
      }
      default -> {
        return OperatorType.NON_OPERATOR;
      }
    }
  }
}
