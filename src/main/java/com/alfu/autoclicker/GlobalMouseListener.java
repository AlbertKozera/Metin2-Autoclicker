package com.alfu.autoclicker;

import java.util.List;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

public class GlobalMouseListener implements NativeMouseListener {

  public static List<Point> points;

  @Override
  public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {
    var point = new Point(nativeMouseEvent.getX(), nativeMouseEvent.getY());
    points.add(point);
    System.out.println("x: " + point.x + ", y: "+ point.y);
  }

  @Override
  public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {

  }

  @Override
  public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {

  }
}