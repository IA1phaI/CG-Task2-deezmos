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
import ru.vsu.cs.course2.deezmos.expressiontree.binaryops.FuncLog;
import ru.vsu.cs.course2.deezmos.expressiontree.binaryops.FuncMultiply;
import ru.vsu.cs.course2.deezmos.expressiontree.binaryops.FuncPow;
import ru.vsu.cs.course2.deezmos.expressiontree.binaryops.FuncSubtract;
import ru.vsu.cs.course2.deezmos.expressiontree.unaryops.FuncAcos;
import ru.vsu.cs.course2.deezmos.expressiontree.unaryops.FuncActg;
import ru.vsu.cs.course2.deezmos.expressiontree.unaryops.FuncAsin;
import ru.vsu.cs.course2.deezmos.expressiontree.unaryops.FuncAtg;
import ru.vsu.cs.course2.deezmos.expressiontree.unaryops.FuncCos;
import ru.vsu.cs.course2.deezmos.expressiontree.unaryops.FuncCtg;
import ru.vsu.cs.course2.deezmos.expressiontree.unaryops.FuncLg;
import ru.vsu.cs.course2.deezmos.expressiontree.unaryops.FuncLn;
import ru.vsu.cs.course2.deezmos.expressiontree.unaryops.FuncNumber;
import ru.vsu.cs.course2.deezmos.expressiontree.unaryops.FuncSin;
import ru.vsu.cs.course2.deezmos.expressiontree.unaryops.FuncTg;

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
            popBinaryOperator(operatorStack, nodeStack);
          }
          // remove L_BRACKET
          operatorStack.pop();
        } else if (operatorStack.peek().precedence() >= token.type().precedence()) {
          popBinaryOperator(operatorStack, nodeStack);
          operatorStack.push(token.type());
        } else {
          operatorStack.push(token.type());
        }
      }
    }

    while (operatorStack.peek() != TokenType.EOL) {
      popBinaryOperator(operatorStack, nodeStack);
    }

    root = nodeStack.peek();
  }

  private void popBinaryOperator(Stack<TokenType> operatorStack, Stack<ETNode> nodeStack) {
    TokenType operator = operatorStack.pop();
    ETNode node = new ETNode(recognizeOperator(operator));
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
      case POW -> {
        return new FuncPow();
      }
      case LOG -> {
        return new FuncLog();
      }
      case ABS -> {
        return new FuncSin();
      }
      case COS -> {
        return new FuncCos();
      }
      case TG -> {
        return new FuncTg();
      }
      case CTG -> {
        return new FuncCtg();
      }
      case ASIN -> {
        return new FuncAsin();
      }
      case ACOS -> {
        return new FuncAcos();
      }
      case ATG -> {
        return new FuncAtg();
      }
      case ACTG -> {
        return new FuncActg();
      }
      case LN -> {
        return new FuncLn();
      }
      case LG -> {
        return new FuncLg();
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
