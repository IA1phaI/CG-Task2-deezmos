package ru.vsu.cs.course2.deezmos.plotdrawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * PlotDrawer
 */
public class Drawer {

  public static final float EPS = 0.0000001f;

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

  // DDA LINE

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

  // BRESENHAM LINE ON FLOAT

  public void drawLineBresenhamFloat(int x0, int y0, int x1, int y1, Color color) {
    if (Math.abs(y1 - y0) < Math.abs(x1 - x0)) {
      if (x0 > x1) {
        drawLineBresenhamFloatLow(x1, y1, x0, y0, color);
      } else {
        drawLineBresenhamFloatLow(x0, y0, x1, y1, color);
      }
    } else {
      if (y0 > y1) {
        drawLineBresenhamFloatHigh(x1, y1, x0, y0, color);
      } else {
        drawLineBresenhamFloatHigh(x0, y0, x1, y1, color);
      }
    }
  }

  public void drawLineBresenhamFloatLow(int x0, int y0, int x1, int y1, Color color) {
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

  public void drawLineBresenhamFloatHigh(int x0, int y0, int x1, int y1, Color color) {
    int deltaX = Math.abs(x1 - x0);
    int deltaY = Math.abs(y1 - y0);
    int x = x0;
    int directionX = x1 - x0;

    float error = 0;
    float deltaErr = (deltaX + 1) * 1.0f / (deltaY + 1);

    if (directionX > 0) {
      directionX = 1;
    } else if (directionX < 0) {
      directionX = -1;
    }

    for (int y = y0; y <= y1; y++) {
      drawPixel(x, y, color);

      error = error + deltaErr;

      if (error >= 1.0f) {
        x = x + directionX;
        error = error - 1.0f;
      }
    }
  }

  // BRESENHAM LINE ON INTEGERS (DEFAULT)

  public void drawLineBresenham(int x0, int y0, int x1, int y1, Color color) {
    if (Math.abs(y1 - y0) < Math.abs(x1 - x0)) {
      if (x0 > x1) {
        drawLineBresenhamLow(x1, y1, x0, y0, color);
      } else {
        drawLineBresenhamLow(x0, y0, x1, y1, color);
      }
    } else {
      if (y0 > y1) {
        drawLineBresenhamHigh(x1, y1, x0, y0, color);
      } else {
        drawLineBresenhamHigh(x0, y0, x1, y1, color);
      }
    }
  }

  private void drawLineBresenhamLow(int x0, int y0, int x1, int y1, Color color) {
    int dirY = y1 - y0;

    int absDx = Math.abs(x1 - x0);
    int absDy = Math.abs(dirY);

    int err = 0;
    int derr = absDy + 1;

    if (dirY > 0) {
      dirY = 1;
    } else if (dirY < 0) {
      dirY = -1;
    }

    int y = y0;

    for (int x = x0; x <= x1; x++) {
      drawPixel(x, y, color);

      err += derr;

      if (err >= (absDx + 1)) {
        y += dirY;
        err -= (absDx + 1);
      }
    }
  }

  private void drawLineBresenhamHigh(int x0, int y0, int x1, int y1, Color color) {
    int dirX = x1 - x0;

    int absDx = Math.abs(dirX);
    int absDy = Math.abs(y1 - y0);

    int err = 0;
    int derr = absDx + 1;

    if (dirX > 0) {
      dirX = 1;
    } else if (dirX < 0) {
      dirX = -1;
    }

    int x = x0;

    for (int y = y0; y <= y1; y++) {
      drawPixel(x, y, color);

      err += derr;

      if (err >= (absDy + 1)) {
        x += dirX;
        err -= (absDy + 1);
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

      tmp = x1;
      x1 = y1;
      y1 = tmp;
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

    float gradient = Math.abs(dx) < EPS ? 1.0f : dy * 1.0f / dx;

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
