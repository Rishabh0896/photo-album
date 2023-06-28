package util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The type Point 2 d test.
 */
public class Point2DTest {

  /**
   * The Point a.
   */
  Point2D pointA;
  /**
   * The Point b.
   */
  Point2D pointB;

  /**
   * Sets .
   */
  @Before
  public void setup() {
    pointA = new Point2D(10012321, 100232);
    pointB = new Point2D(0.0000001, 0.0000001);
  }

  /**
   * Gets coordinate x.
   */
  @Test
  public void getCoordinateX() {
    assertEquals(10012321, pointA.getCoordinateX(), 0.001);
    assertEquals(0.0000001, pointB.getCoordinateX(), 0.0000001);
  }

  /**
   * Gets coordinate y.
   */
  @Test
  public void getCoordinateY() {
    assertEquals(100232, pointA.getCoordinateY(), 0.001);
    assertEquals(0.0000001, pointB.getCoordinateY(), 0.0000001);
  }

  /**
   * Update coordinates.
   */
  @Test
  public void updateCoordinates() {
    assertEquals(10012321, pointA.getCoordinateX(), 0.001);
    assertEquals(100232, pointA.getCoordinateY(), 0.001);
    pointA.updateCoordinates(123213, 324234324);
    assertEquals(123213, pointA.getCoordinateX(), 0.001);
    assertEquals(324234324, pointA.getCoordinateY(), 0.001);
  }
}