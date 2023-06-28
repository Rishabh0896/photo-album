package model;

import org.junit.Before;
import org.junit.Test;

import util.Color;
import util.Point2D;

import static org.junit.Assert.assertEquals;

/**
 * The type Oval test.
 */
public class OvalTest {

  /**
   * The Point A.
   */
  Point2D pointA;

  /**
   * The Point B.
   */
  Point2D pointB;

  /**
   * The Oval.
   */
  Oval oval;

  /**
   * The Color.
   */
  Color color;

  /**
   * Setup.
   */
  @Before
  public void setup() {
    pointA = new Point2D(1001213123, 232323323);
    pointB = new Point2D(0.9846516545, 0.3626515161);
    color = new Color(321321321, 321654161, 531616132);
    oval = new Oval(pointA, color, Double.MAX_VALUE, 0.6541684684864);
  }

  /**
   * Gets x radius.
   */
  @Test
  public void getXRadius() {
    assertEquals(oval.getXRadius(), Double.MAX_VALUE, 0.00001);
  }

  /**
   * Gets y radius.
   */
  @Test
  public void getYRadius() {
    assertEquals(oval.getYRadius(), 0.6541684684864, 0.00001);
  }

  /**
   * Gets color.
   */
  @Test
  public void getColor() {
    assertEquals(oval.getColor(), color);
  }

  /**
   * Test to string.
   */
  @Test
  public void testToString() {
    assertEquals("Type: oval\n" +
                    "Center: (1.001213123E9,2.32323323E8), X radius: 1.7976931348623157E308,"
                    + " Y radius: 0.6541684684864, Color: (3.21321321E8,3.21654161E8,5.31616132E8)",
            oval.toString());
  }

  //All the negative test cases covered in Shape Test.

}