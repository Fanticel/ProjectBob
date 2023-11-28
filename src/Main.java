import Model.*;

import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    ProjectList projectList = new ProjectList();
    projectList.addProject(new CommercialProject("Dupa", "Des", 13, 12, 20, new MyDate()));
    projectList.addProject(new CommercialProject("Dupa", "Des", 13, 12, 20, new MyDate()));
    projectList.addProject(new CommercialProject("Dupa", "Des", 13, 12, 20, new MyDate()));
    projectList.addProject(new CommercialProject("Dupa", "Des", 13, 12, 20, new MyDate()));
    projectList.addProject(new IndustrialProject("Dupa", "Des", 13, 12, 20, new MyDate()));
    projectList.addProject(new IndustrialProject("Dupa", "Des", 13, 12, 20, new MyDate()));
    projectList.addProject(new RoadProject("Dupa", "Des", 13, 12, 20, new MyDate()));
    projectList.addProject(new RoadProject("Dupa", "Des", 13, 12, 20, new MyDate()));
    FileManagerXML fileManagerXML = new FileManagerXML();
    try{
      fileManagerXML.writeToFile("Save.xml", projectList);
    }
    catch (IOException e){
      System.out.println(e.getMessage());
    }
  }
}
