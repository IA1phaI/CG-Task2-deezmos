package ru.vsu.cs.course2.deezmos.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** JavaFX DeezMos GUI */
public class DeezmosApplication extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(DeezmosApplication.class.getResource("mainwindow.fxml"));
    Scene scene = new Scene(fxmlLoader.load());

    stage.setTitle("DeezMos");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) throws IOException, RuntimeException {
    launch();
  }
}
