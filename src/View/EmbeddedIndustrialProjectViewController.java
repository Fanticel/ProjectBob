//Made by Zygmunt Kwaśniewicz and Alan Karasin Stifter
package View;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Region;
import javafx.util.converter.NumberStringConverter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class EmbeddedIndustrialProjectViewController extends EmbeddedViewsController{

  @FXML
  private TextField facilityTypeField;

  @FXML
  private TextField sizeField;

  public EmbeddedIndustrialProjectViewController()
  {
    super();
  }
  //Alan Karasin Stifter
  //Initiates the view and applies a filter to text fields, so they only accept numerical values.
  public void init(ViewHandler viewHandler, ProjectListModel model, Region root, ViewState viewState)
  {
    super.init(viewHandler, model, root, viewState);
    sizeField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
  }
  //Alan Karasin Stifter
  // sets the default values when creating projects
  public void reset(){
    Map<String, Optional<Object>> defaults = getModel().getDefaults("Industrial");
    super.reset(defaults);
    setField("size",sizeField, defaults);
    setField("type",facilityTypeField, defaults);


  }
  //Alan Karasin Stifter
  //gets the existing project values and displays them in text fields when editing projects
  public void editReset(){
    IndustrialProject project;
    project = (IndustrialProject) getModel().getProject((String) getViewState().getData().get(0));
    super.editReset();
    sizeField.setText(String.valueOf(project.getSize()));
    facilityTypeField.setText(project.getType());
  }
  //Zygmunt Kwaśniewicz
  //gets the existing project values and displays them in text fields when viewing details of projects
  public void detailsReset(){
    IndustrialProject project;
    project = (IndustrialProject) getModel().getProject((String) getViewState().getData().get(0));
    super.editReset();
    sizeField.setText(String.valueOf(project.getSize()));
    facilityTypeField.setText(project.getType());
    setEditable(false);
    facilityTypeField.setEditable(false);
    sizeField.setEditable(false);
    facilityTypeField.setEditable(false);
  }
  //Alan Karasin Stifter
  //Checks if any fields are empty and throws a corresponding message.
  //Gets the values from fields and stores it in an arrayList and sends it to the model to create the project.
  public void create(){
    if (sizeField.getText() == null || facilityTypeField.getText() == null){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    if (sizeField.getText().equals("") || facilityTypeField.getText().equals("")){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    ArrayList<Object> data = getViewState().getData();
    super.create(data);
    data.add(facilityTypeField.getText());
    data.add(Integer.valueOf(sizeField.getText().replace(",","").replace(".","")));

    getModel().addProject(data);
  }
  //Alan Karasin Stifter
  //Checks if any fields are empty and throws a corresponding message.
  //Gets the values from fields, stores it in a map and sends it with the given project to the model.
  public void edit(Project project){
    if (sizeField.getText() == null || facilityTypeField.getText() == null){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    if (sizeField.getText().equals("") || facilityTypeField.getText().equals("")){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    Map<String,Object> data = (Map<String,Object>) getViewState().getData().get(1);
    super.edit(data);
    data.put("size", sizeField.getText().replace(",","").replace(".",""));
    data.put("type", facilityTypeField.getText());

    getModel().editProject(project, data);
  }

}
