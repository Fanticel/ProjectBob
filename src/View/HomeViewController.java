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

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.util.ArrayList;

public class HomeViewController
{
  private ProjectListModel model;
  private ViewHandler viewHandler;
  private Region root;

  @FXML private VBox popup;
  @FXML private VBox warnings;
  private boolean isPopupVisible = false;

  public HomeViewController()
  {
  }

  public void init(ViewHandler viewHandler, ProjectListModel model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    popup.setVisible(false);
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
        warnings.getChildren().add(budget);
      }

      if (notificationDetector.checkExpenses()!=null)
      {
        Label expenses = new Label(notificationDetector.checkExpenses());
        warnings.getChildren().add(expenses);
      }

      if(notificationDetector.checkManHours()!=null)
      {
        Label hours = new Label(notificationDetector.checkManHours());
        warnings.getChildren().add(hours);
      }

      if (notificationDetector.checkDeadline()!=null)
      {
        Label deadline = new Label(notificationDetector.checkDeadline());
        warnings.getChildren().add(deadline);
      }
    }

  }

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
        warnings.getChildren().add(budget);
      }

      if (notificationDetector.checkExpenses()!=null)
      {
        Label expenses = new Label(notificationDetector.checkExpenses());
        warnings.getChildren().add(expenses);
      }

      if(notificationDetector.checkManHours()!=null)
      {
        Label hours = new Label(notificationDetector.checkManHours());
        warnings.getChildren().add(hours);
      }

      if (notificationDetector.checkDeadline()!=null)
      {
        Label deadline = new Label(notificationDetector.checkDeadline());
        warnings.getChildren().add(deadline);
      }
    }


  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void clickProjectsButton()
  {
    viewHandler.openView("ProjectList");
  }

  @FXML private void clickDashboardButton()
  {
    viewHandler.openView("Dashboard");
  }

  @FXML public void togglePopup()
  {
    isPopupVisible = !isPopupVisible;
    popup.setVisible(isPopupVisible);
  }

  @FXML public void clickNotificationButton()
  {
    togglePopup();
  }
}
