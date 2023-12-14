//Made by Anthony Richards
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

  /**
   * Creates a new RoadProject with the given parameters
   *
   * @param name               the name of the project
   * @param description        a description of the project
   * @param expectedTotalHours the expected total hours for the project
   * @param expectedExpenses   the expected expenses for the project
   * @param budget             the budget for the project
   * @param timeline           the timeline for the project
   * @param status             the status of the project
   * @param length             the length of the road project
   * @param width              the width of the road project
   * @param numBridTun         the number of bridges and tunnels on the road project
   * @param geoChallenge       the geological challenges of the road project
   */
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

  /**
   * Sets the geological challenges of the road project
   *
   * @param geoChallenge the new geological challenges of the road project
   */
  public void setgeoChallenge(ArrayList<String> geoChallenge)
  {
    this.geoChallenge = geoChallenge;

  }

  /**
   * Returns the geological challenges of the road project
   *
   * @return the geological challenges of the road project
   */
  public ArrayList<String> getgeoChallenge()
  {
    return this.geoChallenge;
  }

  /**
   * Sets the number of bridges and tunnels on the road project
   *
   * @param numBridTun the new number of bridges and tunnels on the road project
   */
  public void setnumBridTun(int numBridTun)
  {
    this.numBridTun = numBridTun;
  }

  /**
   * Returns the number of bridges and tunnels on the road project
   *
   * @return the number of bridges and tunnels on the road project
   */
  public int getnumBridTun()
  {
    return this.numBridTun;
  }

  /**
   * Sets the width of the road project
   *
   * @param width the new width of the road project
   */
  public void setWidth(int width)
  {
    this.width = width;
  }

  /**
   * Returns the width of the road project
   *
   * @return the width of the road project
   */
  public int getWidth()
  {
    return this.width;
  }

  /**
   * Sets the length of the road project
   *
   * @param length the new length of the road project
   */
  public void setLength(long length)
  {
    this.length = length;
  }

  /**
   * Returns the length of the road project
   *
   * @return the length of the road project
   */
  public long getLength()
  {
    return this.length;
  }

  @Override public String toString()
  {
    return super.toString();
  }

  /**
   * Returns a map of the default values for each field of a RoadProject
   *
   * @return a map of the default values for each field of a RoadProject
   */
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
