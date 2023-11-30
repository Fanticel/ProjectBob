package View;

import Model.ProjectListModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class CreateProjectViewController
{
  @FXML
  private TextArea descriptionArea;

  @FXML
  private TextField nameField;

  @FXML
  private ChoiceBox<?> typeChoiceBox;

  @FXML
  void back(ActionEvent event) {

  }
  private Region root;
  private ProjectListModel model;
  private ViewHandler viewHandler;
  public CreateProjectViewController() {
  }

  public void init(ViewHandler viewHandler, ProjectListModel model, Region root)
  {
    this.model = model;
    this.viewHandler = viewHandler;
    this.root = root;
  }

  public void reset(){

  }
  public Region getRoot(){
    return root;
  }


  @FXML
  void create(ActionEvent event) {

  }

}
