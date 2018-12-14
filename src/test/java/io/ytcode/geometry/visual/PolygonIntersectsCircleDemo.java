package io.ytcode.geometry.visual;

import io.ytcode.geometry.Polygon;

public class PolygonIntersectsCircleDemo extends RectangularCoordinateCanvas {

  public static void main(String[] args) {
    new PolygonIntersectsCircleDemo();
  }

  private final Polygon polygon;

  private PolygonIntersectsCircleDemo() {
    super("CircleDemo");

    int[] xx = {0, 50, 100, 0, -100, -50};
    int[] yy = {0, -50, 0, 50, 0, -50};

    this.polygon = Polygon.from(xx, yy);
  }

  @Override
  protected boolean colorPoint(int x, int y) {
    return polygon.intersectsCircle(x, y, 20);
  }
}
