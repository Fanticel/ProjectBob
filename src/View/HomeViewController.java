package View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PopupControl;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import Model.ProjectListModel;

import javax.swing.text.html.ImageView;
import java.io.IOException;

public class HomeViewController
{
  private ProjectListModel model;
  private ViewHandler viewHandler;
  private Region root;

  @FXML private AnchorPane notificationBox;

  public HomeViewController()
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

  @FXML private void clickProjectsButton()
  {
    viewHandler.openView("ProjectList");
  }

  @FXML private void clickDashboardButton()
  {
    viewHandler.openView("Dashboard");
  }

  @FXML private void clickNotificationButton(){
    handleNotificationClick();
  }

  private void handleNotificationClick()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("NotificationPopupView.fxml"));

      Parent notificationTemplate = loader.load();

      notificationBox.getChildren().add(notificationTemplate);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
