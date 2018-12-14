package io.ytcode.geometry;

import static java.lang.Math.addExact;

class Utils {

  static void check(boolean b) {
    if (!b) {
      throw new RuntimeException();
    }
  }

  static void check(boolean b, String msg) {
    if (!b) {
      throw new RuntimeException(msg);
    }
  }

  static void check(boolean b, String format, Object... args) {
    if (!b) {
      throw new RuntimeException(String.format(format, args));
    }
  }

  static void checkAngle(int angle) {
    check(angle >= 0 && angle < 360, "Illegal angle %s, legal range [0,360)", angle);
  }

  static int round(double d) {
    return Math.toIntExact(Math.round(d));
  }

  static long longMask(int nbit) {
    check(nbit >= 1 && nbit <= 64);
    return nbit == 64 ? -1 : (1L << nbit) - 1;
  }

  static long toLong(int i) {
    return i & longMask(32);
  }

  static long multiplyExactToLong(int x, int y) {
    return Math.multiplyExact((long) x, (long) y);
  }
}
