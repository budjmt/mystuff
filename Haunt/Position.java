import java.io.*;
import java.util.*;

public class Position {
  
  private int[][] rooms1, rooms2;
  private RoomStory reference;
  private RoomInventory roomItems;
  private ArrayList<String> inventory;
  private int currRoom;
  private boolean[][] moves;
  private boolean[] solvent;
  
  public Position() {
    rooms1 = new int[8][7];
    rooms2 = new int[4][3];
    reference = new RoomStory();
    roomItems = new RoomInventory();
    inventory = new ArrayList<String>();
    currRoom = 0;
    moves = new boolean[40][4];
    solvent = new boolean[40];
    floor1Setup();
    floor2Setup();
    movesSetup();
    solvedSync();
  }
  
  public int getCurrRoom() {
    return currRoom;
  }
  
  public void floor1Setup() {
    for(int i = 0;i < rooms1.length;i++) {
      for(int r = 0;r < rooms1[i].length;r++)
        rooms1[i][r] = -1;
    }
    rooms1[7][3] = 0;
    rooms1[7][2] = 1;
    rooms1[7][1] = 2;
    rooms1[7][0] = 3;
    rooms1[6][1] = 4;
    rooms1[5][1] = 5;
    rooms1[5][0] = 6;
    rooms1[4][1] = 7;
    rooms1[4][0] = 8;
    rooms1[3][0] = 9;
    rooms1[2][0] = 10;
    rooms1[1][0] = 11;
    rooms1[2][1] = 12;
    rooms1[2][2] = 13;
    rooms1[2][3] = 14;
    rooms1[3][3] = 15;
    rooms1[3][4] = 16;
    rooms1[2][4] = 17;
    rooms1[1][4] = 18;
    rooms1[1][5] = 19;
    rooms1[1][6] = 20;
    rooms1[0][6] = 21;
    rooms1[7][4] = 22;
    rooms1[7][5] = 23;
    rooms1[6][5] = 24;
    rooms1[5][5] = 25;
    rooms1[4][5] = 26;
    rooms1[4][6] = 27;
    rooms1[3][6] = 28;
    rooms1[2][6] = 29;
    rooms1[3][5] = 30;
  }
  
  public void floor2Setup() {
    for(int i = 0;i < rooms2.length;i++) {
      for(int r = 0;r < rooms2[i].length;r++)
        rooms2[i][r] = -1;
    }  
    rooms2[0][1] = 31;
    rooms2[1][1] = 32;
    rooms2[2][1] = 33;
    rooms2[3][1] = 34;
    rooms2[3][0] = 35;
    rooms2[2][0] = 36;
    rooms2[1][0] = 37;
    rooms2[0][0] = 38;
    rooms2[0][2] = 39;
  }
  
  public void movesSetup() {
    for(int i = 0;i < reference.getLocksLength(0);i++) {
      for(int r = 0;r < reference.getLocksLength(1);r++)
        moves[i][r] = reference.getLocks(i, r);
    }
  }
  
  public boolean getSolved(int room) {
    return solvent[room];
  }
  
  public void solvedSync() {
    for(int i = 0;i < reference.getSolvedLength();i++) {
        solvent[i] = reference.getSolved(i);
    }
  }
  
  public void solvedChange(int room) {
    solvent[room] = true;
  }
  
  public void solvedChange() {
    solvent[currRoom] = true;
  }
  
  public void unlockDoor(int door) {
    if(solvent[currRoom])
      moves[currRoom][door] = true;
  }
  
  public void unlockDoor(int room, int door) {
    if(solvent[room])
      moves[room][door] = true;
  }
    
    public int[] findCoord() {
      int[] coord = new int[2];
      if(currRoom < 31) {
        for(int i = 0;i < rooms1.length;i++) {
          for(int r = 0;r < rooms1[i].length;r++) {
            if(currRoom == rooms1[i][r]) {
              coord[0] = i;
              coord[1] = r;
            }
          }
        }
      }
      else {
        for(int i = 0;i < rooms2.length;i++) {
          for(int r = 0;r < rooms2[i].length;r++) {
            if(currRoom == rooms2[i][r]) {
              coord[0] = i;
              coord[1] = r;
            }
          }
        }
      }
      return coord;
    }
    
    public String basicLook(int stage) {
      if(stage == 0)
        return reference.getLook(currRoom, 0);
      else if(stage == 1)
        return reference.getLook(currRoom, 1);
      return "You're hopeless...";
    }
    
