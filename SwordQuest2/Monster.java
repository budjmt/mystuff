import java.io.*;
import java.util.*;

/*
  This class is the nexus of all fighting objects, like players and monsters
  
  Methods:
  Monster(String n)
  Item getDrop(int i)
  boolean getBehavior()
  int getRank()
  double getMultiplier(int i)
  int getSight()
  void determineBehavior(int heroLevel)
  boolean incDeathCount(boolean b)
  
*/

public class Monster extends Combatant {
  
  private ArrayList<Item> drops;
  private ArrayList<Double> odds;
  protected boolean behavior;
  protected int rank, sightLength, deathCount;
  public static final int X = 20;
  
  public Monster(String n, int heroLevel) {
    super(n, "MONSTER", false);
    deathCount = X;
    //other stuff
    determineBehavior(heroLevel);
  }
  
  public Item getDrop(int i) {
    if(i >= drops.size())
      return null;
    if(odds.get(i) < Math.random())
      return drops.get(i);
    return null;
  }
  
  //if behavior is true, the monster will run after the player. Else, it runs away
  public boolean getBehavior() {
    return behavior;
  }
  
  public int getRank() {
    return rank;
  }
  
  public double getMultiplier(int i) {
    return getAttack(i).getMultiplier();
  }
  
  public int getSight() {
    return sightLength;
  }
  
  public void determineBehavior(int heroLevel) {
  }
  
  public boolean incDeathCount(boolean b) {
    if(deathCount == 0)
      return true;
    if(!b) {
      deathCount--;
      return false;
    }
    deathCount = X;
    return false;
  }
  
}