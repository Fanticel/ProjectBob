package View;

import Model.ProjectListModel;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

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
  private static void setField(String fieldName, TextField field,
      Map<String, Optional<Object>> defaults){
    if (defaults.get(fieldName).isPresent()){
      field.setText(defaults.get(fieldName).get().toString());
    }
    else {
      field.setText(null);
    }
  }

  public void reset(){
    Map<String, Optional<Object>> defaults = model.getDefaults("Road");
    setField("budget",budgetField, defaults);

    if (defaults.get("timeline").isPresent()){
      LocalDate defaultDate = model.getDateMonthsAway((Integer) defaults.get("timeline").get());
      timelineDatePicker.setValue(defaultDate);
    }
    setField("length",lengthField, defaults);
    setField("width",widthField, defaults);
    setField("numBridTun",numBridTunField, defaults);
    setField("geoChallenge",geoChallengeField, defaults);
    setField("expectedTotalHours",expectedTotalHoursField, defaults);
    setField("expectedExpenses",expectedExpensesField, defaults);
  }
  public Region getRoot(){
    return root;
  }

}

