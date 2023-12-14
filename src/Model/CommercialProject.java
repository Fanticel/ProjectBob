//Made by Anthony Richards
package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommercialProject extends Project
{
  private int size; // size of the building in square meters
  private int numFloor; // number of floors in the building
  private String intendedUse; // intended use of the building (e.g. office, retail, etc.)
  private static final Object[] defaults = {600000, 18,
      1}; // default values for various fields

  /**
   * Creates a new CommercialProject with the given properties.
   *
   * @param name               the name of the project
   * @param description        a description of the project
   * @param expectedTotalHours the expected total number of hours required to complete the project
   * @param expectedExpenses   the expected total expenses for the project
   * @param budget             the project budget
   * @param timeline           the project timeline
   * @param status             the project status
   * @param size               the size of the building in square meters
   * @param numFloor           the number of floors in the building
   * @param intendedUse        the intended use of the building
   */
  public CommercialProject(String name, String description,
      int expectedTotalHours, int expectedExpenses, long budget,
      MyDate timeline, String status, int size, int numFloor,
      String intendedUse)
  {
    super(name, description, expectedTotalHours, expectedExpenses, budget,
        timeline, status);
    this.size = size;
    this.numFloor = numFloor;
    this.intendedUse = intendedUse;
  }

  /**
   * Sets the intended use of the building.
   *
   * @param intendedUse the intended use of the building
   */
  public void setIntendedUse(String intendedUse)
  {
    this.intendedUse = intendedUse;
  }

  /**
   * Returns the intended use of the building.
   *
   * @return the intended use of the building
   */
  public String getIntendedUse()
  {
    return this.intendedUse;
  }

  /**
   * Sets the number of floors in the building.
   *
   * @param numFloor the number of floors in the building
   */
  public void setNumFloor(int numFloor)
  {
    this.numFloor = numFloor;
  }

  /**
   * Returns the number of floors in the building.
   *
   * @return the number of floors in the building
   */
  public int getNumFloor()
  {
    return this.numFloor;
  }

  /**
   * Sets the size of the building in square meters.
   *
   * @param size the size of the building in square meters
   */
  public void setSize(int size)
  {
    this.size = size;
  }

  /**
   * Returns the size of the building in square meters.
   *
   * @return the size of the building in square meters
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
   * Returns a map of the default values for each field of a CommercialProject.
   *
   * @return a map of the default values for each field of a CommercialProject
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
    fields.put("size", Optional.empty());
    fields.put("numFloor", Optional.of(defaults[2]));
    fields.put("intendedUse", Optional.empty());

    return fields;
  }
}
