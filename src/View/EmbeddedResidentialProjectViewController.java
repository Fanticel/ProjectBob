package View;

import Model.ProjectListModelManager;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Region;

public class EmbeddedResidentialProjectViewController
{

  @FXML
  private RadioButton RenovationRButton;

  @FXML
  private RadioButton buildRButton;

  @FXML
  private TextField expectedExpensesField;

  @FXML
  private TextField expectedTotalHoursField;

  @FXML
  private TextField expensesField;

  @FXML
  private ToggleGroup newBuildGroup;

  @FXML
  private TextField numBathroomsField;

  @FXML
  private TextField numKitchensField;

  @FXML
  private TextField othWPlumbingField;

  @FXML
  private TextField sizeField;

  @FXML
  private TextField totalHoursField;

  private Region root;
  private ProjectListModelManager model;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, ProjectListModelManager model, Region root)
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
}