import java.io.*;
import java.util.*;

/*
   This class is the root of all items.
   
   Methods:
   Item(String n)
   String getName()
   int getNetStock()
   int getStock()
   int getNumEquipped()
   boolean equippable()
   String getDescription()
   void incStock(boolean got)
   void incEquipped(boolean putOn)
   String getItemInfo(String type)
   
 */

public class Item {
  
  protected String name, description;
  protected int stock;
  protected int numEquipped = 0;
  protected boolean equippable;
  
  //constructs an Item with name n
  public Item(String n, int s) {
    name = n;
    stock = s;
  }
  
  //returns the item's name and the number of it possessed
  public String getName() {
    return name + " x " + getNetStock();
  }
  
  //returns the number of the item possessed
  public int getNetStock() {
    return stock + numEquipped;
  }
  
  public int getStock() {
    return stock;
  }
  
  public int getNumEquipped() {
    return numEquipped;
  }
  
  public boolean equippable() {
    return equippable;
  }
  
  public String getDescription() {
    return description;
  }
  
  //increments the number of the item, either if it's been gotten or 
  //if it's been used
  public void incStock(boolean got) {
    if(got)
      stock++;
    else
      stock--;
  }
  
  public void incEquipped(boolean putOn) {
    if(putOn)
      numEquipped++;
    else
      numEquipped--;
  }
  
  //This is the key method for all Items, as without it they would be unable
  //to get their information. All this does is get the String of the relevant
  //qualities for the Item (specified by type)
  //calling it through their name. Subsequent classes clarify assignment.
  public String getItemInfo(String type) {
  }
  
}