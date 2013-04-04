import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
   This class is the player runner.
   It stores all relevant data for the player, including stats and party members.
   
   Methods:
   PlayerRunner()
   PlayerRunner(String save)
   ArrayList<String> save()
   Party getParty()
   ArrayList<Member> getMembers()
   int getNetLevel()
   void addToParty(Member newGuy)
   Inventory getInv()
   void addToInv(Item i)
   void removeFromInv(Item i)
   void usePotion(Potion p)
   void usePotion(Potion p, Member m)
   void equip(Member m, Item i)
   ArrayList<Stat> getStats()
   
*/

public class PlayerRunner {
  
  private Party party;
  private Inventory inventory;
  private int gold;
  private boolean paused;
  
  public PlayerRunner() {
  }
  
  public PlayerRunner(String save) {
  }
   
  public ArrayList<String> save() {
  }
  
  public Party getParty() {
    return party;
  }
  
  public ArrayList<Member> getMembers() {
    return party.getMembers();
  }
  
  public int getNetLevel() {
    int i = 0;
    for(Member m : party.getMembers())
      i += m.getLevel();
    return i;
  }
  
  public void addToParty(Member newGuy) {
    party.add(newGuy);
  }
  
  public Inventory getInv() {
    return inventory;
  }
  
  public void addToInv(Item i) {
    inventory.addItem(i);
  }
   
  public void removeFromInv(int i) {
    inventory.removeItem(i);
  }
  
  public void usePotion(int i) {
    Potion p = (Potion)inventory.getItem(i);
    for(Member m : party.getMembers())
      usePotion(p);
    removeFromInv(i);
  }
  
  public void usePotion(int i, Member m) {
    Potion p = (Potion)inventory.getItem(i);
    m.usePotion(p);
    removeFromInv(i);
  }
  
  public void equip(Member m, Item i) {
    i.incStock(false);
    i.incEquipped(true);
    Item a = m.equip(i);
    a.incStock(true);
    a.incEquipped(false);
  }
   
  public ArrayList<Stat> getStats() {
    ArrayList<Member> p = party.getMembers();
    ArrayList<Stat> s = new ArrayList<Stat>();
    for(Member m : p)
      s.add(m.getStats());
    return s;
  }
   
}