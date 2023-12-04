package View;

import Model.Project;
import Model.ProjectListModel;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EditProjectViewController
{
  private Region root;
  private ProjectListModel model;
  private ViewHandler viewHandler;

  private ViewState viewState;
  private Project project;

  @FXML
  private TextArea descriptionArea;

  @FXML
  private Label errorLabel;

  @FXML
  private TextField nameField;

  @FXML
  private ChoiceBox<String> statusChoiceBox;

  @FXML
  private AnchorPane rightAnchorPane;

  @FXML private EmbeddedCommercialProjectViewController view1Controller;
  @FXML private EmbeddedIndustrialProjectViewController view2Controller;
  @FXML private EmbeddedResidentialProjectViewController view3Controller;
  @FXML private EmbeddedRoadProjectViewController view4Controller;

  @FXML private StackPane stackPane;

  public EditProjectViewController()
  {
  }

  public void init(ViewHandler viewHandler, ProjectListModel model,
      Region root, ViewState viewState) {
    this.model = model;
    this.viewHandler = viewHandler;
    this.root = root;
    this.viewState = viewState;
    view1Controller.init(viewHandler, model, root, viewState);
    view2Controller.init(viewHandler, model, root, viewState);
    view3Controller.init(viewHandler, model, root, viewState);
    view4Controller.init(viewHandler, model, root, viewState);
    statusChoiceBox.getItems().add("Ongoing");
    statusChoiceBox.getItems().add("On hold");
    statusChoiceBox.getItems().add("Canceled");
    statusChoiceBox.getItems().add("Finished");
    reset();
  }
  public void reset() {
    project = model.getProject((String) viewState.getData().get(0));
    statusChoiceBox.setValue(project.getStatus());
    nameField.setText(project.getName());
    descriptionArea.setText(project.getDescription());
    errorLabel.setText("");
    switch (project.getClass().toString()){
      case ("class Model.CommercialProject") ->
      {
        view1Controller.editReset();
        changeView("Commercial");
      }
      case ("class Model.IndustrialProject") ->
      {
        view2Controller.editReset();
        changeView("Industrial");
      }
      case ("class Model.ResidentialProject") ->
      {
        view3Controller.editReset();
        changeView("Residential");
      }
      case ("class Model.RoadProject") ->
      {
        view4Controller.editReset();
        changeView("Road");
      }
    }

  }
  @FXML
  void cancel(ActionEvent event) {
    viewHandler.openView("ProjectList");
  }

  @FXML
  void confirm(ActionEvent event) {
    Map<String,Object> data = new HashMap<>();
    data.put("name", nameField.getText());
    data.put("description", descriptionArea.getText());
    data.put("status", statusChoiceBox.getValue());
    viewState.getData().add(data);
    switch (project.getClass().toString()){
      case ("class Model.CommercialProject") -> view1Controller.edit(project);
      case ("class Model.IndustrialProject") -> view2Controller.edit(project);
      case ("class Model.ResidentialProject") -> view3Controller.edit(project);
      case ("class Model.RoadProject") -> view4Controller.edit(project);
    }
    viewHandler.openView("ProjectList");
  }

  @FXML
  void delete(ActionEvent event) {

  }

  public Region getRoot() {
    return root;
  }
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
}
