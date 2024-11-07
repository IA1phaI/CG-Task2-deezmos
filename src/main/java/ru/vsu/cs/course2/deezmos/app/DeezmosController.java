package ru.vsu.cs.course2.deezmos.app;

import java.net.URL;
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
import ru.vsu.cs.course2.deezmos.expression.tree.Expression;
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
    public int count;
    public HBox hbox;

    public VariableProperties(int count, HBox hbox) {
      this.count = count;
      this.hbox = hbox;
    }
  }

  private class ExpressionHBoxProperties {
    Expression expression;
    Color color;

    public ExpressionHBoxProperties(Expression expression, Color color) {
      this.expression = expression;
      this.color = color;
    }
  }

  private HashMap<HBox, ExpressionHBoxProperties> expressionHBoxProperties = new HashMap<>();

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

      for (ExpressionHBoxProperties exprHBoxProp : expressionHBoxProperties.values()) {
        plotDrawer.draw(exprHBoxProp.expression, exprHBoxProp.color, graphicsContext);
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
      e.printStackTrace(System.err);
    }
  }

  private void addExpressionField() {
    HBox expressionHBox = makeHBox();

    Label label = makeLabel("f(x) =");

    TextField textField = new TextField();

    ColorPicker colorPicker = makeColorPicker(expressionHBox);

    Button buttonAccept = makeExpressionAcceptButton(
        textField,
        expressionHBox,
        colorPicker);

    Button buttonDel = makeExpressionDelButton(expressionHBox);

    expressionHBox.getChildren().add(label);
    expressionHBox.getChildren().add(textField);
    expressionHBox.getChildren().add(buttonAccept);
    expressionHBox.getChildren().add(buttonDel);
    expressionHBox.getChildren().add(colorPicker);

    expressionsBox.getChildren().add(expressionHBox);
  }

  private HBox addVariableField(String variable) {
    HBox hbox = makeHBox();

    Label label = makeLabel(variable + ":");

    TextField textField = new TextField();
    Slider slider = makeVariableSlider();

    textField.setText(String.valueOf(1));
    textField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.matches("-?\\d+(?:\\.\\d+)?")) {
        double value = Double.parseDouble(newValue);
        setVariableValueInExpressions(variable, value);
        redraw();
      }
    });

    slider.valueProperty().addListener(event -> {
      textField.setText(String.format("%.2f", slider.getValue()));
    });

    hbox.getChildren().add(label);
    hbox.getChildren().add(slider);
    hbox.getChildren().add(textField);

    variablesBox.getChildren().add(hbox);
    return hbox;
  }

  private void setVariableValueInExpressions(String variable, double value) {
    for (ExpressionHBoxProperties expressionWithColor : expressionHBoxProperties.values()) {
      expressionWithColor.expression.setVariableIfAbsent(variable, value);
    }
  }

  private Label makeLabel(String text) {
    Label label = new Label();
    label.setText(text);
    label.setStyle("-fx-padding: 13");
    label.setStyle("-fx-font-size: 18");
    return label;
  }

  private HBox makeHBox() {
    HBox hbox = new HBox();
    hbox.setStyle("-fx-padding: 5");
    hbox.setAlignment(Pos.CENTER);
    return hbox;
  }

  private Slider makeVariableSlider() {
    Slider slider = new Slider();
    slider.setMax(10);
    slider.setMin(-10);
    slider.setValue(1);
    return slider;
  }

  private ColorPicker makeColorPicker(HBox linkedHBox) {
    ColorPicker colorPicker = new ColorPicker(Color.RED);
    colorPicker.setOnAction(event -> {
      ExpressionHBoxProperties expressionWithColor = expressionHBoxProperties.get(linkedHBox);
      if (expressionWithColor != null) {
        expressionWithColor.color = colorPicker.getValue();
        redraw();
      }
    });
    return colorPicker;
  }

  private Button makeExpressionAcceptButton(TextField linkedTextField, HBox linkedHBox,
      ColorPicker linkedColorPicker) {
    Button button = new Button();
    button.textProperty().set("v");
    button.setOnAction(event -> {
      try {
        removeVariablesOfExpressionHBox(linkedHBox);
        Expression expression = new Expression(linkedTextField.getText());
        expressionHBoxProperties.put(linkedHBox,
            new ExpressionHBoxProperties(expression, linkedColorPicker.getValue()));
        for (String variable : expression.getVariables()) {
          if (variable.equals("x")) {
            break;
          }
          if (!variableProppertiesMap.containsKey(variable)) {
            HBox variableHBox = addVariableField(variable);
            variableProppertiesMap.put(variable,
                new VariableProperties(1, variableHBox));
          } else {
            variableProppertiesMap.get(variable).count++;
          }
        }
        redraw();
      } catch (Exception e) {
        e.printStackTrace(System.err);
      }
    });
    return button;
  }

  private Button makeExpressionDelButton(HBox linkedHBox) {
    Button button = new Button();
    button.textProperty().set("x");
    button.setOnAction(event -> {
      removeVariablesOfExpressionHBox(linkedHBox);
      expressionHBoxProperties.remove(linkedHBox);
      expressionsBox.getChildren().remove(linkedHBox);
      redraw();
    });

    return button;
  }

  private void removeVariablesOfExpressionHBox(HBox linkedHBox) {
    ExpressionHBoxProperties exprHBoxProp = expressionHBoxProperties
        .get(linkedHBox);
    if (exprHBoxProp != null) {
      Set<String> variables = exprHBoxProp.expression.getVariables();
      for (String variable : variables) {
        VariableProperties varPorperties = variableProppertiesMap.get(variable);
        if (varPorperties == null) {
          break;
        }
        varPorperties.count--;
        if (varPorperties.count < 1) {
          variableProppertiesMap.remove(variable);
          variablesBox.getChildren().remove(varPorperties.hbox);
        }
      }
    }
  }
}
