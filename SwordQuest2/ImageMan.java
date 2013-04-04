import java.io.*;
import java.util.*;
//import java image and file reader stuff
/*
 This class is used to find and store the appropriate image for each GameObject.
 This is then used in the game to display it.
 
 Methods:
 public ImageMan(String loc)
 public void importImage()
 public void addToGameAt(JPanel j, int x, int y)
 public void removeFromGame(JPanel j)
 public void shift(int dir, int numMoves)
 public int getX()
 public int getY()
 
*/

public class ImageMan {
  
  private String imageLoc;
  private JLabel image;
  private JPanel img;
  
  public ImageMan(String loc) {
    imageLoc = loc;
  }
  
  //This uses the stored imageLoc to read and create an JLabel, 
  //then stores it in image
  public void importImage() {
    String currentDir = new File(".").getAbsolutePath();
    String imageDir = currentDir + "\\" + "images\\" + imageLoc + ".png";
    ImageIcon icon = new ImageIcon(imageDir);
    image = new JLabel(icon);
  }
  
  //This creates the image at the relative location in j on the scroll
  public void addToGameAt(JPanel j, int x, int y) {
    img = j;
    j.add(image);
    j.setBounds(x, y, image.getWidth(), image.getHeight());
  }
  
  //tells j to remove the image
  public void removeFromGame() {
    img.remove(image);
  }
  
  //moves the image up if dir = 1, down if dir = 2, right if dir = 3, left otherwise
  public void shift(int dir, int numMoves) {
    if(dir == 1)
      img.setLocation(img.getX(), img.getY() + numMoves);
    else if(dir == 2)
      img.setLocation(img.getX(), img.getY() - numMoves);
    else if(dir == 3)
      img.setLocation(img.getX() - numMoves, img.getY());
    else
      img.setLocation(img.getX() + numMoves, img.getY());
  }
  
  public int getX() {
    return image.getX();
  }
  
  public int getY() {
    return image.getY();
  }
  
}