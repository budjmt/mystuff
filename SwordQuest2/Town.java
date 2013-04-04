import java.io.*;
import java.util.*;

/*
  This class is the basis of all towns on the map
  
  Methods:
  Town(String n)
  ImageMan getBackround();
  GNode getBuilding()
  GNode getPerson()
  
*/

public class Town extends MapObject {
  
  private ImageMan background;
  private GNode building;
  private GNode person;
  
  public Town(String n) {
    name = n;
    String s = getGOInfo("TOWN");
    //search
  }
  
  public ImageMan getBackground() {
    return background;
  }
  
  public GNode getBuilding() {
    return building;
  }
  
  public GNode getPerson() {
    return person;
  }
  
}