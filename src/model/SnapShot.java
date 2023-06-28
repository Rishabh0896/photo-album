package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

/**
 * This object stores and executes all the relevant information needed to operate a snapshot.
 */
public final class SnapShot {

  private static final int MAX_SNAP_ID_LENGTH = 26;

  private static final DateTimeFormatter TIME_STAMP_FORMATTER
          = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  private final String description;

  private final Map<String, IShape> shapeMap;
  private String snapId;
  private String timeStamp;

  /**
   * Instantiates a new Snap shot.
   *
   * @param description the description of the snapshot.
   * @param shapeMap    the shapes present in the snapshot.
   */
  public SnapShot(String description, Map<String, IShape> shapeMap) {
    this.snapId = LocalDateTime.now().toString().substring(0, MAX_SNAP_ID_LENGTH);
    this.timeStamp = LocalDateTime.now().format(TIME_STAMP_FORMATTER);
    try {
      Thread.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    this.description = description;
    this.shapeMap = shapeMap;
  }

  /**
   * Print the snapshot with the necessary info.
   *
   * @return String representation of snapshot info.
   */
  public String print() {
    StringBuilder shapeInfo = new StringBuilder();
    shapeInfo.append("Snapshot ID: ").append(this.snapId).append("\n").append("Timestamp: ")
            .append(this.timeStamp).append("\n").append("Description: ")
            .append(this.description).append("\n").append("Shape Information:").append("\n");
    for (String name : shapeMap.keySet()) {
      shapeInfo.append("Name: ").append(name).append("\n")
              .append(shapeMap.get(name).toString()).append("\n\n");
    }
    return shapeInfo.toString().trim() + "\n";
  }

  /**
   * This method mocks the snapshot ID.
   */
  public void mockSnapId(LocalDateTime mockTime) {
    this.snapId = mockTime.toString().substring(0, MAX_SNAP_ID_LENGTH);
  }

  /**
   * This method mocks the timestamp.
   */
  public void mockTimeStamp(LocalDateTime mockTime) {
    this.timeStamp = mockTime.format(TIME_STAMP_FORMATTER);
  }

  /**
   * Getter method that returns snapshot ID.
   *
   * @return snapshot ID.
   */
  public String getSnapId() {
    return this.snapId;
  }

  /**
   * Getter method that returns timestamp.
   *
   * @return timestamp.
   */
  public String getTimeStamp() {
    return this.timeStamp;
  }


  public String getDescription() {
    return description;
  }


  public Map<String, IShape> getShapeDesc() {
    return shapeMap;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SnapShot snapShot = (SnapShot) o;

    return Objects.equals(snapId, snapShot.snapId);
  }

  @Override
  public String toString() {
    return this.snapId;
  }

}
