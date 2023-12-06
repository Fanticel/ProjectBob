package Model;

import java.security.PublicKey;
import java.util.ArrayList;

public class ProjectList {
  private ArrayList<Project> projects;

  public ProjectList() {
    projects = new ArrayList<Project>();
  }

  public Project getProject(int index) {
    return projects.get(index);
  }

  public Project getProject(String name) {
    for (Project i : projects) {
      if (i.getName().equals(name)) {
        return i;
      }
    }
    return null;
  }

  public void addProject(Project project) {
    projects.add(project);
  }

  public ProjectList getAllProjectByType(Object obj) {
    ProjectList ans = new ProjectList();
    for (Project i : projects) {
      if (i.getClass().equals(obj)) {
        ans.addProject(i);
      }
    }
    return ans;
  }

  public ProjectList getAllProjectsByData(
      ArrayList<Object> data) { //for search use, the data is 0:type, 1:name, 2:status, 3:price range min, 4:price range max, 5:man-hours min, 6:man-hours max
    for (int i = data.size(); i <= 6; i++) {
      data.add(null);
    }
    ProjectList ans = new ProjectList();
    switch ((Integer) data.get(0)) {
      case 0: {
        ans.projects = getAllProjectByType(
            ResidentialProject.class).returnAsArrayList();
        break;
      }
      case 1: {
        ans.projects = getAllProjectByType(
            CommercialProject.class).returnAsArrayList();
        break;
      }
      case 2: {
        ans.projects = getAllProjectByType(
            RoadProject.class).returnAsArrayList();
        break;
      }
      case 3: {
        ans.projects = getAllProjectByType(
            IndustrialProject.class).returnAsArrayList();
        break;
      }
      default: {
        for (Project i : returnAsArrayList()){ans.addProject(i);}
      }
    }
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
    return ans;
  }

  public void removeProject(Project project) {
    projects.remove(project);
  }

  public void removeProject(int index) {
    projects.remove(index);
  }

  public void removeProject(String name) {
    Project i = getProject(name);
    removeProject(i);
  }

  public ArrayList<Project> returnAsArrayList() {
    return projects;
  }
}
