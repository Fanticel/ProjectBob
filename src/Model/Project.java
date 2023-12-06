package Model;

public abstract class Project {
  private String name;
  private String description;
  private int expectedTotalHours;
  private int expectedExpenses;
  private int totalHours;
  private int expenses;
  private long budget;
  private MyDate timeline;
  private String status;

  public Project(String name, String description, int expectedTotalHours,
      int expectedExpenses, long budget, MyDate timeline, String status) {
    this.name = name;
    this.description = description;
    //if (expectedTotalHours <0 || expectedExpenses)
    this.expectedTotalHours = expectedTotalHours;
    this.expectedExpenses = expectedExpenses;
    this.totalHours = 0;
    this.expenses = 0;
    this.budget = budget;
    this.timeline = timeline;
    this.status = status;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public int getExpectedTotalHours() {
    return expectedTotalHours;
  }

  public int getExpectedExpenses() {
    return expectedExpenses;
  }

  public int getTotalHours()
  {
    return totalHours;
  }

  public void setTotalHours(int totalHours)
  {
    this.totalHours = totalHours;
  }

  public int getExpenses()
  {
    return expenses;
  }

  public void setExpenses(int expenses)
  {
    this.expenses = expenses;
  }

  public void setBudget(long budget) {
    this.budget = budget;
  }

  public long getBudget() {
    return budget;
  }

  public void setTimeline(MyDate timeline) {
    this.timeline = timeline;
  }

  public MyDate getTimeline() {
    return timeline;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setExpectedTotalHours(int expectedTotalHours) {
    this.expectedTotalHours = expectedTotalHours;
  }

  public void setExpectedExpenses(int expectedExpenses) {
    this.expectedExpenses = expectedExpenses;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public double getProgress(){
    return (double) (((double)totalHours / expectedTotalHours * 100) + ((double)expenses / expectedExpenses * 100)) / 2/100;
  }

  public boolean equals(Object other) {
    if (!(other instanceof Project otherProject)) {
      return false;
    }
    return this.name.equals(otherProject.getName()) && this.description.equals(
        otherProject.getDescription())
        && this.expectedTotalHours == otherProject.getExpectedTotalHours()
        && this.expectedExpenses == otherProject.getExpectedExpenses()
        && this.budget == otherProject.getBudget() && this.timeline.equals(
        otherProject.getTimeline());
  }

  @Override public String toString() {
    return "Model.Project{" + "name=" + name + ", description=" + description
        + ", expectedTotalHours=" + expectedTotalHours + ", expectedExpenses="
        + expectedExpenses + ", budget=" + budget + ", timeline=" + timeline
        + ", status=" + status + '}';

  }

}
