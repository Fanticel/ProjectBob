package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class IndustrialProject extends Project {
  private int size;
  private String type;

  public IndustrialProject(String name, String description,
      int expectedTotalHours, int expectedExpenses, long budget,
      MyDate timeline, String status, String type, int size) {
    super(name, description, expectedTotalHours, expectedExpenses, budget,
        timeline, status);
    this.size = size;
    this.type = type;

  }

  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return this.type;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int getSize() {
    return this.size;
  }

  @Override public String toString() {
    return super.toString();
  }

  public static Map<String, Optional<Object>> getDefaults(){
    Map<String, Optional<Object>> fields = new HashMap<>();

    return fields;
  }
}
