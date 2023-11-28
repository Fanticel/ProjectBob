package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManagerXML implements FileManagerInterface{
  public void writeToFile(String filePath, ProjectList list) throws IOException {
    PrintWriter out = new PrintWriter(filePath);
    //reads all types of projects and then converts them into respective ArrayLists
    ArrayList<ResidentialProject> residentialProjectsAL = (ArrayList<ResidentialProject>) ((ArrayList<?>)list.getAllProjectByType(ResidentialProject.class).returnAsArrayList());
    ArrayList<RoadProject> roadProjectsAL = (ArrayList<RoadProject>) ((ArrayList<?>)list.getAllProjectByType(RoadProject.class).returnAsArrayList());
    ArrayList<CommercialProject> commercialProjectsAL = (ArrayList<CommercialProject>) ((ArrayList<?>)list.getAllProjectByType(CommercialProject.class).returnAsArrayList());
    ArrayList<IndustrialProject> industrialProjectsAL = (ArrayList<IndustrialProject>) ((ArrayList<?>)list.getAllProjectByType(IndustrialProject.class).returnAsArrayList());
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
  public ProjectList readFromFile(String filePath) throws IOException{
    Scanner in = new Scanner(new File(filePath));
    StringBuilder totalBuilder = new StringBuilder();
    while (in.hasNext()){
      totalBuilder.append(in.nextLine() + "\n");
    }
    String total = totalBuilder.toString();
    ArrayList<String> totalAL = new ArrayList<>();
    total = total.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<root>\n"
        + "\t<ProjectList>\n" + "\t\t<ResidentialProjects>", "");
    totalAL.add(total.split("</ResidentialProjects>")[0]);
    total = total.split("</ResidentialProjects>")[1].replace("<CommercialProjects>", "");
    totalAL.add(total.split("</CommercialProjects>")[0]);
    total = total.split("</CommercialProjects>")[1].replace("<RoadProjects>", "");
    totalAL.add(total.split("</RoadProjects>")[0]);
    total = total.split("</RoadProjects>")[1].replace("<IndustrialProjects>", "");
    totalAL.add(total.split("</IndustrialProjects>")[0]);
    //After all this stripping and splitting we have an ArrayList of strings in which there are four string, first contains all
    //Residential projects, the second all commercial projects, the third all road projects, and the fourth all industrial ones
    //now we can simply deal with all of them based on what type they are
    for (String i : totalAL) {
      System.out.println("\n\n_______________\n\n" + i);
    }
    return null;
  }
  private static String stripXml(String xmlString) {
    String regex = "<[^>]*>";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(xmlString);
    return matcher.replaceAll("");
  }
}
