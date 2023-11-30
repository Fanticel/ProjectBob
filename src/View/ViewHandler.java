package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import Model.ProjectListModel;

public class ViewHandler {
  private Scene currentScene;
  private Stage primaryStage;
  private ProjectListModel model;
  private ViewState viewState;
  private HomeViewController homeViewController;
  private ProjectListViewController projectListViewController;
  private DashboardViewController dashboardViewController;
  private CreateProjectViewController createProjectViewController;

  public ViewHandler(ProjectListModel model) {
    this.currentScene = new Scene(new Region());
    this.model = model;
    viewState = new ViewState();
  }

  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    openView("ProjectList");
    System.out.println("Starting view.");
  }

  public void openView(String id) {
    Region root = loadHomeView("HomeView.fxml");
    switch (id) {
      case "ProjectList" -> root = loadProjectListView("ProjectListView.fxml");
      case "Dashboard" -> root = loadDashboardView("DashboardView.fxml");
      case "CreateProject" -> root = loadCreateProjectView("CreateProjectView.fxml");
    }
    currentScene.setRoot(root);
    String title = "";
    if (root.getUserData() != null) {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
    System.out.println("Open view works.");
  }

  private Region loadHomeView(String fxmlFile) {
    Region root = null;
    if (homeViewController == null) {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        homeViewController = loader.getController();
        homeViewController.init(this, model, root);
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    else {
      homeViewController.reset();
    }
    System.out.println("root works");
    return homeViewController.getRoot();
  }

  private Region loadProjectListView(String fxmlFile) {
    Region root = null;
    if (projectListViewController == null) {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        projectListViewController = loader.getController();
        projectListViewController.init(this, model, root);
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    else {
      projectListViewController.reset();
    }
    System.out.println("root works");
    return projectListViewController.getRoot();
  }

  private Region loadDashboardView(String fxmlFile){
    Region root = null;
    if (dashboardViewController==null){
      try{
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        dashboardViewController = loader.getController();
        dashboardViewController.init(this, model, root);
      }
      catch (Exception e){
        e.printStackTrace();
      }
    }
    else {
      dashboardViewController.reset();
    }
    System.out.println("root works");
    return dashboardViewController.getRoot();
  }
  private Region loadCreateProjectView(String fxmlFile){
    Region root = null;
    if (createProjectViewController==null){
      try{
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        createProjectViewController = loader.getController();
        createProjectViewController.init(this, model, root);
      }
      catch (Exception e){
        e.printStackTrace();
      }
    }
    else {
      createProjectViewController.reset();
    }
    System.out.println("root works");
    return createProjectViewController.getRoot();
  }
}

