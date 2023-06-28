package model;

import org.junit.Before;
import org.junit.Test;

import util.Color;
import util.Point2D;

import static org.junit.Assert.assertEquals;

/**
 * The type Shape test.
 */
public class ShapeTest {

  /**
   * The Rectangle.
   */
  IShape rectangle;

  /**
   * The Oval.
   */
  IShape oval;

  /**
   * The Point a.
   */
  Point2D pointA;
  /**
   * The Point b.
   */
  Point2D pointB;
  /**
   * The Point large.
   */
  Point2D pointLarge;
  /**
   * The Point small.
   */
  Point2D pointSmall;

  /**
   * The Color a.
   */
  Color colorA;
  /**
   * The Color b.
   */
  Color colorB;

  /**
   * Setup.
   */
  @Before
  public void before() {
    pointA = new Point2D(100, 200);
    pointLarge = new Point2D(Double.MAX_VALUE, Double.MAX_VALUE);
    pointSmall = new Point2D(0.000001, 0.000001);
    pointB = new Point2D(999, 0);
    colorA = new Color(12, 12, 12);
    colorB = new Color(12, 12, 12);
    rectangle = new Rectangle(pointA, colorA, 100, 200);
    oval = new Oval(pointA, colorA, 100, 200);
  }

  /**
   * Move pivot.
   */
//Test positive test cases for rectangle and oval shapes
  @Test
  public void movePivot() {
    assertEquals(rectangle.getPivot(), pointA);
    assertEquals(oval.getPivot(), pointA);
    rectangle.movePivot(pointB);
    oval.movePivot(pointB);
    assertEquals(rectangle.getPivot(), pointB);
    assertEquals(oval.getPivot(), pointB);
  }

  /**
   * Change color.
   */
  @Test
  public void changeColor() {
    assertEquals(rectangle.getColor(), colorA);
    assertEquals(oval.getColor(), colorA);
    rectangle.changeColor(colorB);
    oval.changeColor(colorB);
    assertEquals(rectangle.getColor(), colorB);
    assertEquals(oval.getColor(), colorB);
  }


  /**
   * Change scale.
   */
  @Test
  public void changeScale() {
    Rectangle rectangleScale = new Rectangle(pointA, colorA, 100, 200);
    Oval ovalScale = new Oval(pointA, colorA, 100, 200);
    assertEquals(rectangleScale.getWidth(), pointA.getCoordinateX(), 0.001);
    assertEquals(rectangleScale.getHeight(), pointA.getCoordinateY(), 0.001);
    assertEquals(ovalScale.getXRadius(), pointA.getCoordinateX(), 0.001);
    assertEquals(ovalScale.getYRadius(), pointA.getCoordinateY(), 0.001);
    rectangleScale.changeScale(9999999, 999999);
    ovalScale.changeScale(0.000001, 0.000001);
    assertEquals(rectangleScale.getWidth(), 9999999, 0.001);
    assertEquals(rectangleScale.getHeight(), 999999, 0.001);
    assertEquals(ovalScale.getXRadius(), 0.000001, 0.001);
    assertEquals(ovalScale.getYRadius(), 0.000001, 0.001);
  }

  /**
   * Move pivot to large coordinates.
   */
//Test boundary conditions for rectangle and oval shapes
  @Test
  public void movePivotToLargeCoordinates() {
    assertEquals(rectangle.getPivot(), pointA);
    assertEquals(oval.getPivot(), pointA);
    rectangle.movePivot(pointLarge);
    oval.movePivot(pointLarge);
    assertEquals(rectangle.getPivot(), pointLarge);
    assertEquals(oval.getPivot(), pointLarge);
  }

  /**
   * Move pivot to small coordinates.
   */
  @Test
  public void movePivotToSmallCoordinates() {
    assertEquals(rectangle.getPivot(), pointA);
    assertEquals(oval.getPivot(), pointA);
    rectangle.movePivot(pointSmall);
    oval.movePivot(pointSmall);
    assertEquals(rectangle.getPivot(), pointSmall);
    assertEquals(oval.getPivot(), pointSmall);
  }

