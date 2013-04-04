import java.io.*;
import java.util.*;

/*
  This class is the structure which all attacks follow
  
  Methods:
  Attack(String t, double mult, int c)
  double getMultiplier()
  int getCost()
  String getName()
  
*/

public class Attack {
  
  private String type;
  private double multiplier;
  private int cost;
  
  public Attack(String t, double mult, int c) {
    type = t;
    multiplier = mult;
    cost = c;
  }
  
  //returns the attack multiplier
  public double getMultiplier() {
    return multiplier;
  }
  
  //returns the mana cost of the attack
  public int getCost() {
    return cost;
  }
  
  public String getName() {
    return type + ": " + cost;
  }
  
}