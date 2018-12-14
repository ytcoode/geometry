package io.ytcode.geometry.visual;

import io.ytcode.geometry.Point;

public class PointIsInsidePolygonDemo extends RectangularCoordinateCanvas {

  public static void main(String[] args) {
    new PointIsInsidePolygonDemo();
  }

  private final int[] xx = {0, 50, 100, 0, -100, -50};
  private final int[] yy = {0, -50, 0, 50, 0, -50};

  private PointIsInsidePolygonDemo() {
    super("PointDemo");
  }

  @Override
  protected boolean colorPoint(int x, int y) {
    return Point.isInsidePolygon(xx, yy, x, y);
  }
}
