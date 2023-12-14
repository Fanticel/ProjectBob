//Made by Anthony Richards
package View;

import Model.Project;
import Model.ProjectListModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class ProjectDetailsViewController {
  private Region root;
  private ProjectListModel model;
  private ViewHandler viewHandler;
  private Project project;
  private ViewState viewState;
  @FXML private Label errorLabel;

  @FXML private EmbeddedCommercialProjectViewController view1Controller;
  @FXML private EmbeddedIndustrialProjectViewController view2Controller;
  @FXML private EmbeddedResidentialProjectViewController view3Controller;
  @FXML private EmbeddedRoadProjectViewController view4Controller;

  @FXML private StackPane stackPane;
  @FXML private Label projectNameLabel;
  @FXML private Label projectDescriptionLabel;
  @FXML private Label expTotalHoursLabel;
  @FXML private Label expExpensesLabel;
  @FXML private Label budgetLabel;
  @FXML private Label timelineLabel;

  public ProjectDetailsViewController() {
  }
  //Initiates the details view and its embedded views
  public void init(ViewHandler viewHandler, ProjectListModel model, Region root,
      ViewState viewState) {
    this.model = model;
    this.viewHandler = viewHandler;
    this.root = root;
    this.viewState = viewState;
    view1Controller.init(viewHandler, model, root, viewState);
    view2Controller.init(viewHandler, model, root, viewState);
    view3Controller.init(viewHandler, model, root, viewState);
    view4Controller.init(viewHandler, model, root, viewState);
    reset();
  }

  //gets project data and displays them
  public void reset() {
    project = model.getProject((String) viewState.getData().get(0));
    projectNameLabel.setText(project.getName());
    projectDescriptionLabel.setText(project.getDescription());
    expExpensesLabel.setText(String.valueOf(project.getExpectedExpenses()));
    expTotalHoursLabel.setText(String.valueOf(project.getExpectedTotalHours()));
    budgetLabel.setText(String.valueOf(project.getTotalHours()));
    timelineLabel.setText(String.valueOf(project.getTimeline()));
    //depending on the project class displays corresponding view
    switch (project.getClass().toString()) {
      case ("class Model.CommercialProject") -> {
        view1Controller.detailsReset();
        changeView("Commercial");
      }
      case ("class Model.IndustrialProject") -> {
        view2Controller.detailsReset();
        changeView("Industrial");
      }
      case ("class Model.ResidentialProject") -> {
        view3Controller.detailsReset();
        changeView("Residential");
      }
      case ("class Model.RoadProject") -> {
        view4Controller.detailsReset();
        changeView("Road");
      }
    }
  }

  public Region getRoot() {
    return root;
  }

  //Changes view visibility depending on the given id
  private void changeView(String id) {
    switch (id) {
      case "Commercial" -> id = "view1";
      case "Industrial" -> id = "view2";
      case "Residential" -> id = "view3";
      case "Road" -> id = "view4";
      default -> id = null;
    }
    if (id == null) {
      return;
    }
    ObservableList<Node> children = stackPane.getChildren();
    for (Node i : children) {
      i.setVisible(false);
      if (id.equals(i.getId())) {
        i.setVisible(true);
      }
    }
  }
  @FXML private void backButtPress(){
    viewHandler.openView("ProjectList");
  }
}


