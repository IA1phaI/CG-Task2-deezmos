package ru.vsu.cs.course2.deezmos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** EquationTokenizer. */
public class EquationTokenizer {

  private final ArrayList<Pattern> patterns;

  private static final Pattern numberPattern = Pattern.compile("^-?\\d+(?:\\.\\d+)?");
  private static final Pattern varPattern = Pattern.compile("[a-zA-Z]+");
  private static final Pattern spacePattern = Pattern.compile("\\s+");

  public EquationTokenizer(Set<String> simpleTokens) {
    patterns = new ArrayList<>();
    for (String token : simpleTokens) {
      patterns.add(Pattern.compile("^" + token));
      patterns.add(numberPattern);
      patterns.add(varPattern);
      patterns.add(spacePattern);
    }
  }

  public ArrayList<String> tokenize(String string) {
    string = string.toLowerCase();
    ArrayList<String> tokensList = new ArrayList<>();
    int cursor = 0;

    while (cursor < string.length()) {
      String substring = string.substring(cursor);
      for (Pattern pattern : patterns) {
        Matcher matcher = pattern.matcher(substring);

        if (matcher.find()) {
          String token = substring.substring(matcher.start(), matcher.end());
          tokensList.add(token);

          if (token.charAt(0) != ' ') {
            cursor += token.length();
          }
          break;
        }

      }
      
    }

    if (cursor != string.length() - 1) {
      System.out.println("err");
    }
    return tokensList;
  }
}
