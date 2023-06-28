package model;

import util.Color;
import util.Point2D;

/**
 * The Oval Shape Object.
 */
public class Oval extends AbstractShape {
  //TODO Ask Shubham
  public static final ShapeType SHAPE_TYPE = ShapeType.OVAL;
  private static final double SMALLEST_LENGTH = 0.0;
  private double xRadius;
  private double yRadius;

  /**
   * Instantiates a new Oval.
   *
   * @param coordinates the {@link Point2D} coordinates
   * @param color       the color
   * @param xRadius     the x radius
   * @param yRadius     the y radius
   */
  public Oval(Point2D coordinates, Color color, double xRadius,
              double yRadius) {
    this(coordinates.getCoordinateX(), coordinates.getCoordinateY(), color, xRadius, yRadius);
  }

  /**
   * Instantiates a new Oval.
   *
   * @param coordinateX the coordinate x
   * @param coordinateY the coordinate y
   * @param color       the color
   * @param xRadius     the x radius
   * @param yRadius     the y radius
   */
  public Oval(double coordinateX, double coordinateY, Color color, double xRadius,
              double yRadius) {
    super(coordinateX, coordinateY, color);
    if (xRadius < SMALLEST_LENGTH || yRadius < SMALLEST_LENGTH) {
      throw new IllegalArgumentException("xRadius or yRadius cannot be less than "
              + SMALLEST_LENGTH);
    }
    this.xRadius = xRadius;
    this.yRadius = yRadius;
  }

  /**
   * Gets x Radius.
   *
   * @return the x Radius.
   */
  public double getXRadius() {
    return xRadius;
  }

  /**
   * Gets y Radius.
   *
   * @return the y Radius.
   */
  public double getYRadius() {
    return yRadius;
  }

  @Override
  public String toString() {
    return "Type: " + SHAPE_TYPE.toString().toLowerCase() + "\n" + "Center: ("
            + this.getPivot().getCoordinateX() + ","
            + this.getPivot().getCoordinateY() + "), X radius: " + this.xRadius
            + ", Y radius: " + this.yRadius + ", Color: (" + this.getColor().getRedComp() + ","
            + this.getColor().getGreenComp() + "," + this.getColor().getBlueComp() + ")";
  }

  @Override
  public void changeScale(double newDimensionOne, double newDimensionTwo) {
    if (newDimensionOne < SMALLEST_LENGTH || newDimensionTwo < SMALLEST_LENGTH) {
      throw new IllegalArgumentException("xRadius or yRadius cannot be less than "
              + SMALLEST_LENGTH);
    }
    this.xRadius = newDimensionOne;
    this.yRadius = newDimensionTwo;
  }

  @Override
  public Object copy() throws IllegalStateException {
    return new Oval(getPivot().getCoordinateX(), getPivot().getCoordinateY(), getColor(),
            this.xRadius, this.yRadius);
  }

  @Override
  public ShapeType shapeType() {
    return SHAPE_TYPE;
  }

  @Override
  public Double getDimensionOne() {
    return this.xRadius;
  }

  @Override
  public Double getDimensionTwo() {
    return this.yRadius;
  }

}

