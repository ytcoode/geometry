package io.ytcode.geometry;

import static io.ytcode.geometry.Utils.check;
import static java.lang.Math.subtractExact;

public class Line {

  public static boolean intersectsCircle(int x1, int x2, int y, int cx, int cy, int r) {
    check(x1 < x2);
    if (Math.abs(subtractExact(cy, y)) > r) {
      return false;
    }
    if (cx >= x1 && cx <= x2) {
      return true;
    }

    if (Point.isInsideCircle(x1, y, r, cx, cy)) {
      return true;
    }

    if (Point.isInsideCircle(x2, y, r, cx, cy)) {
      return true;
    }
    return false;
  }

  public static boolean intersectsCircle(
      int fromX, int fromY, int toX, int toY, int cx, int cy, int r) {
    int angle = Angle.getDegrees(fromX, fromY, toX, toY);
    angle = Angle.getAngularDistanceByRotatingCounterclockwise(angle, 0);

    long p = Point.rotateCounterclockwise(fromX, fromY, toX, toY, angle);
    toX = Point.getX(p);

    p = Point.rotateCounterclockwise(fromX, fromY, cx, cy, angle);
    cx = Point.getX(p);
    cy = Point.getY(p);

    return intersectsCircle(fromX, toX, fromY, cx, cy, r);
  }

  // 判断两线段是否相交
  // http://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/
  public static boolean intersectsLine(
      int px1, int py1, int px2, int py2, int qx1, int qy1, int qx2, int qy2) {

    int r1 = Point.positionRelativeToLine(px1, py1, px2, py2, qx1, qy1);
    int r2 = Point.positionRelativeToLine(px1, py1, px2, py2, qx2, qy2);

    int r3 = Point.positionRelativeToLine(qx1, qy1, qx2, qy2, px1, py1);
    int r4 = Point.positionRelativeToLine(qx1, qy1, qx2, qy2, px2, py2);

    if (r1 != r2 && r3 != r4) {
      return true;
    }

    if (r1 == 0 && isInsideRectangle(px1, py1, px2, py2, qx1, qy1)) {
      return true;
    }

    if (r2 == 0 && isInsideRectangle(px1, py1, px2, py2, qx2, qy2)) {
      return true;
    }

    if (r3 == 0 && isInsideRectangle(qx1, qy1, qx2, qy2, px1, py1)) {
      return true;
    }

    if (r4 == 0 && isInsideRectangle(qx1, qy1, qx2, qy2, px2, py2)) {
      return true;
    }

    return false;
  }

  private static boolean isInsideRectangle(int fromX, int fromY, int toX, int toY, int x, int y) {
    int x1, x2;
    if (fromX < toX) {
      x1 = fromX;
      x2 = toX;
    } else {
      x1 = toX;
      x2 = fromX;
    }
    if (x < x1 || x > x2) {
      return false;
    }

    int y1, y2;
    if (fromY < toY) {
      y1 = fromY;
      y2 = toY;
    } else {
      y1 = toY;
      y2 = fromY;
    }

    if (y < y1 || y > y2) {
      return false;
    }
    return true;
  }
}
