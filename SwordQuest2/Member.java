import java.io.*;
import java.util.*;

/*
   This is the basis of all party members.
   
   Methods:
   Member(String n)
   boolean isEquippable(Item i)
   Weapon getWeapon()
   Protection getShield()
   Protection getArmor()
   Item equip(Item i)
   void usePotion(Potion p)
   void updateAttacks()
   boolean incXp(int xp)
   
 */

public class Member extends Combatant {
  
  private Weapon weapon;
  private Protection shield, armor;
  
  //constructs a GameObject with name n
  public Member(String n, String stuff) {
    super(n, "MEMBER", true);
    spacesMoved = 1;
    if(stuff.equals(""))
      return;
    //This is where stuff goes into the instances
  }
  
  public boolean isEquippable(Item i) {
    if(i instanceof Protection) {
      Protection prot = (Protection) i;
      if(!prot.shield() && name == "Gog")
        return false;
      return true;
    }
    else {
      Weapon wep = (Weapon) i;
      (wep.getEquipper() == name)
        return true;
      return false;
    }
  }
  
  public Weapon getWeapon() {
    return weapon;
  }
  
  public Protection getShield() {
    return shield;
  }
  
  public Protection getArmor() {
    return armor;
  }
  
  public Item equip(Item i) {
    Item curr;
    if(i instanceof Weapon) {
      curr = weapon;
      weapon = (Weapon)i;
      updateAttacks();
    }
    else {
      Protection use = (Protection) i;
      if(use.shield()) {
        curr = shield;
        shield = use;
      }
      else {
        curr = armor;
        armor = use;
      }
    }
    return curr;
  }
  
  public void usePotion(Potion p) {
    if(!p.magic())
      getStats().incHealth(p.getHeal());
    else
      getStats().incMana(p.getHeal());
  }
  
  public void updateAttacks() {
    attackList = weapon.getAttacks();
  }
  
  public boolean incExp(int amt) {
    return getStats().incExp(amt);
  }
  
}