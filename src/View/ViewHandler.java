package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.image.Image;
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
  private DeletePopupViewController deletePopupViewController;
  private ProjectDetailsViewController projectDetailsViewController;
  private CreationNotificationPopupViewController creationNotificationPopupViewController;

  public ViewHandler(ProjectListModel model) {
    this.currentScene = new Scene(new Region());
    this.popupScene = new Scene(new Region());
    this.model = model;
    viewState = new ViewState();
  }

  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    openView("");
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
      case "ProjectDetails" -> root = loadProjectDetailsView("ProjectDetails.fxml");
    }
    currentScene.setRoot(root);
    String title = "";
    if (root.getUserData() != null) {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    Image icon = new Image("Images/IconOrange.png");
    primaryStage.getIcons().add(icon);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
    System.out.println("Open view works.");
  }
  public void closePopupView(){
    popupStage.close();
  }
  public void openPopupView(String id) {
    startPopup(new Stage());
    Region root = null;
    switch (id) {
      case "Search" -> root = loadSearchAProjectPopupView("SearchAProjectPopupView.fxml");
      case "Delete" -> root = loadDeletePopupViewController("DeletePopupView.fxml");
      case "Creation" -> root = loadCreationNotificationPopupViewController("CreationNotificationPopupView.fxml");
    }
    popupScene.setRoot(root);
    String title = "";
    if (root.getUserData() != null) {
      title += root.getUserData();
    }
    popupStage.setTitle(title);
    popupStage.setScene(popupScene);
    Image icon = new Image("Images/IconOrange.png");
    popupStage.getIcons().add(icon);
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
  private Region loadProjectDetailsView(String fxmlFile){
    Region root = null;
    if (projectDetailsViewController==null){
      try{
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        projectDetailsViewController = loader.getController();
        projectDetailsViewController.init(this, model, root, viewState);
      }
      catch (Exception e){
        e.printStackTrace();
      }
    }
    else {
      projectDetailsViewController.reset();
    }
    System.out.println("Edit root works");
    return projectDetailsViewController.getRoot();
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
  private Region loadDeletePopupViewController(String fxmlFile){
    Region root = null;
    if (deletePopupViewController==null){
      try{
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        deletePopupViewController = loader.getController();
        deletePopupViewController.init(this, model, root, viewState);
      }
      catch (Exception e){
        e.printStackTrace();
      }
    }
    else {
      deletePopupViewController.reset();
    }
    System.out.println("Delete root works");
    return deletePopupViewController.getRoot();
  }
  private Region loadCreationNotificationPopupViewController(String fxmlFile){
    Region root = null;
    if (creationNotificationPopupViewController==null){
      try{
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        creationNotificationPopupViewController = loader.getController();
        creationNotificationPopupViewController.init(this, model, root, viewState);
      }
      catch (Exception e){
        e.printStackTrace();
      }
    }
    else {
      creationNotificationPopupViewController.reset();
    }
    System.out.println("Creation root works");
    return creationNotificationPopupViewController.getRoot();
  }
}

