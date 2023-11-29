package Model;

import java.util.ArrayList;
import java.util.Map;

public interface ProjectListModel
{
  ProjectList getAllProjects();
  Project getProject(int index);
  Project getProject(String name);
  void editProject(int index, Map<String,Object> data);
  void addProject(ArrayList<Object> data);
  void removeProject(Project project);
  void removeProject(int index);
  void removeProject(String name);
  ProjectList getAllProjectsByType(Object project);
  ProjectList getAllProjectsByData(ArrayList<Object> data);
  void reset();
}
