package View;

import Model.ProjectListModelManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class EmbeddedRoadProjectViewController {

  @FXML
  private TextField expectedExpensesField;

  @FXML
  private TextField expectedTotalHoursField;

  @FXML
  private TextField expensesField;

  @FXML
  private TextField geoChallengeField;

  @FXML
  private TextField lengthField;

  @FXML
  private TextField numBridTunField;

  @FXML
  private TextField totalHoursField;

  @FXML
  private TextField widthField;
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

