package io.ytcode.geometry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AngleTest {

  @Test
  void getRadians() {
    double d = Angle.getRadians(0, 0, 1, 0);
    assertEquals(d, 0);

    d = Angle.getRadians(0, 0, -1, 0);
    assertEquals(d, Math.PI);

    d = Angle.getRadians(0, 0, Integer.MAX_VALUE, -1);
    assertTrue(d < 2 * Math.PI);
  }

  @Test
  void getDegrees() {
    int d = Angle.getDegrees(0, 0, 1, 0);
    assertEquals(d, 0);

    d = Angle.getDegrees(0, 0, 1, 1);
    assertEquals(d, 45);

    d = Angle.getDegrees(0, 0, 0, 1);
    assertEquals(d, 90);

    d = Angle.getDegrees(0, 0, -1, 1);
    assertEquals(d, 135);

    d = Angle.getDegrees(0, 0, -1, 0);
    assertEquals(d, 180);

    d = Angle.getDegrees(0, 0, -1, -1);
    assertEquals(d, 225);

    d = Angle.getDegrees(0, 0, 0, -1);
    assertEquals(d, 270);

    d = Angle.getDegrees(0, 0, 1, -1);
    assertEquals(d, 315);

    d = Angle.getDegrees(0, 0, Integer.MAX_VALUE, -1);
    assertEquals(d, 0);
  }

  @Test
  void getShortestAngularDistance() {
    int d = Angle.getShortestAngularDistance(0, 0);
    assertEquals(d, 0);

    d = Angle.getShortestAngularDistance(0, 180);
    assertEquals(d, 180);

    d = Angle.getShortestAngularDistance(0, 359);
    assertEquals(d, 1);

    d = Angle.getShortestAngularDistance(359, 0);
    assertEquals(d, 1);
  }

  @Test
  void getAngleInDegreesByRotatingCounterclockwise() {
    int d = Angle.getAngularDistanceByRotatingCounterclockwise(0, 0);
    assertEquals(d, 0);

    d = Angle.getAngularDistanceByRotatingCounterclockwise(0, 359);
    assertEquals(d, 359);

    d = Angle.getAngularDistanceByRotatingCounterclockwise(359, 0);
    assertEquals(d, 1);
  }
}
