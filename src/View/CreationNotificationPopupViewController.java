//Alan Karasin Stifter
package View;

import Model.ProjectListModel;
import javafx.scene.layout.Region;

public class CreationNotificationPopupViewController
{
  private Region root;
  private ProjectListModel model;
  private ViewHandler viewHandler;
  private ViewState viewState;

  public CreationNotificationPopupViewController()
  {
  }

  public void init(ViewHandler viewHandler, ProjectListModel model, Region root, ViewState viewState){
    this.model = model;
    this.viewHandler = viewHandler;
    this.root = root;
    this.viewState = viewState;
  }
  public void reset() {

  }
  public Region getRoot() {
    return root;
  }
}
