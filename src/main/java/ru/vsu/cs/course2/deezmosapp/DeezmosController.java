package ru.vsu.cs.course2.deezmosapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
  private Canvas canvas;

  @FXML
  private AnchorPane canvasPane;

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

  private void addInputField() {
    HBox inputContainer = new HBox();
    inputContainer.setStyle("-fx-padding: 5");
    inputContainer.setAlignment(Pos.CENTER);

    Label label = new Label();
    label.setText("f(x) =");
    label.setStyle("-fx-padding: 13");
    label.setStyle("-fx-font-size: 18");

    TextField textField = new TextField();

    inputContainer.getChildren().add(label);
    inputContainer.getChildren().add(textField);

    inputBox.getChildren().add(inputContainer);
  }

  @FXML
  void initialize() {
    canvasPane.prefWidthProperty()
        .addListener((ov, oldValue, newValue) -> {
          canvas.setWidth(newValue.doubleValue());
          System.out.println(newValue);
        });
    canvasPane.prefHeightProperty()
        .addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));
    System.out.println(canvasPane.widthProperty());

    canvas.setOnMouseMoved(event -> {
      mouseX = (int) event.getX();
      mouseY = (int) event.getY();
    });

    canvas.setOnMouseDragged(event -> {
      switch (event.getButton()) {
        case PRIMARY -> {
          int newMouseX = (int) event.getX();
          int newMouseY = (int) event.getY();
          try {
            translatePlot(newMouseX - mouseX, newMouseY - mouseY);
          } catch (Exception e) {
            System.err.println(e.getMessage());
          }
          mouseX = newMouseX;
          mouseY = newMouseY;
        }
      }
    });

    canvas.setOnKeyTyped(event -> {
      try {
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
      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    });

    canvas.setOnScroll(event -> {
      try {
        double direction = event.getDeltaY();
        if (direction > 0) {
          plotDrawer.rescale(5);
          redraw();
        } else if (direction < 0) {
          plotDrawer.rescale(-5);
          redraw();
        }
      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    });

    addInputField();
    addInputField();
    graphicsContext = canvas.getGraphicsContext2D();
    plotDrawer = new FxPlotDrawer((int) canvas.heightProperty().get(), (int) canvas.widthProperty().get());

    try {
      redraw();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  private void translatePlot(int dx, int dy) throws IOException {
    plotDrawer.translate(dx, dy);
    redraw();
  }

  private void redraw() throws IOException {
    graphicsContext.setFill(Color.WHITE);
    graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

    plotDrawer.draw("sinx", Color.RED, this.graphicsContext);
    plotDrawer.draw("3", Color.GREEN, this.graphicsContext);
    plotDrawer.draw("12 * x", Color.PURPLE, this.graphicsContext);
    plotDrawer.draw("x^2", Color.MAGENTA, this.graphicsContext);
    plotDrawer.draw("x^3", Color.CYAN, this.graphicsContext);
    plotDrawer.draw("1/x", Color.CORAL, this.graphicsContext);
    plotDrawer.draw("log 2 x", Color.YELLOW, this.graphicsContext);
    plotDrawer.draw("x * cos x", Color.BLUE, this.graphicsContext);
  }
}
