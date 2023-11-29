package View;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import Model.ProjectListModel;

public class DashboardViewController {
  private ProjectListModel model;
  private ViewHandler viewHandler;
  private Region root;
  public DashboardViewController(){}
  public void init(ViewHandler viewHandler, ProjectListModel model, Region root){
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }
  public void reset(){

  }
  public Region getRoot(){
    return root;
  }

  @FXML private void clickProjectsButton(){
    viewHandler.openView("ProjectList");
  }

  @FXML private void clickHomeButton(){
    viewHandler.openView("");
  }
}
