package io.ytcode.geometry;

import java.util.Arrays;

import static io.ytcode.geometry.Point.getX;
import static io.ytcode.geometry.Point.getY;
import static io.ytcode.geometry.Utils.check;
import static io.ytcode.geometry.Utils.checkAngle;
import static java.lang.Math.addExact;
import static java.lang.Math.subtractExact;

public class Polygon {

  // xx,yy为当该多边形中心点为(0,0)，角度为0时的各个点
  public static Polygon from(int[] xx, int[] yy) {
    return from(xx, yy, false);
  }

  public static Polygon from(int[] xx, int[] yy, boolean optimizeForSpeed) {
    check(
        isValid(xx, yy),
        "Polygon.from illegal points! %s, %s",
        Arrays.toString(xx),
        Arrays.toString(yy));
    return new Polygon(xx, yy, optimizeForSpeed);
  }

  private final int[] xx;
  private final int[] yy;

  private final int x1, x2, y1, y2; // 外接矩形
  private RotatedEdge[] rotatedEdges; // 多边形的所有边都旋转到与x轴水平，方便与其他的各种复杂类型做相交判断

  private Polygon(int[] xx, int[] yy, boolean optimizeForSpeed) {
    this.xx = xx;
    this.yy = yy;

    int minX = Integer.MAX_VALUE;
    int maxX = Integer.MIN_VALUE;

    for (int x : xx) {
      if (x < minX) {
        minX = x;
      }
      if (x > maxX) {
        maxX = x;
      }
    }

    int minY = Integer.MAX_VALUE;
    int maxY = Integer.MIN_VALUE;

    for (int y : yy) {
      if (y < minY) {
        minY = y;
      }
      if (y > maxY) {
        maxY = y;
      }
    }

    this.x1 = minX;
    this.x2 = maxX;
    this.y1 = minY;
    this.y2 = maxY;

    if (optimizeForSpeed) {
      initRotatedEdgesIfNull();
    }
  }

  public boolean contains(int pcx, int pcy, int angle, int x, int y) {
    // 先把角度旋转到0度
    angle = Angle.getAngularDistanceByRotatingCounterclockwise(angle, 0);
    long p = Point.rotateCounterclockwise(pcx, pcy, x, y, angle);
    x = Point.getX(p);
    y = Point.getY(p);

    // 再把中心点移动到0
    x = subtractExact(x, pcx);
    y = subtractExact(y, pcy);

    if (!Point.isInsideRectangle(x1, x2, y1, y2, x, y)) {
      return false;
    }
    return Point.isInsidePolygon(xx, yy, x, y);
  }

  public boolean intersectsCircle(int pcx, int pcy, int angle, int cx, int cy, int r) {
    checkAngle(angle);
    /*
     * 该多边形存的各种数据是在多边形中心为0，且角度为0时的情况
     * 所以只要经过旋转、移动，把多边形和圆的相同位置移到0处，即可用该数据计算
     */

    // 先把角度旋转到0度
    angle = Angle.getAngularDistanceByRotatingCounterclockwise(angle, 0);
    long p = Point.rotateCounterclockwise(pcx, pcy, cx, cy, angle);
    cx = Point.getX(p);
    cy = Point.getY(p);

    // 再把中心点移动到0
    cx = subtractExact(cx, pcx);
    cy = subtractExact(cy, pcy);

    // 用当前数据计算
    if (!Point.isInsideRectangle(
        subtractExact(x1, r), addExact(x2, r), subtractExact(y1, r), addExact(y2, r), cx, cy)) {
      return false;
    }

    if (Point.isInsidePolygon(xx, yy, cx, cy)) {
      return true;
    }

    initRotatedEdgesIfNull();
    for (RotatedEdge edge : rotatedEdges) {
      if (edge.intersectsCircle(cx, cy, r)) {
        return true;
      }
    }
    return false;
  }

  private void initRotatedEdgesIfNull() {
    if (rotatedEdges != null) {
      return;
    }
    RotatedEdge[] r = new RotatedEdge[xx.length];
    int lx = xx[xx.length - 1];
    int ly = yy[yy.length - 1];
    int nx, ny;

    for (int i = 0; i < r.length; i++, lx = nx, ly = ny) {
      nx = xx[i];
      ny = yy[i];
      r[i] = new RotatedEdge(lx, ly, nx, ny);
    }
    rotatedEdges = r;
  }

  private static class RotatedEdge {
    final int rotatedAngle;
    final int x1, x2, y1;

    RotatedEdge(int x1, int y1, int x2, int y2) {
      int angle = Angle.getDegrees(x1, y1, x2, y2);
      this.rotatedAngle = Angle.getAngularDistanceByRotatingCounterclockwise(angle, 0);
      long p = Point.rotateCounterclockwise(x1, y1, x2, y2, rotatedAngle);
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = getX(p);
    }

    boolean intersectsCircle(int x, int y, int r) {
      long p = Point.rotateCounterclockwise(x1, y1, x, y, rotatedAngle);
      x = getX(p);
      y = getY(p);
      return Line.intersectsCircle(x1, x2, y1, x, y, r);
    }
  }

  public static boolean isValid(int[] xx, int[] yy) {
    return isValid(xx, yy, true);
  }

  public static boolean isValid(int[] xx, int[] yy, boolean strict) {
    if (xx.length != yy.length) {
      return false;
    }

    if (xx.length < 3) {
      return false;
    }

    int c = xx.length;

    for (int i = 0; i < xx.length; i++) {
      int[] l1 = getLine(xx, yy, i);
      if (l1[0] == l1[2] && l1[1] == l1[3]) {
        if (strict) {
          return false;
        }
        c--;
      }

      int[] l2 = getLine(xx, yy, i + 1);

      if (Angle.getRadians(l1[0], l1[1], l1[2], l1[3])
          == Angle.getRadians(l2[0], l2[1], l2[2], l2[3])) {
        return false; // 两条相邻线段居然斜率相同
      }

      for (int[] l3 : getNotAdjacentLines(xx, yy, i)) {
        if (Line.intersectsLine(l1[0], l1[1], l1[2], l1[3], l3[0], l3[1], l3[2], l3[3])) {
          return false; // 不相邻的两条线段之间居然相交了
        }
      }
    }

    if (c < 3) {
      return false;
    }

    return true;
  }

  private static int[] getLine(int[] xx, int[] yy, int i) {
    i %= xx.length;
    if (i == xx.length - 1) {
      return new int[] {xx[i], yy[i], xx[0], yy[0]};
    }
    return new int[] {xx[i], yy[i], xx[i + 1], yy[i + 1]};
  }

  private static int[][] getNotAdjacentLines(int[] xx, int[] yy, int i) {
    int[][] r = new int[xx.length - 3][];
    for (int j = 0; j < r.length; j++) {
      r[j] = getLine(xx, yy, i + 2 + j);
    }
    return r;
  }
}
