package model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import util.Color;
import util.Point2D;

import static org.junit.Assert.assertEquals;

/**
 * The type Photo album test.
 */
public class PhotoAlbumTest {

  private static final DateTimeFormatter TIME_STAMP_FORMATTER
          = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  /**
   * The Album.
   */
  IPhotoAlbum album;
  /**
   * The Color red.
   */
  Color colorRed;
  /**
   * The Color green.
   */
  Color colorGreen;
  /**
   * The Point A.
   */
  Point2D pointA;
  /**
   * The Point B.
   */
  Point2D pointB;
  /**
   * The Point C.
   */
  Point2D pointC;
  /**
   * The Rectangle one.
   */
  IShape rectangleOne;
  /**
   * The Rectangle two.
   */
  IShape rectangleTwo;
  /**
   * The Oval one.
   */
  IShape ovalOne;
  /**
   * The Oval two.
   */
  IShape ovalTwo;
  /**
   * The Rectangle one name.
   */
  String rectangleOneName;
  /**
   * The Rectangle two name.
   */
  String rectangleTwoName;

  /**
   * The Oval one name.
   */
  String ovalOneName;
  /**
   * The Oval two name.
   */
  String ovalTwoName;

  /**
   * Setup .
   */
  @Before
  public void setup() {
    album = new PhotoAlbum();
    pointA = new Point2D(100, 200);
    pointB = new Point2D(0.01, 0.02);
    pointC = new Point2D(1000000000, 20000000);
    colorRed = new Color(255, 0, 0);
    colorGreen = new Color(0, 255, 0);
    rectangleOne = new Rectangle(pointA, colorRed, 15, 20);
    rectangleTwo = new Rectangle(pointB, colorGreen, 1.1, 0.2);
    ovalOne = new Oval(pointA, colorRed, 20, 10);
    ovalTwo = new Oval(pointB, colorGreen, 0.1, 0.02);
    rectangleOneName = "rectangleOne";
    rectangleTwoName = "rectangleTwo";
    ovalOneName = "ovalOne";
    ovalTwoName = "ovalTwo";
  }

  /**
   * Tests the drawShape method.
   */
//Positive test cases
  @Test
  public void drawShape() {
    album.drawShape(rectangleOneName, rectangleOne);
    album.drawShape(rectangleTwoName, rectangleTwo);
    album.drawShape(ovalOneName, ovalOne);
    album.drawShape(ovalTwoName, ovalTwo);
  }

  /**
   * Tests the getShape method.
   */
  @Test
  public void getShape() {
    drawShape();
    assertEquals(album.getShape(rectangleOneName), rectangleOne);
    assertEquals(album.getShape(rectangleTwoName), rectangleTwo);
    assertEquals(album.getShape(ovalOneName), ovalOne);
    assertEquals(album.getShape(ovalTwoName), ovalTwo);
  }

  /**
   * Tests the deleteShape method. Post deletion trying to retrieve the object leads to a
   * NoSuchElementException exception.
   */
  @Test(expected = NoSuchElementException.class)
  public void deleteShape() {
    drawShape();
    album.deleteShape(rectangleOneName);
    assertEquals(album.getShape(rectangleOneName), rectangleOne);
  }

  /**
   * Tests the captureSnapShot method.
   */
  @Test
  public void captureSnapShot() {
    drawShape();
    album.captureSnapShot("Snapshot_1");
    SnapShot mockSnap = new SnapShot("Snapshot_1", album.currentShapeSystemAsMap());
    mockSnap.mockSnapId(LocalDateTime.now());
    mockSnap.mockTimeStamp(LocalDateTime.now());
    assertEquals("Printing Snapshots\n" +
                    "Snapshot ID: " + mockSnap.getSnapId() + "\n" +
                    "Timestamp: " + mockSnap.getTimeStamp() + "\n" +
                    "Description: Snapshot_1\n" +
                    "Shape Information:\n" +
                    "Name: ovalTwo\n" +
                    "Type: oval\n" +
                    "Center: (0.01,0.02), X radius: 0.1, Y radius: 0.02, Color: (0.0,255.0,0.0)\n" +
                    "\n" +
                    "Name: rectangleOne\n" +
                    "Type: rectangle\n" +
                    "Min corner: (100.0,200.0), Width: 15.0, Height: 20.0, Color: (255.0,0.0,0.0)\n" +
                    "\n" +
                    "Name: ovalOne\n" +
                    "Type: oval\n" +
                    "Center: (100.0,200.0), X radius: 20.0, Y radius: 10.0, Color: (255.0,0.0,0.0)\n" +
                    "\n" +
                    "Name: rectangleTwo\n" +
                    "Type: rectangle\n" +
                    "Min corner: (0.01,0.02), Width: 1.1, Height: 0.2, Color: (0.0,255.0,0.0)"
            , album.printSnapShotDetails());
  }

