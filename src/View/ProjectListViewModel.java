package View;

import Model.Project;
import Model.ProjectListModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ProjectListViewModel {

  private ObservableList<ProjectViewModel> list;
  private ProjectListModel model;

  public ProjectListViewModel(ProjectListModel model) {
    this.model = model;
    this.list = FXCollections.observableArrayList();
    update();
  }

  public ObservableList<ProjectViewModel> getList() {
    return list;
  }

  public void update() {
    list.clear();
    for (Project i : model.getAllProjects().returnAsArrayList()) {
      list.add(new ProjectViewModel(i));
    }
  }
  public void updateSearch(ArrayList<Object> data){
    list.clear();
    for (Project i : model.getAllProjectsByData(data).returnAsArrayList()) {
        list.add(new ProjectViewModel(i));
    }
  }
}
