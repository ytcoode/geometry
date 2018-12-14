package io.ytcode.geometry.visual;

import io.ytcode.geometry.Point;

public class PointIsInsideSectorDemo extends RectangularCoordinateCanvas {

  public static void main(String[] args) {
    new PointIsInsideSectorDemo();
  }

  private PointIsInsideSectorDemo() {
    super("PointDemo");
  }

  @Override
  protected boolean colorPoint(int x, int y) {
    return Point.isInsideSector(0, 0, 200, 45, 45, x, y);
  }
}
