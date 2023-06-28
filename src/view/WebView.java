package view;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import model.IShape;
import model.SnapShot;

import static util.AppProperties.DEFAULT_WINDOW_X_MAX;

/**
 * The type Web view.
 */
public class WebView implements IView {

  private static final String WORKING_DIRECTORY = System.getProperty("user.dir");
  private static final String DIV_TAG_OPEN = "<div";
  private static final String DIV_TAG_CLOSE = "</div>";
  private static final String HEADER_TAG_2_OPEN = "<h2>";
  private static final String HEADER_TAG_2_CLOSE = "</h2>";
  private final FileWriter out;
  private List<SnapShot> snapshots;

  /**
   * Instantiates a new Web view.
   *
   * @param out the out
   */
  public WebView(FileWriter out) {
    this.out = out;
  }

  public void render(List<SnapShot> snapShot) {
    this.snapshots = snapShot;
    try {
      String htmlString = Files.readString(Path.of(WORKING_DIRECTORY
              + "\\resources\\Template.html"));
      htmlString = htmlString.replace("$body", renderBody());
      out.write(htmlString);
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private StringBuilder renderBody() {
    StringBuilder builder = new StringBuilder();

    for (SnapShot snapShot : snapshots) {
      builder.append(DIV_TAG_OPEN).append(" class=\"snapshot\"").append(">")
              .append("\n").append(HEADER_TAG_2_OPEN)
              .append(snapShot.getDescription()).append(HEADER_TAG_2_CLOSE).append("\n");
      renderShapes(builder, snapShot);
      builder.append(DIV_TAG_CLOSE);
    }
    return builder;
  }

  private void renderShapes(StringBuilder builder, SnapShot snapShot) {
    builder.append("<svg width=\"" + DEFAULT_WINDOW_X_MAX + "\" height=\""
            + DEFAULT_WINDOW_X_MAX + "\">").append("\n");
    Map<String, IShape> shapes = snapShot.getShapeDesc();
    for (String shapeName : shapes.keySet()) {
      IShape shape = shapes.get(shapeName);
      shape.shapeType().appendToBuilder(builder, shape, shapeName);
    }
    builder.append("</svg>");
  }

}