  /**
   * Tests the listSnapShots method.
   */
  @Test
  public void listSnapShots() {
    LocalDateTime snap_1 = LocalDateTime.now();
    LocalDateTime snap_2 = LocalDateTime.now();
    LocalDateTime snap_3 = LocalDateTime.now();
    LocalDateTime snap_4 = LocalDateTime.now();
    album.drawShape(rectangleOneName, rectangleOne);
    album.captureSnapShot("Snapshot_1");
    album.getSnapShotById(0).mockSnapId(snap_1);
    album.drawShape(rectangleTwoName, rectangleTwo);
    album.captureSnapShot("Snapshot_2");
    album.getSnapShotById(1).mockSnapId(snap_2);
    album.drawShape(ovalOneName, ovalOne);
    album.captureSnapShot("Snapshot_3");
    album.getSnapShotById(2).mockSnapId(snap_3);
    album.drawShape(ovalTwoName, ovalTwo);
    album.captureSnapShot("Snapshot_4");
    album.getSnapShotById(3).mockSnapId(snap_4);
    System.out.println(album.listSnapShots());
    assertEquals("List of snapshots taken before reset: ["
            + snap_1.toString().substring(0, 26)
            + ", " + snap_2.toString().substring(0, 26)
            + ", " + snap_3.toString().substring(0, 26)
            + ", " + snap_4.toString().substring(0, 26)
            + "]", album.listSnapShots());
  }

  /**
   * Tests the printSnapShotDetails method by adding various shapes.
   */
  @Test
  public void printSnapShotDetails() {
    LocalDateTime snap_1 = LocalDateTime.now();
    drawShape();
    album.captureSnapShot("Snapshot_1");
    album.getSnapShotById(0).mockSnapId(snap_1);
    album.getSnapShotById(0).mockTimeStamp(snap_1);
    assertEquals("Printing Snapshots\n" +
                    "Snapshot ID: " + snap_1.toString().substring(0, 26) + "\n" +
                    "Timestamp: " + snap_1.format(TIME_STAMP_FORMATTER) + "\n" +
                    "Description: Snapshot_1\n" +
                    "Shape Information:\n" +
                    "Name: ovalTwo\n" +
                    "Type: oval\n" +
                    "Center: (0.01,0.02), X radius: 0.1, Y radius: 0.02, Color: (0.0,255.0,0.0)\n" +
                    "\n" +
                    "Name: rectangleOne\n" +
                    "Type: rectangle\n" +
                    "Min corner: (100.0,200.0), Width: 15.0, Height: 20.0, Color: (255.0,0.0,0.0)\n" +
                    "\n" +
                    "Name: ovalOne\n" +
                    "Type: oval\n" +
                    "Center: (100.0,200.0), X radius: 20.0, Y radius: 10.0, Color: (255.0,0.0,0.0)\n" +
                    "\n" +
                    "Name: rectangleTwo\n" +
                    "Type: rectangle\n" +
                    "Min corner: (0.01,0.02), Width: 1.1, Height: 0.2, Color: (0.0,255.0,0.0)",
            album.printSnapShotDetails());
  }

