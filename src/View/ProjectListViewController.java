package View;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import Model.ProjectListModel;
public class ProjectListViewController {
  private ProjectListModel model;
  private ViewHandler viewHandler;
  private Region root;
  public ProjectListViewController(){}
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
  @FXML private void clickBackButt(){
    viewHandler.openView("");
  }

}
