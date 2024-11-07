package ru.vsu.cs.course2.deezmos.plotdrawer;

import java.io.IOException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ru.vsu.cs.course2.deezmos.expression.tree.Expression;

/**
 * FxPlotDrawer
 */
public class FxPlotDrawer {

  private int width;
  private int height;

  private int offsetX;
  private int offsetY;

  private int DEFAULT_SCALE = 50;
  private double scale;

  public FxPlotDrawer(final int width, final int height) {
    setWidth(width);
    setHeight(height);
    this.offsetX = getDefaultOffsetX();
    this.offsetY = getDefaultOffsetY();
    this.scale = DEFAULT_SCALE;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(final int width) {
    this.width = width;
  }

  public void resize(final int width, final int height) {
    setWidth(width);
    setHeight(height);
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(final int height) {
    this.height = height;
  }

  public double getScale() {
    return scale;
  }

  public void setScale(final double scale) {
    this.scale = scale;
  }

  public int getOffsetX() {
    return offsetX;
  }

  public int getOffsetY() {
    return offsetY;
  }

  public void translate(final int dx, final int dy) {
    offsetX += dx;
    offsetY += dy;
  }

  public int getDefaultOffsetX() {
    return this.width / 2;
  }

  public int getDefaultOffsetY() {
    return this.height / 2;
  }

  public void setDefaultOffset() {
    this.offsetX = getDefaultOffsetX();
    this.offsetY = getDefaultOffsetY();
  }

  public void rescale(final double dScale) {
    scale += dScale;
    if (scale < 1) {
      scale = 1;
    }
  }

  public void setDefaultScale() {
    this.scale = DEFAULT_SCALE;
  }

  public void draw(final String expression, final Color color, final GraphicsContext graphicsContext)
      throws IOException {
    draw(new Expression(expression), color, graphicsContext);
  }

  public void draw(final Expression expression, final Color color, final GraphicsContext graphicsContext)
      throws IOException {

    final FxSimpleDrawer drawer = new FxSimpleDrawer(graphicsContext);

    int x0 = 0;
    int x1 = 1;

    expression.setVariableIfAbsent("x", (x0 - getOffsetX()) / scale);

    double y0 = -expression.evaluate() * scale + getOffsetY();
    double y1;

    while (x1 < getWidth()) {

      expression.setVariableIfAbsent("x", (x1 - getOffsetX()) / scale);

      y1 = -expression.evaluate() * this.scale + getOffsetY();

      if (isYOnScreen(y0) && isYOnScreen(y1)) {
        drawer.drawLineBresenham(x0, (int) y0, x1, (int) y1, color);
      } else if (hasBorderBetween(y0, y1) && Double.isFinite(y0) && Double.isFinite(y1)) {
        drawer.drawLineBresenham(
            x0,
            this.roundYCoordinateToScreen(y0),
            x1,
            this.roundYCoordinateToScreen(y1),
            color);
      }

      x0 = x1;
      y0 = y1;

      x1++;
    }
  }

  public void drawAxes(GraphicsContext graphicsContext) {
    FxSimpleDrawer drawer = new FxSimpleDrawer(graphicsContext);
    drawer.drawLineDDA(getOffsetX(), 0, getOffsetX(), getHeight(), Color.BLACK);
    drawer.drawLineDDA(getOffsetX() + 1, 0, getOffsetX() + 1, getHeight(), Color.GRAY);
    drawer.drawLineDDA(getOffsetX() - 1, 0, getOffsetX() - 1, getHeight(), Color.GRAY);
    drawer.drawLineDDA(0, getOffsetY(), getWidth(), getOffsetY(), Color.BLACK);
    drawer.drawLineDDA(0, getOffsetY() + 1, getWidth(), getOffsetY() + 1, Color.GRAY);
    drawer.drawLineDDA(0, getOffsetY() - 1, getWidth(), getOffsetY() - 1, Color.GRAY);

    drawer.drawPixel(getOffsetX() + 1, getOffsetY() + 1, Color.BLACK);
    drawer.drawPixel(getOffsetX() + 1, getOffsetY(), Color.BLACK);
    drawer.drawPixel(getOffsetX(), getOffsetY() + 1, Color.BLACK);
    drawer.drawPixel(getOffsetX() - 1, getOffsetY() - 1, Color.BLACK);
    drawer.drawPixel(getOffsetX() - 1, getOffsetY(), Color.BLACK);
    drawer.drawPixel(getOffsetX(), getOffsetY() - 1, Color.BLACK);
    drawer.drawPixel(getOffsetX() + 1, getOffsetY() - 1, Color.BLACK);
    drawer.drawPixel(getOffsetX() - 1, getOffsetY() + 1, Color.BLACK);
  }

  private boolean isYOnScreen(final double y) {
    return y > 0
        && y < this.getHeight();
  }

  private boolean hasBorderBetween(final double y0, final double y1) {
    return (y0 < 0 && y1 > 0)
        || (y0 > 0 && y1 < 0)
        || (y0 < this.getHeight() && y1 > this.getHeight())
        || (y0 > this.getHeight() && y1 < this.getHeight());
  }

  private int roundYCoordinateToScreen(final double y) {
    if (y < 0) {
      return 0;
    } else if (y > this.getHeight()) {
      return this.getHeight();
    } else {
      return (int) y;
    }
  }
}
