package Model;

import java.util.ArrayList;

public class ProjectList {
  private ArrayList<Project> projects;
  public ProjectList(){
    projects = new ArrayList<Project>();
  }
  public Project getProject(int index){
    return projects.get(index);
  }
  public Project getProject(String name){
    for (Project i : projects) {
      if (i.getName().equals(name)) {
        return i;
      }
    }
    return null;
  }
}
