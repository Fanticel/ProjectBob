package View;

import Model.ProjectListModel;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Region;

import java.util.Map;
import java.util.Optional;

public class EmbeddedResidentialProjectViewController
{

  @FXML
  private RadioButton renovationRButton;

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
  private ProjectListModel model;
  private ViewHandler viewHandler;

  public EmbeddedResidentialProjectViewController()
  {
  }

  public void init(ViewHandler viewHandler, ProjectListModel model, Region root)
  {
    this.model = model;
    this.viewHandler = viewHandler;
    this.root = root;
  }

  public void reset(){
    Map<String, Optional<Object>> defaults = model.getDefaults("Residential");
    setField("size",sizeField, defaults);
    setField("numKitchens",numKitchensField, defaults);
    setField("numBathrooms",numBathroomsField, defaults);
    setField("othWPlumbing",othWPlumbingField, defaults);
    setField("expectedTotalHours",expectedTotalHoursField, defaults);
    setField("expectedExpenses",expectedExpensesField, defaults);

    if (defaults.get("isNewBuild").isPresent()){
      if (defaults.get("isNewBuild").get().toString().equals("new build")){
        newBuildGroup.selectToggle(buildRButton);
      }else newBuildGroup.selectToggle(renovationRButton);
    }

  }
  public Region getRoot(){
    return root;
  }

  private static void setField(String fieldName, TextField field,Map<String, Optional<Object>> defaults){
    if (defaults.get(fieldName).isPresent()){
      field.setText(defaults.get(fieldName).get().toString());
    }
    else {
      field.setText(null);
    }
  }

}