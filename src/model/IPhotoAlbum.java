package model;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Establishes a contract that every {@link PhotoAlbum} must follow.
 */
public interface IPhotoAlbum {

  /**
   * This method is used to draw shapes in the image.
   *
   * @param name  name of the shape to be drawn.
   * @param shape Shape to be drawn.
   */
  void drawShape(String name, IShape shape);

  /**
   * Gets the shape basis its name/id string from the canvas.
   *
   * @param name name/id of the shape to be fetched
   * @return {@link IShape} from the canvas
   * @throws NoSuchElementException when the canvas is black or the shape doesn't exist.
   */
  IShape getShape(String name) throws NoSuchElementException;

  /**
   * This method is used to delete shapes from an image.
   *
   * @param name name of the shape to be deleted.
   */
  void deleteShape(String name);

  /**
   * This method captures a {@link SnapShot} of the current shape system.
   *
   * @param description description to describe the {@link SnapShot}.
   */
  void captureSnapShot(String description);

  /**
   * Show the current shape system configuration.
   *
   * @return String representation of the current shape system configuration.
   */
  String currentShapeSystem();

  /**
   * Show the current shape system configuration.
   *
   * @return Map of shapes of the current shape system configuration.
   */
  Map<String, IShape> currentShapeSystemAsMap();

  /**
   * Gets the snapshot basis its snapshot ID.
   *
   * @param index of the snapshot to be fetched.
   * @return String representation of the snapshot.
   */
  SnapShot getSnapShotById(int index);

  /**
   * Lists down all the {@link SnapShot} present taken in the current {@link PhotoAlbum}.
   *
   * @return String representation of the list of {@link SnapShot}.
   */
  String listSnapShots();

  /**
   * Prints all the {@link SnapShot} with the description that are taken in the
   * current {@link PhotoAlbum}.
   *
   * @return String description of all the snapshots.
   */
  String printSnapShotDetails();

  /**
   * Resets the {@link PhotoAlbum} canvas to a blank canvas. It does not clear the {@link SnapShot}.
   */
  void reset();

  /**
   * Return a list of snapshots to the controller.
   *
   * @return list of snapshots.
   */
  List<SnapShot> listSnapshots();

  /**
   * Populated model
   *
   * @return
   */
  IPhotoAlbum populateModel(Readable in);

}
