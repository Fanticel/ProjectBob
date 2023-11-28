package Model;

public class IndustrialProject extends Project
{
  private int size;
  private String type;
  public IndustrialProject(String name, String description,
      int expectedTotalHours, int expectedExpenses, long budget, MyDate timeline)
  {
    super(name, description, expectedTotalHours, expectedExpenses, budget,
        timeline);
    this.type = "Industrial";
    this.size = 0;
  }
  public void setType(String type){
    this.type = type;
  }
  public String getType(){
    return this.type;
  }
  public void setSize(int size){
    this.size = size;
  }
  public int getSize(){
    return this.size;
  }

}
