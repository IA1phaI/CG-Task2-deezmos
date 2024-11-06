package ru.vsu.cs.course2.deezmos.plotdrawer;

import java.io.IOException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ru.vsu.cs.course2.deezmos.expressiontree.ExpressionTree;

/**
 * FxPlotDrawer
 */
public class FxPlotDrawer {

  private int width;
  private int height;

  private int offsetX;
  private int offsetY;

  private double scale = 50;

  public FxPlotDrawer(final int width, final int height) {
    setWidth(width);
    setHeight(height);
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(final int width) {
    this.width = width;
    this.offsetX = width / 2;
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
    this.offsetY = height / 2;
  }

  public double getScale() {
    return scale;
  }

  public void scale(final double scale) {
    this.scale = scale;
  }

  public int offsetX() {
    return offsetX;
  }

  public int offsetY() {
    return offsetY;
  }

  public void translate(final int dx, final int dy) {
    offsetX += dx;
    offsetY += dy;
  }

  public void rescale(final double dScale) {
    scale += dScale;
    if (scale < 1) {
      scale = 1;
    }
  }

  public void draw(final String expression, final Color color, final GraphicsContext graphicsContext) throws IOException {
    final FxSimpleDrawer drawer = new FxSimpleDrawer(graphicsContext);

    drawer.drawLineDDA(offsetX(), 0, offsetX(), getHeight(), Color.BLACK);
    drawer.drawLineDDA(offsetX() + 1, 0, offsetX() + 1, getHeight(), Color.BLACK);
    drawer.drawLineDDA(0, offsetY(), getWidth(), offsetY(), Color.BLACK);
    drawer.drawLineDDA(0, offsetY() + 1, getWidth(), offsetY() + 1, Color.BLACK);

    final ExpressionTree expressionTree = new ExpressionTree(expression);

    int x0 = 0;
    int x1 = 1;

    expressionTree.setVariableIfAbsent("x", (x0 - offsetX()) / scale);

    double y0 = -expressionTree.evaluate() * scale + offsetY();
    double y1;

    while (x1 < getWidth()) {

      expressionTree.setVariableIfAbsent("x", (x1 - offsetX()) / scale);

      y1 = -expressionTree.evaluate() * this.scale + offsetY();

      if (isYOnScreen(y0) && isYOnScreen(y1)) {
        drawer.drawLineDDA(x0, (int) y0, x1, (int) y1, color);
      } else if (hasBorderBetween(y0, y1) && Double.isFinite(y0) && Double.isFinite(y1)) {
        drawer.drawLineDDA(
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
