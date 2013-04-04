import java.io.*;
import java.util.*;

public class RoomInventory {
  
  private Item[] roomItems;
  
  public RoomInventory() {
    roomItems = new Item[40];
    for(int i = 0;i < roomItems.length;i++)
      roomItems[i] = new Item();
    itemsSetup();
  }
  
  public void itemsSetup() {
    int room = 0;
    roomItems[room].add("CARD");
    room = 3;
    roomItems[room].add("KNIFE");
    room = 5;
    roomItems[room].add("BREAD KEY");
    room = 6;
    roomItems[room].add("VASE KNOB");
    room = 7;
    roomItems[room].add("PANTRY KNOB");
    room = 11;
    roomItems[room].add("FANCY KEY");
    room = 12;
    roomItems[room].add("PLUG");
    room = 15;
    roomItems[room].add("OLD KEY");
    room = 16;
    roomItems[room].add("BANK KEY");
    room = 19;
    roomItems[room].add("TROPHY KEY");
    room = 22;
    roomItems[room].add("BRICK KEY");
    room = 24;
    roomItems[room].add("SHEET MUSIC");
    room = 25;
    roomItems[room].add("LIVING ROOM KNOB");
    room = 26;
    roomItems[room].add("POKER CHIP");
    room = 27;
    roomItems[room].add("PHONEBOOK");
    room = 29;
    roomItems[room].add("BAR KEY");
    room = 35;
    roomItems[room].add("CELLAR KEY");
  }
  
  public String removeItem(int room) {
    return roomItems[room].remove(0);
  }
  
  public String getItem(int room) {
    return roomItems[room].get(0);
  }
  
  public int getSize(int room) {
    return roomItems[room].size();
  }
  
  public boolean itemTaken(String item) {
    for(int i = 0;i < roomItems.length;i++) {
      for(int r = 0;r < roomItems[i].size();r++) {
        if(roomItems[i].get(r).equals(item))
          return false;
      }
    }
    return true;
  }
}