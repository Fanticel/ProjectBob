package View;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.converter.NumberStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

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

  public EmbeddedResidentialProjectViewController()
  {
    super();
  }

  public void init(ViewHandler viewHandler, ProjectListModel model, Region root, ViewState viewState)
  {
    super.init(viewHandler, model, root, viewState);
    sizeField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    numKitchensField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    numBathroomsField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    othWPlumbingField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
  }

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

  }
  public void create(){
    if (sizeField.getText() == null || numBathroomsField.getText() == null || numKitchensField.getText() == null || othWPlumbingField.getText() == null){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    if (sizeField.getText().equals("") || numBathroomsField.getText().equals("") || numKitchensField.getText().equals("") || othWPlumbingField.getText().equals("")){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    ArrayList<Object> data = getViewState().getData();
    super.create(data);
    data.add(Integer.valueOf(sizeField.getText().replace(",","")));
    data.add(Integer.valueOf(numKitchensField.getText().replace(",","")));
    data.add(Integer.valueOf(numBathroomsField.getText().replace(",","")));
    data.add(Integer.valueOf(othWPlumbingField.getText().replace(",","")));

    if (newBuildGroup.getSelectedToggle() == buildRButton)
      data.add( true);
    else data.add( false);

    getModel().addProject(data);
  }
  public void edit(Project project){
    if (sizeField.getText() == null || numBathroomsField.getText() == null || numKitchensField.getText() == null || othWPlumbingField.getText() == null){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    if (sizeField.getText().equals("") || numBathroomsField.getText().equals("") || numKitchensField.getText().equals("") || othWPlumbingField.getText().equals("")){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    Map<String,Object> data = (Map<String,Object>) getViewState().getData().get(1);
    super.edit(data);
    data.put("size", sizeField.getText().replace(",",""));
    data.put("numKitchens", numKitchensField.getText().replace(",",""));
    data.put("numBathrooms", numBathroomsField.getText().replace(",",""));
    data.put("othWPlumbing", othWPlumbingField.getText().replace(",",""));
    if (newBuildGroup.getSelectedToggle() == buildRButton)
      data.put("isNewBuild", true);
    else data.put("isNewBuild", false);
    getModel().editProject(project, data);
  }

}