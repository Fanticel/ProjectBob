package View;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.converter.NumberStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

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

  public EmbeddedResidentialProjectViewController()
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
    numKitchensField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    numBathroomsField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    othWPlumbingField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
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
  public void editReset(){
    ResidentialProject project;
    project = (ResidentialProject) model.getProject((String) viewState.getData().get(0));
    expectedTotalHoursField.setText(String.valueOf(project.getExpectedTotalHours()));
    expectedExpensesField.setText(String.valueOf(project.getExpectedExpenses()));
    budgetField.setText(String.valueOf(project.getBudget()));
    LocalDate date = LocalDate.parse(project.getTimeline().toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    timelineDatePicker.setValue(date);
    sizeField.setText(String.valueOf(project.getSize()));
    hoursHbox.setVisible(true);
    expensesHbox.setVisible(true);
    scrollAnchorPane.setPrefHeight(582);
    totalHoursField.setText(String.valueOf(project.getTotalHours()));
    expensesField.setText(String.valueOf(project.getExpenses()));

    numKitchensField.setText(String.valueOf(project.getNumKitchens()));
    numBathroomsField.setText(String.valueOf(project.getNumBathrooms()));
    othWPlumbingField.setText(String.valueOf(project.getOthWPlumbing()));

    if (project.isNewBuild()){
      newBuildGroup.selectToggle(buildRButton);
    }else newBuildGroup.selectToggle(renovationRButton);

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
    data.add(Integer.valueOf(expectedTotalHoursField.getText()));
    data.add(Integer.valueOf(expectedExpensesField.getText()));
    data.add(budgetField.getText());
    LocalDate chosenDate = timelineDatePicker.getValue();
    MyDate date = new MyDate(chosenDate.getDayOfMonth(), chosenDate.getMonthValue(), chosenDate.getYear());
    data.add(date);
    data.add("Ongoing");
    data.add(Integer.valueOf(sizeField.getText()));
    data.add(Integer.valueOf(numKitchensField.getText()));
    data.add(Integer.valueOf(numBathroomsField.getText()));
    data.add(Integer.valueOf(othWPlumbingField.getText()));

    if (newBuildGroup.getSelectedToggle() == buildRButton)
      data.add( true);
    else data.add( false);

    model.addProject(data);
  }
  public void edit(Project project){
    Map<String,Object> data = (Map<String,Object>) viewState.getData().get(1);
    data.put("expectedTotalHours", expectedTotalHoursField.getText());
    data.put("expectedExpenses", expectedExpensesField.getText());
    data.put("totalHours", totalHoursField.getText());
    data.put("expenses", expensesField.getText());
    data.put("budget", budgetField.getText());
    LocalDate chosenDate = timelineDatePicker.getValue();
    MyDate date = new MyDate(chosenDate.getDayOfMonth(), chosenDate.getMonthValue(), chosenDate.getYear());
    data.put("timeline", date);
    data.put("size", sizeField.getText());
    data.put("numKitchens", numKitchensField.getText());
    data.put("numBathrooms", numBathroomsField.getText());
    data.put("othWPlumbing", othWPlumbingField.getText());
    data.put("isNewBuild", othWPlumbingField.getText());
    if (newBuildGroup.getSelectedToggle() == buildRButton)
      data.put("isNewBuild", true);
    else data.put("isNewBuild", false);
    model.editProject(project, data);
  }

}