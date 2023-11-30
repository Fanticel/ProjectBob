package View;

import Model.ProjectListModel;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class EmbeddedViewController
{
  @FXML private StackPane stackPane;
  @FXML private CreateProjectViewController createProjectViewController = new CreateProjectViewController();
  @FXML private EmbeddedResidentialProjectViewController embeddedResidentialProjectViewController;
  @FXML private EmbeddedCommercialProjectViewController embeddedCommercialProjectViewController;

  @FXML private EmbeddedIndustrialProjectViewController embeddedIndustrialProjectViewController;
  @FXML private EmbeddedRoadProjectViewController embeddedRoadProjectViewController;
  private Region root;

  public EmbeddedViewController()
  {
  }

  public void init(ViewHandler viewHandler, ProjectListModel model, Region root){
    this.root = root;
    createProjectViewController.init(viewHandler, model, root);
    embeddedResidentialProjectViewController.init(viewHandler, model, root);
    embeddedCommercialProjectViewController.init(viewHandler, model, root);
    embeddedIndustrialProjectViewController.init(viewHandler, model, root);
    embeddedRoadProjectViewController.init(viewHandler, model, root);
  }

  public void reset(){
    createProjectViewController.reset();
    embeddedResidentialProjectViewController.reset();
    embeddedCommercialProjectViewController.reset();
    embeddedIndustrialProjectViewController.reset();
    embeddedRoadProjectViewController.reset();
  }


  public void showView (String id){
    if (id == null){
      return;
    }
    ObservableList<Node> children = stackPane.getChildren();
    for (int i = 0; i < children.size(); i++){
      children.get(i).setVisible(false);
      if (id.equalsIgnoreCase(children.get(i).getId())){
        children.get(i).setVisible(true);
      }
    }
  }
  public Region getRoot(){
    return root;
  }
}
