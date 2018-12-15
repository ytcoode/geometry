package io.ytcode.geometry.visual;

import io.ytcode.geometry.Polygon;

public class PolygonContainsDemo extends RectangularCoordinateCanvas {

  public static void main(String[] args) {
    new PolygonContainsDemo();
  }

  private final Polygon polygon;

  private PolygonContainsDemo() {
    super("PolygonDemo");

    int[] xx = {0, 50, 100, 0, -100, -50};
    int[] yy = {0, -50, 0, 50, 0, -50};

    this.polygon = Polygon.from(xx, yy);
  }

  @Override
  protected boolean colorPoint(int x, int y) {
    return polygon.contains(50, -50, 45, x, y);
  }
}
