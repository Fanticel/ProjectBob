package View;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class EmbeddedRoadProjectViewController {

  @FXML
  private VBox geoChallengeVBox;

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
  private TextField lengthField;

  @FXML
  private TextField numBridTunField;

  @FXML
  private TextField totalHoursField;

  @FXML
  private TextField widthField;
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
  public EmbeddedRoadProjectViewController()
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
    lengthField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    widthField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    numBridTunField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
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
    
    geoChallengeVBox.getChildren().removeAll(geoChallengeVBox.getChildren());
    if (defaults.get("geoChallenge").isPresent())
    {
      geoChallengeVBox.getChildren().add(new TextField(defaults.get("geoChallenge").get().toString()));
    }
    setField("expectedTotalHours",expectedTotalHoursField, defaults);
    setField("expectedExpenses",expectedExpensesField, defaults);
  }

  public void editReset(){
    if (expectedTotalHoursField.getText() == null || expectedExpensesField.getText() == null || budgetField.getText() == null || lengthField.getText() == null || widthField.getText() == null || numBridTunField.getText() == null){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    if (expectedTotalHoursField.getText().equals("") || expectedExpensesField.getText().equals("") || budgetField.getText().equals("") || lengthField.getText().equals("") || widthField.getText().equals("") || numBridTunField.getText().equals("")){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    RoadProject project;
    project = (RoadProject) model.getProject((String) viewState.getData().get(0));
    expectedTotalHoursField.setText(String.valueOf(project.getExpectedTotalHours()));
    expectedExpensesField.setText(String.valueOf(project.getExpectedExpenses()));
    budgetField.setText(String.valueOf(project.getBudget()));
    LocalDate date = LocalDate.parse(project.getTimeline().toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    timelineDatePicker.setValue(date);
    hoursHbox.setVisible(true);
    expensesHbox.setVisible(true);
    scrollAnchorPane.setPrefHeight(582);
    totalHoursField.setText(String.valueOf(project.getTotalHours()));
    expensesField.setText(String.valueOf(project.getExpenses()));

    lengthField.setText(String.valueOf(project.getLength()));
    widthField.setText(String.valueOf(project.getWidth()));
    numBridTunField.setText(String.valueOf(project.getnumBridTun()));
    ArrayList<String> geoChallenges = project.getgeoChallenge();

    geoChallengeVBox.getChildren().removeAll(geoChallengeVBox.getChildren());
    //int difference = geoChallenges.size() - geoChallengeVBox.getChildren().size();
    for (int i = 0; i < geoChallenges.size(); i++){
      scrollAnchorPane.setPrefHeight(scrollAnchorPane.getPrefHeight() + 20);
      geoChallengeVBox.getChildren().add(new TextField(geoChallenges.get(i)));
    }
  }
  public Region getRoot(){
    return root;
  }

  @FXML private void addField(){
    geoChallengeVBox.getChildren().add(new TextField());
    scrollAnchorPane.setPrefHeight(scrollAnchorPane.getPrefHeight() + 20);
  }
  public void create(){
    if (expectedTotalHoursField.getText() == null || expectedExpensesField.getText() == null || budgetField.getText() == null || lengthField.getText() == null || widthField.getText() == null || numBridTunField.getText() == null || totalHoursField.getText() == null || expensesField.getText() == null){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    if (expectedTotalHoursField.getText().equals("") || expectedExpensesField.getText().equals("") || budgetField.getText().equals("") || lengthField.getText().equals("") || widthField.getText().equals("") || numBridTunField.getText().equals("") || totalHoursField.getText().equals("") || expensesField.getText().equals("")){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    ArrayList<Object> data = viewState.getData();
    ArrayList<String> geoChallenges = new ArrayList<String>();
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
    data.add(Integer.valueOf(lengthField.getText().replace(",","")));
    data.add(Integer.valueOf(widthField.getText().replace(",","")));
    data.add(Integer.valueOf(numBridTunField.getText().replace(",","")));

    for (Node node : geoChallengeVBox.getChildren()){
      if (!((TextField)node).getText().equals(""))
      {
        geoChallenges.add(((TextField) node).getText());
      }
    }
    if (geoChallenges.size() == 0){
      geoChallenges.add("none");
    }
    data.add(geoChallenges);
    geoChallengeVBox.getChildren().removeAll(geoChallengeVBox.getChildren());
    model.addProject(data);
  }

  public void edit(Project project){
    Map<String,Object> data = (Map<String,Object>) viewState.getData().get(1);
    ArrayList<String> geoChallenges = new ArrayList<String>();
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
    data.put("length", lengthField.getText().replace(",",""));
    data.put("width", widthField.getText().replace(",",""));
    data.put("numBridTun", numBridTunField.getText().replace(",",""));
    int i = 0;
    for (Node node : geoChallengeVBox.getChildren()){
      if (!((TextField)node).getText().equals(""))
      {
        geoChallenges.add(((TextField) node).getText());
      }
    }
    if (geoChallenges.size() == 0){
      geoChallenges.add("none");
    }

  //  geoChallengeVBox.getChildren().add(new TextField());
    data.put("geoChallenge", geoChallenges);
    model.editProject(project, data);
  }
}

