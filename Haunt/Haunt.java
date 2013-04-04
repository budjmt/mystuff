import java.io.*;
import java.util.*;

public class Haunt {
  
  private Manager m;
  
  public Haunt() {
    m = new Manager();
  }
  
  public void start() {
    System.out.println("You seek adventure and treasure; recently you've been given the opportunity to have both.  Rumors say that the mansion outside of town is haunted; but they've also said that it's full of treasure.  You decide to check it out.  You journey to the house and try the door.  It's unlocked, and you enter the foyer.");
  }
  
  public void play() {
    m.cycle();
  }
  
  public static void main(String[] args) {
    Haunt game = new Haunt();
    
    game.start();
    for(int i = 1;i > 0;i++) {
      game.play();
    }
  }
}