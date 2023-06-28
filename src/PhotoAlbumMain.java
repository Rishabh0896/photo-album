import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import controller.AlbumController;
import model.IPhotoAlbum;
import model.PhotoAlbum;
import util.AppProperties;
import view.GraphicalView;
import view.IView;
import view.WebView;

import static util.AppProperties.FLAG_INPUT;
import static util.AppProperties.FLAG_OUTPUT;
import static util.AppProperties.FLAG_VERSION_ABB;
import static util.AppProperties.FLAG_VERSION_FULL;
import static util.AppProperties.GRAPHICAL_VIEW;
import static util.AppProperties.WEB_VIEW;


/**
 * The type Photo album main.
 */
public class PhotoAlbumMain {
  private static final String WORKING_DIRECTORY = System.getProperty("user.dir");
  private static final IPhotoAlbum PHOTO_ALBUM = new PhotoAlbum();
  private static FileReader inputFile;
  private static String version;
  private static int xMax;
  private static int yMax;
  private static IView view;

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    try {
      AppProperties properties = new AppProperties();
      checkAndPopulateArgs(args);
      PHOTO_ALBUM.populateModel(inputFile);
      AlbumController controller = new AlbumController(PHOTO_ALBUM, view);
      controller.run();
    } catch (NumberFormatException | IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * Check and populate args.
   *
   * @param args the args
   * @throws IOException           the io exception
   * @throws NumberFormatException the number format exception
   */
  public static void checkAndPopulateArgs(String[] args) throws IOException,
          NumberFormatException {
    String input = args[Arrays.asList(args).indexOf(FLAG_INPUT) + 1];
    inputFile = new FileReader(WORKING_DIRECTORY + "\\resources\\" + input);
    if (Arrays.asList(args).contains(FLAG_VERSION_FULL)) {
      version = args[Arrays.asList(args).indexOf(FLAG_VERSION_FULL) + 1];
    } else if (Arrays.asList(args).contains(FLAG_VERSION_ABB)) {
      version = args[Arrays.asList(args).indexOf(FLAG_VERSION_ABB) + 1];
    }
    if (version.equals(WEB_VIEW)) {
      FileWriter outputFile = new FileWriter(args[Arrays.asList(args).indexOf(FLAG_OUTPUT) + 1]);
      view = new WebView(outputFile);
    }
    if (version.equals(GRAPHICAL_VIEW)) {
      xMax = Integer.parseInt(args[Arrays.asList(args).indexOf(GRAPHICAL_VIEW) + 1]);
      yMax = Integer.parseInt(args[Arrays.asList(args).indexOf(GRAPHICAL_VIEW) + 2]);
      view = new GraphicalView(xMax, yMax);
    }
  }
}
