package io.ytcode.geometry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PointTest {

  @Test
  void move() {
    int[] xs = new int[] {1, 1, 0, -1, -1, -1, 0, 1};
    int[] ys = new int[] {0, 1, 1, 1, 0, -1, -1, -1};

    for (int i = 0; i < 8; i++) {
      int degrees = i * 45;
      double distance = (i & 1) == 0 ? 1 : Math.hypot(1, 1);
      long p = Point.move(0, 0, degrees, distance);
      int x = Point.getX(p);
      int y = Point.getY(p);
      assertEquals(x, xs[i], "i=" + i);
      assertEquals(y, ys[i], "i=" + i);
    }

    long p = Point.move(-100, 100, 135, -141);
    int x = Point.getX(p);
    int y = Point.getY(p);
    assertEquals(x, 0);
    assertEquals(y, 0);
  }

  @Test
  void rotateCounterclockwise() {
    int d = 10;
    int a = Utils.round(Math.sqrt((double) d * d / 2));

    int[] xs = new int[] {d, a, 0, -a, -d, -a, 0, a};
    int[] ys = new int[] {0, a, d, a, 0, -a, -d, -a};

    for (int i = 0; i < 8; i++) {
      int degrees = i * 45;
      long p = Point.rotateCounterclockwise(0, 0, d, 0, degrees);
      int x0 = Point.getX(p);
      int y = Point.getY(p);

      assertEquals(x0, xs[i], "i=" + i);
      assertEquals(y, ys[i], "i=" + i);
    }
  }

  @Test
  void isInsideRectangle() {
    boolean b = Point.isInsideRectangle(0, 0, 2, 2, 0, -1, 0);
    assertTrue(b);

    b = Point.isInsideRectangle(0, 0, 2, 2, 0, 1, 0);
    assertFalse(b);
  }
}
