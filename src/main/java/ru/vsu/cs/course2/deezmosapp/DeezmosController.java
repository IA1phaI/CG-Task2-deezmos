package ru.vsu.cs.course2.deezmosapp;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ru.vsu.cs.course2.deezmos.expressiontree.ExpressionTree;
import ru.vsu.cs.course2.deezmos.plotdrawer.FxPlotDrawer;

/** Deezmos controller for GUI. */
public class DeezmosController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private AnchorPane anchorPane;

  @FXML
  private Button buttonNewExpression;

  @FXML
  private Button buttonCenterize;

  @FXML
  private Button buttonDefaultScale;

  @FXML
  private Canvas canvas;

  @FXML
  private Pane canvasPane;

  @FXML
  private AnchorPane expressionsPane;

  @FXML
  private VBox expressionsBox;

  @FXML
  private VBox variablesBox;

  @FXML
  private VBox vboxNavigation;
  private int mouseX;
  private int mouseY;
  private FxPlotDrawer plotDrawer;
  private GraphicsContext graphicsContext;

  HashMap<String, VariableProperties> variableProppertiesMap = new HashMap<>();

  private class VariableProperties {
    private int count;
    private HBox container;

    public VariableProperties(int count, HBox container) {
      this.count = count;
      this.container = container;
    }

    public void increaseCount() {
      this.count++;
    }

    public void decreaseCount() {
      this.count--;
    }

    public boolean isRedutantVariable() {
      return count < 1;
    }

    public HBox getContainer() {
      return container;
    }
  }

  /**
   * ExpressionColor
   */
  private class ExpressionContainerProperties {
    private ExpressionTree expressionTree;
    private Color color;

    public ExpressionContainerProperties(ExpressionTree expressionTree, Color color) {
      this.expressionTree = expressionTree;
      this.color = color;
    }

    public ExpressionTree getExpressionTree() {
      return expressionTree;
    }

    public Color getColor() {
      return color;
    }

    public void setColor(Color color) {
      this.color = color;
    }
  }

  private HashMap<HBox, ExpressionContainerProperties> expressionContainerPropertiesMap = new HashMap<>();

  @FXML
  void initialize() {
    anchorPane.widthProperty()
        .addListener((ov, oldValue, newValue) -> {
          canvas.setWidth(newValue.doubleValue());
          resize();
        });
    canvasPane.heightProperty()
        .addListener((ov, oldValue, newValue) -> {
          canvas.setHeight(newValue.doubleValue());
          resize();
        });

    canvas.setOnMouseMoved(event -> {
      mouseX = (int) event.getX();
      mouseY = (int) event.getY();
    });

    canvas.setOnMouseDragged(event -> {
      switch (event.getButton()) {
        case PRIMARY -> {
          int newMouseX = (int) event.getX();
          int newMouseY = (int) event.getY();
          translatePlot(newMouseX - mouseX, newMouseY - mouseY);
          mouseX = newMouseX;
          mouseY = newMouseY;
        }
      }
    });

    canvas.setOnKeyTyped(event -> {
      switch (event.getCharacter()) {
        case "=" -> {
          plotDrawer.rescale(1);
          this.redraw();
        }
        case "-" -> {
          plotDrawer.rescale(-1);
          this.redraw();
        }
      }
    });

    canvas.setOnScroll(event -> {
      double direction = event.getDeltaY();
      if (direction > 0) {
        plotDrawer.rescale(5);
        resize();
      } else if (direction < 0) {
        plotDrawer.rescale(-5);
        resize();
      }
    });

    buttonNewExpression.setOnAction(event -> {
      addExpressionField();
    });

    buttonCenterize.setOnAction(event -> {
      plotDrawer.setDefaultOffset();
      redraw();
    });

    buttonDefaultScale.setOnAction(event -> {
      plotDrawer.setDefaultScale();
      redraw();
    });

    graphicsContext = canvas.getGraphicsContext2D();
    plotDrawer = new FxPlotDrawer((int) canvas.heightProperty().get(), (int) canvas.widthProperty().get());

    redraw();
  }

  private void translatePlot(int dx, int dy) {
    plotDrawer.translate(dx, dy);
    redraw();
  }

  public void resize() {
    plotDrawer.resize((int) canvasPane.getWidth(), (int) canvasPane.getHeight());
    redraw();
  }

  private void redraw() {
    try {
      graphicsContext.setFill(Color.WHITE);
      graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

      plotDrawer.drawAxes(this.graphicsContext);

      for (ExpressionContainerProperties expressionWithColor : expressionContainerPropertiesMap.values()) {
        plotDrawer.draw(expressionWithColor.getExpressionTree(), expressionWithColor.getColor(), graphicsContext);
      }

      // plotDrawer.draw("sinx", Color.RED, this.graphicsContext);
      // plotDrawer.draw("3", Color.GREEN, this.graphicsContext);
      // plotDrawer.draw("12 * x", Color.PURPLE, this.graphicsContext);
      // plotDrawer.draw("x^2", Color.MAGENTA, this.graphicsContext);
      // plotDrawer.draw("x^3", Color.CYAN, this.graphicsContext);
      // plotDrawer.draw("1/x", Color.CORAL, this.graphicsContext);
      // plotDrawer.draw("log 2 x", Color.YELLOW, this.graphicsContext);
      // plotDrawer.draw("x * cos x", Color.BLUE, this.graphicsContext);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  private void addExpressionField() {
    HBox expressionContainer = new HBox();
    expressionContainer.setStyle("-fx-padding: 5");
    expressionContainer.setAlignment(Pos.CENTER);

    Label label = new Label();
    label.setText("f(x) =");
    label.setStyle("-fx-padding: 13");
    label.setStyle("-fx-font-size: 18");

    TextField textField = new TextField();
    ColorPicker colorPicker = new ColorPicker();
    colorPicker.setValue(Color.RED);
    colorPicker.setOnAction(event -> {
      ExpressionContainerProperties expressionWithColor = expressionContainerPropertiesMap.get(expressionContainer);
      if (expressionWithColor != null) {
        expressionWithColor.setColor(colorPicker.getValue());
      }
      redraw();
    });

    Button buttonAccept = new Button();
    buttonAccept.textProperty().set("v");
    buttonAccept.setOnAction(event -> {
      try {
        ExpressionTree expressionTree = new ExpressionTree(textField.textProperty().getValue());
        expressionContainerPropertiesMap.put(expressionContainer,
            new ExpressionContainerProperties(expressionTree, colorPicker.getValue()));
        for (String variable : expressionTree.getVariables()) {
          if (!variable.equals("x")) {
            if (!variableProppertiesMap.containsKey(variable)) {
              HBox variableContainer = addVariableField(variable);
              variableProppertiesMap.put(variable,
                  new VariableProperties(1, variableContainer));
            } else {
              variableProppertiesMap.get(variable).increaseCount();
            }
          }
        }
        redraw();
      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    });

    Button buttonDel = new Button();
    buttonDel.textProperty().set("x");
    buttonDel.setOnAction(event -> {
      ExpressionContainerProperties expressionWithColor = expressionContainerPropertiesMap.get(expressionContainer);
      if (expressionWithColor != null) {
        Set<String> variables = expressionWithColor.getExpressionTree().getVariables();
        for (String variable : variables) {
          VariableProperties varPorperties = variableProppertiesMap.get(variable);
          if (varPorperties != null) {
            varPorperties.decreaseCount();
            if (varPorperties.isRedutantVariable()) {
              variableProppertiesMap.remove(variable);
              variablesBox.getChildren().remove(varPorperties.getContainer());
            }
          }
        }
      }
      expressionContainerPropertiesMap.remove(expressionContainer);
      expressionsBox.getChildren().remove(expressionContainer);
      redraw();
    });

    expressionContainer.getChildren().add(label);
    expressionContainer.getChildren().add(textField);
    expressionContainer.getChildren().add(buttonAccept);
    expressionContainer.getChildren().add(buttonDel);
    expressionContainer.getChildren().add(colorPicker);

    expressionsBox.getChildren().add(expressionContainer);
  }

  private HBox addVariableField(String variable) {
    HBox container = new HBox();
    container.setStyle("-fx-padding: 5");
    container.setAlignment(Pos.CENTER);

    Label label = new Label();

    label.setText(variable + ":");
    label.setStyle("-fx-padding: 13");
    label.setStyle("-fx-font-size: 18");

    TextField textField = new TextField();
    Slider slider = new Slider();

    textField.setText(String.valueOf(1));
    textField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.matches("-?\\d+(?:\\.\\d+)?")) {
        double value = Double.parseDouble(newValue);
        setVariableValueInExpressions(variable, value);
        redraw();
        return;
      }
    });

    slider.setMax(10);
    slider.setMin(-10);
    slider.setValue(1);
    slider.valueProperty().addListener(event -> {
      textField.setText(String.format("%.2f", slider.getValue()));
    });

    container.getChildren().add(label);
    container.getChildren().add(slider);
    container.getChildren().add(textField);

    variablesBox.getChildren().add(container);
    return container;
  }

  private void setVariableValueInExpressions(String variable, double value) {
    for (ExpressionContainerProperties expressionWithColor : expressionContainerPropertiesMap.values()) {
      expressionWithColor.getExpressionTree().setVariableIfAbsent(variable, value);
    }
  }
}
