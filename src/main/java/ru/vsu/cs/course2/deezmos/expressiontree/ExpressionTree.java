package ru.vsu.cs.course2.deezmos.expressiontree;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import ru.vsu.cs.course2.deezmos.ExpressionTokenizer;
import ru.vsu.cs.course2.deezmos.ExpressionTokenizer.Token;
import ru.vsu.cs.course2.deezmos.TokenType;
import ru.vsu.cs.course2.deezmos.expressiontree.binaryops.FuncAdd;
import ru.vsu.cs.course2.deezmos.expressiontree.unaryops.FuncNumber;

/**
 * EpressionTree
 */
public class ExpressionTree {

  private static final HashSet<TokenType> UNARY_OPERATORS = new HashSet<>(
      Set.of(TokenType.ABS, TokenType.SIN, TokenType.COS, TokenType.TG, TokenType.CTG, TokenType.ASIN, TokenType.ACOS,
          TokenType.ATG, TokenType.ACTG, TokenType.LN, TokenType.LG));

  private static final HashSet<TokenType> BINARY_OPERATOR = new HashSet<>(
      Set.of(TokenType.PLUS, TokenType.MINUS, TokenType.MULT, TokenType.DIVISION, TokenType.POW, TokenType.LOG));

  private HashMap<String, Double> paramValues;

  private ETNode root;
  List<Token> tokens;
  HashMap<String, Double> variableValues;

  public ExpressionTree(String expression) throws IOException {
    tokens = tokenize(expression);
    root = new ETNode();
  }

  public List<Token> tokenize(String expression) throws IOException {
    ExpressionTokenizer tokenizer = new ExpressionTokenizer();
    LinkedList<Token> tokens = new LinkedList<>();
    variableValues = new HashMap<>();

    while (tokenizer.hasNext()) {
      Token token = tokenizer.next();

      if (token.type() == TokenType.PARAM) {
        variableValues.put(token.value(), 1.0);
      }

      tokens.add(tokenizer.next());
    }

    return tokens;
  }

  public void setVariableValue(String variable, double value) throws IOException {
    variable = variable.toLowerCase();

    if (hasVariable(variable)) {
      throw new IOException(String.format("No variable \"%s\" in current expression", variable));
    }

    variableValues.put(variable, value);
  }

  public boolean hasVariable(String variable) {
    variable = variable.toLowerCase();
    return variableValues.containsKey(variable);
  }

  public void parse() throws IOException {

    Stack<ETNode> stack = new Stack<>();
    stack.push(root);
    ETNode currentNode = root;

    try {
      for (Token token : tokens) {
        if (token.type() == TokenType.L_PAREN) {
          ETNode newNode = new ETNode();
          currentNode.setLeft(newNode);
          stack.push(currentNode);
          currentNode = newNode;

        } else if (token.type() == TokenType.NUMBER) {
          currentNode.setEvaluator(new FuncNumber(Double.parseDouble(token.value())));
          currentNode = stack.pop();
        } else if (token.type() == TokenType.PARAM) {
          currentNode.setEvaluator(new FuncNumber(paramValues.get(token.value())));
          currentNode = stack.pop();
          Evaluator ev = new FuncAdd();
        } else if (token.type() == TokenType.R_PAREN) {
          currentNode = stack.pop();
        }
        if (BINARY_OPERATOR.contains(token.type())) {

          ETNode child = recognizeOperator(token.type());
        } else if (token.type() == TokenType.R_PAREN) {
          currentNode = stack.pop();
        }
      }
    } catch (Exception exception) {
      throw new IOException("Parse Exception");
    }
  }

  public ETNode recognizeOperator(TokenType tokenType) {
    return null;
  }
}
