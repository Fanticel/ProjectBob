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
  private static final Object[] defaults = {1500000, 18, 0, "none"};

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

  public void setLength(long length)
  {
    this.length = length;
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
    fields.put("length", Optional.empty());
    fields.put("width", Optional.empty());
    fields.put("numBridTun", Optional.of(defaults[2]));
    fields.put("geoChallenge", Optional.of(defaults[3]));

    return fields;
  }
}
