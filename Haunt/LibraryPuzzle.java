import java.io.*;
import java.util.*;

public class LibraryPuzzle extends Puzzle {
  
  public LibraryPuzzle() {
    answer = new String[2];
    question = new String[2];
    submission = new ArrayList<String>();
    response = new String("So you found the pun between 'deer' the animal and 'dear' the beloved! Good job.");
    question[0] = "There are two wheels on the door. One has a selection of animals on it: a deer, a snake, and a bull. The other has a few words: 'lies', 'traitor', and 'beloved'. What animal do you want to select?";
    question[1] = "What word will you select?";
    answer[0] = "DEER";
    answer[1] = "BELOVED";
  }
  
}