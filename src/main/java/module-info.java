module com.alfu.autoclicker {
  requires javafx.controls;
  requires javafx.fxml;
  requires jnativehook;
  requires java.logging;

  requires org.controlsfx.controls;

  opens com.alfu.autoclicker to javafx.fxml;
  exports com.alfu.autoclicker;
}