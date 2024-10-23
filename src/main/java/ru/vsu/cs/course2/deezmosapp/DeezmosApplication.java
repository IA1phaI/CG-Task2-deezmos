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

  public static void main(String[] args) throws IOException, RuntimeException {

    EquationTokenizer tokenizer = new EquationTokenizer();
    tokenizer.setData("+--45 +*");
    while (true) {
      //tokenizer.next().value();
      System.out.println(tokenizer.next().value());
    }

    // launch();
  }
}
