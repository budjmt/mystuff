import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
   This class is the container of relevant movement data. Used in MapRunner.
   
   Methods:
   public GNode(String obj, int blanks, String imageLoc, MapObject ent)
   public GNode getNext()
   public GNode getPrev()
   public void incNext(boolean b, int numMoves)
   public void incPrev(boolean b, int numMoves)
   public int getSpaces()
   public void setSpaces(int n)
   public String getName()
   public ImageMan getImage()
   public boolean sameAs(GNode g)
   
*/

public class GNode {
  
  private GNode next, prev;
  private String name;
  private GameObject thing;
  private int spaces;
  
  //This constructs a GNode with name obj, spaces blanks, an ImageMan directing to
  //imageLoc, and something for specifics
  public GNode(String obj, int blanks, String n, String imageLoc, MapObject ent) {
    name = obj;
    spaces = blanks;
    thing = new GameObject(n);
    entrance = ent;
  }
  
  //returns next GNode
  public GNode getNext() {
    return next;
  }
  
  //returns previous GNode
  public GNode getPrev() {
    return prev;
  }
  
  //adds or subtracts 1 to next if true or false
  public void incNext(boolean b, int numMoves) {
    if(b)
      next.setSpaces(getSpaces() - numMoves);
    else
      next.setSpaces(getSpaces() + numMoves);
  }
  
  //adds or subtracts 1 to prev if true or false
  public void incPrev(boolean b, int numMoves) {
    if(b)
      prev.setSpaces(getSpaces() + numMoves);
    else
      prev.setSpaces(getSpaces() - numMoves);
  }
  
  //returns the number of blank spaces between the GNode and the player
  public int getSpaces() {
    return spaces;
  }
  
  //sets spaces to n
  public void setSpaces(int n) {
    spaces = n;
  }
  
  //returns the name
  public String getName() {
    return name;
  }
  
  public ImageMan getImage() {
    return thing.getImage();
  }
  
  public boolean sameAs(GNode g) {
    if(g.getName() == name && g.getSpaces() == spaces)
      return true;
    return false;
  }
  
}