package Model;

public abstract class Project
{ // Anthony
  /**
   * The name of the project
   */
  private String name;
  /**
   * A description of the project
   */
  private String description;
  /**
   * The expected total number of hours for the project
   */
  private int expectedTotalHours;
  /**
   * The expected total expenses for the project
   */
  private int expectedExpenses;
  /**
   * The total number of hours spent on the project
   */
  private int totalHours;
  /**
   * The total expenses incurred on the project
   */
  private int expenses;
  /**
   * The project budget
   */
  private long budget;
  /**
   * The project timeline
   */
  private MyDate timeline;
  /**
   * The project status
   */
  private String status;

  /**
   * Creates a new project with the specified details
   *
   * @param name               The name of the project
   * @param description        A description of the project
   * @param expectedTotalHours The expected total number of hours for the project
   * @param expectedExpenses   The expected total expenses for the project
   * @param budget             The project budget
   * @param timeline           The project timeline
   * @param status             The project status
   */
  public Project(String name, String description, int expectedTotalHours,
      int expectedExpenses, long budget, MyDate timeline, String status)
  {
    this.name = name;
    this.description = description;
    this.expectedTotalHours = expectedTotalHours;
    this.expectedExpenses = expectedExpenses;
    this.totalHours = 0;
    this.expenses = 0;
    this.budget = budget;
    this.timeline = timeline;
    this.status = status;
  }

  /**
   * Returns the name of the project
   *
   * @return The name of the project
   */
  public String getName()
  {
    return name;
  }

  /**
   * Returns the description of the project
   *
   * @return The description of the project
   */
  public String getDescription()
  {
    return description;
  }

  /**
   * Returns the expected total number of hours for the project
   *
   * @return The expected total number of hours for the project
   */
  public int getExpectedTotalHours()
  {
    return expectedTotalHours;
  }

  /**
   * Returns the expected total expenses for the project
   *
   * @return The expected total expenses for the project
   */
  public int getExpectedExpenses()
  {
    return expectedExpenses;
  }

  /**
   * Returns the total number of hours spent on the project
   *
   * @return The total number of hours spent on the project
   */
  public int getTotalHours()
  {
    return totalHours;
  }

  /**
   * Sets the total number of hours spent on the project
   *
   * @param totalHours The total number of hours spent on the project
   */
  public void setTotalHours(int totalHours)
  {
    this.totalHours = totalHours;
  }

  /**
   * Returns the total expenses incurred on the project
   *
   * @return The total expenses incurred on the project
   */
  public int getExpenses()
  {
    return expenses;
  }

  /**
   * Sets the total expenses incurred on the project
   *
   * @param expenses The total expenses incurred on the project
   */
  public void setExpenses(int expenses)
  {
    this.expenses = expenses;
  }

  /**
   * Sets the project budget
   *
   * @param budget The project budget
   */
  public void setBudget(long budget)
  {
    this.budget = budget;
  }

  /**
   * Returns the project budget
   *
   * @return The project budget
   */
  public long getBudget()
  {
    return budget;
  }

  /**
   * Sets the project timeline
   *
   * @param timeline The project timeline
   */
  public void setTimeline(MyDate timeline)
  {
    this.timeline = timeline;
  }

  /**
   * Returns the project timeline
   *
   * @return The project timeline
   */
  public MyDate getTimeline()
  {
    return timeline;
  }

  /**
   * Sets the name of the project
   *
   * @param name The name of the project
   */
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * Sets the description of the project
   *
   * @param description The description of the project
   */
  public void setDescription(String description)
  {
    this.description = description;
  }

  /**
   * Sets the expected total number of hours for the project
   *
   * @param expectedTotalHours The expected total number of hours for the project
   */
  public void setExpectedTotalHours(int expectedTotalHours)
  {
    this.expectedTotalHours = expectedTotalHours;
  }

  /**
   * Sets the expected total expenses for the project
   *
   * @param expectedExpenses The expected total expenses for the project
   */
  public void setExpectedExpenses(int expectedExpenses)
  {
    this.expectedExpenses = expectedExpenses;
  }

  /**
   * Returns the project status
   *
   * @return The project status
   */
  public String getStatus()
  {
    return status;
  }

  /**
   * Sets the project status
   *
   * @param status The project status
   */
  public void setStatus(String status)
  {
    this.status = status;
  }

  /**
   * Calculates the progress of the project by getting the percentage of total hours and expenses, then getting the average.
   *
   * @return The project progress
   */
  public double getProgress()
  {
    return (double) (((double) totalHours / expectedTotalHours * 100) + (
        (double) expenses / expectedExpenses * 100)) / 2 / 100;
  }

  /**
   * Checks if the specified object is equal to the current object
   *
   * @param other The object to compare with the current object
   * @return True if the specified object is equal to the current object, false otherwise
   */
  public boolean equals(Object other)
  {
    if (!(other instanceof Project otherProject))
    {
      return false;
    }
    return this.name.equals(otherProject.getName()) && this.description.equals(
        otherProject.getDescription())
        && this.expectedTotalHours == otherProject.getExpectedTotalHours()
        && this.expectedExpenses == otherProject.getExpectedExpenses()
        && this.budget == otherProject.getBudget() && this.timeline.equals(
        otherProject.getTimeline());
  }

  /**
   * Returns a string representation of the current object
   *
   * @return A string representation of the current object
   */
  @Override public String toString()
  {
    return "Model.Project{" + "name=" + name + ", description=" + description
        + ", expectedTotalHours=" + expectedTotalHours + ", expectedExpenses="
        + expectedExpenses + ", budget=" + budget + ", timeline=" + timeline
        + ", status=" + status + '}';
  }
}
