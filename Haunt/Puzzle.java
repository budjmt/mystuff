import java.io.*;
import java.util.*;

public class Puzzle {
  
  protected String[] answer, question;
  protected ArrayList<String> submission;
  protected String response;
  
  public boolean correct(int stage) {
    if(submission.get(stage).equals(answer[stage]))
      return true;
    return false;
  }
  
  public void enterSubmission(String submit) {
    String s = submit.toUpperCase();
    submission.add(s);
  }
  
  public boolean solution() {
    for(int i = 0;i < answer.length;i++) {
      if(!correct(i))
        return false;
    }
    return true;
  }
    
  public String getResponse() {
    return response;
  }
  
  public String getQuestion(int i) {
    return question[i];
  }
  
  public String getAnswer(int i) {
    return answer[i];
  }
  
  public int getLength() {
    return question.length;
  }
  
}