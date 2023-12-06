package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManagerXML implements FileManagerInterface {
  public void writeToFile(String filePath, ProjectList list)
      throws IOException {
    PrintWriter out = new PrintWriter(filePath);
    //reads all types of projects and then converts them into respective ArrayLists
    ArrayList<ResidentialProject> residentialProjectsAL = (ArrayList<ResidentialProject>) ((ArrayList<?>) list.getAllProjectByType(
        ResidentialProject.class).returnAsArrayList());
    ArrayList<RoadProject> roadProjectsAL = (ArrayList<RoadProject>) ((ArrayList<?>) list.getAllProjectByType(
        RoadProject.class).returnAsArrayList());
    ArrayList<CommercialProject> commercialProjectsAL = (ArrayList<CommercialProject>) ((ArrayList<?>) list.getAllProjectByType(
        CommercialProject.class).returnAsArrayList());
    ArrayList<IndustrialProject> industrialProjectsAL = (ArrayList<IndustrialProject>) ((ArrayList<?>) list.getAllProjectByType(
        IndustrialProject.class).returnAsArrayList());
    out.println(
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<root>\n\t<ProjectList>"); //write the general xml version along with root and project list
    out.println(
        "\t\t<ResidentialProjects>"); //begin the residential project section
    for (ResidentialProject i : residentialProjectsAL) { //out.println("\t\t\t\t<>" + + "</>");
      out.println("\t\t\t<Project>");
      out.println(writeGeneral(i));
      out.println("\t\t\t\t<Size>" + i.getSize() + "</Size>");
      out.println(
          "\t\t\t\t<NumKitchens>" + i.getNumKitchens() + "</NumKitchens>");
      out.println(
          "\t\t\t\t<NumBathrooms>" + i.getNumBathrooms() + "</NumBathrooms>");
      out.println("\t\t\t\t<NumOthWPlumbing>" + i.getOthWPlumbing()
          + "</NumOthWPlumbing>");
      out.println("\t\t\t\t<IsNewBuild>" + i.isNewBuild() + "</IsNewBuild>");
      out.println("\t\t\t</Project>");
    }
    out.println(
        "\t\t</ResidentialProjects>\n\t\t<CommercialProjects>"); //end previous and begin commercial project section
    for (CommercialProject i : commercialProjectsAL) {
      out.println("\t\t\t<Project>");
      out.println(writeGeneral(i));
      out.println("\t\t\t\t<Size>" + i.getSize() + "</Size>");
      out.println("\t\t\t\t<NumFloors>" + i.getNumFloor() + "</NumFloors>");
      out.println(
          "\t\t\t\t<IntendedUse>" + i.getIntendedUse().replace("\n", "_PrivateN") + "</IntendedUse>");
      out.println("\t\t\t</Project>");
    }
    out.println(
        "\t\t</CommercialProjects>\n\t\t<RoadProjects>"); //end previous and begin road project section
    for (RoadProject i : roadProjectsAL) {
      out.println("\t\t\t<Project>");
      out.println(writeGeneral(i));
      out.println("\t\t\t\t<Length>" + i.getLength() + "</Length>");
      out.println("\t\t\t\t<Width>" + i.getWidth() + "</Width>");
      out.println("\t\t\t\t<NumBridTun>" + i.getnumBridTun() + "</NumBridTun>");
      out.println(
          "\t\t\t\t<GeoChallenge>" + i.getgeoChallenge() + "</GeoChallenge>");
      out.println("\t\t\t</Project>");
    }
    out.println(
        "\t\t</RoadProjects>\n\t\t<IndustrialProjects>"); //end previous and begin industrial project section
    for (IndustrialProject i : industrialProjectsAL) {
      out.println("\t\t\t<Project>");
      out.println(writeGeneral(i));
      out.println("\t\t\t\t<Type>" + i.getType() + "</Type>");
      out.println("\t\t\t\t<Size>" + i.getSize() + "</Size>");
      out.println("\t\t\t</Project>");
    }
    out.println("\t\t</IndustrialProjects>"); //end previous
    out.println("\t</ProjectList>\n</root>");//end project list and root
    out.close();
  }

  private String writeGeneral(Project i) {
    StringBuilder ans = new StringBuilder();
    ans.append("\t\t\t\t<Name>").append(i.getName()).append("</Name>\n");
    ans.append("\t\t\t\t<Description>").append(i.getDescription().replace("\n", "_PrivateN"))
        .append("</Description>\n");
    ans.append("\t\t\t\t<ExpectedTotalHours>").append(i.getExpectedTotalHours())
        .append("</ExpectedTotalHours>\n");
    ans.append("\t\t\t\t<ExpectedExpenses>").append(i.getExpectedExpenses())
        .append("</ExpectedExpenses>\n");
    ans.append("\t\t\t\t<Budget>").append(i.getBudget()).append("</Budget>\n");
    ans.append("\t\t\t\t<Timeline>").append(i.getTimeline())
        .append("</Timeline>\n");
    ans.append("\t\t\t\t<Status>").append(i.getStatus()).append("</Status>\n");
    ans.append("\t\t\t\t<Expenses>").append(i.getExpenses()).append("</Expenses>\n");
    ans.append("\t\t\t\t<TotalHours>").append(i.getTotalHours()).append("</TotalHours>");
    return ans.toString();
  }

  public ProjectList readFromFile(String filePath) throws IOException {
    ProjectList answer = new ProjectList();
    Scanner in = new Scanner(new File(filePath));
    StringBuilder totalBuilder = new StringBuilder();
    while (in.hasNext()) {
      totalBuilder.append(in.nextLine()).append("\n");
    }
    ArrayList<String> totalAL = getStrings(totalBuilder);
    //After all this stripping and splitting we have an ArrayList of strings in which there are four string, first contains all
    //Residential projects, the second all commercial projects, the third all road projects, and the fourth all industrial ones
    //now we can simply deal with all of them based on what type they are
    //0-Residential   1-Commercial    2-Road    3-Industrial
    for (int x = 0; x < 4; x++) {
      String i = totalAL.get(x);
      i = i.replaceAll("</Project>", "");
      ArrayList<String> iss = new ArrayList<>(
          Arrays.asList(i.split("<Project>")));
      iss.remove(0);
      for (String j : iss) {
        j = stripXml(j.replaceAll("\t", ""));
        ArrayList<Object> values = new ArrayList<>(
            Arrays.asList(j.split("\n")));
        values.remove("");
        int expenses = Integer.parseInt(values.get(7).toString());
        int totalHours = Integer.parseInt(values.get(8).toString());
        Project hold = convertValuesToProject(x, values);
        hold.setExpenses(expenses);
        hold.setTotalHours(totalHours);
        answer.addProject(hold);
      }
    }
    return answer;
  }

  private Project convertValuesToProject(int caseID, ArrayList<Object> values) {
    String[] dateHelp = values.get(5).toString().split("/");
    MyDate date = new MyDate(Integer.parseInt(dateHelp[0]),
        Integer.parseInt(dateHelp[1]), Integer.parseInt(dateHelp[2]));
    values.remove(7);
    values.remove(7);
    if (caseID == 0) { //      __Residential__
      return new ResidentialProject(values.get(0).toString(),
          values.get(1).toString().replace("_PrivateN", "\n"), Integer.parseInt(values.get(2).toString()),
          Integer.parseInt(values.get(3).toString()),
          Long.parseLong(values.get(4).toString()), date,
          values.get(6).toString(), Integer.parseInt(values.get(7).toString()),
          Integer.parseInt(values.get(8).toString()),
          Integer.parseInt(values.get(9).toString()),
          Integer.parseInt(values.get(10).toString()),
          Boolean.parseBoolean(values.get(11).toString()));
    }
    if (caseID == 1) { //      __Commercial__
      return new CommercialProject(values.get(0).toString(),
          values.get(1).toString(), Integer.parseInt(values.get(2).toString()),
          Integer.parseInt(values.get(3).toString()),
          Long.parseLong(values.get(4).toString()), date,
          values.get(6).toString(), Integer.parseInt(values.get(7).toString()),
          Integer.parseInt(values.get(8).toString()), values.get(9).toString().replace("_PrivateN", "\n"));
    }
    if (caseID == 2) { //      __Road__
      ArrayList<String> ans = new ArrayList<>(Arrays.asList(
          values.get(10).toString().replace("[", "").replace("]", "")
              .split(", ")));
      return new RoadProject(values.get(0).toString(), values.get(1).toString(),
          Integer.parseInt(values.get(2).toString()),
          Integer.parseInt(values.get(3).toString()),
          Long.parseLong(values.get(4).toString()), date,
          values.get(6).toString(), Long.parseLong(values.get(7).toString()),
          Integer.parseInt(values.get(8).toString()),
          Integer.parseInt(values.get(9).toString()), ans);
    }
    if (caseID == 3) { //      __Industrial__
      return new IndustrialProject(values.get(0).toString(),
          values.get(1).toString(), Integer.parseInt(values.get(2).toString()),
          Integer.parseInt(values.get(3).toString()),
          Long.parseLong(values.get(4).toString()), date,
          values.get(6).toString(), values.get(7).toString(),
          Integer.parseInt(values.get(8).toString()));
    }
    return null;
  }

  private static ArrayList<String> getStrings(StringBuilder totalBuilder) {
    String total = totalBuilder.toString();
    ArrayList<String> totalAL = new ArrayList<>();
    total = total.replace(
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<root>\n"
            + "\t<ProjectList>\n" + "\t\t<ResidentialProjects>", "");
    totalAL.add(total.split("</ResidentialProjects>")[0]);
    total = total.split("</ResidentialProjects>")[1].replace(
        "<CommercialProjects>", "");
    totalAL.add(total.split("</CommercialProjects>")[0]);
    total = total.split("</CommercialProjects>")[1].replace("<RoadProjects>",
        "");
    totalAL.add(total.split("</RoadProjects>")[0]);
    total = total.split("</RoadProjects>")[1].replace("<IndustrialProjects>",
        "");
    totalAL.add(total.split("</IndustrialProjects>")[0]);
    return totalAL;
  }

  private static String stripXml(String xmlString) {
    String regex = "<[^>]*>";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(xmlString);
    return matcher.replaceAll("");
  }
}
