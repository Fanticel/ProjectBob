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
    ArrayList<Object> data = viewState.getData();
    ArrayList<String> geoChallenges = new ArrayList<String>();
    data.add(Integer.valueOf(expectedTotalHoursField.getText()));
    data.add(Integer.valueOf(expectedExpensesField.getText()));
    data.add(budgetField.getText());
    LocalDate chosenDate = timelineDatePicker.getValue();
    MyDate date = new MyDate(chosenDate.getDayOfMonth(), chosenDate.getMonthValue(), chosenDate.getYear());
    data.add(date);
    data.add("Ongoing");
    data.add(Integer.valueOf(lengthField.getText()));
    data.add(Integer.valueOf(widthField.getText()));
    data.add(Integer.valueOf(numBridTunField.getText()));

    for (Node node : geoChallengeVBox.getChildren()){
      geoChallenges.add(((TextField)node).getText());
    }
    data.add(geoChallenges);
    geoChallengeVBox.getChildren().removeAll(geoChallengeVBox.getChildren());
    model.addProject(data);
  }

  public void edit(Project project){
    Map<String,Object> data = (Map<String,Object>) viewState.getData().get(1);
    ArrayList<String> geoChallenges = new ArrayList<String>();
    data.put("expectedTotalHours", expectedTotalHoursField.getText());
    data.put("expectedExpenses", expectedExpensesField.getText());
    data.put("totalHours", totalHoursField.getText());
    data.put("expenses", expensesField.getText());
    data.put("budget", budgetField.getText());
    LocalDate chosenDate = timelineDatePicker.getValue();
    MyDate date = new MyDate(chosenDate.getDayOfMonth(), chosenDate.getMonthValue(), chosenDate.getYear());
    data.put("timeline", date);
    data.put("length", lengthField.getText());
    data.put("width", widthField.getText());
    data.put("numBridTun", numBridTunField.getText());
    int i = 0;
    for (Node node : geoChallengeVBox.getChildren()){
      if (!((TextField)node).getText().equals(""))
      {
        geoChallenges.add(((TextField) node).getText());
      }
    }

  //  geoChallengeVBox.getChildren().add(new TextField());
    data.put("geoChallenge", geoChallenges);
    model.editProject(project, data);
  }
}

