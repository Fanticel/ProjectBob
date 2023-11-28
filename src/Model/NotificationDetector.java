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

    int difference = currentDate.yearsBetween(projectDeadline);

    System.out.println("Difference in days: " + difference);

    if (difference <= 30) {
      System.out.println("Warning: The project " + project.getName() +
          " has a deadline within 30 days." + "the difference: " + difference);
    }
    else System.out.println("u good 1");
  }

  public void checkFunds() {
    double thresholdValue = (double) (project.getBudget() * 90) / 100;

    if (project.getBudget() <= thresholdValue) {
      System.out.println("Warning: The project " + project.getName() +
          " has reached 90% of its budget.");
    }
    else System.out.println("u good 2");
  }
}
