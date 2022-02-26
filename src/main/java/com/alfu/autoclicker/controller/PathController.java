package com.alfu.autoclicker.controller;

import com.alfu.autoclicker.dto.Point;
import com.alfu.autoclicker.dto.RecordButtonStatus;
import com.alfu.autoclicker.listener.GlobalMouseListener;
import com.alfu.autoclicker.service.FileService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.alfu.autoclicker.dto.RecordButtonStatus.DISABLE;
import static com.alfu.autoclicker.dto.RecordButtonStatus.ENABLE;

public class PathController {

    public static List<Point> points = new ArrayList<>();
    public static GlobalMouseListener globalMouseListener;

    private final FileService fileService = new FileService();

    public AnchorPane root;
    public VBox pathControlPanel;
    public ToggleGroup pathRadioButtonGroup = new ToggleGroup();
    public RadioButton path0;
    public RadioButton path1;
    public RadioButton path2;
    public RadioButton path3;
    public RadioButton path4;
    public RadioButton path5;
    public RadioButton path6;
    public RadioButton path7;
    public RadioButton path8;
    public RadioButton path9;
    public TextField pathDescription0;
    public TextField pathDescription1;
    public TextField pathDescription2;
    public TextField pathDescription3;
    public TextField pathDescription4;
    public TextField pathDescription5;
    public TextField pathDescription6;
    public TextField pathDescription7;
    public TextField pathDescription8;
    public TextField pathDescription9;
    public Button startRecordButton;
    public Button stopRecordButton;
    public TextArea recordDisplayer;

    @FXML
    void initialize() throws IOException {
        MainController.pathController = this;
        Platform.runLater(() -> root.requestFocus());
        handleDisableAndEnableTextFields();
        fileService.initFile();
        fillWindowWithDataFromFile();
    }

    @FXML
    protected void startRecord() {
        System.out.println("Recording start!");
        switchStatusOfRecordButtons(ENABLE, DISABLE);
        registerGlobalMouseListener();
    }

    @FXML
    protected void stopRecord() throws IOException {
        System.out.println("Recording stop!");
        switchStatusOfRecordButtons(DISABLE, ENABLE);
        unregisterGlobalMouseListener();
        fileService.serializePathData(pathRadioButtonGroup, points);
        points.clear();
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

    private void switchStatusOfRecordButtons(RecordButtonStatus startButton, RecordButtonStatus stopButton) {
        startRecordButton.setDisable(startButton.value());
        stopRecordButton.setDisable(stopButton.value());
    }

    private void switchStatusOfTextField(TextField oldTextField, TextField newTextField) {
        oldTextField.setDisable(ENABLE.value());
        newTextField.setDisable(DISABLE.value());
    }

    private void fillWindowWithDataFromFile() throws IOException {
        var paths = fileService.deserializePathData();
        if (paths != null && !paths.isEmpty()) {
            paths.forEach(pathData -> {
                var pathDataId = getIdAsIntFromPaths(pathData.getId());
                var textField = (TextField) (
                        ((Pane) (
                                pathControlPanel.getChildren().get(pathDataId))
                        ).getChildren().get(0)
                );
                var description = pathData.getDescription();
                textField.setText(description);
            });
        }
    }

    private int getIdAsIntFromPaths(String pathId) {
        return Integer.parseInt(pathId.replaceAll("[a-zA-Z]", ""));
    }

    private void handleDisableAndEnableTextFields() {
        pathRadioButtonGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            var oldTextField = getTextFieldByRadioButton((RadioButton) oldValue);
            var newTextField = getTextFieldByRadioButton((RadioButton) newValue);
            switchStatusOfTextField(oldTextField, newTextField);
        });
    }

    private TextField getTextFieldByRadioButton(RadioButton radioButton) {
        var pane = (Pane) radioButton.getParent();
        return (TextField) pane.getChildren().get(0);
    }

}