package com.alfu.autoclicker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {

  @FXML
  private Button registerPath;

  @FXML
  protected void onHelloButtonClick() {
    System.out.println("Welcome to JavaFX Application!");
  }
}