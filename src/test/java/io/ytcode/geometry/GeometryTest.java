package io.ytcode.geometry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeometryTest {

  @Test
  void sin() {
    for (int i = 0; i < 360; i++) {
      assertEquals(Geometry.sin(i), Math.sin(Math.toRadians(i)));
    }
    assertEquals(Geometry.sin(30), Geometry.sin(180 - 30));
  }

  @Test
  void cos() {
    for (int i = 0; i < 360; i++) {
      assertEquals(Geometry.cos(i), Math.cos(Math.toRadians(i)));
    }
    assertEquals(Geometry.cos(30), -Geometry.cos(180 - 30));
  }

  @Test
  void tan() {
    for (int i = 0; i < 360; i++) {
      assertEquals(Geometry.tan(i), Math.tan(Math.toRadians(i)));
    }
  }

  @Test
  void getDistance() {
    double d = Geometry.getDistance(0, 0, 4, 3);
    assertEquals(Math.round(d), 5);
  }

  @Test
  void getDistanceToInt() {
    int d = Geometry.getDistanceToInt(0, 0, 4, 3);
    assertEquals(d, 5);
  }
}
