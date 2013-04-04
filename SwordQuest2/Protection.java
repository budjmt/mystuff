import java.io.*;
import java.util.*;

/*
  This class is the basis of all shields and armors
  
  Methods:
  Protection(String n)
  int getBoost()
  boolean shield()
  
*/

public class Protection extends Item {
  
  private boolean shield;
  private int boost;
  
  public Protection(String n, int st) {
    super(n, st);
    equippable = true;
    String s = getItemInfo("PROTECTION");
    //Application
  }
  
  public int getBoost() {
    return boost;
  }
  
  public boolean shield() {
    return shield;
  }
  
}