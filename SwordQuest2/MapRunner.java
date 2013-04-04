import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
   This class is the game map runner.
   It loads, moves, and manages the map and its contents.
   
   Methods:
   public MapRunner()
   public MapRunner(String save)
   public String save()
   public void mapSetup()
   public void scroll(int dir)
   public void shiftAll(int dir, int numMoves)
   public GNode getVLoc()
   public GNode getHLoc()
   public void setVLoc(GNode next)
   public void setHLoc(GNode next)
   public String getZone()
   public void setZone(String s)
   public void move(int dir, int numMoves)
   public void moveForward(GNode g, int numMoves)
   public void moveBack(GNode g, int numMoves)
   public boolean collision()
   
*/

public class MapRunner {
  
  private GNode vert, horiz;
  private ImageMan map, cam, hero;
  private ArrayList<ImageMan> currOnScreen;
  private ArrayList<Integer>[] spacesFromMiddle;
  private String zone;
  public static final int DRAWDIST = 20;//how far away something is in spaces 
  //before being generated graphically.
  public static final int CONV = 3;
  //this is the conversion between the graphicals system and 
  //the distances in the GNodes. i.e, 3 points over in an axis is equal to 1 space
  //on the map here.
  
  //This is used for a new game
  public MapRunner() {
    mapSetup();
    map = new ImageMan("worldMap");
    cam = new ImageMan("camera");
    zone = "GRASS";
  }
  
  //Finds and loads the appropriate map data from save
  public MapRunner(String save) {
  }
  
  //Saves data from the MapRunner as individual entries in an ArrayList,
  //with the 0 index reading "MAPRUNNER". Each entry is stored on a new line.
  public ArraList<String> save() {
  }
  
  public void mapSetup() {
  }
  
  //Moves the camera in dir. 1 is up, 2 is down, 3 is left, and 4 is right
  //Also generates or deletes any GameObject images in view.
  //Generates if the GNode is within DIST, if greater than DIST it's removed
  //Changes vert/horiz accordingly.
  public void scroll(int dir) {
    move(vert, horiz, dir, 1);
    generate();
    remove();
    shiftAll(dir, 1);
  }
  
  public void generate(int x, int y) {
    for(int d = 1;d < 5;d++) {
      Object[] o = traverse(d, DRAWDIST);
      if(o[1] == 0) {
        ImageMan i = o[1].getImageMan();
        int a = hero.getX();
        int b = hero.getY();
        if(dir == 1)
          b += CONV;
        else if(dir == 2)
          b -= CONV;
        else if(dir == 3)
          a -= CONV;
        else
          a += CONV;
        i.addToGameAt(new JPanel(), a, b);
        currOnScreen.add(i);
      }
    }
  }
  
  public void remove() {
    for(int i = 0;i < currOnScreen.size();i++) {
      if(spacesFromMiddle.get(i)[0] > DRAWDIST ||
         spacesFromMiddle.get(i)[1] > DRAWDIST) {
        currOnScreen.get(i).removeFromGame();
        currOnScreen.remove(i);
      }
    }
  }
    
  
  public void shiftAll(int dir, int numMoves) {
    for(ImageMan i : currOnScreen)
      shift(dir, numMoves);
    cam.shift(dir, numMoves);
  }
  
  //this one is used specifically for generation
  public Object[] traverse(int dir, int numMoves) {
    GNode g;
    boolean b;
    if(dir == 1) {
      g = vert;
      b = true;
    }
    else if(dir == 2) {
      g = vert;
      b = false;
    }
    else if(dir == 3) {
      g = horiz;
      b = false;
    }
    else {
      g = horiz;
      b = true;
    }
    return traverse(g, b, numMoves, 1);
  }
  
  //this one is more generic
  public static Object[] traverse(GNode g, boolean direction, 
                           int numMoves, int spacesMoved) {
    GNode x = g;
    int currSpaces = g.getSpaces();
    int nextSpaces;
    if(direction)
      nextSpaces = g.getNext().getSpaces();
    else
      nextSpaces = g.getPrev().getSpaces();
    for(int n = numMoves;n > 0;n--) {
      currSpaces += spacesMoved;
      nextSpaces -= spacesMoved;
      if(nextSpaces <= 0) {
        if(direction) {
          x = x.getNext();
          currSpaces = x.getSpaces() - nextSpaces;
          nextSpaces = x.getNext().getSpaces() + nextSpaces;
        }
        else {
          x = x.getPrev();
          currSpaces = x.getSpaces() - nextSpaces;
          nextSpaces = x.getPrev().getSpaces() + nextSpaces;
        }
      }
    }
    Object o = {x, currSpaces};
    return o;
  }
  
  //returns vert
  public GNode getVLoc() {
    return vert;
  }
  
  //returns horiz
  public GNode getHLoc() {
    return horiz;
  }
  
  //sets vert to next
  public void setVLoc(GNode next) {
    vert = next;
  }
  
  //sets horiz to next
  public void setHLoc(GNode next) {
    horiz = next;
  }
  
  public String getZone() {
    return zone;
  }
  
  public void setZone(String s) {
    zone = s;
  }
  
  //follows normal dir rules. Increments in appropriate direction,
  //and shifts if necessary
  public static void move(GNode v, GNode h, int dir, int numMoves) {
    if(dir == 1)
      moveForward(v, numMoves);
    else if(dir == 2)
      moveBack(v, numMoves);
    else if(dir == 3)
      moveBack(h, numMoves);
    else 
      moveForward(h, numMoves);
  }
  
  //moves g "forward"
  public void moveForward(GNode g, int numMoves) {
    g.setSpaces(g.getSpaces() + numMoves);
    g.incPrev(true, numMoves);
    g.incNext(true, numMoves);
    if(g.getNext().getSpaces() <= 0) {
      g.getPrev().setSpaces(g.getPrev.getSpaces() - g.getSpaces());
      g = g.getNext();
      g.setSpaces(g.getSpaces() * -1);
      setZone(g.getZone());
    }
  }
  
  //move g "back"
  public void moveBack(GNode g, int numMoves) {
    g.setSpaces(g.getSpaces() + numMoves);
    g.incNext(false, numMoves);
    g.incPrev(false, numMoves);
    if(g.getPrev().getSpaces() <= 0) {
      g.getNext().setSpaces(g.getNext().getSpaces() - g.getSpaces());
      g = g.getPrev();
      g.setSpaces(g.getSpaces() * -1);
      setZone(g.getZone());
    }
  }
  
  public GNode[] collision(ArrayList<GNode> monstVert, 
                           ArrayList<GNode> monstHoriz) {
    for(GNode v : monstVert) {
      for(GNode h : monstHoriz) {
        if(v.sameAs(vert) && h.sameAs(horiz)) {
          GNode[] g = new GNode[2];
          g[0] = v;
          g[1] = h;
          return g;
        }
      }
    }
    return null;
  }
  
}