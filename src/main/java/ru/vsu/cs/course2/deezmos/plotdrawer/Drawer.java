package ru.vsu.cs.course2.deezmos.plotdrawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelFormat;
import javafx.scene.paint.Color;

/**
 * PlotDrawer
 */
public class Drawer {

  private GraphicsContext gc;

  public Drawer(GraphicsContext graphicsContext) {
    this.gc = graphicsContext;
  }

  // PIXEL

  public void drawPixel(int x, int y, Color color) {
    gc.getPixelWriter().setColor(x, y, color);
  }

  public void drawPixel(int x, int y, float opacity, Color color) {
    gc.getPixelWriter().setColor((int) x, (int) y, new Color(
        color.getRed(),
        color.getGreen(),
        color.getBlue(),
        opacity));
  }

  public void drawLineDDA(int x0, int y0, int x1, int y1, Color color) {
    int dx = (x1 - x0);
    int dy = (y1 - y0);

    int step = Math.max(Math.abs(dx), Math.abs(dy));

    float dxf = ((float) dx) / step;
    float dyf = ((float) dy) / step;

    float x = x0;
    float y = y0;
    for (int i = 0; i <= step; i++) {
      drawPixel(Math.round(x), Math.round(y), color);
      x = x + dxf;
      y = y + dyf;
    }
  }

  // DDA LINE

  public void drawDefauldLineBresenhamFloat(int x0, int y0, int x1, int y1, Color color) {
    int deltaX = Math.abs(x1 - x0);
    int deltaY = Math.abs(y1 - y0);
    int y = y0;
    int directionY = y1 - y0;

    float error = 0;
    float deltaErr = (deltaY + 1) * 1.0f / (deltaX + 1);

    if (directionY > 0) {
      directionY = 1;
    } else if (directionY < 0) {
      directionY = -1;
    }

    for (int x = x0; x <= x1; x++) {
      drawPixel(x, y, color);

      error = error + deltaErr;

      if (error >= 1.0f) {
        y = y + directionY;
        error = error - 1.0f;
      }
    }
  }

  // BRESENHAM LINE

  public void drawLineBresenham(int x0, int y0, int x1, int y1, Color color) {
    drawDefaultLineBresenham(x0, y0, x1, y1, color);
  }

  private void drawDefaultLineBresenham(int x0, int y0, int x1, int y1, Color color) {
    int diry = y1 - y0;

    int absDx = Math.abs(x1 - x0);
    int absDy = Math.abs(diry);

    int y = y0;

    int err = 0;
    int derr = absDy + 1;

    if (diry > 0) {
      diry = 1;
    } else if (diry < 0) {
      diry = -1;
    }

    for (int x = x0; x <= x1; x++) {
      drawPixel(x, y, color);

      err += derr;

      if (err >= (absDx + 1)) {
        y += diry;
        err -= (absDx + 1);
      }
    }
  }

  // WU LINE

  private float fracPart(float value) {
    return value - ((int) value);
  }

  private float reversedFracPart(float value) {
    return 1 - fracPart(value);
  }

  public void drawLineWu(float x0, float y0, float x1, float y1, Color color) {

    boolean steep = Math.abs(y1 - y0) > Math.abs(x1 - x0);
    if (steep) {
      float tmp = x0;
      x0 = y0;
      y0 = tmp;
    }
    if (x0 > x1) {
      float tmp = x0;
      x0 = x1;
      x1 = tmp;

      tmp = y0;
      y0 = y1;
      y1 = tmp;
    }

    float dx = x1 - x0;
    float dy = y1 - y0;

    float gradient = dx == 0 ? 1.0f : dy * 1.0f / dx;

    float xEnd = Math.round(x0);
    float yEnd = y0 + gradient * (xEnd - x0);
    float xGap = reversedFracPart(x0 + 0.5f);
    int xPixel1 = (int) xEnd;
    int yPixel1 = (int) yEnd;

    if (steep) {
      drawPixel(yPixel1, xPixel1, reversedFracPart(yEnd) * xGap, color);
      drawPixel(yPixel1 + 1, xPixel1, fracPart(yEnd) * xGap, color);
    } else {
      drawPixel(xPixel1, yPixel1, reversedFracPart(yEnd) * xGap, color);
      drawPixel(xPixel1, yPixel1 + 1, fracPart(yEnd) * xGap, color);
    }

    float interY = yEnd + gradient;

    xEnd = Math.round(x1);
    yEnd = y1 + gradient * (xEnd - x1);
    xGap = fracPart(x1 + 0.5f);
    int xPixel2 = (int) xEnd;
    int yPixel2 = (int) yEnd;

    if (steep) {
      drawPixel(yPixel2, xPixel2, reversedFracPart(yEnd) * xGap, color);
      drawPixel(yPixel2 + 1, xPixel2, fracPart(yEnd) * xGap, color);
    } else {
      drawPixel(xPixel2, yPixel2, reversedFracPart(yEnd) * xGap, color);
      drawPixel(xPixel2, yPixel2 + 1, fracPart(yEnd) * xGap, color);
    }

    if (steep) {
      for (int x = xPixel1 + 1; x < xPixel2; x++) {
        drawPixel((int) interY, x, reversedFracPart(interY), color);
        drawPixel((int) interY + 1, x, fracPart(interY), color);
        interY += gradient;
      }
    } else {
      for (int x = xPixel1 + 1; x < xPixel2; x++) {
        drawPixel(x, (int) interY, reversedFracPart(interY), color);
        drawPixel(x, (int) interY + 1, fracPart(interY), color);
        interY += gradient;
      }
    }
  }
}
