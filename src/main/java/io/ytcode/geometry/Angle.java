package io.ytcode.geometry;

import static io.ytcode.geometry.Utils.checkAngle;

public class Angle {

  /** @return [0, 2pi) */
  public static double getRadians(int fromX, int fromY, int toX, int toY) {
    double angle = Math.atan2(Math.subtractExact(toY, fromY), Math.subtractExact(toX, fromX));
    if (angle < 0) {
      angle = Math.PI * 2 + angle;
    }
    return angle;
  }

  /** @return [0, 360) */
  public static int getDegrees(int fromX, int fromY, int toX, int toY) {
    int d = (int) Math.round(Math.toDegrees(getRadians(fromX, fromY, toX, toY)));
    return d == 360 ? 0 : d;
  }

  /**
   * @param angle1 angle1, in degrees [0,360)
   * @param angle2 angle2, in degrees [0,360)
   * @return an angle, in degrees [0,180]
   */
  public static int getShortestAngularDistance(int angle1, int angle2) {
    checkAngle(angle1);
    checkAngle(angle2);
    int a = Math.abs(angle2 - angle1);
    return a <= 180 ? a : 360 - a;
  }

  /**
   * @param fromAngle from angle, in degrees [0,360)
   * @param toAngle to angle, in degrees [0,360)
   * @return an angle, in degrees [0,360)
   */
  public static int getAngularDistanceByRotatingCounterclockwise(int fromAngle, int toAngle) {
    checkAngle(fromAngle);
    checkAngle(toAngle);
    return fromAngle <= toAngle ? toAngle - fromAngle : 360 - (fromAngle - toAngle);
  }

  public static int normalizeDegrees(int angle) {
    if (angle >= 0 && angle < 360) {
      return angle;
    }
    angle %= 360;
    if (angle < 0) {
      angle += 360;
    }
    return angle;
  }
}
