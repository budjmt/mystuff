import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
   This class runs all monster functions.
   
   for each particular geographic setting, every possible monster was put
into an array with weaker monsters towards the left and stronger
monsters towards the right. All you have to do is find the index of
the monster you encountered and then we had an array of ints size 50
that had 0's, 1's, 2's etc with decreasing frequency and then all you
had to do was randomly select one of those numbers, then you have a
50% chance of adding or subtracting that value from your original
index and then whatever you get is the new monster to add into battle.
You keep doing this until you get enough monsters.
   
   Methods:
   public MonsterRunner()
   public MonsterRunner(String save)
   public ArrayList<String> save()
   public void moveMonsters()
   public int findHero(GNode v, GNode h, int sight)
   public void spawnMonsters(int level)
   public int findIndex(int level)
   public Monster getRandomMonster()
   public GNode getLoc()
   
*/

public class MonsterRunner {
  
  private ArrayList<String> zoneMonsters;
  private ArrayList<Monster> currMonsters;
  private ArrayList<GNode> monstVert, monstHoriz;
  
  public MonsterRunner() {
  }
  
  public MonsterRunner(String save) {
  }
  
  public String save() {
  }
  
  public void moveMonsters(GNode vert, GNode horiz) {
    for(int i = 0;i < currMonsters.size();i++) {
      GNode v = monstVert.get(i);
      GNode h = monstHoriz.get(i);
      int dir = findHero(v, h, currMonsters.get(i).getSight());
      if(dir != 0)
          MapRunner.move(v, h, dir, currMonsters.get(i).getSpacesMoved());
      }
  }
  
  //returns the shorter dir from the monster to the hero, either vert or horiz
  public int findHero(GNode v, GNode h, int sight) {
    int vd, hd;
    int vdir = 0;
    int hdir = 0;
    //checks up vs down, uses least dist
    //checks left vs right, same
    if(vd < hd)
      return vdir;
    else
      return hdir;
  }
  
  public void spawnMonsters(int level) {
    for(int i = 0;i < 5;i++) {
      Monster m = new Monster(zoneMonsters.get(findIndex(level)), level);
      currMonsters.add(m);
      int dir = (int)(Math.random() * 4) + 1;
      Object[] o = MapRunner.traverse(dir, MapRunner.DRAWDIST);
      GNode g = o[0];
      g.setSpaces(o[1]);
      //place a node in vert and horiz appropriately
    }
  }
  
  public int findIndex(int level) {
    int index = (int)(Math.random() * 2);
    int extra = level;
    while(extra >= 1) {
      extra /= 10;
      index += 1;
      if(index == zoneMonsters.size())
        index -= zoneMonsters.size();
    }
    if(extra > Math.random())
      index += 1;
    return index;
  }
  
  public ArrayList<GNode> getMonstVert() {
    return monstVert;
  }
  
  public ArrayList<GNode> getMonstHoriz() {
    return monstHoriz;
  }
  
  public void getZoneMonsters(String zone) {
    if(zone == "GRASS")
      zoneMonsters = {"Slime", "Bull", "Thief", "Balrog"};
    else if(zone == "SAND")
      zoneMonsters = {"Sand Fish", "DragonFly"};
    else if(zone == "ROCK")
      zoneMonsters = {"RockMan", "LavaBeast"};
    else if(zone == "NONE")
      zoneMonsters = null;
  }
  
  public Monster getRandomMonster() {
    return new Monster(zoneMonsters.get
                         ((int)(Math.random() * zoneMonsters.size())));
  }
  
}