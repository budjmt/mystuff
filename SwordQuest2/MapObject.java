import java.io.*;
import java.util.*;

/*
  This class is the nexus of all map objects, like towns, dungeons, and obstacles
  
  Methods:
  MapObject(String n)
  boolean passable()
  
*/

public class MapObject extends GameObject {
  
  private boolean passable;
  
  public MapObject(String n, String type, boolean b) {
    super(n);
    passable = b;
    String s = readGOInfo(type);
    //Search and retrieve
  }
  
  public boolean passable() {
    return passable;
  }
  
}