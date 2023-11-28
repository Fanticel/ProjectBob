package Model;

import Model.Project;

import java.util.ArrayList;

public class RoadProject extends Project
{
  private long length;
  private int width;
  private int numBridTun;
  private ArrayList<String> geoChallenge = new ArrayList<>();

  public RoadProject(String name, String description, int expectedTotalHours,
      int expectedExpenses, long budget, MyDate timeline)
  {
    super(name, description, expectedTotalHours, expectedExpenses, budget,
        timeline);
    this.length = 0;
    this.width = 0;
    this.numBridTun = 0;
    this.geoChallenge = new ArrayList<>();
  }

  public void setgeoChallenge(ArrayList<String> geoChallenge)
  {
    this.geoChallenge = geoChallenge;

  }

  public ArrayList<String> getgeoChallenge()
  {
    return this.geoChallenge;
  }

  public void setnumBridTun(int numBridTun)
  {
    this.numBridTun = numBridTun;
  }

  public int getnumBridTun()
  {
    return this.numBridTun;
  }

  public void setWidth(int width)
  {
    this.width = width;
  }

  public int getWidth()
  {
    return this.width;
  }


}
