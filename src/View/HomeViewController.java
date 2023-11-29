package View;

import javafx.scene.layout.Region;
import Model.ProjectListModel;

public class HomeViewController {
  private ProjectListModel model;
  private ViewHandler viewHandler;
  private Region root;
  public HomeViewController(){}
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
}
