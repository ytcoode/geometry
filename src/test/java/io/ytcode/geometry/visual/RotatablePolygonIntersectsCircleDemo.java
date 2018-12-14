package io.ytcode.geometry.visual;

import io.ytcode.geometry.RotatablePolygon;

public class RotatablePolygonIntersectsCircleDemo extends RectangularCoordinateCanvas {

  public static void main(String[] args) {
    new RotatablePolygonIntersectsCircleDemo();
  }

  private final RotatablePolygon polygon;

  private RotatablePolygonIntersectsCircleDemo() {
    super("RotatablePolygonDemo");

    int[] xx = {0, 50, 300, 50, 0, -50};
    int[] yy = {-100, -100, 0, 100, 100, 0};

    this.polygon = RotatablePolygon.from(xx, yy, 0, 0);
  }

  @Override
  protected boolean colorPoint(int x, int y) {
    return polygon.intersectsCircle(x, y, 30, 45);
  }
}
