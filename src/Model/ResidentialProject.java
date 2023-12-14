//Made by Anthony Richards
package Model;

import Model.Project;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ResidentialProject extends Project
{
  private int size;

  private int numKitchens;
  private int numBathrooms;
  private int othWPlumbing;
  private boolean isNewBuild;
  private static final Object[] defaults = {350000, 9, 1, 1, 1, "new build"};

  public ResidentialProject(String name, String description,
      int expectedTotalHours, int expectedExpenses, long budget,
      MyDate timeline, String status, int size, int numKitchens, int numBathrooms,
      int othWPlumbing, boolean isNewBuild)
  {
    super(name, description, expectedTotalHours, expectedExpenses, budget,
        timeline, status);
    this.size = size;
    this.numKitchens = numKitchens;
    this.numBathrooms = numBathrooms;
    this.othWPlumbing = othWPlumbing;
    this.isNewBuild = isNewBuild;

  }

  public void setSize(int size)
  {
    this.size = size;
  }

  public int getSize()
  {
    return size;
  }

  public void setNumKitchens(int numKitchens)
  {
    this.numKitchens = numKitchens;
  }

  public int getNumKitchens()
  {
    return numKitchens;
  }

  public void setNumBathrooms(int numBathrooms)
  {
    this.numBathrooms = numBathrooms;
  }

  public int getNumBathrooms()
  {
    return this.numBathrooms;
  }

  public void setOthWPlumbing(int othWPlumbing)
  {
    this.othWPlumbing = othWPlumbing;
  }

  public int getOthWPlumbing()
  {
    return this.othWPlumbing;
  }

  public void setNewBuild(boolean isNewBuild)
  {
    this.isNewBuild = isNewBuild;
  }

  public boolean isNewBuild()
  {
    return isNewBuild;
  }

  @Override public String toString()
  {
    return super.toString();

  }
  public static Map<String, Optional<Object>> getDefaults(){
    Map<String, Optional<Object>> fields = new HashMap<>();
    fields.put("name", Optional.empty());
    fields.put("description", Optional.empty());
    fields.put("expectedTotalHours", Optional.empty());
    fields.put("expectedExpenses", Optional.empty());
    fields.put("budget", Optional.of(defaults[0]));
    fields.put("timeline", Optional.of(defaults[1]));
    fields.put("size", Optional.empty());
    fields.put("numKitchens", Optional.of(defaults[2]));
    fields.put("numBathrooms", Optional.of(defaults[3]));
    fields.put("othWPlumbing", Optional.of(defaults[4]));
    fields.put("isNewBuild", Optional.of(defaults[5]));

    return fields;
  }
}

