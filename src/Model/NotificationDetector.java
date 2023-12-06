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

  public String checkBudget() {
    int expectedExpenses = project.getExpectedExpenses();
    long budget = project.getBudget();
    double precentage = (double) expectedExpenses /budget*100;
    double roundOff = Math.round(precentage *100.0)/100.0;

    if (expectedExpenses >= budget * 0.9 && expectedExpenses > budget) {
      return("Warning: The expected expenses of the project " + project.getName() +
          " have went over the budget by: " + (expectedExpenses-budget) +" DKK");
    }
    else if (expectedExpenses >= budget * 0.9 && expectedExpenses <= budget)
    {
      return("Warning:" + project.getName() + " expected expenses have reached " + roundOff + "% of the budget.");
    }
    else return null;
  }

  public String checkExpenses() {
    int expectedExpenses = project.getExpectedExpenses();
    long expenses = project.getExpenses();
    double precentage = (double) expenses /expectedExpenses*100;
    double roundOff = Math.round(precentage *100.0)/100.0;

    if (expenses >= expectedExpenses * 0.9 && expenses > expectedExpenses) {
      return("Warning: The project " + project.getName() +
          " has went over the expected expenses by: " + (expenses-expectedExpenses) +" DKK");
    }
    else if (expenses >= expectedExpenses * 0.9 && expenses <= expectedExpenses)
    {
      return("Warning: The project " + project.getName() +
          " has reached " + roundOff + "% of expected expenses.");
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
