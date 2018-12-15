package io.ytcode.geometry;

import static io.ytcode.geometry.Utils.checkAngle;
import static io.ytcode.geometry.Utils.multiplyExactToLong;
import static io.ytcode.geometry.Utils.toLong;
import static java.lang.Math.addExact;
import static java.lang.Math.multiplyExact;
import static java.lang.Math.subtractExact;

public class Point {

  // 沿角度移动一定距离，求新的点坐标
  public static long move(int x, int y, int angle, double distance) {
    x = Math.addExact(x, Utils.round(distance * Geometry.cos(angle)));
    y = Math.addExact(y, Utils.round(distance * Geometry.sin(angle)));
    return toPoint(x, y);
  }

  // 逆时针旋转
  public static long rotateCounterclockwise(int cx, int cy, int x, int y, int angle) {
    checkAngle(angle);
    int deltaX = subtractExact(x, cx);
    int deltaY = subtractExact(y, cy);

    double sin = Geometry.sin(angle);
    double cos = Geometry.cos(angle);

    x = Math.addExact(cx, Utils.round(deltaX * cos - deltaY * sin));
    y = Math.addExact(cy, Utils.round(deltaX * sin + deltaY * cos));

    return toPoint(x, y);
  }

  // 顺时针旋转
  public static long rotateClockwise(int cx, int cy, int x, int y, int angle) {
    checkAngle(angle);
    int deltaX = subtractExact(x, cx);
    int deltaY = subtractExact(y, cy);

    double sin = Geometry.sin(angle);
    double cos = Geometry.cos(angle);

    x = Math.addExact(cx, Utils.round(deltaX * cos + deltaY * sin));
    y = Math.addExact(cy, Utils.round(deltaY * cos - deltaX * sin));

    return toPoint(x, y);
  }

  // 判断点在直线的哪一侧，0是在直线上，<0是左侧，>0是右侧
  public static int positionRelativeToLine(int fromX, int fromY, int toX, int toY, int x, int y) {
    //    long r = (x - toX) * (toY - fromY) - (y - toY) * (toX - fromX); // 两个都一样的
    //    long r = (x - fromX) * (toY - fromY) - (y - fromY) * (toX - fromX);

    long r =
        subtractExact(
            multiplyExactToLong(subtractExact(x, fromX), subtractExact(toY, fromY)),
            multiplyExactToLong(subtractExact(y, fromY), subtractExact(toX, fromX)));

    if (r < 0) {
      return -1;
    }
    if (r > 0) {
      return 1;
    }
    return 0;
  }

  // 判断点是否在直线上
  public static boolean isOnLine(int fromX, int fromY, int toX, int toY, int x, int y) {
    return positionRelativeToLine(fromX, fromY, toX, toY, x, y) == 0;
  }

  // 判断点是否在直线的左侧
  public static boolean isOnTheLeftSideOfTheLine(
      int fromX, int fromY, int toX, int toY, int x, int y) {
    return positionRelativeToLine(fromX, fromY, toX, toY, x, y) < 0;
  }

  // 判断点是否在直线的右侧
  public static boolean isOnTheRightSideOfTheLine(
      int fromX, int fromY, int toX, int toY, int x, int y) {
    return positionRelativeToLine(fromX, fromY, toX, toY, x, y) > 0;
  }

  // 判断点是否在线段上
  public static boolean isOnLineSegment(int fromX, int fromY, int toX, int toY, int x, int y) {
    int a, b;

    if (fromX < toX) {
      a = fromX;
      b = toX;
    } else {
      a = toX;
      b = fromX;
    }

    if (x < a || x > b) {
      return false;
    }

    if (fromY < toY) {
      a = fromY;
      b = toY;
    } else {
      a = toY;
      b = fromY;
    }

    if (y < a || y > b) {
      return false;
    }

    return isOnLine(fromX, fromY, toX, toY, x, y);
  }

  public static boolean isInsideRectangle(int x1, int x2, int y1, int y2, int x, int y) {
    return x >= x1 && x <= x2 && y >= y1 && y <= y2;
  }

  public static boolean isInsideRectangle(
      int rx,
      int ry,
      int rcxOffset,
      int rcyOffset,
      int halfWidth,
      int halfHeight,
      int angle,
      int x,
      int y) {
    checkAngle(angle);
    angle = Angle.getAngularDistanceByRotatingCounterclockwise(angle, 0);

    long p = rotateCounterclockwise(rx, ry, x, y, angle);
    x = getX(p);
    y = getY(p);

    int rcx = addExact(rx, rcxOffset);
    int rcy = addExact(ry, rcyOffset);

    return isInsideRectangle(
        subtractExact(rcx, halfWidth),
        addExact(rcx, halfWidth),
        subtractExact(rcy, halfHeight),
        addExact(rcy, halfHeight),
        x,
        y);
  }

