import java.io.*;
import java.util.*;

/*
  This class is the items' container
  
  Methods:
  Inventory()
  Inventory(String s)
  ArrayList<Item> getItems()
  Item getItem(int i)
  void addItem(Item i)
  Item removeItem(int i)
  
*/

public class Inventory {
  
  private ArrayList<Item> inventory;
  
  public Inventory() {
    inventory = new ArrayList<Item>();
  }
  
  public Inventory(String s) {
  }
  
  public ArrayList<Item> getItems() {
    return inventory;
  }
  
  public Item getItem(int i) {
    return inventory.get(i);
  }
  
  public void addItem(Item i) {
    for(Item a : inventory) {
      if(i.equals(a)) {
        a.incStock(true);
        return;
      }
    }
    inventory.add(i);
  }
  
  public void removeItem(int i) {
    if(inventory.get(i).getStock() > 0)
      inventory.get(i).incStock(false);
    else
      inventory.remove(i);
  }
  
}