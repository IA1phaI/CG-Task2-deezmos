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

  public void drawPlot(String expression, Color color, GraphicsContext graphicsContext) throws IOException {
    FxSimpleDrawer drawer = new FxSimpleDrawer(graphicsContext);

    drawer.drawLineDDA(getShiftX(), 0, getShiftX(), getHeight(), Color.BLACK);
    drawer.drawLineDDA(0, getShiftY(), getWidth(), getShiftY(), Color.BLACK);

    ExpressionTree expressionTree = new ExpressionTree(expression);
    double x1 = 0;
    expressionTree.setVariableValueIfAbsent("x", (x1 - getShiftX()) / scale);
    double y1 = -expressionTree.evaluate() * scale + getShiftY();
    double y2, x2;
    for (int i = 1; i < getWidth(); i += 2) {
      x2 = i;

      expressionTree.setVariableValueIfAbsent("x", (x2 - getShiftX()) / scale);

      y2 = -expressionTree.evaluate() * this.scale + getShiftY();
      if (isPointOnScreen(x1, y1) && isPointOnScreen(x2, y2)) {
        drawer.drawLineDDA((int) x1, (int) y1, (int) x2, (int) y2, color);
      }
      x1 = x2;
      y1 = y2;
    }
  }

  private boolean isPointOnScreen(double x, double y) {
    return x > 0
        && x < this.getWidth()
        && y > 0
        && y < this.getHeight();
  }
}
