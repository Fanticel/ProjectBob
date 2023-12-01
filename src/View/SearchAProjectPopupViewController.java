package View;

import Model.ProjectListModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;


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
  public SearchAProjectPopupViewController(){}
  public void init(ViewHandler viewHandler, ProjectListModel model, Region root, ViewState viewState){
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    this.viewState = viewState;
  }
  public void reset(){}
  public Region getRoot(){
    return root;
  }
  @FXML private void clickBackButt(){
    viewHandler.closePopupView();
  }
  @FXML private void clickSearchButt(){
    System.out.println("Searching...");
    viewState.setName(nameField.getText());
    viewHandler.searchHelper();
  }
}
