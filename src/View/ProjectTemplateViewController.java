package View;

import Model.NotificationDetector;
import Model.Project;
import Model.ProjectListModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class ProjectTemplateViewController
{

  private Region root;

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

//empty constructor
  public ProjectTemplateViewController(){}
  public void init(Region root){
    this.root = root;
  }


  public void setProjectInfo(Project project) {
    //creating a notification detector class for a project
    NotificationDetector notificationDetector = new NotificationDetector(project);

    //if the notification method returns null, it removes the label placeholder, otherwise it returns the notification message
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

    //getting the project type
    String projectType = (
        switch (project.getClass().toString()){
          case ("class Model.CommercialProject") -> "Commercial";
          case ("class Model.IndustrialProject") -> "Industrial";
          case ("class Model.RoadProject") -> "Road";
          case ("class Model.ResidentialProject") -> "Residential";
          default -> "Error";
        }
    );

    //these are the fxml components, setText method is used for adding the messages from the previous code.
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
