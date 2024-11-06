package ru.vsu.cs.course2.deezmosapp;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
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
  private VBox inputBox;

  @FXML
  private VBox vboxNavigation;
  private int mouseX;
  private int mouseY;
  private FxPlotDrawer plotDrawer;
  private GraphicsContext graphicsContext;

  /**
   * ExpressionColor
   */
  private class ExpressionWithColor {
    private ExpressionTree expressionTree;
    private Color color;

    public ExpressionWithColor(ExpressionTree expressionTree, Color color) {
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

  private HashMap<HBox, ExpressionWithColor> expressions = new HashMap<>();

  private void addInputField() {
    HBox inputContainer = new HBox();
    inputContainer.setStyle("-fx-padding: 5");
    inputContainer.setAlignment(Pos.CENTER);

    Label label = new Label();
    label.setText("f(x) =");
    label.setStyle("-fx-padding: 13");
    label.setStyle("-fx-font-size: 18");

    TextField textField = new TextField();
    ColorPicker colorPicker = new ColorPicker();
    colorPicker.setOnAction(event -> {
      ExpressionWithColor expressionWithColor = expressions.get(inputContainer);
      if (expressionWithColor != null) {
        expressionWithColor.setColor(colorPicker.getValue());
      }
      redraw();
    });

    Button buttonAccept = new Button();
    buttonAccept.textProperty().set("v");
    buttonAccept.setOnAction(event -> {
      try {
        expressions.put(inputContainer,
            new ExpressionWithColor(new ExpressionTree(textField.textProperty().getValue()), colorPicker.getValue()));
        redraw();
      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    });

    Button buttonDel = new Button();
    buttonDel.textProperty().set("x");
    buttonDel.setOnAction(event -> {
      expressions.remove(inputContainer);
      inputBox.getChildren().remove(inputContainer);
      redraw();
    });

    inputContainer.getChildren().add(label);
    inputContainer.getChildren().add(textField);
    inputContainer.getChildren().add(buttonAccept);
    inputContainer.getChildren().add(buttonDel);
    inputContainer.getChildren().add(colorPicker);

    inputBox.getChildren().add(inputContainer);
  }

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
        redraw();
      } else if (direction < 0) {
        plotDrawer.rescale(-5);
        resize();
        redraw();
      }
    });

    buttonNewExpression.setOnAction(event -> {
      addInputField();
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

      for (ExpressionWithColor expressionWithColor : expressions.values()) {
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
}
