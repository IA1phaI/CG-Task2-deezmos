package ru.vsu.cs.course2.deezmosapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ru.vsu.cs.course2.deezmos.plotdrawer.FxSimpleDrawer;
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
  private Canvas canvas;

  @FXML
  private AnchorPane expressionsPane;

  @FXML
  private Button buttonNewExpression;

  @FXML
  private VBox inputBox;

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
    anchorPane.prefWidthProperty()
        .addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
    anchorPane.prefHeightProperty()
        .addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

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
    addInputField();
    addInputField();
    graphicsContext = canvas.getGraphicsContext2D();
    // gc.setFill(Color.AQUA);
    // gc.fillOval(12, 12, 12, 12);
    // Drawer drawer = new Drawer(gc);
    // drawer.drawPixel(50, 50, Color.RED);
    // drawer.drawLineDDA(0, 0, 80, 200, Color.BLUE);
    // drawer.drawLineBresenham(10, 20, 200, 100, Color.CORAL);
    // drawer.drawLineBresenham(200, 100, 0, 0, Color.CYAN);
    // drawer.drawLineBresenhamFloat(100, 200, 50, 50, Color.DEEPPINK);
    // drawer.drawLineWu(6, 50, 900, 400, Color.DARKGREEN);
    // drawer.drawLineWu(400, 900, 50, 6, Color.CORAL);

    plotDrawer = new FxPlotDrawer();
    // plot.setScale(1);
    plotDrawer.setSizes((int) canvas.heightProperty().get(), (int) canvas.widthProperty().get());
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

     plotDrawer.drawPlot("sinx", Color.RED, this.graphicsContext);
     plotDrawer.drawPlot("3", Color.GREEN, this.graphicsContext);
     plotDrawer.drawPlot("12 * x", Color.PURPLE, this.graphicsContext);
     plotDrawer.drawPlot("x^2", Color.MAGENTA, this.graphicsContext);
     plotDrawer.drawPlot("x^3", Color.CYAN, this.graphicsContext);
     plotDrawer.drawPlot("1/x", Color.CORAL, this.graphicsContext);
     plotDrawer.drawPlot("log 2 x", Color.YELLOW, this.graphicsContext);
    plotDrawer.drawPlot("log x 2", Color.ORANGE, this.graphicsContext);
    plotDrawer.drawPlot("x * cos x", Color.BLUE, this.graphicsContext);
  }

}
