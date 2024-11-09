package ru.vsu.cs.course2.deezmos.expression.tree;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import ru.vsu.cs.course2.deezmos.expression.tokenizer.ExpressionTokenizer;
import ru.vsu.cs.course2.deezmos.expression.tokenizer.TokenType;
import ru.vsu.cs.course2.deezmos.expression.tokenizer.ExpressionTokenizer.Token;
import ru.vsu.cs.course2.deezmos.expression.tokenizer.TokenType.OperatorType;
import ru.vsu.cs.course2.deezmos.expression.tree.binaryops.FuncAdd;
import ru.vsu.cs.course2.deezmos.expression.tree.binaryops.FuncDivide;
import ru.vsu.cs.course2.deezmos.expression.tree.binaryops.FuncLog;
import ru.vsu.cs.course2.deezmos.expression.tree.binaryops.FuncMultiply;
import ru.vsu.cs.course2.deezmos.expression.tree.binaryops.FuncPow;
import ru.vsu.cs.course2.deezmos.expression.tree.binaryops.FuncSubtract;
import ru.vsu.cs.course2.deezmos.expression.tree.unaryops.FuncAbs;
import ru.vsu.cs.course2.deezmos.expression.tree.unaryops.FuncAcos;
import ru.vsu.cs.course2.deezmos.expression.tree.unaryops.FuncActg;
import ru.vsu.cs.course2.deezmos.expression.tree.unaryops.FuncAsin;
import ru.vsu.cs.course2.deezmos.expression.tree.unaryops.FuncAtg;
import ru.vsu.cs.course2.deezmos.expression.tree.unaryops.FuncCos;
import ru.vsu.cs.course2.deezmos.expression.tree.unaryops.FuncCtg;
import ru.vsu.cs.course2.deezmos.expression.tree.unaryops.FuncLg;
import ru.vsu.cs.course2.deezmos.expression.tree.unaryops.FuncLn;
import ru.vsu.cs.course2.deezmos.expression.tree.unaryops.FuncNegativeVariable;
import ru.vsu.cs.course2.deezmos.expression.tree.unaryops.FuncNumber;
import ru.vsu.cs.course2.deezmos.expression.tree.unaryops.FuncSin;
import ru.vsu.cs.course2.deezmos.expression.tree.unaryops.FuncSqrt;
import ru.vsu.cs.course2.deezmos.expression.tree.unaryops.FuncTg;
import ru.vsu.cs.course2.deezmos.expression.tree.unaryops.FuncPositiveVariable;

/**
 * EpressionTree
 */
public class Expression {

  private ETNode root;
  List<Token> tokens;
  private HashMap<String, Double> variableValues;

  public Expression(String expression) throws IOException {
    if (expression.isBlank()) {
      throw new IOException("Empty expression");
    }
    variableValues = new HashMap<>();
    tokens = tokenize(expression);
    this.parse();
  }

  public Set<String> getVariables() {
    return variableValues.keySet();
  }

  public double getVariableValue(String variable) {
    return variableValues.get(variable);
  }

  private List<Token> tokenize(String expression) throws IOException {
    ExpressionTokenizer tokenizer = new ExpressionTokenizer(expression);
    LinkedList<Token> tokens = new LinkedList<>();

    while (tokenizer.hasNext()) {
      Token token = tokenizer.next();

      if (token.type() == TokenType.POSITIVE_VARIABLE
          || token.type() == TokenType.NEGATIVE_VARIABLE) {
        variableValues.put(token.value(), 1.0);
      }

      tokens.add(token);
    }

    return tokens;
  }

  public void setVariable(String variable, double value) throws IOException {
    if (!setVariableIfAbsent(variable, value)) {
      throw new IOException(String.format("No variable \"%s\" in current expression", variable));
    }
  }

  public boolean setVariableIfAbsent(String variable, double value) {
    variable = variable.toLowerCase();

    if (hasVariable(variable)) {
      this.variableValues.put(variable, value);
      return true;
    }

    return false;
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
      } else if (token.type() == TokenType.PI_CONST) {
        ETNode node = new ETNode(new FuncNumber(Math.PI));
        nodeStack.push(node);
      } else if (token.type() == TokenType.EULER_CONST) {
        ETNode node = new ETNode(new FuncNumber(Math.E));
        nodeStack.push(node);
      } else if (token.type() == TokenType.POSITIVE_VARIABLE) {
        ETNode node = new ETNode(new FuncPositiveVariable(token.value(), variableValues));
        nodeStack.push(node);
      } else if (token.type() == TokenType.NEGATIVE_VARIABLE) {
        ETNode node = new ETNode(new FuncNegativeVariable(token.value(), variableValues));
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
    TokenType operator = operatorStack.pop();
    ETNode node = new ETNode(recognizeOperator(operator));
    if (operator.getOperatorType() == OperatorType.BINARY_OPERATOR) {
      node.setRight(nodeStack.pop());
    }
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
        return new FuncAbs();
      }
      case SIN -> {
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
      case SQRT -> {
        return new FuncSqrt();
      }
      default -> {
        throw new RuntimeException(String.format("Unsupported operator %s", tokenType));
      }
    }
  }

  /**
   * .parse() before evaluation
   */
  public double evaluate() throws IOException {
    return root.evaluate();
  }
}
