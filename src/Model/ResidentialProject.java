package Model;

import Model.Project;

public class ResidentialProject extends Project
{
  private int size;
  private String type;
  private int numKitchens;
  private int numBathrooms;
  private int othWPlumbing;
  private boolean isNewBuild;

  public ResidentialProject(String name, String description,
      int expectedTotalHours, int expectedExpenses, long budget,
      MyDate timeline, int size, int numKitchens, int numBathrooms,
      int othWPlumbing, boolean isNewBuild, String type)
  {
    super(name, description, expectedTotalHours, expectedExpenses, budget,
        timeline);
    this.size = size;
    this.type = type;
    this.numKitchens = numKitchens;
    this.numBathrooms = numBathrooms;
    this.othWPlumbing = othWPlumbing;
    this.isNewBuild = isNewBuild;

  }

  public void setSize(int size)
  {
    this.size = 0;
  }

  public void setType(String type)
  {
    this.type = "Residential Project";
  }

  public String getType()
  {
    return this.type;
  }

  public int getSize()
  {
    return 0;
  }

  public void setNumKitchens(int numKitchens)
  {
    this.numKitchens = 0;
  }

  public int getNumKitchens()
  {
    return 0;
  }

  public void setNumBathrooms(int numBathrooms)
  {
    this.numBathrooms = 0;
  }

  public int getNumBathrooms()
  {
    return this.numBathrooms;
  }

  public void setOthWPlumbing(int othWPlumbing)
  {
    this.othWPlumbing = 0;
  }

  public int getOthWPlumbing()
  {
    return this.othWPlumbing;
  }

  public void setNewBuild(boolean isNewBuild)
  {
    this.isNewBuild = false;
  }

  public void isNewBuild(boolean isNewBuild)
  {
    this.isNewBuild = isNewBuild;
  }

  @Override public String toString()
  {
    return super.toString();

  }
}

