package ru.vsu.cs.course2.deezmos.expression.tokenizer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** EquationTokenizer. */
public class ExpressionTokenizer {

  /** Token. */
  public record Token(String value, TokenType type) {
  }

  /**
   * TokenPattern.
   */
  public record TokenPattern(Pattern pattern, TokenType type) {
  }

  private int cursor;
  private String string;

  private static final LinkedList<TokenPattern> tokenPatterns;

  static {
    tokenPatterns = new LinkedList<>(
        List.of(
            new TokenPattern(Pattern.compile("\\s+"), TokenType.SPACE),
            new TokenPattern(Pattern.compile("-?\\d+(?:\\.\\d+)?"), TokenType.NUMBER),
            new TokenPattern(Pattern.compile("\\("), TokenType.L_BRACKET),
            new TokenPattern(Pattern.compile("\\)"), TokenType.R_BRACKET),
            new TokenPattern(Pattern.compile("\\|"), TokenType.LINE),
            new TokenPattern(Pattern.compile(","), TokenType.COMMA),
            new TokenPattern(Pattern.compile("\\+"), TokenType.PLUS),
            new TokenPattern(Pattern.compile("\\-"), TokenType.MINUS),
            new TokenPattern(Pattern.compile("\\*"), TokenType.MULT),
            new TokenPattern(Pattern.compile("\\^"), TokenType.POW),
            new TokenPattern(Pattern.compile("\\/"), TokenType.DIVISION),
            new TokenPattern(Pattern.compile("log"), TokenType.LOG),
            new TokenPattern(Pattern.compile("abs"), TokenType.ABS),
            new TokenPattern(Pattern.compile("sin"), TokenType.SIN),
            new TokenPattern(Pattern.compile("cos"), TokenType.COS),
            new TokenPattern(Pattern.compile("tg"), TokenType.TG),
            new TokenPattern(Pattern.compile("ctg"), TokenType.CTG),
            new TokenPattern(Pattern.compile("asin"), TokenType.ASIN),
            new TokenPattern(Pattern.compile("acos"), TokenType.ACOS),
            new TokenPattern(Pattern.compile("atg"), TokenType.ATG),
            new TokenPattern(Pattern.compile("actg"), TokenType.ACTG),
            new TokenPattern(Pattern.compile("ln"), TokenType.LN),
            new TokenPattern(Pattern.compile("lg"), TokenType.LG),
            new TokenPattern(Pattern.compile("sqrt"), TokenType.SQRT),
            new TokenPattern(Pattern.compile("e"), TokenType.EULER_CONST),
            new TokenPattern(Pattern.compile("pi"), TokenType.PI_CONST),
            new TokenPattern(Pattern.compile("[a-zA-Z]+"), TokenType.VARIABLE)));
  }

  public ExpressionTokenizer(String string) {
    this.string = string.toLowerCase();
    this.cursor = 0;
  }

  public Token next() throws IOException {
    if (!this.hasNext()) {
      return new Token("<EOL>", TokenType.EOL);
    }

    Matcher matcher;

    for (TokenPattern tokenPattern : tokenPatterns) {
      matcher = tokenPattern.pattern().matcher(string);

      if (!matcher.find(cursor)) {
        continue;
      }

      if (matcher.start() != cursor) {
        continue;
      }

      this.cursor = matcher.end();

      if (tokenPattern.type() == TokenType.SPACE || tokenPattern.type() == TokenType.COMMA) {
        return this.next();
      }

      return new Token(string.substring(matcher.start(), matcher.end()), tokenPattern.type());
    }

    throw new IOException(String.format("Tokenization error: What the sigma is %s", string.substring(cursor)));
  }

  public boolean hasNext() {
    return this.cursor != this.string.length();
  }
}
