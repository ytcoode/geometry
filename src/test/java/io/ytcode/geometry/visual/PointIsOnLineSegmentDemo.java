package io.ytcode.geometry.visual;

import io.ytcode.geometry.Point;

public class PointIsOnLineSegmentDemo extends RectangularCoordinateCanvas {

  public static void main(String[] args) {
    new PointIsOnLineSegmentDemo();
  }

  private PointIsOnLineSegmentDemo() {
    super("PointDemo");
  }

  @Override
  protected boolean colorPoint(int x, int y) {
    return Point.isOnLineSegment(0, 0, 200, 100, x, y);
  }
}
