package View;

import Model.MyDate;
import Model.ProjectListModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

public class EmbeddedResidentialProjectViewController
{

  @FXML
  private TextField budgetField;

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
  private RadioButton renovationRButton;

  @FXML
  private TextField sizeField;

  @FXML
  private DatePicker timelineDatePicker;

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
    setField("budget",budgetField, defaults);

    if (defaults.get("timeline").isPresent()){
      LocalDate defaultDate = model.getDateMonthsAway((Integer) defaults.get("timeline").get());
      timelineDatePicker.setValue(defaultDate);
    }
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