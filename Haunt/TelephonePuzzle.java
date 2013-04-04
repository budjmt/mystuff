import java.io.*;
import java.util.*;

public class TelephonePuzzle extends Puzzle {
  
  public TelephonePuzzle() {
    answer = new String[2];
    question = new String[2];
    submission = new ArrayList<String>();
    response = new String("Good thinking! Though the fact that the clue was in a phonebook was probably helpful...");
    question[0] = "This is probably a case of dialing the right number. Dial the area code.";
    question[1] = "Now dial the number (add the hyphen as you would when writing it).";
    answer[0] = "332";
    answer[1] = "840-4768";
  }
  
}