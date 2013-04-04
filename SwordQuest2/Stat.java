import java.io.*;
import java.util.*;

/*
   This is the container for all player stats except health and mana
   
   Methods:
   Stat(int a, int d, int s, String spec)
   int getAttack()
   int getDefense()
   int getSpeed()
   int getHealth()
   int getMaxHealth()
   int getMana()
   int getMaxMana()
   int getExp()
   int getLevel()
   boolean incHealth(int amt)
   void incMana(int amt)
   boolean incExp(int amt)
   void levelUp(int level)
   
*/

public class Stat {
 
  private int attack, defense, speed, health, maxHealth, mana, maxMana, exp, 
    neededExp, level;
  private String skill;
  
  //makes a stat container with all values as appropriate
  public Stat(int a, int d, int s, int h, int m, int l, String spec) {
    attack = a;
    defense = d;
    speed = s;
    health = maxHealth = h;
    mana = maxMana = m;
    level = l;
    skill = spec;
  }
  
  //returns the Member's attack
  public int getAttack() {
    return attack;
  }
  
  //returns the Member's defense
  public int getDefense() {
    return defense;
  }
  
  //returns the Member's speed
  public int getSpeed() {
    return speed;
  }
  
  //returns the Member's current health
  public int getHealth() {
    return health;
  }
  
  public int getMaxHealth() {
    return maxHealth;
  }
  
  //returns the Member's current mana
  public int getMana() {
    return mana;
  }
  
  public int getMaxMana() {
    return maxMana;
  }
  
  //returns the Member's experience
  public int getExp() {
    return exp;
  }
  
  //returns the Member's level
  public int getLevel() {
    return level;
  }
  
  //see Combatant
  public boolean incHealth(int amt) {
    health += amt;
    if(health > maxHealth)
      health = maxHealth;
    if(health < 0)
      health = 0;
    return health == 0;
  }
  
  //see Combatant
  public void incMana(int amt) {
    mana += amt;
    if(mana > maxMana)
      mana = maxMana;
    if(mana < 0)
      mana = 0;
  }
  
  //see Combatant
  public boolean incExp(int amt) {
    exp += amt;
    if(exp >= neededExp) {
      exp -= neededExp;
      levelUp();
      return true;
    }
    return false;
  }
  
  //This levels up the member it's in. This is affected by a few factors:
  //Higher levels have different stat differences than lower ones.
  //skill is what the Member is strong in; this means it gets a higher stat boost.
  //"attack" is attack, "defense" is defense, etc.
  public void levelUp() {
  }
  
}