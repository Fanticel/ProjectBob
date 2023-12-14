//Made by Alan Karasin Stifter
package View;

import Model.ProjectListModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

public class DeletePopupViewController
{
  private Region root;
  private ProjectListModel model;
  private ViewHandler viewHandler;
  private ViewState viewState;

  public DeletePopupViewController()
  {
  }

  public void init(ViewHandler viewHandler, ProjectListModel model, Region root, ViewState viewState){
    this.model = model;
    this.viewHandler = viewHandler;
    this.root = root;
    this.viewState = viewState;
  }
  @FXML
  void cancel(ActionEvent event) {
    viewHandler.closePopupView();
  }

  @FXML
  void delete(ActionEvent event) {
    String name = (String) viewState.getData().get(0);
    model.delete(name);
    viewHandler.closePopupView();
    viewHandler.openView("ProjectList");
  }
  public void reset() {

  }
  public Region getRoot() {
    return root;
  }

}
