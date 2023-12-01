package Model;

import Model.Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RoadProject extends Project
{
  private long length;
  private String type;
  private int width;
  private int numBridTun;
  private ArrayList<String> geoChallenge = new ArrayList<>();
  private static final Object[] defaults = {350000, 9, 1, 1, 1, "new build"};

  public RoadProject(String name, String description, int expectedTotalHours,
      int expectedExpenses, long budget, MyDate timeline, String status,
      long length, int width, int numBridTun, ArrayList<String> geoChallenge)
  {
    super(name, description, expectedTotalHours, expectedExpenses, budget,
        timeline, status);
    this.length = length;

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

  public static Map<String, Optional<Object>> getDefaults()
  {
    Map<String, Optional<Object>> fields = new HashMap<>();
    fields.put("name", Optional.empty());
    fields.put("description", Optional.empty());
    fields.put("expectedTotalHours", Optional.empty());
    fields.put("expectedExpenses", Optional.empty());
    fields.put("budget", Optional.of(defaults[0]));
    fields.put("timeline", Optional.of(defaults[1]));
    fields.put("status", Optional.of(defaults[2]));
    fields.put("length", Optional.of(defaults[3]));
    fields.put("width", Optional.of(defaults[4]));
    fields.put("numBridTun", Optional.of(defaults[5]));

    return fields;
  }
}
