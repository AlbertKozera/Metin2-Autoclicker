package com.alfu.autoclicker;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class HelloApplication extends Application {

  static HelloController helloController;

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/fxml/hello-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setTitle("Jebać hipsterów");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    LogManager.getLogManager().reset();
    Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
    logger.setLevel(Level.OFF);
    launch();
  }



}