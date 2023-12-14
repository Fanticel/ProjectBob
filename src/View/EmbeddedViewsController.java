package View;

import Model.MyDate;
import Model.Project;
import Model.ProjectListModel;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
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
import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public abstract class EmbeddedViewsController {
  @FXML private DatePicker timelineDatePicker;

  @FXML private TextField budgetField;

  @FXML private TextField expectedExpensesField;

  @FXML private TextField expectedTotalHoursField;

  @FXML private TextField expensesField;

  @FXML private TextField totalHoursField;

  @FXML private HBox expensesHbox;

  @FXML private HBox hoursHbox;

  @FXML private AnchorPane scrollAnchorPane;

  private Region root;
  private ProjectListModel model;
  private ViewHandler viewHandler;
  private ViewState viewState;
  UnaryOperator<TextFormatter.Change> filter = change -> {
    String newText = change.getControlNewText();
    if (Pattern.matches("[0-9,.]*", newText)) {
      return change; // Allow the change
    }
    else {
      return null; // Reject the change
    }
  };

  public EmbeddedViewsController() {
  }

  public void init(ViewHandler viewHandler, ProjectListModel model, Region root,
      ViewState viewState) {
    this.model = model;
    this.viewHandler = viewHandler;
    this.root = root;
    this.viewState = viewState;

    budgetField.setTextFormatter(
        new TextFormatter<>(new NumberStringConverter(), 0, filter));
    expectedTotalHoursField.setTextFormatter(
        new TextFormatter<>(new NumberStringConverter(), 0, filter));
    expectedExpensesField.setTextFormatter(
        new TextFormatter<>(new NumberStringConverter(), 0, filter));
    totalHoursField.setTextFormatter(
        new TextFormatter<>(new NumberStringConverter(), 0, filter));
    expensesField.setTextFormatter(
        new TextFormatter<>(new NumberStringConverter(), 0, filter));
  }

  public void reset(Map<String, Optional<Object>> defaults) {
    setField("budget", budgetField, defaults);

    if (defaults.get("timeline").isPresent()) {
      LocalDate defaultDate = model.getDateMonthsAway(
          (Integer) defaults.get("timeline").get());
      timelineDatePicker.setValue(defaultDate);
    }
    setField("expectedTotalHours", expectedTotalHoursField, defaults);
    setField("expectedExpenses", expectedExpensesField, defaults);
  }

  public void editReset() {
    Project project = getModel().getProject(
        (String) getViewState().getData().get(0));
    expectedTotalHoursField.setText(
        String.valueOf(project.getExpectedTotalHours()));
    expectedExpensesField.setText(
        String.valueOf(project.getExpectedExpenses()));
    budgetField.setText(String.valueOf(project.getBudget()));
    timelineDatePicker.setValue(null);
    LocalDate date = LocalDate.parse(project.getTimeline().toString(),
        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    timelineDatePicker.setValue(date);

    hoursHbox.setVisible(true);
    expensesHbox.setVisible(true);
    scrollAnchorPane.setPrefHeight(582);

    totalHoursField.setText(String.valueOf(project.getTotalHours()));
    expensesField.setText(String.valueOf(project.getExpenses()));
  }

  static void setField(String fieldName, TextField field,
      Map<String, Optional<Object>> defaults) {
    if (defaults.get(fieldName).isPresent()) {
      field.setText(defaults.get(fieldName).get().toString());
    }
    else {
      field.setText(null);
    }
  }

  public Region getRoot() {
    return root;
  }

  public void create(ArrayList<Object> data) {
    if (expectedTotalHoursField.getText() == null
        || expectedExpensesField.getText() == null
        || budgetField.getText() == null) {
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    if (expectedTotalHoursField.getText().equals("")
        || expectedExpensesField.getText().equals("") || budgetField.getText()
        .equals("")) {
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    try {
      timelineDatePicker.getConverter()
          .fromString(timelineDatePicker.getEditor().getText());
    }
    catch (DateTimeParseException e) {
      throw new IllegalArgumentException("Date is not valid");
    }
    if (timelineDatePicker.getConverter()
        .fromString(timelineDatePicker.getEditor().getText()) == null) {
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    if (timelineDatePicker.getConverter()
        .fromString(timelineDatePicker.getEditor().getText()).toString()
        .equals("")) {
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    data.add(
        Integer.valueOf(expectedTotalHoursField.getText().replace(",", "").replace(".","")));
    data.add(Integer.valueOf(expectedExpensesField.getText().replace(",", "").replace(".","")));
    data.add(budgetField.getText().replace(",", "").replace(".",""));
    LocalDate chosenDate = timelineDatePicker.getValue();
    if (chosenDate.isBefore(LocalDate.now())) {
      throw new IllegalArgumentException("Date has to be after today");
    }
    MyDate date = new MyDate(chosenDate.getDayOfMonth(),
        chosenDate.getMonthValue(), chosenDate.getYear());
    data.add(date);
    data.add("Ongoing");
  }

  public void edit(Map<String, Object> data) {
    if (expectedTotalHoursField.getText() == null
        || expectedExpensesField.getText() == null
        || budgetField.getText() == null || totalHoursField.getText() == null
        || expensesField.getText() == null) {
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    if (expectedTotalHoursField.getText().equals("")
        || expectedExpensesField.getText().equals("") || budgetField.getText()
        .equals("") || totalHoursField.getText().equals("")
        || expensesField.getText().equals("")) {
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    try {
      timelineDatePicker.getConverter()
          .fromString(timelineDatePicker.getEditor().getText());
    }
    catch (DateTimeParseException e) {
      throw new IllegalArgumentException("Date is not valid");
    }
    if (timelineDatePicker.getConverter()
        .fromString(timelineDatePicker.getEditor().getText()) == null) {
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    if (timelineDatePicker.getConverter()
        .fromString(timelineDatePicker.getEditor().getText()).toString()
        .equals("")) {
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    data.put("expectedTotalHours",
        expectedTotalHoursField.getText().replace(",", "").replace(".",""));
    data.put("expectedExpenses",
        expectedExpensesField.getText().replace(",", "").replace(".",""));
    data.put("totalHours", totalHoursField.getText().replace(",", "").replace(".",""));
    data.put("expenses", expensesField.getText().replace(",", "").replace(".",""));
    data.put("budget", budgetField.getText().replace(",", "").replace(".",""));
    LocalDate chosenDate = timelineDatePicker.getValue();
    if (chosenDate.isBefore(LocalDate.now())) {
      throw new IllegalArgumentException("Date has to be after today");
    }
    MyDate date = new MyDate(chosenDate.getDayOfMonth(),
        chosenDate.getMonthValue(), chosenDate.getYear());
    data.put("timeline", date);

  }

  public void setRoot(Region root) {
    this.root = root;
  }

  public ProjectListModel getModel() {
    return model;
  }

  public void setModel(ProjectListModel model) {
    this.model = model;
  }

  public ViewHandler getViewHandler() {
    return viewHandler;
  }

  public void setViewHandler(ViewHandler viewHandler) {
    this.viewHandler = viewHandler;
  }

  public ViewState getViewState() {
    return viewState;
  }

  public void setViewState(ViewState viewState) {
    this.viewState = viewState;
  }

  public AnchorPane getScrollAnchorPane() {
    return scrollAnchorPane;
  }

  public void setScrollAnchorPane(AnchorPane scrollAnchorPane) {
    this.scrollAnchorPane = scrollAnchorPane;
  }
  public void setEditable(boolean value) {
    budgetField.setEditable(value);
    expectedExpensesField.setEditable(value);
    expectedTotalHoursField.setEditable(value);
    expensesField.setEditable(value);
    timelineDatePicker.setEditable(value);
    totalHoursField.setEditable(value);
  }
}
