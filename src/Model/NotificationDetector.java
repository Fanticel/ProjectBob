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

  public void checkDeadline()
  {
    MyDate currentDate = new MyDate();
    MyDate projectDeadline = project.getTimeline();

    int difference = currentDate.daysBetween(projectDeadline);

    System.out.println("Difference in days: " + difference);

    if (difference <= 30) {
      System.out.println("Warning: The project " + project.getName() +
          " has a deadline within 30 days." + "the difference: " + difference);
    }
  }

  public void checkFunds() {
    int expectedExpenses = project.getExpectedExpenses();
    long budget = project.getBudget();

    if (budget >= expectedExpenses * 0.9) {
      System.out.println("Warning: The project " + project.getName() +
          " has reached 90% of its budget.");
    }
  }
}
