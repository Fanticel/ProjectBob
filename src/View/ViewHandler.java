package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import Model.ProjectListModel;

public class ViewHandler {
  private Scene currentScene;
  private Scene popupScene;
  private Stage primaryStage;
  private Stage popupStage;
  private ProjectListModel model;
  private ViewState viewState;
  private HomeViewController homeViewController;
  private ProjectListViewController projectListViewController;
  private DashboardViewController dashboardViewController;
  private CreateProjectViewController createProjectViewController;
  private SearchAProjectPopupViewController searchAProjectPopupViewController;
  private EditProjectViewController editProjectViewController;

  public ViewHandler(ProjectListModel model) {
    this.currentScene = new Scene(new Region());
    this.popupScene = new Scene(new Region());
    this.model = model;
    viewState = new ViewState();
  }

  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    openView("ProjectList");
    System.out.println("Starting view.");
  }
  public void startPopup(Stage popupStage){
    this.popupStage = popupStage;
    System.out.println("Starting popupView");
  }

  public void openView(String id) {
    Region root = loadHomeView("HomeView.fxml");
    switch (id) {
      case "ProjectList" -> root = loadProjectListView("ProjectListView.fxml");
      case "Dashboard" -> root = loadDashboardView("DashboardView.fxml");
      case "CreateProject" -> root = loadCreateProjectView("CreateProjectView.fxml");
      case "EditProject" -> root = loadEditProjectView("EditProjectView.fxml");

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
  public void closePopupView(){
    popupStage.close();
  }
  public void openPopupView() {
    startPopup(new Stage());
    Region root = loadSearchAProjectPopupView("SearchAProjectPopupView.fxml");
    popupScene.setRoot(root);
    String title = "";
    if (root.getUserData() != null) {
      title += root.getUserData();
    }
    popupStage.setTitle(title);
    popupStage.setScene(popupScene);
    popupStage.setWidth(root.getPrefWidth());
    popupStage.setHeight(root.getPrefHeight());
    popupStage.show();
    System.out.println("Open view works.");
  }
  public void searchHelper(){
    projectListViewController.search();
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
        projectListViewController.init(this, model, root, viewState);
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
        createProjectViewController.init(this, model, root, viewState);
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
  private Region loadEditProjectView(String fxmlFile){
    Region root = null;
    if (editProjectViewController==null){
      try{
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        editProjectViewController = loader.getController();
        editProjectViewController.init(this, model, root, viewState);
      }
      catch (Exception e){
        e.printStackTrace();
      }
    }
    else {
      editProjectViewController.reset();
    }
    System.out.println("Edit root works");
    return editProjectViewController.getRoot();
  }
  private Region loadSearchAProjectPopupView(String fxmlFile){
    Region root = null;
    if (searchAProjectPopupViewController==null){
      try{
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        searchAProjectPopupViewController = loader.getController();
        searchAProjectPopupViewController.init(this, model, root, viewState);
      }
      catch (Exception e){
        e.printStackTrace();
      }
    }
    else {
      searchAProjectPopupViewController.reset();
    }
    System.out.println("root works");
    return searchAProjectPopupViewController.getRoot();
  }
}

