// Made by Zygmunt Kwaśniewicz
package View;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import Model.ProjectListModel;

import java.util.ArrayList;

public class ProjectListViewController {
  private ProjectListModel model;
  private ViewHandler viewHandler;
  private Region root;
  private ViewState viewState;
  private ProjectListViewModel projectListViewModel;
  private String projectName;
  @FXML private Button AddButton;
  @FXML private Button EditButton;
  @FXML private Button DetailsButton;
  @FXML private TableView<ProjectViewModel> projectTable;
  @FXML private TableColumn<ProjectViewModel, String> nameColumn;
  @FXML private TableColumn<ProjectViewModel, String> typeColumn;
  @FXML private TableColumn<ProjectViewModel, String> statusColumn;

  public ProjectListViewController() {
  }

  //Initiates the list
  public void init(ViewHandler viewHandler, ProjectListModel model,
      Region root, ViewState viewState) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    this.viewState = viewState;
    this.projectListViewModel = new ProjectListViewModel(model);
    nameColumn.setCellValueFactory(
        cellData -> cellData.getValue().namePropertyProperty()
    );
    typeColumn.setCellValueFactory(
        cellData -> cellData.getValue().typePropertyProperty()
    );
    statusColumn.setCellValueFactory(
        cellData -> cellData.getValue().statusPropertyProperty()
    );
    projectTable.setItems(projectListViewModel.getList());
    projectTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      if (newSelection != null) {
        System.out.println("Selected: " + newSelection.namePropertyProperty().getValue() + ", " + newSelection.typePropertyProperty().getValue() + ", " + newSelection.statusPropertyProperty().getValue());
        projectName = newSelection.namePropertyProperty().getValue();
        EditButton.disableProperty().setValue(false);
        DetailsButton.disableProperty().setValue(false);
      }
    });

  }

  public void reset() {
    projectListViewModel.update();
  }
  public void search(){
    projectListViewModel.updateSearch(viewState.getData());
  }

  public Region getRoot() {
    return root;
  }

  @FXML private void clickBackButt() {
    viewHandler.openView("");
  }

  //Opens edit view and sends project name to viewState
  @FXML private void clickEditButt(){
    ArrayList<Object> data = new ArrayList<Object>();
    data.add(projectName);
    viewState.setData(data);
    viewHandler.openView("EditProject");
    unselected();
  }
  //Opens details view and sends project name to viewState
  @FXML private void clickDetailButt(){
    ArrayList<Object> data = new ArrayList<Object>();
    data.add(projectName);
    viewState.setData(data);
    viewHandler.openView("ProjectDetails");
    System.out.println("Details: " + projectName);
    unselected();
  }
  @FXML private void clickAddButt(){
    viewHandler.openView("CreateProject");
    unselected();
  }
  @FXML private void clickSearchButt(){
    viewHandler.openPopupView("Search");
    unselected();
  }
  @FXML private void escapeClicked(KeyEvent event){
    if (event.getCode().toString().equals("ESCAPE")) {
      unselected();
    }
  }
  //refreshes the list
  @FXML private void clickRefreshButt(){
    reset();
  }
  private void unselected(){
    AddButton.requestFocus();
    EditButton.disableProperty().setValue(true);
    DetailsButton.disableProperty().setValue(true);
    System.out.println("Unselected");
  }

}
