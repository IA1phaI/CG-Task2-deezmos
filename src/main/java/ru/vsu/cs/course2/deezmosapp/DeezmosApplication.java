package ru.vsu.cs.course2.deezmosapp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.vsu.cs.course2.deezmos.EquationTokenizer;

/** JavaFX DeezMos GUI. */
public class DeezmosApplication extends Application {

  // @Override
  // public void start(Stage stage) {
  // var javaVersion = SystemInfo.javaVersion();
  // var javafxVersion = SystemInfo.javafxVersion();
  //
  // var label =
  // new Label("Hello, JavaFX " + javafxVersion + ", running on Java " +
  // javaVersion + ".");
  // var scene = new Scene(new StackPane(label), 640, 480);
  // stage.setScene(scene);
  // stage.show();
  // }
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(DeezmosApplication.class.getResource("mainwindow.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 800, 600);
    System.out.println(fxmlLoader);
    stage.setTitle("DeezMos");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {

    HashMap<String, TokenType> simpleTokensMap =
        new HashMap<>(
            Map.ofEntries(
                // Map.entry("^-?\\d+(?:\\.\\d+)?", TokenType.NUMBER),
                Map.entry("x", TokenType.X),
                Map.entry("y", TokenType.Y),
                Map.entry("(", TokenType.L_PARENT),
                Map.entry(")", TokenType.R_PARENT),
                Map.entry(",", TokenType.COMMA),
                Map.entry("+", TokenType.PLUS),
                Map.entry("-", TokenType.MINUS),
                Map.entry("*", TokenType.MULT),
                Map.entry("^", TokenType.POW),
                Map.entry("/", TokenType.DIVISION),
                Map.entry("ln", TokenType.LN),
                Map.entry("lg", TokenType.LG),
                Map.entry("log", TokenType.LOG),
                Map.entry("sin", TokenType.SIN),
                Map.entry("cos", TokenType.COS),
                Map.entry("tg", TokenType.TG),
                Map.entry("ctg", TokenType.CTG),
                Map.entry("asin", TokenType.ASIN),
                Map.entry("acos", TokenType.ACOS),
                Map.entry("atg", TokenType.ATG),
                Map.entry("actg", TokenType.ACTG)));

    EquationTokenizer tokenizer = new EquationTokenizer(simpleTokensMap.keySet());
    System.out.println(tokenizer.tokenize("++-1 + 2 + 3"));
    launch();
  }
}
