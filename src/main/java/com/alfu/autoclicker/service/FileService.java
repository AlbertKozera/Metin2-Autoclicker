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
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    public void serializePaths(ToggleGroup pathRadioButtonGroup, List<Point> points) throws IOException {
        var gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        var selectedRadioButton = (RadioButton) pathRadioButtonGroup.getSelectedToggle();
        var selectedPath = getSelectedPath(points, selectedRadioButton);

        List<PathData> paths = updateAndGetPaths(selectedPath);
        Type collectionType = new TypeToken<List<PathData>>() {
        }.getType();
        try (var writer = new FileWriter("data.json")) {
            gson.toJson(paths, collectionType, writer);
        }
    }

    public List<PathData> getDeserializedPaths() throws IOException {
        var file = new File("data.json");
        if (file.length() != 0) {
            Type collectionType = new TypeToken<List<PathData>>() {
            }.getType();
            try (var reader = new FileReader(file)) {
                return new Gson().fromJson(reader, collectionType);
            }
        }
        return new ArrayList<>();
    }

    private List<PathData> updateAndGetPaths(PathData selectedPath) throws IOException {
        var paths = getDeserializedPaths();
        if (!paths.isEmpty()) {
            for (int i = 0; i < paths.size(); i++) {
                var PathData = paths.get(i);
                if (PathData.getId().equals(selectedPath.getId())) {
                    paths.set(i, selectedPath);
                    return paths;
                }
            }
        }
        paths.add(selectedPath);
        return paths;
    }

    private PathData getSelectedPath(List<Point> points, RadioButton selectedRadioButton) {
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
