package ru.vsu.cs.course2.deezmosapp;

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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ru.vsu.cs.course2.deezmos.plotdrawer.Drawer;

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
    anchorPane
        .prefHeightProperty()
        .addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

    addInputField();
    addInputField();
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.setFill(Color.AQUA);
    gc.fillOval(12, 12, 12, 12);
    Drawer drawer = new Drawer(gc);
    drawer.drawPixel(50, 50, Color.RED);
    drawer.drawLineDDA(0, 0, 80, 200, Color.BLUE);
    drawer.drawLineBresenham(0, 0, 2000, 1000, Color.CORAL);
    drawer.drawLineBresenham(1000, 2000, 922, 228, Color.DEEPPINK);
    drawer.drawLineWu(6, 50, 900, 400, Color.DARKGREEN);
    drawer.drawLineWu(400, 900, 50, 6, Color.CORAL);
  }

}
