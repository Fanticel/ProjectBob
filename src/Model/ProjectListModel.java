package Model;

import java.util.ArrayList;

public interface ProjectListModel
{
  ProjectList getAllProjects();
  Project getProject(int index);
  Project getProject(String name);
  void editProject(int index, ArrayList<Object> data);
  void editProjectByName(String name, ArrayList<Object> data);
  void addProject(ArrayList<Object> data);
  void removeProject(Project project);
  void removeProject(int index);
  void removeProject(String name);
  ProjectList getAllProjectsByType(Object project);
  ProjectList getAllProjectsByData(ArrayList<Object> data);
}
