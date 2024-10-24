package ru.vsu.cs.course2.deezmos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/** Tokens of equation. */
public enum TokenType {
  SPACE,
  NUMBER,
  PARAM,
  X,
  L_PAREN,
  R_PAREN,
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

  public HashSet<TokenType> getUnaryOperators() {
    return new HashSet<>(Set.of(ABS, SIN, COS, TG, CTG, ASIN, ACOS, ATG, ACTG, LN, LG));
  }

  public HashSet<TokenType> getBynaryOperators() {
    return new HashSet<>(Set.of(PLUS, MINUS, MULT, DIVISION, POW, LOG));
  }
}
