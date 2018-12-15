package io.ytcode.geometry.visual;

import io.ytcode.geometry.Circle;

public class CircleIntersectsRectangleDemo extends RectangularCoordinateCanvas {

  public static void main(String[] args) {
    new CircleIntersectsRectangleDemo();
  }

  private CircleIntersectsRectangleDemo() {
    super("CircleDemo");
  }

  @Override
  protected boolean colorPoint(int x, int y) {
//    return Circle.intersectsRectangle(0,0, 100, 50, x, y, 50);
    return Circle.intersectsRectangle(100, 100, 50, 0, 0, 45, x, y, 50);
  }
}
