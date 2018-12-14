package io.ytcode.geometry;

import java.util.concurrent.ThreadLocalRandom;

import static io.ytcode.geometry.Point.toPoint;
import static java.lang.Math.addExact;

public class Sector {

  public static long randomPointOnTheArc(
      int cx, int cy, int r, int middleRayAngle, int halfCentralAngle) {
    int angle =
        ThreadLocalRandom.current()
            .nextInt(middleRayAngle - halfCentralAngle, middleRayAngle + halfCentralAngle);
    angle = Angle.normalizeDegrees(angle);
    int x = addExact(cx, Utils.round(Geometry.cos(angle) * r));
    int y = addExact(cy, Utils.round(Geometry.sin(angle) * r));
    return toPoint(x, y);
  }

  public static long randomPoint(int cx, int cy, int r, int faceAngle, int halfAngle) {
    return randomPointOnTheArc(
        cx, cy, ThreadLocalRandom.current().nextInt(r), faceAngle, halfAngle);
  }
}
