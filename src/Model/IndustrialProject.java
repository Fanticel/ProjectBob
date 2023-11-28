package Model;

public class IndustrialProject extends Project
{
  private int size;
  private String type;

  public IndustrialProject(String name, String description,
      int expectedTotalHours, int expectedExpenses, long budget,
      MyDate timeline, String type, int size)
  {
    super(name, description, expectedTotalHours, expectedExpenses, budget,
        timeline);
    this.size = size;
    this.type = type;

  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getType()
  {
    return this.type;
  }

  public void setSize(int size)
  {
    this.size = size;
  }

  public int getSize()
  {
    return this.size;
  }

  @Override public String toString()
  {
    return super.toString();
  }

}
