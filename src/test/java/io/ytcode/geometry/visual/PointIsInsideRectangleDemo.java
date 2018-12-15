package io.ytcode.geometry.visual;

import io.ytcode.geometry.Point;

public class PointIsInsideRectangleDemo extends RectangularCoordinateCanvas {

  public static void main(String[] args) {
    new PointIsInsideRectangleDemo();
  }

  private PointIsInsideRectangleDemo() {
    super("PointDemo");
  }

  @Override
  protected boolean colorPoint(int x, int y) {
    return Point.isInsideRectangle(100, 100, 50, 0, 0, 135, x, y);
  }
}
