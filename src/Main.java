import Model.MyDate;
import Model.ProjectListModelManager;
import javafx.application.Application;
import View.MyApplication;

import java.util.ArrayList;
import java.util.Arrays;

public class Main
{
  public static void main(String[] args)
  {
//    Application.launch(MyApplication.class);
    ArrayList<Object> data = new ArrayList<>(Arrays.asList("Dupa", "Zupa", 2, 3, 4, new MyDate(), "Kupa", 10));
    ProjectListModelManager projectListModelManager = new ProjectListModelManager();
    projectListModelManager.addProject(data);
  }
}
