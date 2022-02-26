package com.alfu.autoclicker.controller;

import com.alfu.autoclicker.dto.Point;
import com.alfu.autoclicker.dto.RecordButtonStatus;
import com.alfu.autoclicker.listener.GlobalMouseListener;
import com.alfu.autoclicker.service.FileService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    private FileService fileService = new FileService();

    public VBox savePathPanel;
    public ToggleGroup pathRadioButtonGroup = new ToggleGroup();
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
    public TextField pathDescription1;
    public TextField pathDescription2;
    public TextField pathDescription3;
    public TextField pathDescription4;
    public TextField pathDescription5;
    public TextField pathDescription6;
    public TextField pathDescription7;
    public TextField pathDescription8;
    public TextField pathDescription9;
    public TextField pathDescription10;
    public Button startRecordButton;
    public Button stopRecordButton;
    public TextArea recordDisplayer;

    @FXML
    void initialize() throws IOException {
        MainController.pathController = this;
        fileService.initFile();
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

}