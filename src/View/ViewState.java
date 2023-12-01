package View;

import java.util.ArrayList;

public class ViewState {
  private ArrayList<Object> data;
  public ViewState(){
    data = null;
  }

  public ArrayList<Object> getData() {
    return data;
  }

  public void setData(ArrayList<Object> data) {
    this.data = data;
  }
  public void reset(){
    data = null;
  }
}
