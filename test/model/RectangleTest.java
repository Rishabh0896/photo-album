package model;

import org.junit.Before;
import org.junit.Test;

import util.Color;
import util.Point2D;

import static org.junit.Assert.assertEquals;

/**
 * The type Rectangle test.
 */
public class RectangleTest {

  /**
   * The Point A.
   */
  Point2D pointA;

  /**
   * The Point B.
   */
  Point2D pointB;

  /**
   * The Rectangle.
   */
  Rectangle rectangle;

  /**
   * The Color.
   */
  Color color;

  /**
   * Setup .
   */
  @Before
  public void setup() {
    pointA = new Point2D(1001213123, 232323323);
    pointB = new Point2D(0.9846516545, 0.3626515161);
    color = new Color(321321321, 321654161, 531616132);
    rectangle = new Rectangle(pointA, color, Double.MAX_VALUE, 0.6541684684864);
  }

  /**
   * Gets color.
   */
  @Test
  public void getColor() {
    assertEquals(rectangle.getColor(), color);
  }

  /**
   * Gets width.
   */
  @Test
  public void getWidth() {
    assertEquals(rectangle.getWidth(), Double.MAX_VALUE, 0.01);
  }

  /**
   * Gets height.
   */
  @Test
  public void getHeight() {
    assertEquals(rectangle.getHeight(), 0.6541684684864, 0.000000000000000000001);
  }

  /**
   * Test to string.
   */
  @Test
  public void testToString() {
    assertEquals("Type: rectangle\n" +
                    "Min corner: (1.001213123E9,2.32323323E8), Width: 1.7976931348623157E308," +
                    " Height: 0.6541684684864, Color: (3.21321321E8,3.21654161E8,5.31616132E8)",
            rectangle.toString());
  }

}