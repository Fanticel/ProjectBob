import Model.*;

import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    FileManagerXML fileManagerXML = new FileManagerXML();
    /*ProjectList projectList = new ProjectList();
    projectList.addProject(new CommercialProject("Dupa", "Des", 13, 12, 20, new MyDate()));
    projectList.addProject(new CommercialProject("Dupa", "Des", 13, 12, 20, new MyDate()));
    projectList.addProject(new CommercialProject("Dupa", "Des", 13, 12, 20, new MyDate()));
    projectList.addProject(new CommercialProject("Dupa", "Des", 13, 12, 20, new MyDate()));
    projectList.addProject(new IndustrialProject("Dupa", "Des", 13, 12, 20, new MyDate()));
    projectList.addProject(new IndustrialProject("Dupa", "Des", 13, 12, 20, new MyDate()));
    projectList.addProject(new RoadProject("Dupa", "Des", 13, 12, 20, new MyDate()));
    projectList.addProject(new RoadProject("Dupa", "Des", 13, 12, 20, new MyDate()));
    projectList.addProject(new ResidentialProject("Dupa", "Des", 13, 12, 20, new MyDate()));
    projectList.addProject(new ResidentialProject("Dupa", "Des", 13, 12, 20, new MyDate()));
    try{
      fileManagerXML.writeToFile("Save.xml", projectList);
    }
    catch (IOException e){
      System.out.println(e.getMessage());
    }*/
    try{
      fileManagerXML.readFromFile("Save.xml");
    }catch (IOException e){
      System.out.println(e.getMessage());
    }
  }
}
