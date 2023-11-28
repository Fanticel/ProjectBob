package Model;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class ProjectListModelManager
{
  /*private ProjectList projectList;
  private FileManagerInterface fileManagerSCV;

  @Override ProjectList getAllProjects(){
    return fileManagerSCV.readFromFile();
  }
  @Override Project getProject(int index){
    return projectList.getProject(index);
  }
  @Override Project getProject(String name){
    return projectList.getProject(name);
  }
  @Override void editProject(int index, ArrayList<Object> data){
    Project project = projectList.getProject(index);
    project.edit(data);
  }
  @Override void editProjectByName(String name, ArrayList<Object> data){
    Project project = projectList.getProject(name);
    edit(project, data);
  }
  @Override void addProject(ArrayList<Object> data){
    projectList.addProject(data);
  }
  @Override void removeProject(Project project){
    projectList.remove(project);
  }
  @Override void removeProject(int index){
    projectList.remove(index);
  }
  @Override void removeProject(String name){
    projectList.remove(name);
  }
  @Override ProjectList getAllProjectsByType(String type){
    return projectList.getAllProjectsByType(type);
  }
  @Override ProjectList getAllProjectsByData(ArrayList<Object> data){
    return projectList.getAllProjectsByData(data);
  }

  void edit(Project project, ArrayList<Object> data){
    project.setName(data[0]);
    project.setDescription(data[1]);
    project.setExpectedTotalHours(data[2]);
    project.setExpectedExpenses(data[3]);
    project.setBudget(data[4]);
    project.setTimeline[data(5)];

    if (project.getClass() == ResidentialProject){
      project.setSize(data[6]);
      project.setNumKitchens(data[7]);
      project.setNumBathrooms(data[8]);
      project.setNewBuild(data[9]);
      project.setOthWPlumbing(data[10]);
    }
    else if (project.getClass() == CoomercialProject){
      project.setSize(data[6]);
      project.setNumFloor(data[7]);
      project.setIntendedUse(data[8]);
    }
    else if (project.getClass() == IndustrialProject){
      project.setSize(data[6]);
      project.setType(data[7]);
    }else
    {
      project.setLength(data[6]);
      project.setWidth(data[7]);
      project.setGeoChallenge(data[8]);
      project.setNumBridTun(data[9]);
    }
  }*/
}
