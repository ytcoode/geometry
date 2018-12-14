package io.ytcode.geometry.visual;

import io.ytcode.geometry.Point;

public class PointIsInsideEllipseDemo extends RectangularCoordinateCanvas {

  public static void main(String[] args) {
    new PointIsInsideEllipseDemo();
  }

  private PointIsInsideEllipseDemo() {
    super("PointDemo");
  }

  @Override
  protected boolean colorPoint(int x, int y) {
    return Point.isInsideEllipse(0, 0, 100, 50, 45, x, y);
  }
}
