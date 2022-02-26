package com.alfu.autoclicker.listener;

import com.alfu.autoclicker.controller.HelloApplication;
import com.alfu.autoclicker.dto.Point;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

import static com.alfu.autoclicker.controller.HelloController.points;

public class GlobalMouseListener implements NativeMouseListener {


    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {
        var point = new Point(nativeMouseEvent.getX(), nativeMouseEvent.getY());
        points.add(point);
        System.out.println("x: " + point.getX() + ", y: " + point.getY());
        HelloApplication.helloController.recordDisplayer.setText("x: " + point.getX() + ", y: " + point.getY());
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {

    }

}