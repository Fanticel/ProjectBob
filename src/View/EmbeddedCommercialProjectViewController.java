package View;

import Model.CommercialProject;
import Model.MyDate;
import Model.Project;
import Model.ProjectListModel;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.converter.NumberStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

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

  @FXML
  private HBox expensesHbox;

  @FXML
  private HBox hoursHbox;

  @FXML
  private AnchorPane scrollAnchorPane;
  private Region root;
  private ProjectListModel model;
  private ViewHandler viewHandler;
  private ViewState viewState;

  UnaryOperator<TextFormatter.Change> filter = change -> {
    String newText = change.getControlNewText();
    if (Pattern.matches("[0-9,]*", newText)) {
      return change; // Allow the change
    } else {
      return null; // Reject the change
    }
  };
  public EmbeddedCommercialProjectViewController()
  {
  }

  public void init(ViewHandler viewHandler, ProjectListModel model, Region root, ViewState viewState)
  {
    this.model = model;
    this.viewHandler = viewHandler;
    this.root = root;
    this.viewState = viewState;

    budgetField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    expectedTotalHoursField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    expectedExpensesField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    totalHoursField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    expensesField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    sizeField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    numFloorsField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
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

  public void editReset(){
    CommercialProject project;
    project = (CommercialProject) model.getProject((String) viewState.getData().get(0));
    expectedTotalHoursField.setText(String.valueOf(project.getExpectedTotalHours()));
    expectedExpensesField.setText(String.valueOf(project.getExpectedExpenses()));
    budgetField.setText(String.valueOf(project.getBudget()));
    timelineDatePicker.setValue(null);
    LocalDate date = LocalDate.parse(project.getTimeline().toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    timelineDatePicker.setValue(date);
    sizeField.setText(String.valueOf(project.getSize()));
    numFloorsField.setText(String.valueOf(project.getNumFloor()));
    intendedUseArea.setText(String.valueOf(project.getIntendedUse()));

    hoursHbox.setVisible(true);
    expensesHbox.setVisible(true);
    scrollAnchorPane.setPrefHeight(582);

    totalHoursField.setText(String.valueOf(project.getTotalHours()));
    expensesField.setText(String.valueOf(project.getExpenses()));
  }
  public Region getRoot(){
    return root;
  }

  public void create(){
    if (expectedTotalHoursField.getText() == null || expectedExpensesField.getText() == null || budgetField.getText() == null || sizeField.getText() == null || numFloorsField.getText() == null || intendedUseArea.getText() == null){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    if (expectedTotalHoursField.getText().equals("") || expectedExpensesField.getText().equals("") || budgetField.getText().equals("") || sizeField.getText().equals("") || numFloorsField.getText().equals("") || intendedUseArea.getText().equals("")){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    ArrayList<Object> data = viewState.getData();
    data.add(Integer.valueOf(expectedTotalHoursField.getText().replace(",","")));
    data.add(Integer.valueOf(expectedExpensesField.getText().replace(",","")));
    data.add(budgetField.getText().replace(",",""));
    LocalDate chosenDate = timelineDatePicker.getValue();
    if (chosenDate.isBefore( LocalDate.now())){
      throw new IllegalArgumentException("Date has to be after today");
    }
    try {
      timelineDatePicker.getConverter().fromString(
          timelineDatePicker.getEditor().getText());
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException("Date is not valid");
    }
    if(chosenDate.getDayOfMonth() > 31 || chosenDate.getMonthValue() > 12 || chosenDate.getYear() > 5000000000L){
      throw new IllegalArgumentException("Invalid date values");
    }
    MyDate date = new MyDate(chosenDate.getDayOfMonth(), chosenDate.getMonthValue(), chosenDate.getYear());
    data.add(date);
    data.add("Ongoing");
    data.add(Integer.valueOf(sizeField.getText().replace(",","")));
    data.add(Integer.valueOf(numFloorsField.getText().replace(",","")));
    data.add(intendedUseArea.getText());

    model.addProject(data);
  }
  public void edit(Project project){
    if (expectedTotalHoursField.getText() == null || expectedExpensesField.getText() == null || budgetField.getText() == null || sizeField.getText() == null || numFloorsField.getText() == null || intendedUseArea.getText() == null || totalHoursField.getText() == null || expensesField.getText() == null){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    if (expectedTotalHoursField.getText().equals("") || expectedExpensesField.getText().equals("") || budgetField.getText().equals("") || sizeField.getText().equals("") || numFloorsField.getText().equals("") || intendedUseArea.getText().equals("") || totalHoursField.getText().equals("") || expensesField.getText().equals("")){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    Map<String,Object> data = (Map<String,Object>) viewState.getData().get(1);
    data.put("expectedTotalHours", expectedTotalHoursField.getText().replace(",",""));
    data.put("expectedExpenses", expectedExpensesField.getText().replace(",",""));
    data.put("totalHours", totalHoursField.getText().replace(",",""));
    data.put("expenses", expensesField.getText().replace(",",""));
    data.put("budget", budgetField.getText().replace(",",""));
    LocalDate chosenDate = timelineDatePicker.getValue();
    if (chosenDate.isBefore( LocalDate.now())){
      throw new IllegalArgumentException("Date has to be after today");
    }
    try {
      timelineDatePicker.getConverter().fromString(
          timelineDatePicker.getEditor().getText());
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException("Date is not valid");
    }
    if(chosenDate.getDayOfMonth() > 31 || chosenDate.getMonthValue() > 12 || chosenDate.getYear() > 5000000000L){
      throw new IllegalArgumentException("Invalid date values");
    }
    MyDate date = new MyDate(chosenDate.getDayOfMonth(), chosenDate.getMonthValue(), chosenDate.getYear());
    data.put("timeline", date);
    data.put("size", sizeField.getText().replace(",",""));
    data.put("numFloor", numFloorsField.getText().replace(",",""));
    data.put("intendedUse", intendedUseArea.getText());

    model.editProject(project, data);
  }
}
