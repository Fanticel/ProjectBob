package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommercialProject extends Project {
  private int size;
  private int numFloor;
  private String intendedUse;

  public CommercialProject(String name, String description,
      int expectedTotalHours, int expectedExpenses, long budget,
      MyDate timeline, String status, int size, int numFloor,
      String intendedUse) {
    super(name, description, expectedTotalHours, expectedExpenses, budget,
        timeline, status);
    this.size = size;
    this.numFloor = numFloor;
    this.intendedUse = intendedUse;

  }

  public void setIntendedUse(String intendedUse) {
    this.intendedUse = " ";

  }

  public String getIntendedUse() {
    return this.intendedUse;
  }

  public void setNumFloor(int numFloor) {
    this.numFloor = 0;
  }

  public int getNumFloor() {
    return this.numFloor;
  }

  public void setSize(int size) {
    this.size = 0;
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
