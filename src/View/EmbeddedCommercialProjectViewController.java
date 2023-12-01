package View;

import Model.MyDate;
import Model.ProjectListModel;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class EmbeddedCommercialProjectViewController {

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
  private TextArea intendedUseArea;

  @FXML
  private TextField numFloorsField;

  @FXML
  private TextField sizeField;

  @FXML
  private TextField totalHoursField;

  private Region root;
  private ProjectListModel model;
  private ViewHandler viewHandler;
  private ViewState viewState;

  public EmbeddedCommercialProjectViewController()
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
    Map<String, Optional<Object>> defaults = model.getDefaults("Commercial");
    setField("budget",budgetField, defaults);

    if (defaults.get("timeline").isPresent()){
      LocalDate defaultDate = model.getDateMonthsAway((Integer) defaults.get("timeline").get());
      timelineDatePicker.setValue(defaultDate);
    }
    setField("size",sizeField, defaults);
    setField("numFloor",numFloorsField, defaults);

    if (defaults.get("intendedUse").isPresent()){
      intendedUseArea.setText(defaults.get("intendedUse").get().toString());
    }
    else {
      intendedUseArea.setText(null);
    }

    setField("expectedTotalHours",expectedTotalHoursField, defaults);
    setField("expectedExpenses",expectedExpensesField, defaults);
  }
  private static void setField(String fieldName, TextField field,Map<String, Optional<Object>> defaults){
    if (defaults.get(fieldName).isPresent()){
      field.setText(defaults.get(fieldName).get().toString());
    }
    else {
      field.setText(null);
    }
  }
  public Region getRoot(){
    return root;
  }

  public void create(){
    ArrayList<Object> data = viewState.getData();
    data.add(Integer.valueOf(expectedTotalHoursField.getText()));
    data.add(Integer.valueOf(expectedExpensesField.getText()));
    data.add(budgetField.getText());
    LocalDate chosenDate = timelineDatePicker.getValue();
    MyDate date = new MyDate(chosenDate.getDayOfMonth(), chosenDate.getMonthValue(), chosenDate.getYear());
    data.add(date);
    data.add("Ongoing");
    data.add(Integer.valueOf(sizeField.getText()));
    data.add(Integer.valueOf(numFloorsField.getText()));
    data.add(intendedUseArea.getText());

    model.addProject(data);
  }
}
