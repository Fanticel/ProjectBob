package Model;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;

public class ProjectDetailsViewController
{
  private ViewHandler viewHandler;
  private ProjectListModel projectListModel;

  private Region root;

  public ProjectDetailsViewController()
  {
    this.viewHandler = null;
    this.projectListModel = null;
    this.root = null;

  }

  public void reset()
  {


  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void initialize()
  {


  }

}


