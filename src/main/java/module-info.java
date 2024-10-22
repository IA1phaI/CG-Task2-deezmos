module ru.vsu.cs.course2.deezmosapp {
  requires javafx.controls;
  requires javafx.fxml;

  opens ru.vsu.cs.course2.deezmosapp to javafx.fxml;
  exports ru.vsu.cs.course2.deezmosapp;
}
