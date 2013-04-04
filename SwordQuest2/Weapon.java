import java.io.*;
import java.util.*;

/*
  This class is the basis of all kinds of weapons
  
  Methods:
  Weapon(String n)
  int getMultiplier()
  String getEquipper()
  ArrayList<Attack> getAttacks()
  
*/

public class Weapon extends Item {
  
  private int multiplier;
  private String equipper;
  private ArrayList<Attack> attacks;
  
  public Weapon(String n, int st) {
    super(n, st);
    equippable = true;
    String s = getItemInfo("WEAPON");
    //Assignments
  }
 
  public int getMultiplier() {
    return multiplier;
  }
  
  public String getEquipper() {
    return equipper;
  }
  
  public ArrayList<Attack> getAttacks() {
    return attacks;
  }
  
}