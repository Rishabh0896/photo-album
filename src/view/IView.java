package view;

import java.util.List;

import model.SnapShot;

/**
 * The interface View.
 */
public interface IView {

  /**
   * Render.
   *
   * @param snapShot the snap shot
   */
  void render(List<SnapShot> snapShot);

}
