package io.ytcode.geometry.visual;

import javax.swing.*;
import java.awt.*;

public abstract class RectangularCoordinateCanvas extends JFrame {

  private static final int width = 800;
  private static final int height = 800;

  private static final int halfWidth = width / 2;
  private static final int halfHeight = height / 2;

  private static final int gridSize = 50;
  private static final int gridWidth = width / gridSize;
  private static final int gridHeight = height / gridSize;

  private static final int startX = 60;
  private static final int startY = 80;
  private static final int endX = startX + width;
  private static final int endY = startY + height;

  RectangularCoordinateCanvas(String name) {
    super(name);
    setSize(endX, endY);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private static int toPixelX(int x) {
    return startX + halfWidth + x;
  }

  private static int toPixelY(int y) {
    return startY + halfHeight - y;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);

    // 画竖线，分x轴
    for (int x = -halfWidth; x <= halfWidth; x += gridSize) {
      drawLine(g, x, -halfHeight, x, halfHeight, Color.BLACK);
      drawString(g, "" + x, x - 5, halfHeight + 5);
      drawString(g, "" + x, x - 5, -halfHeight - 15);
    }

    // 画横线，分y轴
    for (int y = -halfHeight; y <= halfHeight; y += gridSize) {
      drawLine(g, -halfWidth, y, halfWidth, y, Color.BLACK);
      drawString(g, "" + y, -halfWidth - 25, y - 5);
      drawString(g, "" + y, halfWidth + 5, y - 5);
    }

    // 画中心线
    drawLine(g, -halfWidth, 0, halfWidth, 0, Color.RED);
    drawLine(g, 0, -halfHeight, 0, halfHeight, Color.RED);
    drawString(g, "(0 0)", -14, -5);

    // 测试所有点
    for (int x = -halfWidth; x <= halfWidth; x ++) {
      for (int y = -halfHeight; y <= halfHeight; y ++) {
        if (colorPoint(x, y)) {
          drawLine(g, x, y, x, y, Color.GRAY);
        }
      }
    }
  }

  protected abstract boolean colorPoint(int x, int y);

  protected void drawLine(Graphics g, int x1, int y1, int x2, int y2, Color c) {
    x1 = toPixelX(x1);
    y1 = toPixelY(y1);

    x2 = toPixelX(x2);
    y2 = toPixelY(y2);

    g.setColor(c);
    g.drawLine(x1, y1, x2, y2);
  }

  protected void drawString(Graphics g, String s, int x, int y) {
    x = toPixelX(x);
    y = toPixelY(y);
    g.drawString(s, x, y);
  }


  static void check(boolean b) {
    if (!b) {
      throw new RuntimeException();
    }
  }
}
