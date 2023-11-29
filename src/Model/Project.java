package Model;

public abstract class Project {
  private String name;
  private String description;
  private int expectedTotalHours;
  private int expectedExpenses;
  private long budget;
  private MyDate timeline;
  private String status;

  public Project(String name, String description, int expectedTotalHours,
      int expectedExpenses, long budget, MyDate timeline, String status) {
    this.name = name;
    this.description = description;
    this.expectedTotalHours = expectedTotalHours;
    this.expectedExpenses = expectedExpenses;
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

  public void setBudget(long budget) {
    this.budget = 0;
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
