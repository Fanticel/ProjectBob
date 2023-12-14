//Made by Zygmunt Kwaśniewicz and Alan Karasin Stifter
package View;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.util.converter.NumberStringConverter;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class EmbeddedResidentialProjectViewController extends EmbeddedViewsController
{
  @FXML
  private RadioButton buildRButton;

  @FXML
  private ToggleGroup newBuildGroup;

  @FXML
  private TextField numBathroomsField;

  @FXML
  private TextField numKitchensField;

  @FXML
  private TextField othWPlumbingField;

  @FXML
  private RadioButton renovationRButton;

  @FXML
  private TextField sizeField;

  //Alan Karasin Stifter
  public EmbeddedResidentialProjectViewController()
  {
    super();
  }

  //Alan Karasin Stifter
  //Initiates the view and applies a filter to text fields, so they only accept numerical values.
  public void init(ViewHandler viewHandler, ProjectListModel model, Region root, ViewState viewState)
  {
    super.init(viewHandler, model, root, viewState);
    sizeField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    numKitchensField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    numBathroomsField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    othWPlumbingField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
  }

  //Alan Karasin Stifter
  // sets the default values when creating projects
  public void reset(){
    Map<String, Optional<Object>> defaults = getModel().getDefaults("Residential");
    super.reset(defaults);
    setField("size",sizeField, defaults);
    setField("numKitchens",numKitchensField, defaults);
    setField("numBathrooms",numBathroomsField, defaults);
    setField("othWPlumbing",othWPlumbingField, defaults);

    if (defaults.get("isNewBuild").isPresent()){
      if (defaults.get("isNewBuild").get().toString().equals("new build")){
        newBuildGroup.selectToggle(buildRButton);
      }else newBuildGroup.selectToggle(renovationRButton);
    }

  }
  //Alan Karasin Stifter
  //gets the existing project values and displays them in text fields when editing projects
  public void editReset(){
    ResidentialProject project;
    project = (ResidentialProject) getModel().getProject((String) getViewState().getData().get(0));
    super.editReset();
    sizeField.setText(String.valueOf(project.getSize()));
    numKitchensField.setText(String.valueOf(project.getNumKitchens()));
    numBathroomsField.setText(String.valueOf(project.getNumBathrooms()));
    othWPlumbingField.setText(String.valueOf(project.getOthWPlumbing()));
    if (project.isNewBuild()){
      newBuildGroup.selectToggle(buildRButton);
    }else newBuildGroup.selectToggle(renovationRButton);
    setEditable(true);
    numKitchensField.setEditable(true);
    numBathroomsField.setEditable(true);
    othWPlumbingField.setEditable(true);
    newBuildGroup.getToggles().forEach(toggle -> {
      Node node = (Node) toggle;
      node.setDisable(false);
    });
  }
  //Zygmunt Kwaśniewicz
  //gets the existing project values and displays them in text fields when viewing details of projects
  public void detailsReset(){
    ResidentialProject project;
    project = (ResidentialProject) getModel().getProject((String) getViewState().getData().get(0));
    super.editReset();
    sizeField.setText(String.valueOf(project.getSize()));
    numKitchensField.setText(String.valueOf(project.getNumKitchens()));
    numBathroomsField.setText(String.valueOf(project.getNumBathrooms()));
    othWPlumbingField.setText(String.valueOf(project.getOthWPlumbing()));
    if (project.isNewBuild()){
      newBuildGroup.selectToggle(buildRButton);
    }else newBuildGroup.selectToggle(renovationRButton);
    setEditable(false);
    numKitchensField.setEditable(false);
    numBathroomsField.setEditable(false);
    othWPlumbingField.setEditable(false);
    newBuildGroup.getToggles().forEach(toggle -> {
      Node node = (Node) toggle;
      node.setDisable(true);
    });
  }
  //Alan Karasin Stifter
  //Checks if any fields are empty and throws a corresponding message.
  //Gets the values from fields and stores it in an arrayList and sends it to the model to create the project.
  public void create(){
    if (sizeField.getText() == null || numBathroomsField.getText() == null || numKitchensField.getText() == null || othWPlumbingField.getText() == null){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    if (sizeField.getText().equals("") || numBathroomsField.getText().equals("") || numKitchensField.getText().equals("") || othWPlumbingField.getText().equals("")){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    ArrayList<Object> data = getViewState().getData();
    super.create(data);
    data.add(Integer.valueOf(sizeField.getText().replace(",","").replace(".","")));
    data.add(Integer.valueOf(numKitchensField.getText().replace(",","").replace(".","")));
    data.add(Integer.valueOf(numBathroomsField.getText().replace(",","").replace(".","")));
    data.add(Integer.valueOf(othWPlumbingField.getText().replace(",","").replace(".","")));

    if (newBuildGroup.getSelectedToggle() == buildRButton)
      data.add( true);
    else data.add( false);

    getModel().addProject(data);
  }
  //Alan Karasin Stifter
  //Checks if any fields are empty and throws a corresponding message.
  //Gets the values from fields, stores it in a map and sends it with the given project to the model.
  public void edit(Project project){
    if (sizeField.getText() == null || numBathroomsField.getText() == null || numKitchensField.getText() == null || othWPlumbingField.getText() == null){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    if (sizeField.getText().equals("") || numBathroomsField.getText().equals("") || numKitchensField.getText().equals("") || othWPlumbingField.getText().equals("")){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    Map<String,Object> data = (Map<String,Object>) getViewState().getData().get(1);
    super.edit(data);
    data.put("size", sizeField.getText().replace(",","").replace(".",""));
    data.put("numKitchens", numKitchensField.getText().replace(",","").replace(".",""));
    data.put("numBathrooms", numBathroomsField.getText().replace(",","").replace(".",""));
    data.put("othWPlumbing", othWPlumbingField.getText().replace(",","").replace(".",""));
    if (newBuildGroup.getSelectedToggle() == buildRButton)
      data.put("isNewBuild", true);
    else data.put("isNewBuild", false);
    getModel().editProject(project, data);
  }

}