package io.ytcode.geometry.visual;

import io.ytcode.geometry.Point;

public class PointPositionRelativeToLineDemo extends RectangularCoordinateCanvas {

  public static void main(String[] args) {
    new PointPositionRelativeToLineDemo();
  }

  private PointPositionRelativeToLineDemo() {
    super("PointDemo");
  }

  @Override
  protected boolean colorPoint(int x, int y) {
    return Point.isOnTheLeftSideOfTheLine(0, 0, 200, 100, x, y);
  }
}
