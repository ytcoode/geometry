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
    return Point.isInsideRectangle(0, 0, 100, 0, 200, 100, 135, x, y);
  }
}
