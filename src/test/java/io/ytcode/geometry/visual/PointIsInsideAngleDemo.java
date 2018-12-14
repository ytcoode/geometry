package io.ytcode.geometry.visual;

import io.ytcode.geometry.Point;

public class PointIsInsideAngleDemo extends RectangularCoordinateCanvas {

  public static void main(String[] args) {
    new PointIsInsideAngleDemo();
  }

  private PointIsInsideAngleDemo() {
    super("PointDemo");
  }

  @Override
  protected boolean colorPoint(int x, int y) {
    return Point.isInsideAngle(0, 0, 45, 15, x, y);
  }
}
