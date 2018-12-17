package io.ytcode.geometry;

import java.util.concurrent.ThreadLocalRandom;

import static io.ytcode.geometry.Point.getX;
import static io.ytcode.geometry.Point.getY;
import static io.ytcode.geometry.Point.toPoint;
import static io.ytcode.geometry.Utils.checkAngle;
import static io.ytcode.geometry.Utils.isEven;
import static java.lang.Math.addExact;
import static java.lang.Math.subtractExact;

public class Circle {

  // 判断圆形是否和规范化的矩形相交
  public static boolean intersectsRectangle2(
      int x1, int x2, int y1, int y2, int cx, int cy, int r) {
    // http://stackoverflow.com/questions/401847/circle-rectangle-collision-detection-intersection

    if (!Point.isInsideRectangle(
        subtractExact(x1, r), addExact(x2, r), subtractExact(y1, r), addExact(y2, r), cx, cy)) {
      return false;
    }

    if (cx >= x1 && cx <= x2) {
      return true;
    }

    if (cy >= y1 && cy <= y2) {
      return true;
    }

    if (Point.isInsideCircle(x1, y1, r, cx, cy)) {
      return true;
    }

    if (Point.isInsideCircle(x2, y1, r, cx, cy)) {
      return true;
    }

    if (Point.isInsideCircle(x1, y2, r, cx, cy)) {
      return true;
    }

    if (Point.isInsideCircle(x2, y2, r, cx, cy)) {
      return true;
    }

    return false;
  }

  public static boolean intersectsRectangle(
      int rx,
      int ry,
      int rcxOffset,
      int rcyOffset,
      int width,
      int height,
      int angle,
      int cx,
      int cy,
      int r) {

    checkAngle(angle);
    angle = Angle.getAngularDistanceByRotatingCounterclockwise(angle, 0);

    long p = Point.rotateCounterclockwise(rx, ry, cx, cy, angle);
    cx = getX(p);
    cy = getY(p);

    int rcx = addExact(rx, rcxOffset);
    int rcy = addExact(ry, rcyOffset);

    int halfWidth = width / 2;
    int halfHeight = height / 2;

    int x1 = subtractExact(rcx, halfWidth);
    int x2 = addExact(rcx, halfWidth);

    int y1 = subtractExact(rcy, halfHeight);
    int y2 = addExact(rcy, halfHeight);

    if (isEven(width)) {
      x2 = subtractExact(x2, 1);
    }

    if (isEven(height)) {
      y2 = subtractExact(y2, 1);
    }

    return intersectsRectangle2(x1, x2, y1, y2, cx, cy, r);
  }

  public static boolean intersectsRectangle(
      int rx, int ry, int width, int height, int angle, int cx, int cy, int r) {
    return intersectsRectangle(rx, ry, 0, 0, width, height, angle, cx, cy, r);
  }

  // 是否与圆相交
  public static boolean intersectsCircle(int cx1, int cy1, int r1, int cx2, int cy2, int r2) {
    int dr = addExact(r1, r2);
    int dx = subtractExact(cx2, cx1);
    int dy = subtractExact(cy2, cy1);
    return Point.isInsideCircle(dr, dx, dy);
  }

  // 是否与扇形相交
  public static boolean intersectsSector(
      int cx1,
      int cy1,
      int r1,
      int middleRayAngle,
      int halfCentralAngle,
      int cx2,
      int cy2,
      int r2) {
    checkAngle(middleRayAngle);

    // 是否在扇形所属圆内
    if (!intersectsCircle(cx1, cy1, r1, cx2, cy2, r2)) {
      return false;
    }

    // 是否在扇形角度内
    if (Point.isInsideAngle(cx1, cy1, middleRayAngle, halfCentralAngle, cx2, cy2)) {
      return true;
    }

    // 是否在扇形所属圆的圆心周围
    if (Point.isInsideCircle(r2, subtractExact(cx2, cx1), subtractExact(cy2, cy1))) {
      return true;
    }

    // 旋转扇形，使其起始边和终止边分别和x右半轴重合，然后计算相交的其他特殊情况

    int edgeX = cx1 + r1; // 旋转之后，扇形圆周与x右半轴的交点

    // 旋转扇形，使起始边和x右半轴重合
    int angle = middleRayAngle - halfCentralAngle;
    angle = Angle.normalizeDegrees(angle);
    angle = Angle.getAngularDistanceByRotatingCounterclockwise(angle, 0);

    long p = Point.rotateCounterclockwise(cx1, cy1, cx2, cy2, angle);
    int newCx2 = getX(p);
    int newCy2 = getY(p);

    if (newCx2 >= cx1) {
      if (newCx2 <= edgeX) { // 边下面的矩形
        if (newCy2 <= cy1 && newCy2 >= subtractExact(cy1, r2)) {
          return true;
        }
      } else { // 角附近的圆形
        if (Point.isInsideCircle(r2, subtractExact(newCx2, edgeX), subtractExact(newCy2, cy1))) {
          return true;
        }
      }
    }

    // 旋转扇形，使终止边和x右半轴重合
    angle = middleRayAngle + halfCentralAngle;
    angle = Angle.normalizeDegrees(angle);
    angle = Angle.getAngularDistanceByRotatingCounterclockwise(angle, 0);

    p = Point.rotateCounterclockwise(cx1, cy1, cx2, cy2, angle);
    newCx2 = getX(p);
    newCy2 = getY(p);

    if (newCx2 >= cx1) {
      if (newCx2 <= edgeX) { // 边上面的矩形
        if (newCy2 >= cy1 && newCy2 <= addExact(cy1, r2)) {
          return true;
        }
      } else { // 角附近的圆形
        if (Point.isInsideCircle(r2, subtractExact(newCx2, edgeX), subtractExact(newCy2, cy1))) {
          return true;
        }
      }
    }

    // 其他
    return false;
  }

  // 是否与椭圆相交
  public static boolean intersectsEllipse(
      int cx1, int cy1, int a, int b, int angle, int cx2, int cy2, int r) {
    return Point.isInsideEllipse(
        cx1, cy1, addExact(a, r), addExact(b, r), angle, cx2, cy2); // 缺少理论证明
  }

  public static long randomPointOnTheEdge(int cx, int cy, int r) {
    int angle = ThreadLocalRandom.current().nextInt(360);
    int x = addExact(cx, Utils.round(Geometry.cos(angle) * r));
    int y = addExact(cy, Utils.round(Geometry.sin(angle) * r));
    return toPoint(x, y);
  }

  public static long randomPoint(int cx, int cy, int r) {
    return randomPointOnTheEdge(cx, cy, ThreadLocalRandom.current().nextInt(r));
  }
}
