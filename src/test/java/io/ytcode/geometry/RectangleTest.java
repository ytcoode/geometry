package io.ytcode.geometry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RectangleTest {

  @Test
  void intersectsRectangle() {
    boolean b = Rectangle.intersectsRectangle(-2, 2, -1, 1, 2, 3, 1, 2);
    assertTrue(b);
  }
}
