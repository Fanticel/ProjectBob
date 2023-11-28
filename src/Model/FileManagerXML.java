package Model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileManagerXML implements FileManagerInterface{
  public void writeToFile(String filePath, ProjectList list) throws IOException {
    File file = new File(filePath);
    PrintWriter out = new PrintWriter(filePath);
    ProjectList residentialProjects = list.getAllProjectByType("Residential"); //reads all types of projects and then converts them into respective ArrayLists
    ArrayList<ResidentialProjects> residentialProjects = new ArrayList<ResidentialProjects>();
    for (Project i : residentialProjects) {
      residentialProjects.add((ResidentialProjects) i);
    }
    ProjectList roadProjects = list.getAllProjectByType("Road");
    ProjectList commercialProject = list.getAllProjectByType("Commercial");
    ProjectList industrialProjects = list.getAllProjectByType("Industrial");
    out.println("<ProjectList>");
    out.println("</ProjectList>");
  }
  public ProjectList readFromFile(String file) throws IOException{

  }
}
