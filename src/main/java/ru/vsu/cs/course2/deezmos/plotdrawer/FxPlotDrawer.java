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

  private double scale;

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

  public void drawPlot(String expression, GraphicsContext graphicsContext) throws IOException {
    System.out.println(this.width + "x" + this.height);
    for (int i = 0; i < this.width; i++) {
      int x = i;

      ExpressionTree expressionTree = new ExpressionTree(expression);
      // expressionTree.setVariableValue("x", x);

      int y = (int) expressionTree.evaluate();
      System.out.println(x + " " + y);
      FxSimpleDrawer drawer = new FxSimpleDrawer(graphicsContext);
      drawer.drawPixel(x, y, Color.RED);
    }
  }
}
