package View;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import Model.ProjectListModel;

public class ProjectListViewController {
  private ProjectListModel model;
  private ViewHandler viewHandler;
  private Region root;
  private ProjectListViewModel projectListViewModel;
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

}
