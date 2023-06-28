package model;

import util.Color;
import util.Point2D;

/**
 * The Abstract Shape class that sums up to the common operation of every shape.
 */
public abstract class AbstractShape implements IShape {

  private Point2D pivot;

  private Color color;

  /**
   * Instantiates a new Abstract shape given the coordinates.
   *
   * @param coordinateX the coordinate x
   * @param coordinateY the coordinate y
   * @param color       the color
   * @throws IllegalArgumentException the illegal argument exception
   */
  AbstractShape(double coordinateX, double coordinateY, Color color)
          throws IllegalArgumentException {
    this(new Point2D(coordinateX, coordinateY), color);
  }

  /**
   * Instantiates a new Abstract shape given a Point2D Object.
   *
   * @param coordinates the coordinates
   * @param color       the color
   * @throws IllegalArgumentException the illegal argument exception
   */
  AbstractShape(Point2D coordinates, Color color)
          throws IllegalArgumentException {
    if (color == null) {
      throw new IllegalArgumentException("Color cannot be null.");
    }
    this.pivot = coordinates;
    this.color = color;
  }

  /**
   * Gets pivot.
   *
   * @return the pivot
   */
  public Point2D getPivot() {
    return this.pivot;
  }

  /**
   * Gets color.
   *
   * @return the color
   */
  public Color getColor() {
    return this.color;
  }

  @Override
  public void movePivot(Point2D coordinates) {
    this.pivot = coordinates;
  }

  @Override
  public void movePivot(double coordinateX, double coordinateY) {
    movePivot(new Point2D(coordinateX, coordinateY));
  }

  @Override
  public void changeColor(Color newColor) {
    this.color = newColor;
  }

}
