import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 It's the game. And you just lost. This is the main game runner. Hence the title.
 
 Methods:
 SwordQuest2()
 void titleScreen()
 void newGame()
 void load()
 void main(String[] args)
 
*/

public class SwordQuest2 {
  
  GameRunner game;
  JFrame j;
  
  public SwordQuest2() {
    j = new JFrame();
    j.setSize(800, 800);
    j.setVisible(true);
    titleScreen();
  }
  
  //just a JDialog that asks you if you want to start a new game or load one.
  public void titleScreen() {
  }
  
  public void newGame() {
    game = new GameRunner(j);
    //game.openingCutScene();
  }
  
  //loads a save file
  public void load() {
    //get the save file as selected by the user
    //convert into a String save
    game = new GameRunner(j, save);
  }
  
  //The main method. This runs the game, effectively.
  public static void main(String[] args) {
  }
  
}