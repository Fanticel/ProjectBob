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

import java.util.ArrayList;

public class CreateProjectViewController {
  private Region root;
  private ProjectListModel model;
  private ViewHandler viewHandler;

  private ViewState viewState;

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
      Region root, ViewState viewState) {
    this.model = model;
    this.viewHandler = viewHandler;
    this.root = root;
    this.viewState = viewState;
    view1Controller.init(viewHandler, model, root, viewState);
    view2Controller.init(viewHandler, model, root, viewState);
    view3Controller.init(viewHandler, model, root, viewState);
    view4Controller.init(viewHandler, model, root, viewState);
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
    typeChoiceBox.setValue("");
    nameField.setText("");
    descriptionArea.setText("");
    errorLabel.setText("");
    view1Controller.reset();
    view2Controller.reset();
    view3Controller.reset();
    view4Controller.reset();

    ObservableList<Node> children = stackPane.getChildren();
    for (Node i : children) {
      i.setVisible(false);
    }
  }

  public Region getRoot() {
    return root;
  }

  @FXML void create(ActionEvent event) {
   /* try
    {*/
      ArrayList<Object> data = new ArrayList<Object>();
      data.add(nameField.getText());
      data.add(descriptionArea.getText());
      viewState.setData(data);
      switch (typeChoiceBox.getValue()){
        case "Residential" -> view3Controller.create();
        case "Commercial" -> view1Controller.create();
        case "Industrial" -> view2Controller.create();
        case "Road" -> view4Controller.create();
      }
      viewHandler.openView("ProjectList");
    /*}
    catch (NumberFormatException e)
    {
      errorLabel.setText("Input error: " + e.getMessage());
    }
    catch (Exception e)
    {
      errorLabel.setText(e.getMessage());
    }*/
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
