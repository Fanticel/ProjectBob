package Model;

import java.security.PublicKey;
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
  public void addProject(Project project){
    projects.add(project);
  }
  public ProjectList getAllProjectByType(Object obj){
    ProjectList ans = new ProjectList();
    for (Project i : projects) {
      if (i.getClass().equals(obj)){
        ans.addProject(i);
      }
    }
    return ans;
  }
  public ProjectList getAllProjectsByData(ArrayList<Object> data){
    ProjectList ans = new ProjectList();
    int PType = Integer.parseInt(data.get(0).toString());
    switch (PType){
      case 0 : {
        ArrayList<ResidentialProject> residentialProjectsAL = (ArrayList<ResidentialProject>) ((ArrayList<?>)getAllProjectByType(ResidentialProject.class).returnAsArrayList());
        break;
      }
      case 1 : {
        break;
      }
      case 2 : {
        break;
      }
      case 3 : {

      }
    }
    return ans;
  }
  public void removeProject(Project project){
    projects.remove(project);
  }
  public void removeProject(int index){
    projects.remove(index);
  }
  public void removeProject(String name){
    Project i = getProject(name);
    removeProject(i);
  }
  public ArrayList<Project> returnAsArrayList(){
    return projects;
  }
}
