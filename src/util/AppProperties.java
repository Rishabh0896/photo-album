package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;

/**
 * The type App properties.
 */
public final class AppProperties {

  /**
   * The constant SHAPE_RECTANGLE.
   */
  public static final String SHAPE_RECTANGLE;
  /**
   * The constant SHAPE_OVAL.
   */
  public static final String SHAPE_OVAL;
  /**
   * The constant WEB_VIEW.
   */
  public static final String WEB_VIEW;
  /**
   * The constant GRAPHICAL_VIEW.
   */
  public static final String GRAPHICAL_VIEW;
  /**
   * The constant COMMAND_DRAW.
   */
  public static final String COMMAND_DRAW;
  /**
   * The constant COMMAND_MOVE.
   */
  public static final String COMMAND_MOVE;
  /**
   * The constant COMMAND_RESIZE.
   */
  public static final String COMMAND_RESIZE;
  /**
   * The constant COMMAND_COLOR.
   */
  public static final String COMMAND_COLOR;
  /**
   * The constant COMMAND_SNAPSHOT.
   */
  public static final String COMMAND_SNAPSHOT;
  /**
   * The constant COMMAND_REMOVE.
   */
  public static final String COMMAND_REMOVE;
  /**
   * The constant COMMENT_CHAR.
   */
  public static final String COMMENT_CHAR;
  /**
   * The constant DEFAULT_WINDOW_X_MAX.
   */
  public static final int DEFAULT_WINDOW_X_MAX = 1000;
  /**
   * The constant DEFAULT_WINDOW_Y_MAX.
   */
  public static final int DEFAULT_WINDOW_Y_MAX = 1000;

  /**
   * The constant FLAG_INPUT.
   */
  public static final String FLAG_INPUT = "-in";
  /**
   * The constant FLAG_OUTPUT.
   */
  public static final String FLAG_OUTPUT = "-out";
  /**
   * The constant FLAG_VERSION_FULL.
   */
  public static final String FLAG_VERSION_FULL = "-view";
  /**
   * The constant FLAG_VERSION_ABB.
   */
  public static final String FLAG_VERSION_ABB = "-v";
  private static Properties appProperties;
  private static Path pathProp;

  static {
    appProperties = new Properties();
    pathProp = Path.of(System.getProperty("user.dir"));
    try (InputStream input = new FileInputStream(pathProp + "\\resources\\config.properties")) {
      appProperties = new Properties();
      appProperties.load(input);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    WEB_VIEW = appProperties.containsKey("WEB_VIEW")
            ? appProperties.getProperty("WEB_VIEW") : "web";
    GRAPHICAL_VIEW = appProperties.containsKey("GRAPHICAL_VIEW")
            ? appProperties.getProperty("GRAPHICAL_VIEW") : "graphical";
    SHAPE_RECTANGLE = appProperties.containsKey("SHAPE_RECTANGLE")
            ? appProperties.getProperty("SHAPE_RECTANGLE") : "rectangle";
    SHAPE_OVAL = appProperties.containsKey("SHAPE_OVAL")
            ? appProperties.getProperty("SHAPE_OVAL") : "oval";
    COMMAND_DRAW = appProperties.containsKey("COMMAND_DRAW")
            ? appProperties.getProperty("COMMAND_DRAW") : "draw";
    COMMAND_MOVE = appProperties.containsKey("COMMAND_MOVE")
            ? appProperties.getProperty("COMMAND_MOVE") : "move";
    COMMAND_RESIZE = appProperties.containsKey("COMMAND_RESIZE")
            ? appProperties.getProperty("COMMAND_RESIZE") : "resize";
    COMMAND_COLOR = appProperties.containsKey("COMMAND_COLOR")
            ? appProperties.getProperty("COMMAND_COLOR") : "color";
    COMMAND_SNAPSHOT = appProperties.containsKey("COMMAND_SNAPSHOT")
            ? appProperties.getProperty("COMMAND_SNAPSHOT") : "snapshot";
    COMMAND_REMOVE = appProperties.containsKey("COMMAND_REMOVE")
            ? appProperties.getProperty("COMMAND_REMOVE") : "remove";
    COMMENT_CHAR = appProperties.containsKey("COMMENT_CHAR")
            ? appProperties.getProperty("COMMENT_CHAR") : "#";
  }

  /**
   * Instantiates a new App properties.
   */
  public AppProperties() {
  }

}
