import java.io.*;
import java.util.*;

/*
  This class is the nexus of all fighting objects, like players and monsters
  
  Methods:
  Combatant(String n, boolean k)
  Stat makeStats(String s)
  ArrayList<Attack> makeAttacks(String s)
  ArrayList<String> getAttacks()
  Attack getAttack(int index)
  Stat getStats()
  int getAttack()
  int getDefense()
  int getSpeed()
  int getHealth()
  int getMaxHealth()
  int getMana()
  int getMaxMana()
  int getExp()
  int getLevel()
  int getSpacesMoved()
  boolean friend()
  boolean incHealth(int amt)
  void incMana(int amt)
  boolean incExp(int amt)
  
*/

public class Combatant extends GameObject {
 
  protected ArrayList<Attack> attackList;
  protected Stat stats;
  protected boolean friend;
  protected int spacesMoved;//number of spaces covered in each move
  
  //this constructs a Combatant with name n and friend k.
  //Then it reads GOInfo, and gives the stuff to the accompanying methods.
  public Combatant(String n, String type, boolean k) {
    super(n);
    friend = k;
    String ref = readGOInfo(type);
    stats = makeStats(ref);
    attackList = makeAttacks(ref);
  }
  
  public Stat makeStats(String s) {
  }
  
  public ArrayList<Attack> makeAttacks(String s) {
  }
  
  //returns all the attacks in attackList
  public ArrayList<Attack> getAttacks() {
    return attackList;
  }
  
  //returns the Attack names
  public ArrayList<String> getAttackNames() {
    ArrayList<String> s = new ArrayList<String>;
    for(Attack a : attackList)
      s.add(a.getName());
    return s;
  }
  
  public Stat getStats() {
    return stats;
  }
  
  //returns the attack at index i in attackList
  public Attack getAttack(int i) {
    return attackList.get(i);
  }
  
  //returns attack from stats
  public int getAttack() {
    return stats.getAttack();
  }
  
  //returns defense from stats
  public int getDefense() {
    return stats.getDefense();
  }
  
  //returns speed from stats
  public int getSpeed() {
    return stats.getSpeed();
  }
  
  //returns health
  public int getHealth() {
    return stats.getHealth();
  }
  
  public int getMaxHealth() {
    return stats.getMaxHealth();
  }
  
  //returns mana
  public int getMana() {
    return stats.getMana();
  }
  
  public int getMaxMana() {
    return stats.getMaxMana();
  }
  
  //returns the exp
  public int getExp() {
    return stats.getExp();
  }
  
  //returns the level
  public int getLevel() {
    return stats.getLevel();
  }
  
  public int getSpacesMoved() {
    return spacesMoved;
  }
  
  public boolean friend() {
    return friend;
  }
  
  //decreases/increases health by amt.
  //If the new value is greater than maxHealth, health is maxHealth
  //If the new value is less than zero, health is zero.
  //If health is 0, return true. else return false.
  public boolean incHealth(int amt) {
    return stats.incHealth(amt);
  }
  
  //decreases/increases mana by amt.
  //If the new value is greater than maxMana, mana is maxMana
  //If the new value is less than zero, mana is zero
  public void incMana(int amt) {
    stats.incMana(amt);
  }
  
  //decreases/increases exp by amt.
  //If the new value is greater than or equal to the exp needed to level up, 
  //exp subtracts that amount and calls the levelUp method. Then in returns true
  //Otherwise it returns false.
  public boolean incExp(int amt) {
    return stats.incExp(amt);
  }
  
}