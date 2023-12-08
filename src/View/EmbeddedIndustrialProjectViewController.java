package View;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
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

public class EmbeddedIndustrialProjectViewController extends EmbeddedViewsController{

  @FXML
  private TextField facilityTypeField;

  @FXML
  private TextField sizeField;

  public EmbeddedIndustrialProjectViewController()
  {
    super();
  }

  public void init(ViewHandler viewHandler, ProjectListModel model, Region root, ViewState viewState)
  {
    super.init(viewHandler, model, root, viewState);
    sizeField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(),0, filter));
  }

  public void reset(){
    Map<String, Optional<Object>> defaults = getModel().getDefaults("Industrial");
    super.reset(defaults);
    setField("size",sizeField, defaults);
    setField("type",facilityTypeField, defaults);


  }

  public void editReset(){
    IndustrialProject project;
    project = (IndustrialProject) getModel().getProject((String) getViewState().getData().get(0));
    super.editReset();
    sizeField.setText(String.valueOf(project.getSize()));
    facilityTypeField.setText(project.getType());
  }

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
    data.add(Integer.valueOf(sizeField.getText().replace(",","")));

    getModel().addProject(data);
  }

  public void edit(Project project){
    if (sizeField.getText() == null || facilityTypeField.getText() == null){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    if (sizeField.getText().equals("") || facilityTypeField.getText().equals("")){
      throw new IllegalArgumentException("Fields cannot be empty");
    }
    Map<String,Object> data = (Map<String,Object>) getViewState().getData().get(1);
    super.edit(data);
    data.put("size", sizeField.getText().replace(",",""));
    data.put("type", facilityTypeField.getText());

    getModel().editProject(project, data);
  }

}
