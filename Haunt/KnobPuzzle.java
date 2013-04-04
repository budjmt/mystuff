import java.io.*;
import java.util.*;

public class KnobPuzzle extends Puzzle {
  
  public KnobPuzzle() {
    answer = new String[3];
    question = new String[3];
    submission = new ArrayList<String>();
    response = new String("You deduced what the note meant! Congratulations.");
    question[0] = "The hole on the left is a square.";
    question[1] = "The hole in the middle is a triangle.";
    question[2] = "The hole on the right is a circle.";
    for(int i = 0;i < question.length;i++)
      question[i] += "\n" + "Type 1 for Pantry Knob"
      + "\n" + "Type 2 for Vase Knob" + "\n" + "Type 3 for Living Room Knob";
    answer[0] = "2";
    answer[1] = "1";
    answer[2] = "3";
  }
  
}