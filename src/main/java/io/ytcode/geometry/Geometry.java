package io.ytcode.geometry;

public class Geometry {

  private static final double[] cacheSin;
  private static final double[] cacheCos;
  private static final double[] cacheTan;

  static {
    int degrees = 360;

    cacheSin = new double[degrees];
    cacheCos = new double[degrees];
    cacheTan = new double[degrees];

    for (int i = 0; i < degrees; i++) {
      cacheSin[i] = Math.sin(Math.toRadians(i));
      cacheCos[i] = Math.cos(Math.toRadians(i));
      cacheTan[i] = Math.tan(Math.toRadians(i));
    }
  }

  /** @param degrees [0,360) */
  public static double sin(int degrees) {
    return cacheSin[degrees];
  }

  /** @param degrees [0,360) */
  public static double cos(int degrees) {
    return cacheCos[degrees];
  }

  /** @param degrees [0,360) */
  public static double tan(int degrees) {
    return cacheTan[degrees];
  }

  public static double getDistance(int x1, int y1, int x2, int y2) {
    int a = Math.subtractExact(x2, x1);
    a = Math.multiplyExact(a, a);

    int b = Math.subtractExact(y2, y1);
    b = Math.multiplyExact(b, b);

    return Math.sqrt(Math.addExact(a, b)); // 比hypot方式快几十倍
    //        return Math.hypot(x1 - x2, y1 - y2);
  }

  public static int getDistanceToInt(int x1, int y1, int x2, int y2) {
    return Utils.round(getDistance(x1, y1, x2, y2));
  }
}
