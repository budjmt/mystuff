import java.io.*;
import java.util.*;

public class LampPuzzle extends Puzzle {
  
  public LampPuzzle() {
    answer = new String[4];
    question = new String[4];
    submission = new ArrayList<String>();
    response = new String("So those place settings DID end up being important...");
    question[0] = "There are four buttons. They are green, blue, red, and yellow. What button will you push first?";
    question[1] = "What button will you push second?";
    question[2] = "What button will you push third?";
    question[3] = "What button will you push fourth?";
    answer[0] = "BLUE";
    answer[1] = "GREEN";
    answer[2] = "YELLOW";
    answer[3] = "RED";
  }
  
}