package io.ytcode.geometry.visual;

import io.ytcode.geometry.Circle;

public class CircleIntersectsEllipseDemo extends RectangularCoordinateCanvas {

  public static void main(String[] args) {
    new CircleIntersectsEllipseDemo();
  }

  private CircleIntersectsEllipseDemo() {
    super("CircleDemo");
  }

  @Override
  protected boolean colorPoint(int x, int y) {
    return Circle.intersectsEllipse(50, 50, 100, 50, 45, x, y, 50);
  }
}
