package controller;

import model.IPhotoAlbum;
import view.IView;

public class AlbumController {
  private final IPhotoAlbum model;
  private final IView view;

  public AlbumController(IPhotoAlbum model, IView view) {
    this.view = view;
    this.model = model;
  }


  public void run() throws IllegalArgumentException {
    view.render(model.listSnapshots());
  }
}
