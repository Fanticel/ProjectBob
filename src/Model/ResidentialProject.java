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
      int othWPlumbing, boolean isNewBuild)
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
    this.size = size;
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
    return size;
  }

  public void setNumKitchens(int numKitchens)
  {
    this.numKitchens = numKitchens;
  }

  public int getNumKitchens()
  {
    return numKitchens;
  }

  public void setNumBathrooms(int numBathrooms)
  {
    this.numBathrooms = numBathrooms;
  }

  public int getNumBathrooms()
  {
    return this.numBathrooms;
  }

  public void setOthWPlumbing(int othWPlumbing)
  {
    this.othWPlumbing = othWPlumbing;
  }

  public int getOthWPlumbing()
  {
    return this.othWPlumbing;
  }

  public boolean isNewBuild()
  {
    return isNewBuild;
  }

  public void setNewBuild(boolean isNewBuild)
  {
    this.isNewBuild = isNewBuild;
  }

  @Override public String toString()
  {
    return super.toString();

  }
}

