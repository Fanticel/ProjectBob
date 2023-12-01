package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommercialProject extends Project
{
  private int size;
  private int numFloor;
  private String intendedUse;
  private static final Object[] defaults = {600000, 18, 1};

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

  public void setIntendedUse(String intendedUse)
  {
    this.intendedUse = " ";

  }

  public String getIntendedUse()
  {
    return this.intendedUse;
  }

  public void setNumFloor(int numFloor)
  {
    this.numFloor = 0;
  }

  public int getNumFloor()
  {
    return this.numFloor;
  }

  public void setSize(int size)
  {
    this.size = 0;
  }

  public int getSize()
  {
    return this.size;
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
    fields.put("size", Optional.empty());
    fields.put("numFloor", Optional.of(defaults[2]));
    fields.put("intendedUse", Optional.empty());

    return fields;
  }
}
