package util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The type Color test.
 */
public class ColorTest {

  /**
   * The Color A.
   */
  Color colorA;

  /**
   * The Color B.
   */
  Color colorB;

  /**
   * Setup.
   */
  @Before
  public void before() {
    colorA = new Color(100000, 9999, 123213);
    colorB = new Color(0.000001, 0.12321321, 0.86548968);
  }

  /**
   * Gets red comp.
   */
  @Test
  public void getRedComp() {
    assertEquals(100000, colorA.getRedComp(), 0.000000001);
    assertEquals(0.000001, colorB.getRedComp(), 0.000000001);
  }

  /**
   * Gets green comp.
   */
  @Test
  public void getGreenComp() {
    assertEquals(9999, colorA.getGreenComp(), 0.000000001);
    assertEquals(0.12321321, colorB.getGreenComp(), 0.000000001);
  }

  /**
   * Gets blue comp.
   */
  @Test
  public void getBlueComp() {
    assertEquals(123213, colorA.getBlueComp(), 0.000000001);
    assertEquals(0.86548968, colorB.getBlueComp(), 0.000000001);
  }

  /**
   * Update color.
   */
  @Test
  public void updateColor() {
    assertEquals(100000, colorA.getRedComp(), 0.000000001);
    assertEquals(9999, colorA.getGreenComp(), 0.000000001);
    assertEquals(123213, colorA.getBlueComp(), 0.000000001);
    colorA.updateColor(colorB.getRedComp(), colorB.getGreenComp(), colorB.getBlueComp());
    assertEquals(0.000001, colorB.getRedComp(), 0.000000001);
    assertEquals(0.12321321, colorB.getGreenComp(), 0.000000001);
    assertEquals(0.86548968, colorB.getBlueComp(), 0.000000001);
  }
}