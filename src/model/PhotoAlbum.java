package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import util.Color;
import util.Point2D;

import static util.AppProperties.COMMAND_COLOR;
import static util.AppProperties.COMMAND_DRAW;
import static util.AppProperties.COMMAND_MOVE;
import static util.AppProperties.COMMAND_REMOVE;
import static util.AppProperties.COMMAND_RESIZE;
import static util.AppProperties.COMMAND_SNAPSHOT;
import static util.AppProperties.COMMENT_CHAR;
import static util.AppProperties.SHAPE_OVAL;
import static util.AppProperties.SHAPE_RECTANGLE;

/**
 * This class represents the photo album that stores photos (analogous to {@link SnapShot}) of
 * {@link IShape} shapes.
 */
public class PhotoAlbum implements IPhotoAlbum {

  private final Map<String, IShape> shapes;

  private final List<SnapShot> snapShots;

  private final Map<String, Consumer<String[]>> operations;
  /**
   * Instantiates a new Photo album.
   */
  public PhotoAlbum() {
    operations = new HashMap<>();
    operations.put(COMMAND_DRAW, this::drawShapeFromFile);
    operations.put(COMMAND_MOVE, (String[] a) -> getShape(a[1])
            .movePivot(Double.parseDouble(a[2]), Double.parseDouble(a[2])));
    operations.put(COMMAND_RESIZE, (String[] a) -> getShape(a[1])
            .changeScale(Double.parseDouble(a[2]), Double.parseDouble(a[2])));
    operations.put(COMMAND_COLOR, (String[] a) -> getShape(a[1])
            .changeColor(new Color(Double.parseDouble(a[2]),
                    Double.parseDouble(a[3]), Double.parseDouble(a[4]))));
    operations.put(COMMAND_SNAPSHOT, (String[] a) -> captureSnapShot(String.join(" ", a)));
    operations.put(COMMAND_REMOVE, (String[] a) -> deleteShape(a[1]));
    shapes = new LinkedHashMap<>();
    snapShots = new ArrayList<>();
  }

