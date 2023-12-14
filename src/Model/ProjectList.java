package Model;

import java.util.ArrayList;

public class ProjectList { //The class done by Zygmunt Kwaśniewicz
  private ArrayList<Project> projects;

  public ProjectList() {
    projects = new ArrayList<Project>(); //creating a new arraylist of type project everytime the constructor is called
  }

  public Project getProject(int index) {
    return projects.get(index); //if we have the index of the project, we can simply use .get
  }

  public Project getProject(String name) { //to get by name we need to check through all projects in the project arrayList then return the first one that matches
    for (Project i : projects) {
      if (i.getName().equals(name)) {
        return i;
      }
    }
    return null; //if there are no matches return null
  }

  public void addProject(Project project) {
    projects.add(project); //simple add
  }

  public ProjectList getAllProjectsByType(Object obj) {
    ProjectList ans = new ProjectList(); //The obj is supposed to be for example 'RoadProject.class'
    for (Project i : projects) { //go through each project
      if (i.getClass().equals(obj)) { //if the class of the project equals the passed class, add it to the answer projectList
        ans.addProject(i);
      }
    }
    return ans; //return the projectList
  }

  public ProjectList getAllProjectsByData(
      ArrayList<Object> data) { //for search use, the data is 0:type, 1:name, 2:status, 3:price range min, 4:price range max, 5:man-hours min, 6:man-hours max
    for (int i = data.size(); i <= 6; i++) { //if the data passed is incomplete, we assume everything else is null
      data.add(null);
    }
    ProjectList ans = new ProjectList();
    switch ((Integer) data.get(0)) { //the first part of the data arrayList is the type so we can getByType using the knowledge
      case 0: {
        ans.projects = getAllProjectsByType(
            ResidentialProject.class).returnAsArrayList();
        break;
      }
      case 1: {
        ans.projects = getAllProjectsByType(
            CommercialProject.class).returnAsArrayList();
        break;
      }
      case 2: {
        ans.projects = getAllProjectsByType(
            RoadProject.class).returnAsArrayList();
        break;
      }
      case 3: {
        ans.projects = getAllProjectsByType(
            IndustrialProject.class).returnAsArrayList();
        break;
      }
      default: {
        for (Project i : returnAsArrayList()){ans.addProject(i);} //by default get all projects
      }
    } //it all works the same way for all data.get(x); if it is present (not null) simply check if the desired part of the project
    // is different from the data passed in the arraylist, if it is different remove it from the answer arrayList.
    // this way we can simply at the return the whole arrayList knowing that everything that didn't match the data given got removed.
    if (data.get(1) != null) {
      for (int i = 0; i < ans.returnAsArrayList().size(); i++) {
        if (!ans.returnAsArrayList().get(i).getName().toLowerCase()
            .contains(data.get(1).toString().toLowerCase())) {
          ans.returnAsArrayList().remove(i);
          i--;
        }
      }
    }
    if (data.get(2) != null) {
      for (int i = 0; i < ans.returnAsArrayList().size(); i++) {
        if (!ans.returnAsArrayList().get(i).getStatus()
            .equals(data.get(2).toString())) {
          ans.returnAsArrayList().remove(i);
          i--;
        }
      }
    }
    if (data.get(3) != null) {
      for (int i = 0; i < ans.returnAsArrayList().size(); i++) {
        if (ans.returnAsArrayList().get(i).getExpectedExpenses()
            < Integer.parseInt(data.get(3).toString())) {
          ans.returnAsArrayList().remove(i);
          i--;
        }
      }
    }
    if (data.get(4) != null) {
      for (int i = 0; i < ans.returnAsArrayList().size(); i++) {
        if (ans.returnAsArrayList().get(i).getExpectedExpenses()
            > Integer.parseInt(data.get(4).toString())) {
          ans.returnAsArrayList().remove(i);
          i--;
        }
      }
    }
    if (data.get(5) != null) {
      for (int i = 0; i < ans.returnAsArrayList().size(); i++) {
        if (ans.returnAsArrayList().get(i).getExpectedTotalHours()
            < Integer.parseInt(data.get(5).toString())) {
          ans.returnAsArrayList().remove(i);
          i--;
        }
      }
    }
    if (data.get(6) != null) {
      for (int i = 0; i < ans.returnAsArrayList().size(); i++) {
        if (ans.returnAsArrayList().get(i).getExpectedTotalHours()
            > Integer.parseInt(data.get(6).toString())) {
          ans.returnAsArrayList().remove(i);
          i--;
        }
      }
    }
    return ans; //return the arraylist.
  }

  public void removeProject(Project project) {
    projects.remove(project); //simply remove a project
  }

  public void removeProject(int index) {
    projects.remove(index); //remove by index
  }

  public void removeProject(String name) {
    removeProject(getProject(name)); //get project by name, then call removeProject(Project)
  }

  public ArrayList<Project> returnAsArrayList() {
    return projects; //return the ProjectList, as an arrayList, very useful.
  }
}
