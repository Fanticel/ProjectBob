package View;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class EmbeddedRoadProjectViewController extends EmbeddedViewsController{

  @FXML
  private VBox geoChallengeVBox;

  @FXML
  private TextField lengthField;

  @FXML
  private TextField numBridTunField;

  @FXML
  private TextField widthField;
  @FXML private Button addButton;

  public EmbeddedRoadProjectViewController()
  {
    super();
  }

  public void init(ViewHandler viewHandler, ProjectListModel model, Region root, ViewState viewState)
  {
    super.init(viewHandler, model, root, viewState);
    lengthField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    widthField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    numBridTunField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
  }

  public void reset(){
    Map<String, Optional<Object>> defaults = getModel().getDefaults("Road");
    super.reset(defaults);
    setField("length",lengthField, defaults);
    setField("width",widthField, defaults);
    setField("numBridTun",numBridTunField, defaults);
    
    geoChallengeVBox.getChildren().removeAll(geoChallengeVBox.getChildren());
    if (defaults.get("geoChallenge").isPresent())
    {
      geoChallengeVBox.getChildren().add(new TextField(defaults.get("geoChallenge").get().toString()));
    }
  }

  public void editReset(){
    if (lengthField.getText() == null || widthField.getText() == null || numBridTunField.getText() == null){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    if (lengthField.getText().equals("") || widthField.getText().equals("") || numBridTunField.getText().equals("")){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    RoadProject project;
    project = (RoadProject) getModel().getProject((String) getViewState().getData().get(0));
    super.editReset();
    lengthField.setText(String.valueOf(project.getLength()));
    widthField.setText(String.valueOf(project.getWidth()));
    numBridTunField.setText(String.valueOf(project.getnumBridTun()));
    ArrayList<String> geoChallenges = project.getgeoChallenge();

    geoChallengeVBox.getChildren().removeAll(geoChallengeVBox.getChildren());
    for (int i = 0; i < geoChallenges.size(); i++){
      getScrollAnchorPane().setPrefHeight(getScrollAnchorPane().getPrefHeight() + 20);
      geoChallengeVBox.getChildren().add(new TextField(geoChallenges.get(i)));
    }
    geoChallengeVBox.getChildren().forEach(node -> {
      TextField textField = (TextField) node;
      textField.setEditable(true);
    });
    setEditable(true);
    lengthField.setEditable(true);
    widthField.setEditable(true);
    numBridTunField.setEditable(true);
    addButton.setDisable(false);
  }
  public void detailsReset(){
    if (lengthField.getText() == null || widthField.getText() == null || numBridTunField.getText() == null){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    if (lengthField.getText().equals("") || widthField.getText().equals("") || numBridTunField.getText().equals("")){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    RoadProject project;
    project = (RoadProject) getModel().getProject((String) getViewState().getData().get(0));
    super.editReset();
    lengthField.setText(String.valueOf(project.getLength()));
    widthField.setText(String.valueOf(project.getWidth()));
    numBridTunField.setText(String.valueOf(project.getnumBridTun()));
    ArrayList<String> geoChallenges = project.getgeoChallenge();
    geoChallengeVBox.getChildren().removeAll(geoChallengeVBox.getChildren());
    for (int i = 0; i < geoChallenges.size(); i++){
      getScrollAnchorPane().setPrefHeight(getScrollAnchorPane().getPrefHeight() + 20);
      geoChallengeVBox.getChildren().add(new TextField(geoChallenges.get(i)));
    }
    geoChallengeVBox.getChildren().forEach(node -> {
      TextField textField = (TextField) node;
      textField.setEditable(false);
    });
    setEditable(false);
    lengthField.setEditable(false);
    widthField.setEditable(false);
    numBridTunField.setEditable(false);
    addButton.setDisable(true);
  }

  @FXML private void addField(){
    geoChallengeVBox.getChildren().add(new TextField());
    getScrollAnchorPane().setPrefHeight(getScrollAnchorPane().getPrefHeight() + 20);
  }
  public void create(){
    if (lengthField.getText() == null || widthField.getText() == null || numBridTunField.getText() == null){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    if (lengthField.getText().equals("") || widthField.getText().equals("") || numBridTunField.getText().equals("")){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    ArrayList<Object> data = getViewState().getData();
    super.create(data);
    ArrayList<String> geoChallenges = new ArrayList<String>();
    data.add(Integer.valueOf(lengthField.getText().replace(",","")));
    data.add(Integer.valueOf(widthField.getText().replace(",","")));
    data.add(Integer.valueOf(numBridTunField.getText().replace(",","")));

    for (Node node : geoChallengeVBox.getChildren()){
      if (!((TextField)node).getText().equals(""))
      {
        geoChallenges.add(((TextField) node).getText());
      }
    }
    if (geoChallenges.size() == 0){
      geoChallenges.add("none");
    }
    data.add(geoChallenges);
    geoChallengeVBox.getChildren().removeAll(geoChallengeVBox.getChildren());
    getModel().addProject(data);
  }

  public void edit(Project project){
    Map<String,Object> data = (Map<String,Object>) getViewState().getData().get(1);
    ArrayList<String> geoChallenges = new ArrayList<String>();
    super.edit(data);
    data.put("length", lengthField.getText().replace(",",""));
    data.put("width", widthField.getText().replace(",",""));
    data.put("numBridTun", numBridTunField.getText().replace(",",""));
    int i = 0;
    for (Node node : geoChallengeVBox.getChildren()){
      if (!((TextField)node).getText().equals(""))
      {
        geoChallenges.add(((TextField) node).getText());
      }
    }
    if (geoChallenges.size() == 0){
      geoChallenges.add("none");
    }
    data.put("geoChallenge", geoChallenges);
    getModel().editProject(project, data);
  }
}

