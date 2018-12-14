package io.ytcode.geometry.visual;

import io.ytcode.geometry.Point;

public class PointIsInsideCircleDemo extends RectangularCoordinateCanvas {

  public static void main(String[] args) {
    new PointIsInsideCircleDemo();
  }

  private PointIsInsideCircleDemo() {
    super("PointDemo");
  }

  @Override
  protected boolean colorPoint(int x, int y) {
    return Point.isInsideCircle(0, 0, 100, x, y);
  }
}
