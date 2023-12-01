package View;

import Model.ProjectListModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class CreateProjectViewController {
  private Region root;
  private ProjectListModel model;
  private ViewHandler viewHandler;

  @FXML private Label errorLabel;

  @FXML private TextArea descriptionArea;

  @FXML private TextField nameField;

  @FXML private ChoiceBox<String> typeChoiceBox;
  @FXML private EmbeddedCommercialProjectViewController view1Controller;
  @FXML private EmbeddedIndustrialProjectViewController view2Controller;
  @FXML private EmbeddedResidentialProjectViewController view3Controller;
  @FXML private EmbeddedRoadProjectViewController view4Controller;
  @FXML private StackPane stackPane;

  public CreateProjectViewController() {
  }

  public void init(ViewHandler viewHandler, ProjectListModel model,
      Region root) {
    this.model = model;
    this.viewHandler = viewHandler;
    this.root = root;
    view1Controller.init(viewHandler, model, root);
    view2Controller.init(viewHandler, model, root);
    view3Controller.init(viewHandler, model, root);
    view4Controller.init(viewHandler, model, root);
    typeChoiceBox.getItems().add("Commercial");
    typeChoiceBox.getItems().add("Industrial");
    typeChoiceBox.getItems().add("Residential");
    typeChoiceBox.getItems().add("Road");
    typeChoiceBox.getSelectionModel().selectedItemProperty().addListener(
        (ObservableValue<? extends String> observable, String oldValue, String newValue) -> changeView(
            newValue));
    reset();
  }

  public void reset() {
    view1Controller.reset();
    view2Controller.reset();
    view3Controller.reset();
    view4Controller.reset();
  }

  public Region getRoot() {
    return root;
  }

  @FXML void create(ActionEvent event) {

  }

  @FXML void back(ActionEvent event) {
    viewHandler.openView("ProjectList");
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
