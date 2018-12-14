package io.ytcode.geometry.visual;

import io.ytcode.geometry.RotatablePolygon;

public class RotatablePolygonContainsDemo extends RectangularCoordinateCanvas {

  public static void main(String[] args) {
    new RotatablePolygonContainsDemo();
  }

  private final RotatablePolygon polygon;

  private RotatablePolygonContainsDemo() {
    super("RotatablePolygonDemo");

    int[] xx = {0, 50, 100, 0, -100, -50};
    int[] yy = {0, -50, 0, 50, 0, -50};

    this.polygon = RotatablePolygon.from(xx, yy, 0, 0);
  }

  @Override
  protected boolean colorPoint(int x, int y) {
    return polygon.contains(x, y, 45);
  }
}
