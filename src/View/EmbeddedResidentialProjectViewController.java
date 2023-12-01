package View;

import Model.MyDate;
import Model.ProjectListModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import java.time.LocalDate;
import java.util.ArrayList;
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

  private ViewState viewState;

  public EmbeddedResidentialProjectViewController()
  {
  }

  public void init(ViewHandler viewHandler, ProjectListModel model, Region root, ViewState viewState)
  {
    this.model = model;
    this.viewHandler = viewHandler;
    this.root = root;
    this.viewState = viewState;
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
  public void create(){
    ArrayList<Object> data = viewState.getData();
    data.add((Object) Integer.valueOf(expectedTotalHoursField.getText()));
    data.add((Object) Integer.valueOf(expectedExpensesField.getText()));
    data.add((Object) budgetField.getText());
    LocalDate chosenDate = timelineDatePicker.getValue();
    MyDate date = new MyDate(chosenDate.getDayOfMonth(), chosenDate.getMonthValue(), chosenDate.getYear());
    data.add((Object) date);
    data.add((Object) "Created");
    data.add((Object) Integer.valueOf(sizeField.getText()));
    data.add((Object) Integer.valueOf(numKitchensField.getText()));
    data.add((Object) Integer.valueOf(numBathroomsField.getText()));
    data.add((Object) Integer.valueOf(othWPlumbingField.getText()));

    if (newBuildGroup.getSelectedToggle() == buildRButton)
      data.add((Object) true);
    else data.add((Object) false);

    model.addProject(data);
  }

}