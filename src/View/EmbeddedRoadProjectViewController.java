package View;

import Model.ProjectListModel;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class EmbeddedRoadProjectViewController {

  @FXML
  private DatePicker timelineDatePicker;

  @FXML
  private TextField budgetField;

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
  private ProjectListModel model;
  private ViewHandler viewHandler;

  public EmbeddedRoadProjectViewController()
  {
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

}

