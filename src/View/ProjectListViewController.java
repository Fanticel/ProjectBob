package View;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import Model.ProjectListModel;

public class ProjectListViewController {
  private ProjectListModel model;
  private ViewHandler viewHandler;
  private Region root;
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

  public void init(ViewHandler viewHandler, ProjectListModel model,
      Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
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

  public Region getRoot() {
    return root;
  }

  @FXML private void clickBackButt() {
    viewHandler.openView("");
  }
  @FXML private void clickEditButt(){
    System.out.println("Editing: " + projectName);
  }
  @FXML private void clickDetailButt(){
    System.out.println("Details: " + projectName);
  }
  @FXML private void clickAddButt(){
    viewHandler.openView("CreateProject");
    unselected();
  }
  @FXML private void clickSearchButt(){
    System.out.println("Searching a project");
    unselected();
  }
  @FXML private void escapeClicked(KeyEvent event){
    if (event.getCode().toString().equals("ESCAPE")) {
      unselected();
    }
  }
  private void unselected(){
    AddButton.requestFocus();
    EditButton.disableProperty().setValue(true);
    DetailsButton.disableProperty().setValue(true);
    System.out.println("Unselected");
  }

}
