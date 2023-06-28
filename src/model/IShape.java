package model;

import util.Color;
import util.Point2D;

/**
 * This interface defines the contract for a Shape.
 */
public interface IShape {

  /**
   * Move the pivot of the shape basis it's coordinates as .
   *
   * @param a the updated coordinates.
   * @param b the updated coordinates.
   */
  void movePivot(double a, double b);

  /**
   * Move the pivot of the shape basis it's coordinates as {@link Point2D}.
   *
   * @param coordinates the updated coordinates.
   */
  void movePivot(Point2D coordinates);

  /**
   * Get the pivot point as a {@link Point2D} for the given shape.
   *
   * @return {@link Point2D} pivot.
   */
  Point2D getPivot();

  /**
   * Change {@link Color} of the shape.
   *
   * @param newColor the new color to be updated.
   */
  void changeColor(Color newColor);

  /**
   * Get {@link Color} for the shape.
   *
   * @return {@link Color} of the shape.
   */
  Color getColor();

  /**
   * Change scale of the shape.
   *
   * @param newDimensionOne the updated dimension one.
   * @param newDimensionTwo the updated dimension two.
   */
  void changeScale(double newDimensionOne, double newDimensionTwo);


  /**
   * Deep Copy a shape.
   *
   * @return the copied object.
   */
  Object copy();

  /**
   * Tells about the shape type of the shape.
   *
   * @return shape type of the shape.
   */
  ShapeType shapeType();

  /**
   * Gets dimensionOne for the IShape object.
   *
   * @return dimensionOne of the shape.
   */
  Double getDimensionOne();

  /**
   * Gets dimensionTwo for the IShape object.
   *
   * @return dimensionTwo of the shape.
   */
  Double getDimensionTwo();

}
