//made by Josip Brljevic
package Model;
import java.time.LocalDate;
public class NotificationDetector
{
  private Project project;

  //constructor that takes a single project as an instance variable
  public NotificationDetector(Project project)
  {
    this.project = project;
  }

//A method that compares the current date with the project deadline. It returns a string that presents a notification warning.
  public String checkDeadline()
  {
    //current date and the deadline of the project
    MyDate currentDate = new MyDate();
    MyDate projectDeadline = project.getTimeline();

    //difference between the deadline and today
    int difference = currentDate.daysBetween(projectDeadline);

    //a warning will be displayed if the difference is less or equal to 30
    if (difference <= 30) {
      return ("Warning: The project " + project.getName() +
          " has a deadline within next " + difference + " days.");
    }
    else return null;
  }

  //A method that compares the expected expenses of the project with the budget.
  public String checkBudget() {
    //expected expenses of the project
    int expectedExpenses = project.getExpectedExpenses();
    //budget of the project
    long budget = project.getBudget();
    //percentage of expected expenses compared to the budget
    double precentage = (double) expectedExpenses /budget*100;
    double roundOff = Math.round(precentage *100.0)/100.0;

    //return warning if the expected expenses have went over 90% of the budget and it is higher than the budget
    if (expectedExpenses >= budget * 0.9 && expectedExpenses > budget) {
      return("Warning: The expected expenses of the project " + project.getName() +
          " have went over the budget by: " + (expectedExpenses-budget) +" DKK");
    }
    //return warning if the expected expenses have went over 90% of the budget and it is close to the budget
    else if (expectedExpenses >= budget * 0.9 && expectedExpenses <= budget)
    {
      return("Warning:" + project.getName() + " expected expenses have reached " + roundOff + "% of the budget.");
    }
    else return null;
  }

  //A method that compares current expenses of the project with the expected expenses of the project
  public String checkExpenses() {
    //expected expenses of the project
    int expectedExpenses = project.getExpectedExpenses();
    //current expenses for the project
    long expenses = project.getExpenses();
    //percentage of expenses compared to the expected expenses
    double precentage = (double) expenses /expectedExpenses*100;
    double roundOff = Math.round(precentage *100.0)/100.0;

    //return warning if the expenses have went over 90% of the expected expenses and it is higher than the expected expenses
    if (expenses >= expectedExpenses * 0.9 && expenses > expectedExpenses) {
      return("Warning: The project " + project.getName() +
          " has went over the expected expenses by: " + (expenses-expectedExpenses) +" DKK");
    }
    //return warning if the expected expenses have went over 90% of the expected expenses and it is close to the expected expenses
    else if (expenses >= expectedExpenses * 0.9 && expenses <= expectedExpenses)
    {
      return("Warning: The project " + project.getName() +
          " has reached " + roundOff + "% of expected expenses.");
    }
    else return null;
  }

  //A method that compares the expected hours of the project with the total hours invested into the project currentl
  public String checkManHours(){
    //expected man hours for the project
    int expectedManHours = project.getExpectedTotalHours();
    //total hours of the project
    int totalHours = project.getTotalHours();

    //percentage of the total hours compared to the expected man hours
    double precentage = (double) totalHours /expectedManHours*100;
    double roundOff = Math.round(precentage *100.0)/100.0;

    //return warning if the totalHours have went over 90% of the expected man hours and it is higher than the expected man hours
    if (totalHours >= expectedManHours * 0.9 && totalHours > expectedManHours) {
      return("Warning: The project " + project.getName() +
          " has went over the expected hours by: " + (totalHours-expectedManHours) +" hours");
    }
    //return warning if the totalHours have went over 90% of the expected man hours and it is close to the expected man hours
    else if (totalHours >= expectedManHours * 0.9 && totalHours <= expectedManHours)
    {
      return("Warning: The project " + project.getName() +
          " has reached " + roundOff + "% of the expected hours.");
    }
    else return null;
  }
}
