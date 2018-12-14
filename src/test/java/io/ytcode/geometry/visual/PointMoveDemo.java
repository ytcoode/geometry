package io.ytcode.geometry.visual;

import io.ytcode.geometry.Point;

import java.util.HashSet;
import java.util.Set;

public class PointMoveDemo extends RectangularCoordinateCanvas {

  public static void main(String[] args) {
    Set<Long> ps = new HashSet<>();

    int fromDegrees = 90;
    int toDegrees = 360;

    for (int i = fromDegrees; i < toDegrees; i++) {
      check(ps.add(Point.move(0, 0, i, 100)));
    }
    new PointMoveDemo(ps);
  }

  private final Set<Long> ps;

  private PointMoveDemo(Set<Long> ps) {
    super("PointDemo");
    this.ps = ps;
  }

  @Override
  protected boolean colorPoint(int x, int y) {
    return ps.contains(Point.toPoint(x, y));
  }
}
