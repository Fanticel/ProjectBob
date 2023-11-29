package Model;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class ProjectListModelManager implements ProjectListModel {
  private ProjectList projectList;
  private FileManagerXML fileManagerXML;

  public ProjectListModelManager() {
    this.projectList = new ProjectList();
    this.fileManagerXML = new FileManagerXML();
  }

  @Override public ProjectList getAllProjects() {
    try {
      return fileManagerXML.readFromFile("placeholder");
    }
    catch (IOException e) {
      System.out.println(e);
    }
    return projectList;
  }

  @Override public Project getProject(int index) {
    return projectList.getProject(index);
  }

  @Override public Project getProject(String name) {
    return projectList.getProject(name);
  }

  @Override public void editProject(int index, Map<String, Object> data) {
    Project project = projectList.getProject(index);
//    edit(project, data);
  }

  @Override public void addProject(ArrayList<Object> data) {
    Project project;
    if (data.size() == 12) {
      project = new ResidentialProject((String) data.get(0),
          (String) data.get(1), (Integer) data.get(2), (Integer) data.get(3),
          Long.parseLong(data.get(4).toString()), (MyDate) data.get(5), (String) data.get(6),
          (Integer) data.get(7), (Integer) data.get(8), (Integer) data.get(9),
          (Integer) data.get(10), (Boolean) data.get(11));
    }
    else if (data.size() == 10) {
      project = new CommercialProject((String) data.get(0),
          (String) data.get(1), (Integer) data.get(2), (Integer) data.get(3),
          Long.parseLong(data.get(4).toString()), (MyDate) data.get(5), (String) data.get(6),
          (Integer) data.get(7), (Integer) data.get(8), (String) data.get(9));
    }
    else if (data.size() == 9) {
      project = new IndustrialProject((String) data.get(0),
          (String) data.get(1), (Integer) data.get(2), (Integer) data.get(3),
          Long.parseLong(data.get(4).toString()), (MyDate) data.get(5), (String) data.get(6),
          (String) data.get(7), (Integer) data.get(8));
    }
    else {
      project = new RoadProject((String) data.get(0), (String) data.get(1),
          (Integer) data.get(2), (Integer) data.get(3),
          Long.parseLong(data.get(4).toString()), (MyDate) data.get(5), (String) data.get(6),
          (Integer) data.get(7), (Integer) data.get(8), (Integer) data.get(9),
          (ArrayList<String>) data.get(10));
    }
    projectList.addProject(project);
  }

  @Override public void removeProject(Project project) {
    projectList.removeProject(project);
  }

  @Override public void removeProject(int index) {
    projectList.removeProject(index);
  }

  @Override public void removeProject(String name) {
    projectList.removeProject(name);
  }

  @Override public ProjectList getAllProjectsByType(Object project) {
    return projectList.getAllProjectByType(project);
  }

  @Override public ProjectList getAllProjectsByData(ArrayList<Object> data) {
    return projectList.getAllProjectsByData(data);
  }

  public void createSampleData() {
    ArrayList<Object> data1 = new ArrayList<>(
        Arrays.asList("Dupa", "Zupa", 2, 3, 4, new MyDate(), "Ongoing", "Kupa", 10));
    addProject(data1);
    addProject(data1);
    addProject(data1);
    ArrayList<Object> data2 = new ArrayList<>(
        Arrays.asList("Dupa2", "Zupa2", 5, 6, 7, new MyDate(), "Ongoing", 11, 12,
            "Kupa2"));
    addProject(data2);
    addProject(data1);
    addProject(data2);
    ArrayList<Object> data3 = new ArrayList<>(
        Arrays.asList("Dupa3", "Zupa3", 8, 9, 10, new MyDate(), "Ongoing", 13, 14, 15,
            new ArrayList<String>(Arrays.asList("Lupa1", "lupa2", "lupa3"))));
    addProject(data1);
    addProject(data2);
    addProject(data3);
  }

  private void edit(Project project, Map<String, Object> data) {
    project.setName((String) data.get("name"));
    project.setDescription((String) data.get("description"));
    project.setExpectedTotalHours((Integer) data.get("expectedTotalHours"));
    project.setExpectedExpenses((Integer) data.get("expectedExpenses"));
    project.setBudget((long) data.get("budget"));
    project.setTimeline((MyDate) data.get("timeline"));

    if (project instanceof ResidentialProject residentialProject) {
      residentialProject.setSize((Integer) data.get("size"));
      residentialProject.setNumKitchens((Integer) data.get("numKitchens"));
      residentialProject.setNumBathrooms((Integer) data.get("numBathrooms"));
      residentialProject.setNewBuild((Boolean) data.get("isNewBuild"));
      residentialProject.setOthWPlumbing((Integer) data.get("othWPlumbing"));
    }
    else if (project instanceof CommercialProject commercialProject) {
      commercialProject.setSize((Integer) data.get("size"));
      commercialProject.setNumFloor((Integer) data.get("numFloor"));
      commercialProject.setIntendedUse((String) data.get("intendedUse"));
    }
    else if (project instanceof IndustrialProject industrialProject) {
      industrialProject.setSize((Integer) data.get("size"));
      industrialProject.setType((String) data.get("type"));
    }
    else {
      RoadProject roadProject = (RoadProject) project;
      roadProject.setLength((Integer) data.get("length"));
      roadProject.setWidth((Integer) data.get("width"));
      roadProject.setgeoChallenge((ArrayList<String>) data.get("geoChallenge"));
      roadProject.setnumBridTun((Integer) data.get("numBridTun"));
    }
  }
}