  /**
   * Tests the currentShapeSystem method by adding various shapes.
   */
  @Test
  public void currentShapeSystem() {
    drawShape();
    assertEquals("Name: ovalTwo\n" +
                    "Type: oval\n" +
                    "Center: (0.01,0.02), X radius: 0.1, Y radius: 0.02, Color: (0.0,255.0,0.0)\n" +
                    "\n" +
                    "Name: rectangleOne\n" +
                    "Type: rectangle\n" +
                    "Min corner: (100.0,200.0), Width: 15.0, Height: 20.0, Color: (255.0,0.0,0.0)\n" +
                    "\n" +
                    "Name: ovalOne\n" +
                    "Type: oval\n" +
                    "Center: (100.0,200.0), X radius: 20.0, Y radius: 10.0, Color: (255.0,0.0,0.0)\n" +
                    "\n" +
                    "Name: rectangleTwo\n" +
                    "Type: rectangle\n" +
                    "Min corner: (0.01,0.02), Width: 1.1, Height: 0.2, Color: (0.0,255.0,0.0)",
            album.currentShapeSystem());
  }

  /**
   * Reset.
   */
  @Test
  public void reset() {
    drawShape();
    album.reset();
    assertEquals("", album.currentShapeSystem());
  }

  /**
   * Draw very large shape.
   */
//Boundary Cases
  @Test
  public void drawVeryLargeShape() {
    Color maxColor = new Color(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
    Point2D maxPivot = new Point2D(Double.MAX_VALUE, Double.MAX_VALUE);
    Rectangle rectangle = new Rectangle(maxPivot, maxColor, Double.MAX_VALUE, Double.MAX_VALUE);
    assertEquals(maxPivot, rectangle.getPivot());
    assertEquals(maxColor, rectangle.getColor());
    assertEquals(Double.MAX_VALUE, rectangle.getHeight(), 0.01);
    assertEquals(Double.MAX_VALUE, rectangle.getWidth(), 0.01);
    //Oval
    Oval oval = new Oval(maxPivot, maxColor, Double.MAX_VALUE, Double.MAX_VALUE);
    assertEquals(maxPivot, oval.getPivot());
    assertEquals(maxColor, oval.getColor());
    assertEquals(Double.MAX_VALUE, oval.getXRadius(), 0.01);
    assertEquals(Double.MAX_VALUE, oval.getYRadius(), 0.01);
  }

  /**
   * Draw very small shape.
   */
  @Test
  public void drawVerySmallShape() {
    Color maxColor = new Color(0.01, 0.01, 0.01);
    Point2D maxPivot = new Point2D(0.01, 0.01);
    Rectangle rectangle = new Rectangle(maxPivot, maxColor, 0.01, 0.01);
    assertEquals(maxPivot, rectangle.getPivot());
    assertEquals(maxColor, rectangle.getColor());
    assertEquals(0.01, rectangle.getHeight(), 0.01);
    assertEquals(0.01, rectangle.getWidth(), 0.01);
    //Oval
    Oval oval = new Oval(maxPivot, maxColor, 0.01, 0.01);
    assertEquals(maxPivot, oval.getPivot());
    assertEquals(maxColor, oval.getColor());
    assertEquals(0.01, oval.getXRadius(), 0.01);
    assertEquals(0.01, oval.getYRadius(), 0.01);
  }

  /**
   * Print snap shot details with no shapes.
   */
  @Test
  public void printSnapShotDetailsWithNoShapes() {
    LocalDateTime snap_1 = LocalDateTime.now();
    album.captureSnapShot("Snap_NoShape");
    album.getSnapShotById(0).mockSnapId(snap_1);
    album.getSnapShotById(0).mockTimeStamp(snap_1);
    assertEquals("Printing Snapshots\n" +
            "Snapshot ID: " + snap_1.toString().substring(0, 26) + "\n" +
            "Timestamp: " + snap_1.format(TIME_STAMP_FORMATTER) + "\n" +
            "Description: Snap_NoShape\n" +
            "Shape Information:", album.printSnapShotDetails());
  }

  /**
   * Print snap shot details with many shapes.
   */
  @Test
  public void printSnapShotDetailsWithManyShapes() {
    LocalDateTime snap_1 = LocalDateTime.now();
    drawShape();
    album.captureSnapShot("Snapshot_1");
    album.getSnapShotById(0).mockSnapId(snap_1);
    album.getSnapShotById(0).mockTimeStamp(snap_1);
    assertEquals("Printing Snapshots\n" +
                    "Snapshot ID: " + snap_1.toString().substring(0, 26) + "\n" +
                    "Timestamp: " + snap_1.format(TIME_STAMP_FORMATTER) + "\n" +
                    "Description: Snapshot_1\n" +
                    "Shape Information:\n" +
                    "Name: ovalTwo\n" +
                    "Type: oval\n" +
                    "Center: (0.01,0.02), X radius: 0.1, Y radius: 0.02, Color: (0.0,255.0,0.0)\n" +
                    "\n" +
                    "Name: rectangleOne\n" +
                    "Type: rectangle\n" +
                    "Min corner: (100.0,200.0), Width: 15.0, Height: 20.0, Color: (255.0,0.0,0.0)\n" +
                    "\n" +
                    "Name: ovalOne\n" +
                    "Type: oval\n" +
                    "Center: (100.0,200.0), X radius: 20.0, Y radius: 10.0, Color: (255.0,0.0,0.0)\n" +
                    "\n" +
                    "Name: rectangleTwo\n" +
                    "Type: rectangle\n" +
                    "Min corner: (0.01,0.02), Width: 1.1, Height: 0.2, Color: (0.0,255.0,0.0)",
            album.printSnapShotDetails());
  }

  /**
   * Capture snap shot with large name.
   */
  @Test
  public void captureSnapShotWithLargeName() {
    LocalDateTime snapLargeName = LocalDateTime.now();
    album.captureSnapShot("Awrpebvgx~`aaa@#!$%^&*()_=_+<>%+=123456789*-+:,.;'{}[]|");
    album.getSnapShotById(0).mockSnapId(snapLargeName);
    album.getSnapShotById(0).mockTimeStamp(snapLargeName);
    assertEquals("Printing Snapshots\n" +
            "Snapshot ID: " + snapLargeName.toString().substring(0, 26) + "\n" +
            "Timestamp: " + snapLargeName.format(TIME_STAMP_FORMATTER) + "\n" +
            "Description: Awrpebvgx~`aaa@#!$%^&*()_=_+<>%+=123456789*-+:,.;'{}[]|\n" +
            "Shape Information:", album.printSnapShotDetails());
  }

  /**
   * Capture snap shot with small name.
   */
  @Test
  public void captureSnapShotWithSmallName() {
    LocalDateTime snapSmallName = LocalDateTime.now();
    album.captureSnapShot("A");
    album.getSnapShotById(0).mockSnapId(snapSmallName);
    album.getSnapShotById(0).mockTimeStamp(snapSmallName);
    assertEquals("Printing Snapshots\n" +
            "Snapshot ID: " + snapSmallName.toString().substring(0, 26) + "\n" +
            "Timestamp: " + snapSmallName.format(TIME_STAMP_FORMATTER) + "\n" +
            "Description: A\n" +
            "Shape Information:", album.printSnapShotDetails());
  }

  /**
   * List snap shots with one snap shot.
   */
  @Test
  public void listSnapShotsWithOneSnapShot() {
    LocalDateTime snap_1 = LocalDateTime.now();
    album.captureSnapShot("Snap_NoShape");
    album.getSnapShotById(0).mockSnapId(snap_1);
    album.getSnapShotById(0).mockTimeStamp(snap_1);
    assertEquals("List of snapshots taken before reset: ["
            + snap_1.toString().substring(0, 26) + "]", album.listSnapShots());
  }

  /**
   * List snap shots with multiple snap shots.
   */
  @Test
  public void listSnapShotsWithMultipleSnapShots() {
    LocalDateTime snap_1 = LocalDateTime.now();
    LocalDateTime snap_2 = LocalDateTime.now();
    LocalDateTime snap_3 = LocalDateTime.now();
    LocalDateTime snap_4 = LocalDateTime.now();
    LocalDateTime snap_5 = LocalDateTime.now();
    album.captureSnapShot("Snapshot_1");
    album.drawShape(rectangleOneName, rectangleOne);
    album.captureSnapShot("Snapshot_2");
    album.drawShape(rectangleTwoName, rectangleTwo);
    album.captureSnapShot("Snapshot_3");
    album.drawShape(ovalOneName, ovalOne);
    album.captureSnapShot("Snapshot_4");
    album.drawShape(ovalTwoName, ovalTwo);
    album.captureSnapShot("Snapshot_5");
    album.getSnapShotById(0).mockSnapId(snap_1);
    album.getSnapShotById(0).mockTimeStamp(snap_1);
    album.getSnapShotById(1).mockSnapId(snap_2);
    album.getSnapShotById(1).mockTimeStamp(snap_2);
    album.getSnapShotById(2).mockSnapId(snap_3);
    album.getSnapShotById(2).mockTimeStamp(snap_3);
    album.getSnapShotById(3).mockSnapId(snap_4);
    album.getSnapShotById(3).mockTimeStamp(snap_4);
    album.getSnapShotById(4).mockSnapId(snap_5);
    album.getSnapShotById(4).mockTimeStamp(snap_5);
    System.out.println(album.listSnapShots());
    assertEquals("List of snapshots taken before reset: ["
            + snap_1.toString().substring(0, 26)
            + ", " + snap_2.toString().substring(0, 26)
            + ", " + snap_3.toString().substring(0, 26)
            + ", " + snap_4.toString().substring(0, 26)
            + ", " + snap_5.toString().substring(0, 26)
            + "]", album.listSnapShots());
  }

  /**
   * Draw null shape.
   */
//Negative test cases
  @Test(expected = IllegalArgumentException.class)
  public void drawNullShape() {
    IShape shape = null;
    album.drawShape("Null Shape", shape);
  }

  /**
   * Draw shape with null name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void drawShapeWithNullName() {
    album.drawShape(null, rectangleOne);
  }

  /**
   * Gets shape with null name.
   */
  @Test(expected = NoSuchElementException.class)
  public void getShapeWithNullName() {
    album.drawShape("GG", rectangleOne);
    album.getShape(null);
  }

  /**
   * Gets shape from null map.
   */
  @Test(expected = NoSuchElementException.class)
  public void getShapeFromNullMap() {
    album.getShape("Shape");
  }

  /**
   * Gets non existing shape.
   */
  @Test(expected = NoSuchElementException.class)
  public void getNonExistingShape() {
    album.drawShape("GG", rectangleOne);
    album.getShape("GG2");
  }

  /**
   * Delete shape with null name.
   */
  @Test(expected = NoSuchElementException.class)
  public void deleteShapeWithNullName() {
    album.drawShape("GG", rectangleOne);
    album.deleteShape(null);
  }

  /**
   * Delete shape from null map.
   */
  @Test(expected = NoSuchElementException.class)
  public void deleteShapeFromNullMap() {
    album.deleteShape("Shape");
  }

  /**
   * Delete non existing shape.
   */
  @Test(expected = NoSuchElementException.class)
  public void deleteNonExistingShape() {
    album.drawShape("GG", rectangleOne);
    album.deleteShape("GG2");
  }

  /**
   * Capture snap shot with null name.
   */
  @Test
  public void captureSnapShotWithNullName() {
    album.drawShape("GG", rectangleOne);
    album.captureSnapShot(null);
  }

  /**
   * Reset empty canvas.
   */
  @Test
  public void resetEmptyCanvas() {
    album.reset();
    System.out.println(album.currentShapeSystem());
    assertEquals("", album.currentShapeSystem());
  }

  /**
   * Check if reset deletes snapshots.
   */
  @Test
  public void checkIfResetDeletesSnapshots() {
    LocalDateTime snap_1 = LocalDateTime.now();
    album.captureSnapShot("Snapshot");
    album.getSnapShotById(0).mockSnapId(snap_1);
    album.getSnapShotById(0).mockTimeStamp(snap_1);
    drawShape();
    album.reset();
    System.out.println(album.listSnapShots());
    assertEquals("List of snapshots taken before reset: ["
            + snap_1.toString().substring(0, 26)
            + "]", album.listSnapShots());
  }


}