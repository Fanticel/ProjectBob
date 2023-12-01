package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class IndustrialProject extends Project
{
  private int size;
  private String type;

  private static final Object[] defaults = {350000, 9, 1, 1, 1, "new build"};

  public IndustrialProject(String name, String description,
      int expectedTotalHours, int expectedExpenses, long budget,
      MyDate timeline, String status, String type, int size)
  {
    super(name, description, expectedTotalHours, expectedExpenses, budget,
        timeline, status);
    this.size = size;
    this.type = type;

  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getType()
  {
    return this.type;
  }

  public void setSize(int size)
  {
    this.size = size;
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
    fields.put("status", Optional.of(defaults[2]));
    fields.put("isNewBuild", Optional.of(defaults[3]));
    fields.put("type", Optional.of(defaults[4]));
    fields.put("size", Optional.of(defaults[5]));

    return fields;
  }
}
