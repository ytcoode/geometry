package io.ytcode.geometry.visual;

import io.ytcode.geometry.Point;

import java.util.HashSet;
import java.util.Set;

public class PointRotateCounterclockwiseDemo extends RectangularCoordinateCanvas {

  public static void main(String[] args) {
    Set<Long> ps = new HashSet<>();
    int fromDegrees = 45;
    int toDegrees = 235;

    for (int i = fromDegrees; i < toDegrees; i++) {
      check(ps.add(Point.rotateCounterclockwise(0, 0, 100, 0, i)));
    }
    new PointRotateCounterclockwiseDemo(ps);
  }

  private final Set<Long> ps;

  private PointRotateCounterclockwiseDemo(Set<Long> ps) {
    super("PointDemo");
    this.ps = ps;
  }

  @Override
  protected boolean colorPoint(int x, int y) {
    return ps.contains(Point.toPoint(x, y));
  }
}
