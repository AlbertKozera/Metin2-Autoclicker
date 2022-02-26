package com.alfu.autoclicker.service;

import com.alfu.autoclicker.dto.PathData;
import com.alfu.autoclicker.dto.Point;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    public void initFile() throws IOException {
        var file = new File("data.json");
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void serializePathData(ToggleGroup pathRadioButtonGroup, List<Point> points) throws IOException {
        List<PathData> paths = updateAndGetPaths(getSelectedPathData(pathRadioButtonGroup, points));
        var collectionType = new TypeToken<List<PathData>>() {
        }.getType();
        try (var writer = new FileWriter("data.json")) {
            new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(paths, collectionType, writer);
        }
    }

    public List<PathData> deserializePathData() throws IOException {
        var collectionType = new TypeToken<List<PathData>>() {
        }.getType();
        try (var reader = new FileReader("data.json")) {
            return new Gson().fromJson(reader, collectionType);
        }
    }

    private List<PathData> updateAndGetPaths(PathData selectedPath) throws IOException {
        var paths = deserializePathData();
        if (paths != null && !paths.isEmpty()) {
            for (int i = 0; i < paths.size(); i++) {
                var PathData = paths.get(i);
                if (PathData.getId().equals(selectedPath.getId())) {
                    paths.set(i, selectedPath);
                    return paths;
                }
            }
        }
        if(paths == null) {
            paths = new ArrayList<>();
        }
        paths.add(selectedPath);
        return paths;
    }

    private PathData getSelectedPathData(ToggleGroup pathRadioButtonGroup, List<Point> points) {
        var selectedRadioButton = (RadioButton) pathRadioButtonGroup.getSelectedToggle();
        return new PathData(
                selectedRadioButton.getId(),
                getTextFieldValueByRadioButton(selectedRadioButton),
                points);
    }

    private String getTextFieldValueByRadioButton(RadioButton selectedRadioButton) {
        var parentPane = (Pane) selectedRadioButton.getParent();
        var selectedTextField = (TextField) parentPane.getChildren().get(0);
        return selectedTextField.getText();
    }

}