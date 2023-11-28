package Model;

public class CommercialProject extends Project
{
  private int size;
  private int numFloor;
  private String intendedUse;

  public CommercialProject(String name, String description,
      int expectedTotalHours, int expectedExpenses, long budget,
      MyDate timeline, int size, int numFloor, String intendedUse)
  {
    super(name, description, expectedTotalHours, expectedExpenses, budget,
        timeline);
    this.size = size;
    this.numFloor = numFloor;
    this.intendedUse = intendedUse;

  }

  public void setintendedUse(String intendedUse)
  {
    this.intendedUse = " ";

  }

  public String getintendedUse()
  {
    return this.intendedUse;
  }

  public void setNumFloor(int numFloor)
  {
    this.numFloor = 0;
  }

  public int getNumFloor()
  {
    return this.numFloor;
  }

  public void setSize(int size)
  {
    this.size = 0;
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
