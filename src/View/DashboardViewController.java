package View;

import Model.Project;
import Model.ProjectListModelManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import Model.ProjectListModel;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.List;

public class DashboardViewController
{
  private ProjectListModel model;
  private ViewHandler viewHandler;
  private Region root;

  @FXML private ScrollPane scrollPane;

  @FXML private HBox noProjects;


  public DashboardViewController()
  {
  }

  public void init(ViewHandler viewHandler, ProjectListModel model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    ProjectListModelManager projectListModelManager = new ProjectListModelManager();
    projectListModelManager.getFromFile();

    ArrayList<Project> ongoingProjects = new ArrayList<>();

    for (Project i : projectListModelManager.getAllProjects()
        .returnAsArrayList())
    {
      if (i.getStatus().equals("Ongoing"))
      {
        ongoingProjects.add(i);
      }
    }

    VBox container = new VBox();

    if(ongoingProjects.isEmpty()){
      noProjects.setVisible(true);
    }
    else {
    for (Project i : ongoingProjects)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("ProjectTemplateView.fxml"));

        Parent projectTemplate = loader.load();
        ProjectTemplateViewController controller = loader.getController();

        controller.setProjectInfo(i);
        container.getChildren().add(projectTemplate);

      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    scrollPane.setContent(container);
    noProjects.setVisible(true);
  }}

  public void reset()
  {

  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void clickProjectsButton()
  {
    viewHandler.openView("ProjectList");
  }

  @FXML private void clickHomeButton()
  {
    viewHandler.openView("");
  }

}
