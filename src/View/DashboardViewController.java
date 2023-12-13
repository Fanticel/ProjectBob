//Made by Josip Brljevic

package View;

import Model.Project;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import Model.ProjectListModel;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class DashboardViewController
{
  private ProjectListModel model;
  private ViewHandler viewHandler;
  private Region root;

  @FXML private ScrollPane scrollPane;

  @FXML private HBox noProjects;

//empty constructor
  public DashboardViewController()
  {

  }
//init method
  public void init(ViewHandler viewHandler, ProjectListModel model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    //Creating a new array list of projects
    ArrayList<Project> ongoingProjects = new ArrayList<>();

    //going through each project, checking if its status is "ongoing", and if it is, it is added to the array list
    for (Project i : model.getAllProjects().returnAsArrayList())
    {
      if (i.getStatus().equals("Ongoing"))
      {
        ongoingProjects.add(i);
      }
    }

    //Creating a new Vbox
    VBox container = new VBox();

    //If there are no ongoing projects, a message is displayed from the fxml file
    if(ongoingProjects.isEmpty()){
      noProjects.setVisible(true);
    }
    else {
      //for each ongoing project, it makes a new view from the ProjectTemplateView file, and it is added to the previously created Vbox
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
    //The Vbox container is added to the scroll pane
    scrollPane.setContent(container);
    noProjects.setVisible(true);
  }}

  //reset function activates when the user enters the view for the second time, it calls the same methods from the init method
  public void reset()
  {
    ArrayList<Project> ongoingProjects = new ArrayList<>();

    for (Project i : model.getAllProjects().returnAsArrayList())
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
      noProjects.setVisible(true);}
  }

  //gets the root of the view
  public Region getRoot()
  {
    return root;
  }

  // method that leads us to the project list view when you click on a Projects button
  @FXML private void clickProjectsButton()
  {
    viewHandler.openView("ProjectList");
  }

  // method that leads us to the home view when you click on a Home button
  @FXML private void clickHomeButton()
  {
    viewHandler.openView("");
  }

}
