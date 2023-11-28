package Model;

import Model.Project;

import java.util.ArrayList;

public class RoadProject extends Project
{
  private long length;
  private String type;
  private int width;
  private int numBridTun;
  private ArrayList<String> geoChallenge = new ArrayList<>();

  public RoadProject(String name, String description, int expectedTotalHours,
      int expectedExpenses, long budget, MyDate timeline, long length,
      int width, int numBridTun, ArrayList<String> geoChallenge)
  {
    super(name, description, expectedTotalHours, expectedExpenses, budget,
        timeline);
    this.length = length;
    this.type = type;
    this.width = width;
    this.numBridTun = numBridTun;
    this.geoChallenge = new ArrayList<>(geoChallenge);
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
    this.numBridTun = 0;
  }

  public void setType(String type)
  {
    this.type = "Road Project";
  }

  public String getType()
  {
    return this.type;
  }

  public int getnumBridTun()
  {
    return this.numBridTun;
  }

  public void setWidth(int width)
  {
    this.width = 0;
  }

  public int getWidth()
  {
    return this.width;
  }

  public void setLength(long length)
  {
    this.length = 0;
  }

  public long getLength()
  {
    return this.length;
  }

  @Override public String toString()
  {
    return super.toString();
  }

}
