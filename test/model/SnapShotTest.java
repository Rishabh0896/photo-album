package model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import util.Color;
import util.Point2D;

import static org.junit.Assert.assertEquals;

/**
 * The type Snap shot test.
 */
public class SnapShotTest {

  private static final DateTimeFormatter TIME_STAMP_FORMATTER
          = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  /**
   * The Snap 1.
   */
  SnapShot snap_1;
  /**
   * The Mock date time.
   */
  LocalDateTime mockDateTime = LocalDateTime.now();
  /**
   * The Shape system.
   */

  Map<String, IShape> shapeMap = new HashMap<>();

  String shapeSystem = "Name: ovalTwo\n" +
          "Type: oval\n" +
          "Center: (0.01,0.02), X radius: 0.1, Y radius: 0.02, Color: (0.0,255.0,0.0)\n";

  Point2D pointA;
  Color colorA;

  /**
   * Setup.
   */
  @Before
  public void before() {
    pointA = new Point2D(0.01, 0.02);
    colorA = new Color(0, 255, 0);
    IShape ovalTwo = new Oval(pointA, colorA, 0.1, 0.02);
    shapeMap.put("ovalTwo", ovalTwo);
    snap_1 = new SnapShot("SnapShot_1", shapeMap);
    snap_1.mockTimeStamp(mockDateTime);
  }

  /**
   * Print.
   */
  @Test
  public void print() {
    assertEquals("Snapshot ID: " + mockDateTime.toString().substring(0, 26) + "\n" +
                    "Timestamp: " + mockDateTime.format(TIME_STAMP_FORMATTER) + "\n" +
                    "Description: SnapShot_1\n" +
                    "Shape Information:\n" +
                    "Name: ovalTwo\n" +
                    "Type: oval\n" +
                    "Center: (0.01,0.02), X radius: 0.1, Y radius: 0.02, Color: (0.0,255.0,0.0)\n",
            snap_1.print());
  }

  /**
   * Test to string.
   */
  @Test
  public void testToString() {
    assertEquals(mockDateTime.toString(), mockDateTime.toString());
  }

  /**
   * Print null snap shot.
   */
  @Test(expected = NullPointerException.class)
  public void printNullSnapShot() {
    SnapShot snapShot = null;
    snapShot.print();
  }

  /**
   * Test null snap shot to string.
   */
  @Test(expected = NullPointerException.class)
  public void testNullSnapShotToString() {
    SnapShot snapShot = null;
    snapShot.toString();
  }
}