  /**
   * Change scale to large numbers.
   */
  @Test
  public void changeScaleToLargeNumbers() {
    Rectangle rectangleScale = new Rectangle(pointA, colorA, 100, 200);
    Oval ovalScale = new Oval(pointA, colorA, 100, 200);
    assertEquals(rectangleScale.getWidth(), pointA.getCoordinateX(), 0.001);
    assertEquals(rectangleScale.getHeight(), pointA.getCoordinateY(), 0.001);
    assertEquals(ovalScale.getXRadius(), pointA.getCoordinateX(), 0.001);
    assertEquals(ovalScale.getYRadius(), pointA.getCoordinateY(), 0.001);
    rectangleScale.changeScale(Double.MAX_VALUE, Double.MAX_VALUE);
    ovalScale.changeScale(Double.MAX_VALUE, Double.MAX_VALUE);
    assertEquals(rectangleScale.getWidth(), Double.MAX_VALUE, 0.001);
    assertEquals(rectangleScale.getHeight(), Double.MAX_VALUE, 0.001);
    assertEquals(ovalScale.getXRadius(), Double.MAX_VALUE, 0.001);
    assertEquals(ovalScale.getYRadius(), Double.MAX_VALUE, 0.001);
  }

  /**
   * Change scale to small numbers.
   */
  @Test
  public void changeScaleToSmallNumbers() {
    Rectangle rectangleScale = new Rectangle(pointA, colorA, 100, 200);
    Oval ovalScale = new Oval(pointA, colorA, 100, 200);
    assertEquals(rectangleScale.getWidth(), pointA.getCoordinateX(), 0.001);
    assertEquals(rectangleScale.getHeight(), pointA.getCoordinateY(), 0.001);
    assertEquals(ovalScale.getXRadius(), pointA.getCoordinateX(), 0.001);
    assertEquals(ovalScale.getYRadius(), pointA.getCoordinateY(), 0.001);
    rectangleScale.changeScale(0.00001, 0.00001);
    ovalScale.changeScale(0.00001, 0.00001);
    assertEquals(rectangleScale.getWidth(), 0.00001, 0.00001);
    assertEquals(rectangleScale.getHeight(), 0.00001, 0.00001);
    assertEquals(ovalScale.getXRadius(), 0.00001, 0.00001);
    assertEquals(ovalScale.getYRadius(), 0.00001, 0.00001);
  }


  /**
   * Move pivot for null shape.
   */
// Test negative test cases for rectangle and oval shapes
  @Test(expected = NullPointerException.class)
  public void movePivotForNullShape() {
    IShape shape = null;
    shape.movePivot(new Point2D(100, 200));
  }

  /**
   * Move pivot to negative coordinates.
   */
  @Test(expected = IllegalArgumentException.class)
  public void movePivotToNegativeCoordinates() {
    rectangle.movePivot(new Point2D(-1111111, -0.000001));
  }

  /**
   * Move pivot to origin.
   */
  @Test
  public void movePivotToOrigin() {
    rectangle.movePivot(new Point2D(0, 0));
  }

  /**
   * Change color for null shape.
   */
  @Test(expected = NullPointerException.class)
  public void changeColorForNullShape() {
    IShape shape = null;
    shape.changeColor(colorB);
  }

  /**
   * Change color to negative components.
   */
  @Test(expected = IllegalArgumentException.class)
  public void changeColorToNegativeComponents() {
    rectangle.changeColor(new Color(-21, -23, -11));
  }

  /**
   * Change scale to zero.
   */
  @Test
  public void changeScaleToZero() {
    rectangle.changeScale(0, 0);
  }

  /**
   * Change scale to negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void changeScaleToNegative() {
    rectangle.changeScale(-123123, -0.12321321);
  }

}