  private void drawShapeFromFile(String[] arr) {
    IShape shape;
    String name = arr[1];
    String shapeText = arr[2];
    double xCoordinate = Double.parseDouble(arr[3]);
    double yCoordinate = Double.parseDouble(arr[4]);
    double dimensionOne = Double.parseDouble(arr[5]);
    double dimensionTwo = Double.parseDouble(arr[6]);
    double redComp = Double.parseDouble(arr[7]);
    double greenComp = Double.parseDouble(arr[8]);
    double blueComp = Double.parseDouble(arr[9]);
    Point2D pivot = new Point2D(xCoordinate, yCoordinate);
    Color color = new Color(redComp, greenComp, blueComp);
    if (shapeText.equals(SHAPE_RECTANGLE)) {
      try {
        shape = new Rectangle(pivot, color, dimensionOne, dimensionTwo);
        this.drawShape(name, shape);
      } catch (NumberFormatException e) {
        System.out.println("Exception occurred while parsing dimensions");
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }
    }
    if (shapeText.equals(SHAPE_OVAL)) {
      try {
        shape = new Oval(pivot, color, dimensionOne, dimensionTwo);
        this.drawShape(name, shape);
      } catch (NumberFormatException e) {
        System.out.println("Exception occurred while parsing dimensions");
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Draw a the {@link IShape} on the canvas.
   *
   * @param name  the identifier/name of the shape.
   * @param shape the {@link IShape} shape to be drawn.
   * @throws IllegalArgumentException when null or empty name or shape is passed.
   */
  @Override
  public void drawShape(String name, IShape shape) throws IllegalArgumentException {
    if (name == null || name.trim().isEmpty() || shape == null) {
      throw new IllegalArgumentException("Invalid Shape arguments to draw");
    }
    shapes.put(name, shape);
  }

  /**
   * Gets shape from the canvas given the name identifier.
   *
   * @param name the name identifier of the {@link IShape} shape.
   * @return the {@link IShape} shape.
   */
  @Override
  public IShape getShape(String name) throws NoSuchElementException {
    if (shapes.isEmpty() || !shapes.containsKey(name)) {
      throw new NoSuchElementException("Shape not present!");
    }
    return shapes.get(name);
  }

  /**
   * Delete the {@link IShape} shape from the canvas identified by its name.
   *
   * @param name the name identifier of the {@link IShape} shape.
   */
  @Override
  public void deleteShape(String name) throws NoSuchElementException {
    if (shapes.isEmpty() || !shapes.containsKey(name)) {
      throw new NoSuchElementException("Shape not present!");
    }
    shapes.remove(name);
  }

  /**
   * Capture snapshot of the current shape system.
   *
   * @param description the description of the snapshot. Can be null or blank.
   */
  @Override
  public void captureSnapShot(String description) {
    snapShots.add(new SnapShot(description, copy(shapes)));
  }

  /**
   * Show the current shape system configuration.
   *
   * @return String representation of the current shape system configuration.
   */
  @Override
  public String currentShapeSystem() {
    StringBuilder currentShapeSystem = new StringBuilder();
    for (String name : shapes.keySet()) {
      currentShapeSystem.append("Name: ").append(name).append("\n")
              .append(shapes.get(name).toString()).append("\n\n");
    }
    return currentShapeSystem.toString().trim();
  }

  @Override
  public Map<String, IShape> currentShapeSystemAsMap() {
    return shapes;
  }

  /**
   * Gets the snapshot basis its snapshot ID.
   *
   * @param index of the snapshot to be fetched.
   * @return Snapshot object corresponding to the snapshot.
   */
  @Override
  public SnapShot getSnapShotById(int index) {
    return snapShots.get(index);
  }

  /**
   * List all {@link SnapShot} ID's as a string.
   *
   * @return the string of all snapshots.
   */
  @Override
  public String listSnapShots() {
    return "List of snapshots taken before reset: " + snapShots;
  }

  /**
   * Print {@link SnapShot} with their details as a String.
   *
   * @return the string description of all {@link SnapShot}.
   */
  @Override
  public String printSnapShotDetails() {
    return "Printing Snapshots" + "\n" + snapShots.stream()
            .map(SnapShot::print)
            .collect(Collectors.joining("\n\n")).trim();
  }

  /**
   * Reset the canvas to blank.
   */
  @Override
  public void reset() {
    shapes.clear();
  }

  @Override
  public List listSnapshots() {
    //snapShots.sort(Comparator.comparingInt(s -> Integer.parseInt(s.getSnapId())));
    return snapShots;
  }

  @Override
  public IPhotoAlbum populateModel(Readable in) {
    Scanner scanFile = new Scanner(in);
    while (scanFile.hasNext()) {
      String buffer = scanFile.nextLine().strip().replaceAll(" +", " ");
      if (!buffer.isEmpty() && !buffer.startsWith(COMMENT_CHAR)) {
        String[] arr = buffer.stripLeading().split(" ");
        operations.entrySet().stream().filter(e -> e.getKey().equals(arr[0]))
                .findFirst().ifPresent(e -> e.getValue().accept(arr));
      }
    }
    return this;
  }

  /**
   * Private method that deep copies the shape system so that snapshots have the
   * accurate description of shapes at that particular moment.
   *
   * @param source shape to be copied from
   * @return the copy of the shape
   */
  private Map<String, IShape> copy(Map<String, IShape> source) {
    if (source == null) {
      throw new IllegalArgumentException("Cannot copy empty shapes");
    }
    Map<String, IShape> shapeCopy = new LinkedHashMap<>();
    for (String key : source.keySet()) {
      shapeCopy.put(key, (IShape) source.get(key).copy());
    }
    return shapeCopy;
  }


}
