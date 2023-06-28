package util;

/**
 * Represents a point in a 2D space.
 */
public class Point2D {

  private static final double ORIGIN_X = 0.00;

  private static final double ORIGIN_Y = 0.00;

  /**
   * X coordinate of the point.
   */
  private double coordinateX;

  /**
   * Y coordinate of the point.
   */
  private double coordinateY;

  /**
   * Creates a Point2D Object. Added exception handling so association classes
   * can avoid handling there.
   *
   * @param coordinateX X coordinate of the point.
   * @param coordinateY Y coordinate of the point.
   * @throws IllegalArgumentException the illegal argument exception
   */
  public Point2D(double coordinateX, double coordinateY) throws IllegalArgumentException {
    if (coordinateX < ORIGIN_X || coordinateY < ORIGIN_Y) {
      throw new IllegalArgumentException("Coordinates cannot be negative");
    }
    this.coordinateX = coordinateX;
    this.coordinateY = coordinateY;
  }

  /**
   * Gets the X coordinate of the point.
   *
   * @return X coordinate of the point.
   */
  public double getCoordinateX() {
    return this.coordinateX;
  }

  /**
   * Gets the Y coordinate of the point.
   *
   * @return Y coordinate of the point.
   */
  public double getCoordinateY() {
    return this.coordinateY;
  }

  /**
   * Updates the coordinates of the Point2D Object.
   *
   * @param coordinateX updated X coordinate.
   * @param coordinateY updated Y coordinate.
   */
  public void updateCoordinates(double coordinateX, double coordinateY) {
    this.coordinateX = coordinateX;
    this.coordinateY = coordinateY;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Point2D point2D = (Point2D) o;

    if (Double.compare(point2D.coordinateX, coordinateX) != 0) {
      return false;
    }

    return Double.compare(point2D.coordinateY, coordinateY) == 0;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    temp = Double.doubleToLongBits(coordinateX);
    result = (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(coordinateY);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

}
