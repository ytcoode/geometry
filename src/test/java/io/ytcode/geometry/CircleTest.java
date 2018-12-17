package io.ytcode.geometry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CircleTest {

  @Test
  void intersectsRectangle() {
    boolean b = Circle.intersectsRectangle(0, 0, 2, 2, 0, -2, 0, 1);
    assertTrue(b);

    b = Circle.intersectsRectangle(0, 0, 2, 2, 0, 2, 0, 1);
    assertFalse(b);
  }
}
