package ru.vsu.cs.course2.deezmosapp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.vsu.cs.course2.deezmos.ExpressionTokenizer;
import ru.vsu.cs.course2.deezmos.ExpressionTokenizer.Token;
import ru.vsu.cs.course2.deezmos.expressiontree.ExpressionTree;

/** JavaFX DeezMos GUI. */
public class DeezmosApplication extends Application {

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

    String expression = "25 + x - 34 * (25- 12)/cos-a^56";
    ExpressionTokenizer tokenizer = new ExpressionTokenizer(expression);
    while (tokenizer.hasNext()) {
      // tokenizer.next().value();
      Token token = tokenizer.next();
      System.out.printf("%s, %s\n", token.value(), token.type());
    }
    expression = "(1 + 4 - 5)";
    expression = "(7 + 3 * (5 - 2) - 4)";
    // expression = "3 + 4 * 5";
    ExpressionTree eprTree = new ExpressionTree(expression);
    System.out.println(eprTree.evaluate());

    // launch();
  }
}
