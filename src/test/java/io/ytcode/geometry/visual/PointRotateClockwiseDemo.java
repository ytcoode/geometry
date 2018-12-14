package io.ytcode.geometry.visual;

import io.ytcode.geometry.Point;

import java.util.HashSet;
import java.util.Set;

public class PointRotateClockwiseDemo extends RectangularCoordinateCanvas {

  public static void main(String[] args) {
    Set<Long> ps = new HashSet<>();
    int fromDegrees = 0;
    int toDegrees = 270;

    for (int i = fromDegrees; i <= toDegrees; i++) {
      check(ps.add(Point.rotateClockwise(0, 0, 100, 0, i)));
    }
    new PointRotateClockwiseDemo(ps);
  }

  private final Set<Long> ps;

  private PointRotateClockwiseDemo(Set<Long> ps) {
    super("PointDemo");
    this.ps = ps;
  }

  @Override
  protected boolean colorPoint(int x, int y) {
    return ps.contains(Point.toPoint(x, y));
  }
}
