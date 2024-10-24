package ru.vsu.cs.course2.deezmos;

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

  private LinkedList<Token> tokens;
  private int cursor;
  private String string;

  private static final LinkedList<TokenPattern> tokenPatterns;

  public ExpressionTokenizer() {
    this.tokens = new LinkedList<>();
    this.cursor = 0;
  }

  public void setData(String string) {
    this.string = string;
  }

  static {
    tokenPatterns = new LinkedList<>(
        List.of(
            new TokenPattern(Pattern.compile("^\\s+"), TokenType.SPACE),
            new TokenPattern(Pattern.compile("^-?\\d+(?:\\.\\d+)?"), TokenType.NUMBER),
            new TokenPattern(Pattern.compile("^\\("), TokenType.L_PAREN),
            new TokenPattern(Pattern.compile("^\\)"), TokenType.R_PAREN),
            new TokenPattern(Pattern.compile("^\\|"), TokenType.LINE),
            new TokenPattern(Pattern.compile("^,"), TokenType.COMMA),
            new TokenPattern(Pattern.compile("^\\+"), TokenType.PLUS),
            new TokenPattern(Pattern.compile("^\\-"), TokenType.MINUS),
            new TokenPattern(Pattern.compile("^\\*"), TokenType.MULT),
            new TokenPattern(Pattern.compile("^\\^"), TokenType.POW),
            new TokenPattern(Pattern.compile("^\\/"), TokenType.DIVISION),
            new TokenPattern(Pattern.compile("^[a-zA-Z]+"), TokenType.WORD)));
  }

  public Token next() throws IOException {
    if (!this.hasNext()) {
      return new Token("<EOL>", TokenType.EOL);
    }

    String substr = this.string.substring(cursor, string.length());
    Matcher matcher;

    for (TokenPattern tokenPattern : tokenPatterns) {
      matcher = tokenPattern.pattern().matcher(substr);

      if (!matcher.find()) {
        continue;
      }

      this.cursor += matcher.end();

      if (tokenPattern.type() == TokenType.SPACE) {
        return this.next();
      }

      return new Token(substr.substring(matcher.start(), matcher.end()), tokenPattern.type());
    }

    throw new IOException(String.format("Tokenization error: What the sigma is %s", substr));
  }

  public boolean hasNext() {
    return this.cursor != this.string.length();
  }
}