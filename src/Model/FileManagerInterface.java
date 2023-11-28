package Model;

import java.io.IOException;

public interface FileManagerInterface {
  void writeToFile(String file, ProjectList list) throws IOException;
  ProjectList readFromFile(String file) throws IOException;
}
