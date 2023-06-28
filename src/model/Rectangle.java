package model;

import util.Color;
import util.Point2D;

/**
 * The Rectangle Shape Object.
 */
public class Rectangle extends AbstractShape {
  //TODO Ask Shubham
  public static final ShapeType SHAPE_TYPE = ShapeType.RECTANGLE;
  private static final double SMALLEST_LENGTH = 0.0;
  private double width;
  private double height;

  /**
   * Instantiates a new Rectangle.
   *
   * @param coordinates the {@link Point2D} coordinates
   * @param color       the color
   * @param width       the width
   * @param height      the height
   */
  public Rectangle(Point2D coordinates, Color color, double width,
                   double height) {
    this(coordinates.getCoordinateX(), coordinates.getCoordinateY(), color, width, height);
  }

  /**
   * Instantiates a new Rectangle.
   *
   * @param coordinateX the coordinate x
   * @param coordinateY the coordinate y
   * @param color       the color
   * @param width       the width
   * @param height      the height
   */
  public Rectangle(double coordinateX, double coordinateY, Color color, double width,
                   double height) {
    super(coordinateX, coordinateY, color);
    if (width < SMALLEST_LENGTH || height < SMALLEST_LENGTH) {
      throw new IllegalArgumentException("Width and Height cannot be less than " + SMALLEST_LENGTH);
    }
    this.width = width;
    this.height = height;
  }

  /**
   * Gets width.
   *
   * @return the width
   */
  public double getWidth() {
    return this.width;
  }

  /**
   * Gets height.
   *
   * @return the height
   */
  public double getHeight() {
    return this.height;
  }


  @Override
  public String toString() {
    return "Type: " + SHAPE_TYPE.toString().toLowerCase() + "\n" + "Min corner: ("
            + this.getPivot().getCoordinateX() + "," + this.getPivot().getCoordinateY()
            + "), Width: " + this.width + ", Height: " + this.height
            + ", Color: (" + this.getColor().getRedComp() + ","
            + this.getColor().getGreenComp() + "," + this.getColor().getBlueComp() + ")";
  }

  @Override
  public void changeScale(double newDimensionOne, double newDimensionTwo) {
    if (newDimensionOne < SMALLEST_LENGTH || newDimensionTwo < SMALLEST_LENGTH) {
      throw new IllegalArgumentException("Width and Height cannot be less than " + SMALLEST_LENGTH);
    }
    this.width = newDimensionOne;
    this.height = newDimensionTwo;
  }

  @Override
  public Object copy() {
    return new Rectangle(getPivot().getCoordinateX(), getPivot().getCoordinateY(), getColor(),
            this.width, this.height);
  }

  @Override
  public ShapeType shapeType() {
    return SHAPE_TYPE;
  }

  @Override
  public Double getDimensionOne() {
    return this.width;
  }

  @Override
  public Double getDimensionTwo() {
    return this.height;
  }

}
