package io.ytcode.geometry.visual;

import io.ytcode.geometry.Circle;

public class CircleIntersectsCircleDemo extends RectangularCoordinateCanvas {

  public static void main(String[] args) {
    new CircleIntersectsCircleDemo();
  }

  private CircleIntersectsCircleDemo() {
    super("CircleDemo");
  }

  @Override
  protected boolean colorPoint(int x, int y) {
    return Circle.intersectsCircle(0, 0, 50, x, y, 50);
  }
}
