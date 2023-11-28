package Model;

import Model.Project;

public class ResidentialProject extends Project
{
  private int size;
  private int numKitchens;
  private int numBathrooms;
  private int othWPlumbing;
  private boolean isNewBuild;

  public ResidentialProject(String name, String description,
      int expectedTotalHours, int expectedExpenses, long budget, MyDate timeline)
  {
    super(name, description, expectedTotalHours, expectedExpenses, budget,
        timeline);
    this.size = 0;
    this.numKitchens = 0;
    this.numBathrooms = 0;
    this.othWPlumbing = 0;
    this.isNewBuild = false;
  }

  public void setSize(int size)
  {
    this.size = size;
  }

  public int getSize()
  {
    return this.size;
  }

  public void setNumKitchens(int numKitchens)
  {
    this.numKitchens = numKitchens;
  }

  public int getNumKitchens()
  {
    return this.numKitchens;
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

  public void setNewBuild(boolean isNewBuild)
  {
    this.isNewBuild = isNewBuild;
  }

  public void isNewBuild(boolean isNewBuild)
  {
    this.isNewBuild = isNewBuild;
  }
}

