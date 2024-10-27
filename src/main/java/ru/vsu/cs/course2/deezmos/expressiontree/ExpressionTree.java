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
import ru.vsu.cs.course2.deezmos.expressiontree.binaryops.FuncDivide;
import ru.vsu.cs.course2.deezmos.expressiontree.binaryops.FuncMultiply;
import ru.vsu.cs.course2.deezmos.expressiontree.binaryops.FuncSubtract;
import ru.vsu.cs.course2.deezmos.expressiontree.unaryops.FuncNumber;

/**
 * EpressionTree
 */
public class ExpressionTree {

  private static final HashSet<TokenType> UNARY_OPERATORS = new HashSet<>(
      Set.of(
          TokenType.ABS,
          TokenType.SIN,
          TokenType.COS,
          TokenType.TG,
          TokenType.CTG,
          TokenType.ASIN,
          TokenType.ACOS,
          TokenType.ATG,
          TokenType.ACTG,
          TokenType.LN,
          TokenType.LG));

  private static final HashSet<TokenType> BINARY_OPERATOR = new HashSet<>(
      Set.of(
          TokenType.PLUS,
          TokenType.MINUS,
          TokenType.MULT,
          TokenType.DIVISION,
          TokenType.POW,
          TokenType.LOG));

  private ETNode root;
  List<Token> tokens;
  private HashMap<String, Double> variableValues;

  public ExpressionTree(String expression) throws IOException {
    tokens = tokenize(expression);
  }

  private List<Token> tokenize(String expression) throws IOException {
    ExpressionTokenizer tokenizer = new ExpressionTokenizer(expression);
    LinkedList<Token> tokens = new LinkedList<>();
    variableValues = new HashMap<>();

    while (tokenizer.hasNext()) {
      Token token = tokenizer.next();

      if (token.type() == TokenType.VARIABLE) {
        variableValues.put(token.value(), 1.0);
      }

      tokens.add(token);
    }

    return tokens;
  }

  public void setVariableValue(String variable, double value) throws IOException {
    variable = variable.toLowerCase();

    if (!hasVariable(variable)) {
      throw new IOException(String.format("No variable \"%s\" in current expression", variable));
    }

    variableValues.put(variable, value);
  }

  public boolean hasVariable(String variable) {
    variable = variable.toLowerCase();
    return variableValues.containsKey(variable);
  }

  private void parse() throws IOException {

    Stack<TokenType> operatorStack = new Stack<>();
    operatorStack.push(TokenType.EOL);
    Stack<ETNode> nodeStack = new Stack<>();

    for (Token token : tokens) {
      if (token.type() == TokenType.NUMBER) {
        ETNode node = new ETNode(new FuncNumber(token.value()));
        nodeStack.push(node);
      } else if (token.type() == TokenType.VARIABLE) {
        ETNode node = new ETNode(new FuncNumber(variableValues.get(token.value())));
        nodeStack.push(node);
      } else {
        if (token.type() == TokenType.L_BRACKET) {
          operatorStack.push(token.type());
        } else if (token.type() == TokenType.R_BRACKET) {
          while (operatorStack.peek() != TokenType.L_BRACKET) {
            popOperator(operatorStack, nodeStack);
          }
          // remove L_BRACKET
          operatorStack.pop();
        } else if (operatorStack.peek().precedence() >= token.type().precedence()) {
          popOperator(operatorStack, nodeStack);
          operatorStack.push(token.type());
        } else {
          operatorStack.push(token.type());
        }
      }
    }

    while (operatorStack.peek() != TokenType.EOL) {
      popOperator(operatorStack, nodeStack);
    }

    root = nodeStack.peek();
  }

  private void popOperator(Stack<TokenType> operatorStack, Stack<ETNode> nodeStack) {
    ETNode node = new ETNode(recognizeOperator(operatorStack.pop()));
    node.setRight(nodeStack.pop());
    node.setLeft(nodeStack.pop());

    nodeStack.push(node);
  }

  public Evaluator recognizeOperator(TokenType tokenType) throws RuntimeException {
    switch (tokenType) {
      case PLUS -> {
        return new FuncAdd();
      }
      case MINUS -> {
        return new FuncSubtract();
      }
      case MULT -> {
        return new FuncMultiply();
      }
      case DIVISION -> {
        return new FuncDivide();
      }
      default -> {
        throw new RuntimeException(String.format("Unsupported operator %s", tokenType));
      }
    }
  }

  public double evaluate() throws IOException {
    this.parse();
    return root.evaluate();
  }
}
