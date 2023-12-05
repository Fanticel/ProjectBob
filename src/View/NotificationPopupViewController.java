package View;

import Model.ProjectListModel;
import javafx.scene.layout.Region;

public class NotificationPopupViewController

{
  private ProjectListModel model;
  private ViewHandler viewHandler;
  private Region root;

  public NotificationPopupViewController()
  {
  }

  public void init(ViewHandler viewHandler, ProjectListModel model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }

  public void reset()
  {

  }

  public Region getRoot()
  {
    return root;
  }



}
