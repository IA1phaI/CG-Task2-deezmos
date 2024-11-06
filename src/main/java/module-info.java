module ru.vsu.cs.course2.deezmos.app {
  requires javafx.controls;
  requires javafx.fxml;

  opens ru.vsu.cs.course2.deezmos.app to javafx.fxml;

  exports ru.vsu.cs.course2.deezmos.app;
}
