package View;

import Model.NotificationDetector;
import Model.Project;
import Model.ProjectListModel;
import Model.ProjectListModelManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ProjectTemplateViewController
{
  private ProjectListModel model;
  private ViewHandler viewHandler;
  private Region root;
  private ProjectListViewModel projectListViewModel;

  @FXML private Label type;
  @FXML private TextArea description;
  @FXML private Label deadlineNotification;
  @FXML private Label fundsNotification;
  @FXML private Label hoursNotification;
  @FXML private Label name;
  @FXML private Label expectedPrice;
  @FXML private Label estimatedHours;


  public ProjectTemplateViewController(){}
  public void init(ViewHandler viewHandler, ProjectListModel model, Region root){
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }


  public void setProjectInfo(Project project) {
    NotificationDetector notificationDetector = new NotificationDetector(project);

    if (notificationDetector.checkDeadline() == null) {
      Pane parent = (Pane) deadlineNotification.getParent();
      parent.getChildren().remove(deadlineNotification);
    } else {
      deadlineNotification.setText(notificationDetector.checkDeadline());
    }

    if (notificationDetector.checkFunds() == null) {
      Pane parent = (Pane) fundsNotification.getParent();
      parent.getChildren().remove(fundsNotification);
    } else {
      fundsNotification.setText(notificationDetector.checkFunds());
    }

    if (notificationDetector.checkManHours() == null) {
      Pane parent = (Pane) hoursNotification.getParent();
      parent.getChildren().remove(hoursNotification);
    } else {
      hoursNotification.setText(notificationDetector.checkManHours());
    }

    name.setText(project.getName());
    type.setText(type.getText() + " " + project.getClass().getSimpleName());
    description.setText(project.getDescription());
    estimatedHours.setText(estimatedHours.getText() + " " + String.valueOf(project.getExpectedTotalHours()) + " hours");
    expectedPrice.setText(expectedPrice.getText() + " " + String.valueOf(project.getExpectedExpenses()) + " DK");
  }



  public void reset(){

  }
  public Region getRoot(){
    return root;
  }

}
