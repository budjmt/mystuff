import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
   This class is the main game runner.
   It uses the individual engines to handle specific things, but gives them cues.
   It is also the shell for the whole game.
   However, the actual game file cues this - it isn't the real game.
   
   Methods:
   GameRunner(JFrame j)
   GameRunner(JFrame j, String save)
   void save()
   void keyPressed(KeyEvent e)
   void keyReleased(KeyEvent e)
   PlayerRunner getPlayerRunner()
   MonsterRunner getMonsterRunner()
   MapRunner getMapRunner()
   MapObject getTempRunner()
   void monsterCue()
   void cycle()
   boolean collision()
   void battle()
   void menu
   void pause()
   void unpause()
   
*/

public class GameRunner implements KeyListener {
  
  private MapRunner map;
  private PlayerRunner player;
  private MonsterRunner monsters;
  private MapObject tempRunner;
  private JFrame game;
  private boolean paused;
  //some variable tracking progress
  
  //This constructor is for a new game
  public GameRunner(JFrame j) {
    map = new MapRunner();
    player = new PlayerRunner();
    monsters = new MonsterRunner();
    tempRunner = null;
    game = j;
    paused = false;
    //add elements to game
  }
  
  //This is for loading a previous save file - which file is managed in game file
  public GameRunner(JFrame j, String save) {
    game = j;
    //other stuff
  }
  
  //Calls the save methods in each instance variable, then records them all in a 
  //save file
  public void save() {
  }
  
  //Triggers the menu and movement
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_UP)
      map.scroll(1);
    else if(e.getKeyCode() == KeyEvent.VK_DOWN)
      map.scroll(2);
    else if(e.getKeyCode() == KeyEvent.VK_LEFT)
      map.scroll(3);
    else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
      map.scroll(4);
    else if(e.getKeyCode() == KeyEvent.VK_ENTER)
      menu();
  }
  
  public void keyReleased(KeyEvent e) {
    return;
  }
  
  public void keyTyped(KeyEvent e) {
    return;
  }
  
  public PlayerRunner getPlayerRunner() {
    return player;
  }
  
  public MonsterRunner getMonsterRunner() {
    return monsters;
  }
  
  public MapRunner getMapRunner() {
    return map;
  }
  
  public MapObject getTempRunner() {
    return tempRunner;
  }
  
  //This gives the monsters the coordinates of the hero, then the MonsterRunner acts
  public void monsterCue() {
  }
  
  //This calls monsterCue continuously as long as these conditions are met:
  //-The hero is on the map and not in a town
  //[Within MonsterRunner's method, it checks if there are actually any monsters]
  //Then the remaining monsters are killed (if any) and if the hero isn't in a town,
  //after about 40 seconds new monsters are spawned.
  public void cycle() {
    while(!paused && monsters.getCurrMonsters()!= null && tempRunner == null)
      monsterCue();
    if(!paused)
      monsters.killAllMonsters();
    if(!paused && monsters.getCurrMonsters() == null && tempRunner == null) {
      delay();
      monsterCue();
    }
  }
  
  public void delay() {
    long start = System.currentTimeMillis();
    while(System.currentTimeMillis() - start < 40000) {
    }
  }
  
  //This cross-references the MonsterRunner LinkedList locs with the PlayerRunner's
  //If they are next to or on top of each other, then it returns true. Else, false.
  public boolean collision() {
    if(map.collision() == null)
      return false;
    return true;
  }//EDIT
  
  //In cycle, checks for collision first; if true, then main game is paused, 
  //the monster, data is loaded, the hero data is loaded, 
  //and the BattleRunner is created.
  public void battle(Monster m) {
    pause();
    JPanel j = new JPanel();
    BattleRunner b = new BattleRunner(this, j, m);
    //Need BR to finalize.
    unpause();
    cycle();
  }
  
  //An earlier method activates this, a MenuRunner is created with the GameRunner,
  //and the game is paused
  public void menu() {
    pause();
    JPanel j = new JPanel();
    MenuRunner m = new MenuRunner(this, j);
    //Need MR to finalize.
    unpause();
    cycle();
  }
  
  //This halts all in-game processes. Used for all engines running on top of the
  //main game(battle, menu, text, etc.)
  public void pause() {
    paused = true;
  }
  
  //This undoes pause()
  public void unpause() {
    paused = false;
  }
  
}