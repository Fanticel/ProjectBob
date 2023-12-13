package View;

import Model.NotificationDetector;
import Model.Project;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PopupControl;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import Model.ProjectListModel;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.util.ArrayList;

//Made by Josip Brljevic
public class HomeViewController
{
  private ProjectListModel model;
  private ViewHandler viewHandler;
  private Region root;

  @FXML private VBox popup;
  @FXML private VBox warnings;

  //setting the popup of the notifications invisible
  private boolean isPopupVisible = false;

  //empty constructor
  public HomeViewController()
  {
  }

  //init method
  public void init(ViewHandler viewHandler, ProjectListModel model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    //this makes sure that the popup is invisible
    popup.setVisible(false);

    //creating a new array list of ongoing projects
    ArrayList<Project> ongoingProjects = new ArrayList<>();

    //for each ongoing project in the model, it is added to the array list
    for (Project i : model.getAllProjects().returnAsArrayList())
    {
      if (i.getStatus().equals("Ongoing"))
      {
        ongoingProjects.add(i);
      }
    }

    for (Project i : ongoingProjects)
    {
      //The loop creates the notification detector object for each ongoing project
      //if the method from the notification object isn't null, it adds a label to the VBox
      NotificationDetector notificationDetector = new NotificationDetector(i);

      if(notificationDetector.checkBudget()!=null)
      {
        Label budget = new Label(notificationDetector.checkBudget());
        budget.setTextFill(Color.RED);
        warnings.getChildren().add(budget);
      }

      if (notificationDetector.checkExpenses()!=null)
      {
        Label expenses = new Label(notificationDetector.checkExpenses());
        expenses.setTextFill(Color.RED);
        warnings.getChildren().add(expenses);
      }

      if(notificationDetector.checkManHours()!=null)
      {
        Label hours = new Label(notificationDetector.checkManHours());
        hours.setTextFill(Color.RED);
        warnings.getChildren().add(hours);
      }

      if (notificationDetector.checkDeadline()!=null)
      {
        Label deadline = new Label(notificationDetector.checkDeadline());
        deadline.setTextFill(Color.RED);
        warnings.getChildren().add(deadline);
      }
    }

  }

  //reset method does all the same functionality as the previous method, the only addition is the clear method in line 104, which clears all the messages displayed previously.
  public void reset()
  {
    popup.setVisible(false);
    warnings.getChildren().clear();
    ArrayList<Project> ongoingProjects = new ArrayList<>();

    for (Project i : model.getAllProjects().returnAsArrayList())
    {
      if (i.getStatus().equals("Ongoing"))
      {
        ongoingProjects.add(i);
      }
    }

    for (Project i : ongoingProjects)
    {
      NotificationDetector notificationDetector = new NotificationDetector(i);

      if(notificationDetector.checkBudget()!=null)
      {
        Label budget = new Label(notificationDetector.checkBudget());
        budget.setTextFill(Color.RED);
        warnings.getChildren().add(budget);
      }

      if (notificationDetector.checkExpenses()!=null)
      {
        Label expenses = new Label(notificationDetector.checkExpenses());
        expenses.setTextFill(Color.RED);
        warnings.getChildren().add(expenses);
      }

      if(notificationDetector.checkManHours()!=null)
      {
        Label hours = new Label(notificationDetector.checkManHours());
        hours.setTextFill(Color.RED);
        warnings.getChildren().add(hours);
      }

      if (notificationDetector.checkDeadline()!=null)
      {
        Label deadline = new Label(notificationDetector.checkDeadline());
        deadline.setTextFill(Color.RED);
        warnings.getChildren().add(deadline);
      }
    }


  }

  //gets root
  public Region getRoot()
  {
    return root;
  }

  //method for navigating to the project list view
  @FXML private void clickProjectsButton()
  {
    viewHandler.openView("ProjectList");
  }

  //method for navigating to the dashboard view
  @FXML private void clickDashboardButton()
  {
    viewHandler.openView("Dashboard");
  }

  //method for toggling the visibility of the popup
  @FXML public void togglePopup()
  {
    isPopupVisible = !isPopupVisible;
    popup.setVisible(isPopupVisible);
  }

  //method for clicking the popup
  @FXML public void clickNotificationButton()
  {
    togglePopup();
  }
}
