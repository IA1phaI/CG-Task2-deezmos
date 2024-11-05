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

  private int shiftX;
  private int shiftY;

  private double scale = 50;

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
    this.shiftX = width / 2;
  }

  public void setSizes(int width, int height) {
    setWidth(width);
    setHeight(height);
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
    this.shiftY = height / 2;
  }

  public double getScale() {
    return scale;
  }

  public void setScale(double scale) {
    this.scale = scale;
  }

  public int getShiftX() {
    return shiftX;
  }

  public int getShiftY() {
    return shiftY;
  }

  public void translate(int dx, int dy) {
    shiftX += dx;
    shiftY += dy;
  }

  public void rescale(double dScale) {
    scale += dScale;
    if (scale < 1) {
      scale = 1;
    }
  }

  public void drawPlot(String expression, Color color, GraphicsContext graphicsContext) throws IOException {
    FxSimpleDrawer drawer = new FxSimpleDrawer(graphicsContext);

    drawer.drawLineDDA(getShiftX(), 0, getShiftX(), getHeight(), Color.BLACK);
    drawer.drawLineDDA(0, getShiftY(), getWidth(), getShiftY(), Color.BLACK);

    ExpressionTree expressionTree = new ExpressionTree(expression);

    int x1 = 0;
    int x2 = 1;

    expressionTree.setVariableValueIfAbsent("x", (x1 - getShiftX()) / scale);

    double y1 = -expressionTree.evaluate() * scale + getShiftY();
    double y2;

    while (x2 < getWidth()) {

      expressionTree.setVariableValueIfAbsent("x", (x2 - getShiftX()) / scale);

      y2 = -expressionTree.evaluate() * this.scale + getShiftY();

      if (isPointOnScreen(x1, y1) && isPointOnScreen(x2, y2)) {
        drawer.drawLineDDA(x1, (int) y1, x2, (int) y2, color);
      } else if (isBetweenEdgePoints(y1, y2) && Double.isFinite(y1) && Double.isFinite(y2)) {
        drawer.drawLineDDA(
            x1,
            this.roundYCoordinateToScreen(y1),
            x2,
            this.roundYCoordinateToScreen(y2),
            color);
      }
      // System.out.println(x1 + " " + y1);

      x1 = x2;
      y1 = y2;

      x2++;
    }
  }

  private boolean isPointOnScreen(double x, double y) {
    return x > 0
        && x < this.getWidth()
        && y > 0
        && y < this.getHeight();
  }

  private boolean isBetweenEdgePoints(double y1, double y2) {
    return (y1 < 0 && y2 > 0)
        || (y1 > 0 && y2 < 0)
        || (y1 < this.getHeight() && y2 > this.getHeight())
        || (y1 > this.getHeight() && y2 < this.getHeight());
  }

  private int roundYCoordinateToScreen(double y) {
    if (y < 0) {
      return 0;
    } else if (y > this.getHeight()) {
      return this.getHeight();
    } else {
      return (int) y;
    }
  }
}
