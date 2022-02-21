package com.alfu.autoclicker;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class HelloController {

  public static GlobalMouseListener globalMouseListener;

  @FXML
  ToggleGroup group = new ToggleGroup();
  public RadioButton path1;
  public RadioButton path2;
  public RadioButton path3;
  public RadioButton path4;
  public RadioButton path5;
  public RadioButton path6;
  public RadioButton path7;
  public RadioButton path8;
  public RadioButton path9;
  public RadioButton path10;

  @FXML
  public TextArea recordDisplayer;

  @FXML
  void initialize() {
    HelloApplication.helloController = this;
  }

  @FXML
  protected void startRecord() {
    System.out.println("Recording start!");
    registerGlobalMouseListener();
  }

  @FXML
  protected void stopRecord() {
    System.out.println("Recording stop!");
    unregisterGlobalMouseListener();
  }

  private static void registerGlobalMouseListener() {
    try {
      GlobalScreen.registerNativeHook();
    } catch (NativeHookException ex) {
      System.exit(1);
    }
    globalMouseListener = new GlobalMouseListener();
    GlobalScreen.addNativeMouseListener(globalMouseListener);
  }

  private static void unregisterGlobalMouseListener() {
    GlobalScreen.removeNativeMouseListener(globalMouseListener);
    try {
      GlobalScreen.unregisterNativeHook();
    } catch (NativeHookException ex) {
      System.exit(1);
    }
  }

}