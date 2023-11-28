package Model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileManagerXML implements FileManagerInterface{
  public void writeToFile(String filePath, ProjectList list) throws IOException {
    File file = new File(filePath);
    PrintWriter out = new PrintWriter(filePath);
    ProjectList residentialProjects = list.getAllProjectByType(ResidentialProject.class); //reads all types of projects and then converts them into respective ArrayLists
    ArrayList<ResidentialProject> residentialProjectsAL = (ArrayList<ResidentialProject>) ((ArrayList<?>)residentialProjects.returnAsArrayList());
    ProjectList roadProjects = list.getAllProjectByType(RoadProject.class);
    ArrayList<RoadProject> roadProjectsAL = (ArrayList<RoadProject>) ((ArrayList<?>)roadProjects.returnAsArrayList());
    ProjectList commercialProjects = list.getAllProjectByType(CommercialProject.class);
    ArrayList<CommercialProject> commercialProjectsAL = (ArrayList<CommercialProject>) ((ArrayList<?>)commercialProjects.returnAsArrayList());
    ProjectList industrialProjects = list.getAllProjectByType(IndustrialProject.class);
    ArrayList<IndustrialProject> industrialProjectsAL = (ArrayList<IndustrialProject>) ((ArrayList<?>)industrialProjects.returnAsArrayList());
    out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
        + "<root>\n" + "\t<ProjectList>"); //write the general xml version along with root and project list
    out.println("\t\t<ResidentialProjects>"); //begin the residential project section
    for (ResidentialProject i : residentialProjectsAL) {
      out.println("\t\t\t<Project>");
      out.println(writeGeneral(i));
      out.println("\t\t\t</Project>");
    }
    out.println("\t\t</ResidentialProjects>\n\t\t<CommercialProjects>"); //end previous and begin commercial project section
    for (CommercialProject i : commercialProjectsAL) {
      out.println("\t\t\t<Project>");
      out.println(writeGeneral(i));
      out.println("\t\t\t</Project>");
    }
    out.println("\t\t</CommercialProjects>\n\t\t<RoadProjects>"); //end previous and begin road project section
    for (RoadProject i : roadProjectsAL) {
      out.println("\t\t\t<Project>");
      out.println(writeGeneral(i));
      out.println("\t\t\t</Project>");
    }
    out.println("\t\t</RoadProjects>\n\t\t<IndustrialProjects>"); //end previous and begin industrial project section
    for (IndustrialProject i : industrialProjectsAL) {
      out.println("\t\t\t<Project>");
      out.println(writeGeneral(i));
      out.println("\t\t\t</Project>");
    }
    out.println("\t\t</IndustrialProjects>"); //end previous
    out.println("\t</ProjectList>\n</root>");//end project list and root
    out.close();
  }
  public ProjectList readFromFile(String file) throws IOException{
    return null;
  }
  private String writeGeneral(Project i){
    StringBuilder ans = new StringBuilder();
    ans.append("\t\t\t\t<Name>").append(i.getName()).append("</Name>\n");
    ans.append("\t\t\t\t<Description>").append(i.getDescription()).append("</Description>\n");
    ans.append("\t\t\t\t<ExpectedTotalHours>").append(i.getExpectedTotalHours()).append("</ExpectedTotalHours>\n");
    ans.append("\t\t\t\t<ExpectedExpenses>").append(i.getExpectedExpenses()).append("</ExpectedExpenses>\n");
    ans.append("\t\t\t\t<Budget>").append(i.getBudget()).append("</Budget>\n");
    ans.append("\t\t\t\t<Timeline>").append(i.getTimeline()).append("</Timeline>");
    return ans.toString();
  }
}
