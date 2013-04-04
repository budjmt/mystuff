import java.io.*;
import java.util.*;

/*
  This class is the basis of all kinds of potions
  
  Methods:
  Potion(String n)
  int getHeal()
  boolean magic()
  boolean everyone()
  
*/

public class Potion extends Item {
  
  private int healFactor;
  private boolean magic, everyone;
  
  //constructs an item with the appropriate information based on its name, n
  public Potion(String n, int st) {
    super(n, st);
    equippable = false;
    String ref = getItemInfo("POTION");
    //This is where the relevant info is retrieved
  }
  
  //returns how much the potion is supposed to heal
  public int getHeal() {
    return healFactor;
  }
  
  //returns whether or not the item heals everyone
  public boolean everyone() {
    return everyone;
  }
  
  //returns whether or not the potion heals mana
  public boolean magic() {
    return magic;
  }
  
}