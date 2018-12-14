package io.ytcode.geometry.benchmark;

import io.ytcode.geometry.Geometry;

public class Benchmark {

  public static void main(String[] args) {
    for (int i = 0; i < 3; i++) {
      benchmark();
      System.out.println();
    }
  }

  private static void benchmark() {
    double r;
    long ct;
    int n = 1_000_000_000;

    r = 0;
    ct = System.currentTimeMillis();
    for (int i = 0; i < n; i++) {
      r += baseline(i, 11, i, 10010);
    }
    System.out.println("0: " + r + ", " + (System.currentTimeMillis() - ct));

    r = 0;
    ct = System.currentTimeMillis();
    for (int i = 0; i < n; i++) {
      r += Geometry.getDistance(i, 11, i, 10010);
    }
    System.out.println("1: " + r + ", " + (System.currentTimeMillis() - ct));

  }

  public static double baseline(int x1, int y1, int x2, int y2) {
    return x1 + y1 + x2 + y2;
  }
}