  public static boolean isInsideRectangle(
      int rx, int ry, int rcxOffset, int halfWidth, int halfHeight, int angle, int x, int y) {
    return isInsideRectangle(rx, ry, rcxOffset, 0, halfWidth, halfHeight, angle, x, y);
  }

  public static boolean isInsideRectangle(
      int rx, int ry, int halfWidth, int halfHeight, int angle, int x, int y) {
    return isInsideRectangle(rx, ry, 0, halfWidth, halfHeight, angle, x, y);
  }

  // 判断点是否在圆形范围内
  public static boolean isInsideCircle(int cx, int cy, int r, int x, int y) {
    int dx = subtractExact(x, cx);
    int dy = subtractExact(y, cy);
    return isInsideCircle(r, dx, dy);
  }

  public static boolean isInsideCircle(int r, int x, int y) {
    if (Math.abs(x) > r) {
      return false;
    }
    if (Math.abs(y) > r) {
      return false;
    }
    return addExact(multiplyExactToLong(x, x), multiplyExactToLong(y, y))
        <= multiplyExactToLong(r, r);
  }

  // 判断点是否在扇形中
  public static boolean isInsideSector(
      int cx, int cy, int r, int middleRayAngle, int halfCentralAngle, int x, int y) {
    checkAngle(middleRayAngle);

    if (!isInsideCircle(cx, cy, r, x, y)) {
      return false;
    }
    return isInsideAngle(cx, cy, middleRayAngle, halfCentralAngle, x, y);
  }

  public static boolean isInsideAngle(
      int cx, int cy, int middleRayAngle, int halfCentralAngle, int x, int y) {
    if (halfCentralAngle >= 180) {
      return true;
    }
    int a = Angle.getDegrees(cx, cy, x, y);
    a = Angle.getShortestAngularDistance(a, middleRayAngle);
    return a <= halfCentralAngle;
  }

  // 判断点是否在椭圆内
  public static boolean isInsideEllipse(
      int cx, int cy, int a, int b, int angle, int x, int y) { // cx、cy为椭圆心，a、b为长短半轴

    // 椭圆公式：x^2/a^2 + y^2/b^2 = 1
    // 推导公式：x^2*b^2 + y^2*a^2 = a^2*b^2
    // 当等于时，xy构成椭圆边
    // 大于时，xy位于椭圆外
    // 小于时，xy位于椭圆内

    if (angle != 90 && angle != 270) {
      angle = Angle.getAngularDistanceByRotatingCounterclockwise(angle, 90);
      long p = Point.rotateCounterclockwise(cx, cy, x, y, angle);
      x = Point.getX(p);
      y = Point.getY(p);
    }

    int dx = subtractExact(x, cx);
    if (Math.abs(dx) > a) {
      return false;
    }

    int dy = subtractExact(y, cy);
    if (Math.abs(dy) > b) {
      return false;
    }

    long dxx = multiplyExactToLong(dx, dx);
    long dyy = multiplyExactToLong(dy, dy);
    long aa = multiplyExactToLong(a, a);
    long bb = multiplyExactToLong(b, b);

    return addExact(multiplyExact(dxx, bb), multiplyExact(dyy, aa)) <= multiplyExact(aa, bb);
  }

  // 判断点是否在多边形内
  public static boolean isInsidePolygon(
      int[] xx, int[] yy, double x, double y) { // 参照jdk的Polygon.contains方法
    int hits = 0;

    int lastx = xx[xx.length - 1];
    int lasty = yy[yy.length - 1];
    int curx, cury;

    // Walk the edges of the polygon
    for (int i = 0; i < xx.length; lastx = curx, lasty = cury, i++) {
      curx = xx[i];
      cury = yy[i];

      if (cury == lasty) {
        continue;
      }

      int leftx;
      if (curx < lastx) {
        if (x >= lastx) {
          continue;
        }
        leftx = curx;
      } else {
        if (x >= curx) {
          continue;
        }
        leftx = lastx;
      }

      double test1, test2;
      if (cury < lasty) {
        if (y < cury || y >= lasty) {
          continue;
        }
        if (x < leftx) {
          hits++;
          continue;
        }
        test1 = x - curx;
        test2 = y - cury;
      } else {
        if (y < lasty || y >= cury) {
          continue;
        }
        if (x < leftx) {
          hits++;
          continue;
        }
        test1 = x - lastx;
        test2 = y - lasty;
      }

      if (test1 < (test2 / (lasty - cury) * (lastx - curx))) {
        hits++;
      }
    }

    return ((hits & 1) != 0);
  }

  public static long toPoint(int x, int y) {
    return toLong(x) << 32 | toLong(y);
  }

  public static int getY(long l) {
    return (int) l;
  }

  public static int getX(long l) {
    return (int) (l >>> 32);
  }
}
