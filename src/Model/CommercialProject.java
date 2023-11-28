package Model;

public class CommercialProject extends Project
{
  private int size;
  private int numFloor;
  private String intendedUse;

  public CommercialProject(String name, String description,
      int expectedTotalHours, int expectedExpenses, long budget, MyDate timeline)
  {
    super(name, description, expectedTotalHours, expectedExpenses, budget,
        timeline);
    this.size = 0;
    this.numFloor = 0;
    this.intendedUse = "";

  }

  public void setintendedUse(String intendedUse)
  {
    this.intendedUse = intendedUse;

  }

  public String getintendedUse()
  {
    return this.intendedUse;
  }

  public void setNumFloor(int numFloor)
  {
    this.numFloor = numFloor;
  }

  public int getNumFloor()
  {
    return this.numFloor;
  }

  public void setSize(int size)
  {
    this.size = size;
  }

  public int getSize()
  {
    return this.size;
  }
}
