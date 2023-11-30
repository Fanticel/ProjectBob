package View;

import Model.ProjectListModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class EmbeddedCommercialProjectViewController {

  @FXML
  private TextField expectedExpensesField;

  @FXML
  private TextField expectedTotalHoursField;

  @FXML
  private TextField expensesField;

  @FXML
  private TextArea intendedUseArea;

  @FXML
  private TextField numKitchensField;

  @FXML
  private TextField sizeField;

  @FXML
  private TextField totalHoursField;

  private Region root;
  private ProjectListModel model;
  private ViewHandler viewHandler;

  public EmbeddedCommercialProjectViewController()
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