    public String getRelLookText(String o) {
      String s = "You can't LOOK at that.";
      int index = -1;
      for(int i = 2;i < reference.getLCLength(currRoom);i++) {
        if(o.equals(reference.getLookCue(currRoom, i)))
          index = i;
      }
      if(index < 0)
        return s;
      else {
        s = reference.getLook(currRoom, index);
        return s;
      }
    }
    
    public String getRelGetText(String o) {
      String s = "You can't GET that.";
      int index = -1;
      for(int i = 0;i < reference.getGCLength(currRoom);i++) {
        if(o.equals(reference.getGetCue(currRoom, i)))
          index = i;
      }
      if(index < 0)
        return s;
      else {
        s = reference.getGet(currRoom, index);
        return s;
      }
    }
    
    public String getUse(int i) {
      return reference.getUse(currRoom, i);
    }
    
    public String getRelUseText(String o) {
      String s = "You can't USE that.";
      int index = -1;
      for(int i = 0;i < reference.getUCLength(currRoom);i++) {
        if(o.equals(reference.getUseCue(currRoom, i)))
          index = i;
      }
      if(index < 0)
        return s;
      else {
        s = reference.getUse(currRoom, index);
        return s;
      }
    }
    
    public String getGoodEnd1() {
      String s = reference.getUse(currRoom, 1);
      if(currRoom == 34) {
        removeUse("LEFT DOOR"); removeUse("LEFT DOOR");
      }
      else {
        removeUse("NORTH DOOR"); removeUse("NORTH DOOR");
      }
      return s;
    }
    
    public String getGoodEnd2() {
      return reference.getLook(currRoom, 2);
    }
    
    public String getRelSolveText() {
      return reference.getSolve(currRoom);
    }
    
    public String moveNorm(int dir) {
      int[] coord = findCoord();
      int y = coord[0];
      int x = coord[1];
      if(moves[currRoom][dir]) {
        if(currRoom < 31) {
          if(dir == 0)
            currRoom = rooms1[y - 1][x];
          else if(dir == 1)
            currRoom = rooms1[y][x + 1];
          else if(dir == 2)
            currRoom = rooms1[y + 1][x];
          else
            currRoom = rooms1[y][x - 1];
          return basicLook(0);
        }
        else {
          if(dir == 0)
            currRoom = rooms2[y - 1][x];
          else if(dir == 1)
            currRoom = rooms2[y][x + 1];
          else if(dir == 2)
            currRoom = rooms2[y + 1][x];
          else
            currRoom = rooms2[y][x - 1];
          return basicLook(0);
        }
      }
      return "invalid";
    }
    
    public void removeUse(String s) {
      reference.removeUC(currRoom, s);
    }
    
    public void removeGet(String s) {
      reference.removeGC(currRoom, s);
    }
    
    public void setRoom(int room) {
      currRoom = room;
    }
    
    public void addItem(String s) {
      inventory.add(s);
    }
    
    public boolean pickUp(String item) {
      if(roomItems.getSize(currRoom) > 0) {
        if(((currRoom == 5 || currRoom == 11 || currRoom == 15 || currRoom == 16 || 
           currRoom == 19 || currRoom == 22 || currRoom == 29 || currRoom == 35) &&
           item.equals("KEY")) || 
           ((currRoom == 7 || currRoom == 6 || currRoom == 25) 
              && item.equals("KNOB")) ||
             roomItems.getItem(currRoom).equals(item)) {
          inventory.add(roomItems.removeItem(currRoom));
          if(itemIn("OLD KEY")) {
            solvedChange(34);
            unlockDoor(34, 3);
          }
          if(itemIn("BANK KEY")) {
            solvedChange(36);
            unlockDoor(36, 0);
          }
          if(itemIn("FANCY KEY"))
            solvedChange(38);
          return true;
        }
      }
      return false;
    }
    
    public int getInventoryLength() {
      return inventory.size();
    }
    
    public String getItem(int i) {
      return inventory.get(i);
    }
    
    public boolean isPickedUp(String item) {
      return roomItems.itemTaken(item);
    }
    
    public boolean itemIn(String item) {
      for(int i = 0;i < getInventoryLength();i++) {
        if(item.equals(getItem(i)))
          return true;
      }
      return false;
    }
    
    public String moveSpecial() {
      if(currRoom == 21) {
        currRoom = 31;
        return basicLook(0);
      }
      else if(currRoom == 31) {
        currRoom = 21;
        return basicLook(0);
      }
      else if(currRoom == 30) {
        currRoom = 39;
        return basicLook(0);
      }
      else if(currRoom == 39) {
        currRoom = 30;
        return basicLook(0);
      }
      return "You're doing it wrong!";
    }
}