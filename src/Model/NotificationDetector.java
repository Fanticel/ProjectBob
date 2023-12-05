package Model;
import java.time.LocalDate;
public class NotificationDetector
{
  private Project project;

  public NotificationDetector(Project project)
  {
    this.project = project;
  }

  public MyDate getCurrentDate(){
    LocalDate today= LocalDate.now();
    return new MyDate(today.getDayOfMonth(), today.getMonthValue(), today.getYear());
  }

  public String checkDeadline()
  {
    MyDate currentDate = new MyDate();
    MyDate projectDeadline = project.getTimeline();

    int difference = currentDate.daysBetween(projectDeadline);

    System.out.println("Difference in days: " + difference);

    if (difference <= 30) {
      return ("Warning: The project " + project.getName() +
          " has a deadline within next " + difference + " days.");
    }
    else return null;
  }

  public String checkFunds() {
    int expectedExpenses = project.getExpectedExpenses();
    long budget = project.getBudget();
    double precentage = (double) budget /expectedExpenses*100;
    double roundOff = Math.round(precentage *100.0)/100.0;

    if (budget >= expectedExpenses * 0.9 && budget > expectedExpenses) {
      return("Warning: The project " + project.getName() +
          " has went over the expected price by: " + (budget-expectedExpenses) +" DK");
    }
    else if (budget >= expectedExpenses * 0.9 && budget <= expectedExpenses)
    {
      return("Warning: The project " + project.getName() +
          " has reached " + roundOff + "% of the expected price.");
    }
    else return null;
  }

  public String checkManHours(){
    int expectedManHours = project.getExpectedTotalHours();
    int totalHours = project.getTotalHours();

    double precentage = (double) totalHours /expectedManHours*100;
    double roundOff = Math.round(precentage *100.0)/100.0;

    if (totalHours >= expectedManHours * 0.9 && totalHours > expectedManHours) {
      return("Warning: The project " + project.getName() +
          " has went over the expected hours by: " + (totalHours-expectedManHours) +" hours");
    }
    else if (totalHours >= expectedManHours * 0.9 && totalHours <= expectedManHours)
    {
      return("Warning: The project " + project.getName() +
          " has reached " + roundOff + "% of the expected hours.");
    }
    else return null;
  }
}
