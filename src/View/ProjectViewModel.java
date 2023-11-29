package View;

import Model.Project;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProjectViewModel {
  private StringProperty nameProperty;
  private StringProperty typeProperty;
  private StringProperty statusProperty;

  public ProjectViewModel(Project project) {
    nameProperty = new SimpleStringProperty(project.getName());
    typeProperty = new SimpleStringProperty(
        switch (project.getClass().toString()){
          case ("class Model.CommercialProject") -> "Commercial";
          case ("class Model.IndustrialProject") -> "Industrial";
          case ("class Model.RoadProject") -> "Road";
          case ("class Model.ResidentialProject") -> "Residential";
          default -> "Error";
        }
    );
    statusProperty = new SimpleStringProperty(project.getStatus());
  }

  public StringProperty namePropertyProperty() {
    return nameProperty;
  }

  public StringProperty typePropertyProperty() {
    return typeProperty;
  }

  public StringProperty statusPropertyProperty() {
    return statusProperty;
  }
}
