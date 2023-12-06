package View;

import Model.NotificationDetector;
import Model.Project;
import Model.ProjectListModel;
import Model.ProjectListModelManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
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
  @FXML private Label expensesNotification;
  @FXML private Label name;
  @FXML private Label expectedPrice;
  @FXML private Label estimatedHours;
  @FXML private Label manHours;
  @FXML private Label budget;
  @FXML private Label expenses;
  @FXML private ProgressBar progressBar;


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

    if (notificationDetector.checkBudget() == null) {
      Pane parent = (Pane) fundsNotification.getParent();
      parent.getChildren().remove(fundsNotification);
    } else {
      fundsNotification.setText(notificationDetector.checkBudget());
    }

    if (notificationDetector.checkManHours() == null) {
      Pane parent = (Pane) hoursNotification.getParent();
      parent.getChildren().remove(hoursNotification);
    } else {
      hoursNotification.setText(notificationDetector.checkManHours());
    }

    if (notificationDetector.checkExpenses() == null) {
      Pane parent = (Pane) expensesNotification.getParent();
      parent.getChildren().remove(expensesNotification);
    } else {
      expensesNotification.setText(notificationDetector.checkExpenses());
    }

    String projectType = (
        switch (project.getClass().toString()){
          case ("class Model.CommercialProject") -> "Commercial";
          case ("class Model.IndustrialProject") -> "Industrial";
          case ("class Model.RoadProject") -> "Road";
          case ("class Model.ResidentialProject") -> "Residential";
          default -> "Error";
        }
    );

   progressBar.setProgress(project.getProgress());
    name.setText(project.getName());
    budget.setText(budget.getText() + " " + String.valueOf(project.getBudget()) + " DKK");
    manHours.setText(manHours.getText() + " " + String.valueOf(project.getTotalHours()) + " hours");
    expenses.setText(expenses.getText()+ " " + String.valueOf(project.getExpenses()) + " DKK");
    type.setText(type.getText() + " " + projectType);
    description.setText(project.getDescription());
    estimatedHours.setText(estimatedHours.getText() + " " + String.valueOf(project.getExpectedTotalHours()) + " hours");
    expectedPrice.setText(expectedPrice.getText() + " " + String.valueOf(project.getExpectedExpenses()) + " DKK");
  }



  public void reset(){

  }
  public Region getRoot(){
    return root;
  }

}
