import java.io.*;
import java.util.*;

/*
   This class is the root of all in game objects.
   
   Methods:
   GameObject(String n)
   String getName()
   ImageMan getImage()
   String readGOInfo(String type)
   
 */

public class GameObject {
  
  protected String name, fileLoc;
  protected ImageMan image
  
  //constructs a GameObject with name n
  public GameObject(String n) {
    name = n;
  }
  
  //returns the GO's name
  public String getName() {
    return name;
  }
  
  public ImageMan getImage() {
    return image;
  }
  
  //This is the key method for all GameObjects, as without it they would be unable
  //to get their information. All this does is get the String of the relevant
  //qualities for the GO (specified by type)
  //calling it through their name. Subsequent classes clarify assignment.
  public String readGOInfo(String type) {
  }
  
}