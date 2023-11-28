package Model;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.util.ArrayList;
public class ProjectListModelManager implements ProjectListModel
{
  private ProjectList projectList;
  private FileManagerXML fileManagerXML;

  @Override public ProjectList getAllProjects(){
    try
    {
      return fileManagerXML.readFromFile("placeholder");
    }
    catch (IOException e){
      System.out.println(e);
    }
    return projectList;
  }
  @Override public Project getProject(int index){
    return projectList.getProject(index);
  }
  @Override public Project getProject(String name){
    return projectList.getProject(name);
  }
  @Override public void editProject(int index, ArrayList<Object> data){
    Project project = projectList.getProject(index);
    edit(project, data);
  }
  @Override public void editProjectByName(String name, ArrayList<Object> data){
    Project project = projectList.getProject(name);
    edit(project, data);
  }
  @Override public void addProject(ArrayList<Object> data){
    Project project;
    if (data.size() == 11){
       project = new ResidentialProject((String)data.get(0), (String)data.get(1), (int) data.get(2),(int) data.get(3),(long) data.get(4),(MyDate) data.get(5),(int) data.get(6), (int) data.get(7), (int) data.get(8), (int) data.get(9) ,(Boolean) data.get(10));
    }
    else if (data.size() == 9)
    {
       project = new CommercialProject((String)data.get(0), (String)data.get(1), (int) data.get(2),(int) data.get(3),(long) data.get(4),(MyDate) data.get(5),(int) data.get(6), (int) data.get(7), (String) data.get(8));
    }
    else if (data.size() == 8)
    {
       project = new IndustrialProject((String)data.get(0), (String)data.get(1), (int) data.get(2),(int) data.get(3),(long) data.get(4),(MyDate) data.get(5),(String) data.get(6), (int) data.get(7));
    }
    else {
       project = new RoadProject((String)data.get(0), (String)data.get(1), (int) data.get(2),(int) data.get(3),(long) data.get(4),(MyDate) data.get(5),(int) data.get(6), (int) data.get(7),(int) data.get(8),(ArrayList<String>) data.get(9));
    }
    projectList.addProject(project);
  }
  @Override public void removeProject(Project project){
    projectList.removeProject(project);
  }
  @Override public void removeProject(int index){
    projectList.removeProject(index);
  }
  @Override public void removeProject(String name){
    projectList.removeProject(name);
  }
  @Override public ProjectList getAllProjectsByType(Object project){
    return projectList.getAllProjectByType(project);
  }
  @Override public ProjectList getAllProjectsByData(ArrayList<Object> data){
    return projectList.getAllProjectsByData(data);
  }

  void edit(Project project, ArrayList<Object> data){
    project.setName((String) data.get(0));
    project.setDescription((String) data.get(1));
    project.setExpectedTotalHours((Integer) data.get(2));
    project.setExpectedExpenses((Integer) data.get(3));
    project.setBudget(data.get(4));
    project.setTimeline[data.get(5)]

    if (project instanceof ResidentialProject residentialProject){
      residentialProject.setSize((Integer) data.get(6));
      residentialProject.setNumKitchens((Integer) data.get(7));
      residentialProject.setNumBathrooms((Integer) data.get(8));
      residentialProject.setNewBuild((Boolean) data.get(9));
      residentialProject.setOthWPlumbing((Integer) data.get(10));
    }
    else if (project instanceof CommercialProject commercialProject){
      commercialProject.setSize((Integer) data.get(6));
      commercialProject.setNumFloor((Integer) data.get(7));
      commercialProject.setIntendedUse((String) data.get(8));
    }
    else if (project instanceof IndustrialProject industrialProject){
      industrialProject.setSize((Integer) data.get(6));
      industrialProject.setType((String) data.get(7));
    }else
    {
      RoadProject roadProject = (RoadProject) project;
      roadProject.setLength((Integer) data.get(6));
      roadProject.setWidth((Integer) data.get(7));
      roadProject.setgeoChallenge((ArrayList<String>) data.get(8));
      roadProject.setnumBridTun((Integer) data.get(9));
    }
  }
}
