package Model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public class ProjectListModelManager implements ProjectListModel {
  private ProjectList projectList;
  private FileManagerXML fileManagerXML;
  //Alan Karasin Stifter
  public ProjectListModelManager() {
    this.projectList = new ProjectList();
    this.fileManagerXML = new FileManagerXML();
  }
  //Alan Karasin Stifter
  //reads from save file and returns a projectList with all projects
  @Override public ProjectList getAllProjects() {
    try {
      return fileManagerXML.readFromFile("Save.xml");
    }
    catch (IOException e) {
      System.out.println(e);
    }
    return projectList;
  }
  //Alan Karasin Stifter
  @Override public Project getProject(int index) {
    return projectList.getProject(index);
  }
  //Alan Karasin Stifter
  @Override public Project getProject(String name) {
    return projectList.getProject(name);
  }

  //Alan Karasin Stifter
  //Checks if the new name is unique and throws a corresponding message if it isn´t.
  //Gets values from the given map and updates corresponding values in the given project and save file.
  @Override public void editProject(Project project, Map<String, Object> data)
  {
    try
    {
      if (!project.getName().equals(data.get("name")) && projectList.getProject((String) data.get("name")) != null){
        throw new IllegalArgumentException("Name has to be unique");
      }
      project.setName((String) data.get("name"));
      project.setDescription((String) data.get("description"));
      project.setExpectedTotalHours(Integer.parseInt(data.get("expectedTotalHours").toString()));
      project.setExpectedExpenses(Integer.parseInt(data.get("expectedExpenses").toString()));
      project.setTotalHours(Integer.parseInt(data.get("totalHours").toString()));
      project.setExpenses(Integer.parseInt(data.get("expenses").toString()));
      project.setBudget(Long.parseLong(data.get("budget").toString()));
      project.setTimeline((MyDate) data.get("timeline"));
      project.setStatus((String) data.get("status"));

      if (project instanceof ResidentialProject residentialProject)
      {
        residentialProject.setSize(Integer.parseInt(data.get("size").toString()));
        residentialProject.setNumKitchens(Integer.parseInt(data.get("numKitchens").toString()));
        residentialProject.setNumBathrooms(Integer.parseInt(data.get("numBathrooms").toString()));
        residentialProject.setNewBuild((Boolean) data.get("isNewBuild"));
        residentialProject.setOthWPlumbing(Integer.parseInt(data.get("othWPlumbing").toString()));
      }
      else if (project instanceof CommercialProject commercialProject)
      {
        commercialProject.setSize(Integer.parseInt(data.get("size").toString()));
        commercialProject.setNumFloor(Integer.parseInt(data.get("numFloor").toString()));
        commercialProject.setIntendedUse((String) data.get("intendedUse"));
      }
      else if (project instanceof IndustrialProject industrialProject)
      {
        industrialProject.setSize(Integer.parseInt(data.get("size").toString()));
        industrialProject.setType((String) data.get("type"));
      }
      else
      {
        RoadProject roadProject = (RoadProject) project;
        roadProject.setLength(Integer.parseInt(data.get("length").toString()));
        roadProject.setWidth(Integer.parseInt(data.get("width").toString()));
        roadProject.setgeoChallenge((ArrayList<String>) data.get("geoChallenge"));
        roadProject.setnumBridTun(Integer.parseInt(data.get("numBridTun").toString()));
      }
      fileManagerXML.writeToFile("Save.xml", projectList);
    }
    catch (IOException e)
    {
      System.out.println(e.getMessage());
    }
  }
  //Alan Karasin Stifter
  //Checks if the name is unique and throws a corresponding message if it isn´t.
  //Gets values from the given arrayList and creates projects with them.
  @Override public void addProject(ArrayList<Object> data) {
    try
    {
      Project project;
      if (projectList.getProject((String) data.get(0)) != null){
        throw new IllegalArgumentException("Name has to be unique");
      }
      if (data.size() == 12) {
        project = new ResidentialProject((String) data.get(0),
            (String) data.get(1), (Integer) data.get(2), (Integer) data.get(3),
            Long.parseLong(data.get(4).toString()), (MyDate) data.get(5),
            (String) data.get(6), (Integer) data.get(7), (Integer) data.get(8),
            (Integer) data.get(9), (Integer) data.get(10),
            (Boolean) data.get(11));
      }
      else if (data.size() == 10) {
        project = new CommercialProject((String) data.get(0),
            (String) data.get(1), (Integer) data.get(2), (Integer) data.get(3),
            Long.parseLong(data.get(4).toString()), (MyDate) data.get(5),
            (String) data.get(6), (Integer) data.get(7), (Integer) data.get(8),
            (String) data.get(9));
      }
      else if (data.size() == 9) {
        project = new IndustrialProject((String) data.get(0),
            (String) data.get(1), (Integer) data.get(2), (Integer) data.get(3),
            Long.parseLong(data.get(4).toString()), (MyDate) data.get(5),
            (String) data.get(6), (String) data.get(7), (Integer) data.get(8));
      }
      else {
        project = new RoadProject((String) data.get(0), (String) data.get(1),
            (Integer) data.get(2), (Integer) data.get(3),
            Long.parseLong(data.get(4).toString()), (MyDate) data.get(5),
            (String) data.get(6), (Integer) data.get(7), (Integer) data.get(8),
            (Integer) data.get(9), (ArrayList<String>) data.get(10));
      }
      projectList.addProject(project);
      fileManagerXML.writeToFile("Save.xml", projectList);
    }catch (IOException e){
      System.out.println(e.getMessage());
    }
  }
  //Alan Karasin Stifter
  @Override public void removeProject(Project project) {
    projectList.removeProject(project);
  }

  //Alan Karasin Stifter
  @Override public void removeProject(int index) {
    projectList.removeProject(index);
  }

  //Alan Karasin Stifter
  @Override public void removeProject(String name) {
    projectList.removeProject(name);
  }

  //Alan Karasin Stifter
  @Override public ProjectList getAllProjectsByType(Object project) {
    return projectList.getAllProjectsByType(project);
  }

  //Alan Karasin Stifter
  @Override public ProjectList getAllProjectsByData(ArrayList<Object> data) {
    return projectList.getAllProjectsByData(data);
  }
  //Zygmunt Kwaśniewicz
  public void getFromFile(){ //Just a simple method that calls the readFromFile, then goes throgh each project in the returned ProjectList, and adds it to the main projectlist
    try{ //try catch for the theoretical IOException
      for (Project i : fileManagerXML.readFromFile("Save.xml").returnAsArrayList()) {
        projectList.addProject(i);
      }
    }
    catch (IOException e){
      System.out.println(e.getMessage());
    }
  }
  //Alan Karasin Stifter
  //Depending on the given project type returns gets and returns corresponding default values.
  @Override public Map<String, Optional<Object>> getDefaults(String type){
    if (type.equals("Residential"))
    {
      return ResidentialProject.getDefaults();
    }
    else if (type.equals("Commercial"))
    {
      return CommercialProject.getDefaults();
    }
    else if (type.equals("Industrial"))
    {
      return IndustrialProject.getDefaults();
    }else
    return RoadProject.getDefaults();
  }
  //Alan Karasin Stifter
  //Passes given number of months to the method 
  @Override public LocalDate getDateMonthsAway(int months){
    return MyDate.getDateMonthsAway(months);
  }

  //Deletes the project with the given name.
  @Override public void delete(String name)
  {
    try{
      projectList.removeProject(name);
      fileManagerXML.writeToFile("Save.xml", projectList);
    }
    catch (IOException e){
      System.out.println(e.getMessage());
    }
  }
}
