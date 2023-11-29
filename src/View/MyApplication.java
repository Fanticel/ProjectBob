package View;

import Model.ProjectListModelManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class MyApplication extends Application
{
  public void start(Stage primaryStage)
  {
    ProjectListModelManager projectListModelManager = new ProjectListModelManager();
    projectListModelManager.createSampleData();
    ViewHandler view = new ViewHandler(projectListModelManager);
    view.start(primaryStage);
  }
}
