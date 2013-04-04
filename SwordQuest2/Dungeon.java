import java.io.*;
import java.util.*;

/*
  This class is the nexus of all fighting objects, like players and monsters
  
  Methods:
  Dungeon(String n)
  ArrayList<Room>[] getRooms()
  void goToNextRoom(int dir)
  void loadRoom()
  
*/

public class Dungeon extends MapObject {
  
  private MapRunner temp;
  private ArrayList<Room>[] rooms;
  private int x, y;
  
  public Dungeon(String n) {
    name = n;
    x = 0;
    y = 0;
    String s = getGOInfo("DUNGEON");
    //Stuff
  }
  
  public ArrayList<Room>[] getRooms() {
    return rooms;
  }
  
  public void goToNextRoom(int dir) {
    if(dir == 1)
      y++;
    else if(dir = 2)
      y--;
    else if(dir = 3)
      x--;
    else
      x++;
    loadRoom();
  }
  
  public void loadRoom() {
    //gets all stuff out of Room at x, y and projects it.
  }
  
}