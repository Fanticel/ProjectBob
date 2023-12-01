package View;

import Model.ProjectListModel;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

public class EmbeddedIndustrialProjectViewController {

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
  private TextField facilityTypeField;

  @FXML
  private TextField sizeField;

  @FXML
  private TextField totalHoursField;
  private Region root;
  private ProjectListModel model;
  private ViewHandler viewHandler;

  public EmbeddedIndustrialProjectViewController()
  {
  }

  public void init(ViewHandler viewHandler, ProjectListModel model, Region root)
  {
    this.model = model;
    this.viewHandler = viewHandler;
    this.root = root;
  }

  public void reset(){
    Map<String, Optional<Object>> defaults = model.getDefaults("Industrial");
    setField("budget",budgetField, defaults);

    if (defaults.get("timeline").isPresent()){
      LocalDate defaultDate = model.getDateMonthsAway((Integer) defaults.get("timeline").get());
      timelineDatePicker.setValue(defaultDate);
    }
    setField("size",sizeField, defaults);
    setField("type",facilityTypeField, defaults);
    setField("expectedTotalHours",expectedTotalHoursField, defaults);
    setField("expectedExpenses",expectedExpensesField, defaults);

  }
  private static void setField(String fieldName, TextField field,
      Map<String, Optional<Object>> defaults){
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

}
