package View;

import Model.ProjectListModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Region;
import javafx.util.converter.NumberStringConverter;

import java.util.ArrayList;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class SearchAProjectPopupViewController {
  private ViewHandler viewHandler;
  private ProjectListModel model;
  private Region root;
  private ViewState viewState;

  @FXML private ChoiceBox<String> typeChoiceBox;
  @FXML private ChoiceBox<String> statusChoiceBox;
  @FXML private TextField nameField;
  @FXML private TextField priceRangeMin;
  @FXML private TextField priceRangeMax;
  @FXML private TextField manHoursMin;
  @FXML private TextField manHoursMax;
  @FXML private Button backButton;
  @FXML private Button searchButton;
  UnaryOperator<TextFormatter.Change> filter = change -> {
    String newText = change.getControlNewText();
    if (Pattern.matches("[0-9]*", newText)) {
      return change; // Allow the change
    } else {
      return null; // Reject the change
    }
  };
  public SearchAProjectPopupViewController(){}
  public void init(ViewHandler viewHandler, ProjectListModel model, Region root, ViewState viewState){
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    this.viewState = viewState;
    typeChoiceBox.getItems().add("All");
    typeChoiceBox.getItems().add("Commercial");
    typeChoiceBox.getItems().add("Industrial");
    typeChoiceBox.getItems().add("Residential");
    typeChoiceBox.getItems().add("Road");
    statusChoiceBox.getItems().add("All");
    statusChoiceBox.getItems().add("Ongoing");
    statusChoiceBox.getItems().add("Finished");
    statusChoiceBox.getItems().add("Canceled");
    statusChoiceBox.getItems().add("On hold");
    priceRangeMin.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    priceRangeMin.setText("");
    priceRangeMax.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    priceRangeMax.setText("");
    manHoursMin.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    manHoursMin.setText("");
    manHoursMax.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    manHoursMax.setText("");
  }
  public void reset(){}
  public Region getRoot(){
    return root;
  }
  @FXML private void clickBackButt(){
    viewHandler.closePopupView();
  }
  @FXML private void clickSearchButt(){
    ArrayList<Object> data = new ArrayList<>();
    //for search use, the data is 0:type, 1:name, 2:status, 3:price range min, 4:price range max, 5:man-hours min, 6:man-hours max
    if (typeChoiceBox.getValue() == null) {data.add(-1);} else {
      switch (typeChoiceBox.getValue()) {
        case "Residential" -> data.add(0);
        case "Commercial" -> data.add(1);
        case "Road" -> data.add(2);
        case "Industrial" -> data.add(3);
        default -> data.add(-1);
      }
    }
    if (!nameField.getText().isEmpty()){data.add(nameField.getText());}else{data.add(null);}
    if (statusChoiceBox.getValue() == null || statusChoiceBox.getValue().equals("All")) {data.add(null);} else {
      data.add(statusChoiceBox.getValue());
    }
    if(!priceRangeMin.getText().isEmpty()){data.add(priceRangeMin.getText());}else{data.add(null);}
    if(!priceRangeMax.getText().isEmpty()){data.add(priceRangeMax.getText());}else{data.add(null);}
    if(!manHoursMin.getText().isEmpty()){data.add(manHoursMin.getText());}else{data.add(null);}
    if(!manHoursMax.getText().isEmpty()){data.add(manHoursMax.getText());}else{data.add(null);}
    System.out.println(data);
    viewState.setData(data);
    System.out.println("Searching...");
    viewHandler.searchHelper();
  }
}
