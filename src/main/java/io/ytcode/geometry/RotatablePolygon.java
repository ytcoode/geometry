package io.ytcode.geometry;

import java.util.Arrays;

import static io.ytcode.geometry.Point.getX;
import static io.ytcode.geometry.Point.getY;
import static io.ytcode.geometry.Polygon.isValid;
import static io.ytcode.geometry.Utils.check;
import static io.ytcode.geometry.Utils.checkAngle;

public class RotatablePolygon {

  public static RotatablePolygon from(int[] xx, int[] yy, int cx, int cy) {
    check(
        isValid(xx, yy),
        "RotatablePolygon.from illegal points! %s, %s",
        Arrays.toString(xx),
        Arrays.toString(yy));

    check(
        Point.isInsidePolygon(xx, yy, cx, cy),
        "RotatablePolygon.from illegal center point! %s, %s, %s-%s",
        Arrays.toString(xx),
        Arrays.toString(yy),
        cx,
        cy);

    return new RotatablePolygon(xx, yy, cx, cy);
  }

  private final Polygon[] polygons;

  private RotatablePolygon(int[] xx, int[] yy, int cx, int cy) {
    this.polygons = new Polygon[360];
    for (int angle = 0; angle < polygons.length; angle++) {
      int[] xx2 = new int[xx.length];
      int[] yy2 = new int[yy.length];

      for (int i = 0; i < xx.length; i++) {
        int x = xx[i];
        int y = yy[i];

        long p = Point.rotateCounterclockwise(cx, cy, x, y, angle);
        x = getX(p);
        y = getY(p);

        xx2[i] = x;
        yy2[i] = y;
      }
      polygons[angle] = Polygon.from(xx2, yy2, true);
    }
  }

  public boolean contains(int x, int y, int angle) {
    checkAngle(angle);
    return polygons[angle].contains(x, y);
  }

  public boolean intersectsCircle(int x, int y, int r, int angle) {
    checkAngle(angle);
    return polygons[angle].intersectsCircle(x, y, r);
  }
}
