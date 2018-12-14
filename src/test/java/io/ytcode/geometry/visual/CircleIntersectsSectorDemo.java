package io.ytcode.geometry.visual;

import io.ytcode.geometry.Circle;

public class CircleIntersectsSectorDemo extends RectangularCoordinateCanvas {

  public static void main(String[] args) {
    new CircleIntersectsSectorDemo();
  }

  private CircleIntersectsSectorDemo() {
    super("CircleDemo");
  }

  @Override
  protected boolean colorPoint(int x, int y) {
    return Circle.intersectsSector(50, 50, 100, 45, 45, x, y, 50);
  }
}
