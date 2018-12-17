package io.ytcode.geometry;

public class Rectangle {

  public static boolean intersectsRectangle(
      int ax1, int ax2, int ay1, int ay2, int bx1, int bx2, int by1, int by2) {
    if (bx2 < ax1) {
      return false;
    }
    if (bx1 > ax2) {
      return false;
    }

    if (by2 < ay1) {
      return false;
    }

    if (by1 > ay2) {
      return false;
    }
    return true;
  }
}
