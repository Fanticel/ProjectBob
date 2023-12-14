//Made by Anthony Richards
package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class IndustrialProject extends Project
{
  private int size;
  private String type;

  private static final Object[] defaults = {7500000, 30};

  /**
   * Creates a new IndustrialProject with the given parameters
   *
   * @param name               the name of the project
   * @param description        a description of the project
   * @param expectedTotalHours the expected total number of hours required to complete the project
   * @param expectedExpenses   the expected total expenses of the project
   * @param budget             the budget allocated for the project
   * @param timeline           the timeline for the project
   * @param status             the status of the project
   * @param type               the type of the project
   * @param size               the size of the project
   */
  public IndustrialProject(String name, String description,
      int expectedTotalHours, int expectedExpenses, long budget,
      MyDate timeline, String status, String type, int size)
  {
    super(name, description, expectedTotalHours, expectedExpenses, budget,
        timeline, status);
    this.size = size;
    this.type = type;
  }

  /**
   * Sets the type of the project
   *
   * @param type the type of the project
   */
  public void setType(String type)
  {
    this.type = type;
  }

  /**
   * Returns the type of the project
   *
   * @return the type of the project
   */
  public String getType()
  {
    return this.type;
  }

  /**
   * Sets the size of the project
   *
   * @param size the size of the project
   */
  public void setSize(int size)
  {
    this.size = size;
  }

  /**
   * Returns the size of the project
   *
   * @return the size of the project
   */
  public int getSize()
  {
    return this.size;
  }

  @Override public String toString()
  {
    return super.toString();
  }

  /**
   * Returns a map of the default values for each field of an IndustrialProject
   *
   * @return a map of the default values for each field of an IndustrialProject
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
    fields.put("type", Optional.empty());
    fields.put("size", Optional.empty());

    return fields;
  }
}
