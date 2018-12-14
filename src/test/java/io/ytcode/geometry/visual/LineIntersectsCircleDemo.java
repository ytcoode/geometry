package io.ytcode.geometry.visual;

import io.ytcode.geometry.Line;

public class LineIntersectsCircleDemo extends RectangularCoordinateCanvas {

  public static void main(String[] args) {
    new LineIntersectsCircleDemo();
  }

  private LineIntersectsCircleDemo() {
    super("LineDemo");
  }

  @Override
  protected boolean colorPoint(int x, int y) {
//    return Line.intersectsCircle(-50, 50, 50, x, y, 50);
    return Line.intersectsCircle(0, 0, 100, 100, x, y, 50);
  }
}
