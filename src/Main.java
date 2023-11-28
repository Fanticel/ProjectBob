import Model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    FileManagerXML fileManagerXML = new FileManagerXML();
    ProjectList projectList = null;
    try{
      projectList = fileManagerXML.readFromFile("Save.xml");
    }catch (IOException e){
      System.out.println(e.getMessage());
    }
    for (Project i :
            projectList.returnAsArrayList()) {
      System.out.println(i);
    }
  }
}
