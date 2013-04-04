import java.io.*;
import java.util.*;

/*
  This class is all of the people's framework.
  
  Methods:
  Person(String n)
  String[] getLines()
  void setCue(int i)
  void setLength(int i)
  
*/

public class Person extends GameObject {
  
  private String[] lines;
  private int cue, length;
  
  public Person(String n) {
    super(n);
    cue = 0;
    String s = getGOInfo("PERSON");
    //Application
  }
  
  public String[] getLines() {
    String[] s = new String[cue + length - 1];
    int c = 0;
    for(int i = cue;i < cue + length - 1;i++) {
      s[c] = lines[i];
      c++;
    }
    return s;
  }
  
  public void setCue(int i) {
    cue = i;
  }
  
  public void setLength(int i) {
    length = i;
  }
  
}