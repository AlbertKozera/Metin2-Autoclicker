package com.alfu.autoclicker;

import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

import java.util.ArrayList;
import java.util.List;

public class GlobalMouseListener implements NativeMouseListener {

    public static List<Point> points = new ArrayList<>();

    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {
        var point = new Point(nativeMouseEvent.getX(), nativeMouseEvent.getY());
        points.add(point);
        System.out.println("x: " + point.x() + ", y: " + point.y());

        HelloApplication.helloController.recordDisplayer.setText("x: " + point.x() + ", y: " + point.y());
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {

    }
}