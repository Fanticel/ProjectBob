package View;

import Model.ProjectListModelManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class MyApplication extends Application
{
  public void start(Stage primaryStage)
  {
    ViewHandler view = new ViewHandler(new ProjectListModelManager());
    view.start(primaryStage);
  }
}
