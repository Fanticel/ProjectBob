package View;

import Model.CommercialProject;
import Model.Project;
import Model.ProjectListModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Region;
import javafx.util.converter.NumberStringConverter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;


public class EmbeddedCommercialProjectViewController extends EmbeddedViewsController{

  @FXML
  private TextArea intendedUseArea;

  @FXML
  private TextField numFloorsField;

  @FXML
  private TextField sizeField;



  public EmbeddedCommercialProjectViewController()
  {
    super();
  }

  //Initiates the view and applies a filter to text fields, so they only accept numerical values.
  public void init(ViewHandler viewHandler, ProjectListModel model, Region root, ViewState viewState)
  {
    super.init(viewHandler, model, root, viewState);
    sizeField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
    numFloorsField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
  }

  // sets the default values when creating projects
  public void reset(){
    Map<String, Optional<Object>> defaults = getModel().getDefaults("Commercial");
    super.reset(defaults);
    setField("size",sizeField, defaults);
    setField("numFloor",numFloorsField, defaults);

    if (defaults.get("intendedUse").isPresent()){
      intendedUseArea.setText(defaults.get("intendedUse").get().toString());
    }
    else {
      intendedUseArea.setText(null);
    }
  }

  //gets the existing project values and applies displays them in text fields when editing projects
  public void editReset(){
    CommercialProject project;
    project = (CommercialProject) getModel().getProject((String) getViewState().getData().get(0));
    super.editReset();
    sizeField.setText(String.valueOf(project.getSize()));
    numFloorsField.setText(String.valueOf(project.getNumFloor()));
    intendedUseArea.setText(String.valueOf(project.getIntendedUse()));
    setEditable(true);
    intendedUseArea.setEditable(true);
    numFloorsField.setEditable(true);
    sizeField.setEditable(true);
  }
  public void detailsReset(){
    CommercialProject project;
    project = (CommercialProject) getModel().getProject((String) getViewState().getData().get(0));
    super.editReset();
    sizeField.setText(String.valueOf(project.getSize()));
    numFloorsField.setText(String.valueOf(project.getNumFloor()));
    intendedUseArea.setText(String.valueOf(project.getIntendedUse()));
    setEditable(false);
    intendedUseArea.setEditable(false);
    numFloorsField.setEditable(false);
    sizeField.setEditable(false);
  }

  //Checks if any fields are empty and throws a corresponding message.
  //Gets the values from fields and stores it in an arrayList and sends it to the model to create the project.
  public void create(){
    if (sizeField.getText() == null || numFloorsField.getText() == null || intendedUseArea.getText() == null){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    if (sizeField.getText().equals("") || numFloorsField.getText().equals("") || intendedUseArea.getText().equals("")){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    ArrayList<Object> data = getViewState().getData();
    super.create(data);
    data.add(Integer.valueOf(sizeField.getText().replace(",","").replace(".","")));
    data.add(Integer.valueOf(numFloorsField.getText().replace(",","").replace(".","")));
    data.add(intendedUseArea.getText());

    getModel().addProject(data);
  }
  //Checks if any fields are empty and throws a corresponding message.
  //Gets the values from fields, stores it in a map and sends it with the given project to the model.
  public void edit(Project project){
    if (sizeField.getText() == null || numFloorsField.getText() == null || intendedUseArea.getText() == null){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    if (sizeField.getText().equals("") || numFloorsField.getText().equals("") || intendedUseArea.getText().equals("")){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    Map<String,Object> data = (Map<String,Object>) getViewState().getData().get(1);
    super.edit(data);
    data.put("size", sizeField.getText().replace(",","").replace(".",""));
    data.put("numFloor", numFloorsField.getText().replace(",","").replace(".",""));
    data.put("intendedUse", intendedUseArea.getText());

    getModel().editProject(project, data);
  }

}
