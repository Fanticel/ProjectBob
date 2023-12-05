package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public interface ProjectListModel
{
  ProjectList getAllProjects();
  Project getProject(int index);
  Project getProject(String name);
  void editProject(Project project, Map<String,Object> data);
  void addProject(ArrayList<Object> data);
  void removeProject(Project project);
  void removeProject(int index);
  void removeProject(String name);
  ProjectList getAllProjectsByType(Object project);
  ProjectList getAllProjectsByData(ArrayList<Object> data);
  Map<String, Optional<Object>> getDefaults(String type);
  public LocalDate getDateMonthsAway(int months);
  void delete(String projectName);
}
