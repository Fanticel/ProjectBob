package View;

import Model.ProjectListModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

public class ProjectDetailsViewController
{
  @FXML ImageView backImage;

  @FXML Label projectName;

  @FXML Label projectDescription;

  @FXML Label expHours;

  @FXML Label expExpenses;

  @FXML Label budget;

  @FXML Label timeline;

  @FXML Button industrial;

  @FXML Button commercial;

  @FXML Button road;

  @FXML Button residential;

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


