/*
Manager
This is the game engine. It will run in Haunt.

*/
import java.io.*;
import java.util.*;

public class Manager {
  
  private CommandInput input;
  private String errorMessage;
  private Position pos;
  
  public Manager() {
    input = new CommandInput();
    errorMessage = new String("That's not a valid entry. Try again.");
    pos = new Position();
  }
  
  public boolean puzzleSolve(Puzzle p) {
    for(int i = 0;i < p.getLength();i++) {
      System.out.println(p.getQuestion(i));
      p.enterSubmission(input.puzzleCommand());
    }
    if(p.solution()) {
      System.out.println(p.getResponse());
      return true;
    }
    System.out.println("Nothing happened...");
    return false;
  }
  
  public String initPuzzle() {
    String fail = "Try the puzzle again when you think you have the answer.";
    if(pos.getCurrRoom() == 10) {
      if(!pos.itemIn("VASE KNOB") || !pos.itemIn("PANTRY KNOB") || 
         !pos.itemIn("LIVING ROOM KNOB"))
        return "You don't have everything you need.";
      KnobPuzzle k = new KnobPuzzle();
      if(puzzleSolve(k)) {
        pos.solvedChange();
        pos.unlockDoor(0);
        return pos.getRelSolveText();
      }
      return fail;
    }
    else if(pos.getCurrRoom() == 17) {
      LampPuzzle l = new LampPuzzle();
      if(puzzleSolve(l)) {
        pos.solvedChange();
        pos.unlockDoor(0);
        return pos.getRelSolveText();
      }
      return fail;
    }
    else if(pos.getCurrRoom() == 20) {
      TelephonePuzzle t = new TelephonePuzzle();
      if(puzzleSolve(t)) {
        pos.solvedChange();
        pos.unlockDoor(0);
        return pos.getRelSolveText();
      }
      return fail;
    }
    else if(pos.getCurrRoom() == 27) {
      LibraryPuzzle l = new LibraryPuzzle();
      if(puzzleSolve(l)) {
        pos.solvedChange();
        pos.unlockDoor(0);
        return pos.getRelSolveText();
      }
      return fail;
    }
    else if(pos.getCurrRoom() == 37) {
      ClockPuzzle c = new ClockPuzzle();
      if(puzzleSolve(c)) {
        pos.solvedChange();
        pos.unlockDoor(0);
        return pos.getRelSolveText();
      }
      return fail;
    }
    return "No puzzles to be solved here.";
  }
  
  public String displayInventory() {
   String list = "";
   for(int i = 0;i < pos.getInventoryLength();i++)
     list += pos.getItem(i) + "\n";
   return list;
  }
  
  public String displayMap() {
    if(pos.getCurrRoom() < 31)
      return map1();
    else
      return map2();
  }
  
  public String map1() {
    String[][] map = new String[8][7];
    for(int i = 0;i < map.length;i++) {
      for(int r = 0;r < map[i].length;r++)
        map[i][r] = " ";
    }
    map[7][3] = "0"; map[7][2] = "1"; map[7][1] = "2"; map[7][0] = "3"; map[6][1] = "4";
    map[5][1] = "5"; map[5][0] = "6"; map[4][1] = "7"; map[4][0] = "8"; map[3][0] = "9";
    map[2][0] = "10"; map[1][0] = "11"; map[2][1] = "12"; map[2][2] = "13"; map[2][3] = "14";
    map[3][3] = "15"; map[3][4] = "16"; map[2][4] = "17"; map[1][4] = "18"; map[1][5] = "19";
    map[1][6] = "20"; map[0][6] = "      21"; map[7][4] = "22"; map[7][5] = "23"; map[6][5] = "24";
    map[5][5] = "25"; map[4][5] = "26"; map[4][6] = "27"; map[3][6] = "28"; map[2][6] = "29";
    map[3][5] = "30";
    String s = "";
    for(int i = 0;i < map.length;i++) {
      for(int r = 0;r < map[i].length;r++)
        s += map[i][r];
      s += "\n";
    }
    s += "Current room: " + pos.getCurrRoom();
    return s;
  }
  
  public String map2() {
    String[][] map = new String[4][4];
    for(int i = 0;i < map.length;i++) {
      for(int r = 0;r < map[i].length;r++)
        map[i][r] = " ";
    }
    map[0][1] = "31"; map[1][1] = "32"; map[2][1] = "33"; map[3][1] = "34";
    map[3][0] = "35"; map[2][0] = "36"; map[1][0] = "37"; map[0][0] = "38";
    map[0][2] = "    39";
    String s = "";
    for(int i = 0;i < map.length;i++) {
      for(int r = 0;r < map[i].length;r++)
        s += map[i][r];
      s += "\n";
    }
    s += "Current room: " + pos.getCurrRoom();
    return s;
  }
  
  public boolean moveChecker(String o) {
    if(o.equals("NORTH DOOR") || o.equals("RIGHT DOOR") || o.equals("SOUTH DOOR") ||
       o.equals("LEFT DOOR") || o.equals("STAIRS") || o.equals("PANEL"))
      return true;
    return false;
  }
  
  public String moveCheck(String o) {
    String s = "You can't do that here.";
    String m = "";
    if(o.equals("NORTH DOOR")) {
      if(pos.getCurrRoom() == 36) {
        branch();
        s = "";
      }
      m = pos.moveNorm(0);
      if(m.equals("invalid"))
        return s;
      else
        return m;
    }
    else if(o.equals("RIGHT DOOR")) {
      m = pos.moveNorm(1);
      if(m.equals("invalid"))
        return s;
      else
        return m;
    }
    else if(o.equals("SOUTH DOOR")) {
      m = pos.moveNorm(2);
      if(m.equals("invalid"))
        return s;
      else
        return m;
    }
    else if(o.equals("LEFT DOOR")) {
      if(pos.getCurrRoom() == 2 && !pos.getSolved(2))
        return s;
      if(pos.getCurrRoom() == 34) {
        branch();
        s = "";
      }
      m = pos.moveNorm(3);
      if(m.equals("invalid"))
        return s;
      else
        return m;
    }
    else if(o.equals("STAIRS")) {
      if(pos.getCurrRoom() == 21 || pos.getCurrRoom() == 31)
        return pos.moveSpecial();
      else
        return s;
    }
    else if(o.equals("PANEL")) {
      if(pos.getCurrRoom() == 30 || pos.getCurrRoom() == 39)
        return pos.moveSpecial();
      else
        return s;
    }
    return errorMessage;
  }
  
  public boolean isValidItem(String o) {
    if(pos.isPickedUp(o))
      return true;
    return false;
  }
  
  public String correctUsage(String o) {
    String s = "You can't USE that.";
    String u = pos.getRelUseText(o);
    if(!u.equals(s) && isValidItem(o)) {
      return doAction(o);
    }
    return s;
  }
  
  public String doAction(String o) {
    String s = "The door is now unlocked.";
    if(o.equals("BREAD KEY")) {
      pos.solvedChange();
      pos.unlockDoor(0);
      pos.removeUse("NORTH DOOR");
      return s;
    }
    if(o.equals("BRICK KEY")) {
      pos.solvedChange();
      pos.removeUse("NIGHT TABLE");
      return "The night table is now unlocked.";
    }
    if(o.equals("PLUG")) {
      pos.solvedChange();
      return pos.getRelUseText(o);
    }
    if(o.equals("TROPHY KEY")) {
      pos.solvedChange();
      pos.unlockDoor(1);
      return s;
    }
    if(o.equals("SHEET MUSIC")) {
      pos.solvedChange();
      pos.unlockDoor(0);
      return pos.getRelUseText(o);
    }
    if(o.equals("RUG")) {
      pos.solvedChange();
      return pos.getRelUseText(o);
    }
    if(o.equals("BAR KEY")) {
      pos.solvedChange();
      pos.unlockDoor(0);
      return s;
    }
    if(o.equals("JUMP")) {
      pos.solvedChange();
      pos.removeUse("SOUTH DOOR");
      return pos.getRelUseText(o);
    }
    if(o.equals("WALL")) {
      pos.solvedChange();
      pos.removeUse("SOUTH DOOR");
      return pos.getRelUseText(o);
    }
    if(o.equals("CELLAR KEY")) {
      pos.solvedChange();
      pos.unlockDoor(0);
      return s;
    }
    else
      return pos.getRelUseText(o);
  }
  
  public void branch() {
    int room = pos.getCurrRoom();
    if(room == 34) {
      if(!pos.getSolved(room)) {
        System.out.println(pos.getUse(0));
        String s = input.puzzleCommand().toUpperCase();
        if(s.equals("START"))
          reset();
      }
      else
        goodEnd(34);
    }
    else if(room == 36) {
      if(!pos.getSolved(room)) {
        System.out.println(pos.getUse(0));
        String s = input.puzzleCommand().toUpperCase();
        if(s.equals("START"))
          reset();
      }
      else
        goodEnd(36);
    }
    else if(room == 38) {
      if (!pos.getSolved(room)) {
        System.out.println(pos.basicLook(1));
        System.exit(0);
      }
      else
        goodEnd(38);
      }
    }
    
    public void goodEnd(int room) {
      if(room == 34 || room == 36) {
        System.out.println(pos.getGoodEnd1());
      }
      if(room == 38) {
        System.out.println(pos.getGoodEnd2());
        System.exit(0);
      }
    }
      
  
  public String commandInterpret() {
//checks what cmd is, then uses the appropriate method from the appropriate class
//ie checks if its a puzzle, then just proceeds as normal if not.
    String c = input.getCmd();
    String o = input.getObj();
    if(c.equals("SOLVE") && o.equals("PUZZLE")) {
      return initPuzzle();
    }
    if(c.equals("LOOK")) {
      if(o.equals("ROOM")) {
        if(pos.getCurrRoom() == 38)
          branch();
        return pos.basicLook(1);
      }
      if(pos.getCurrRoom() == 2 && o.equals("LEFT CURTAIN")) {
        pos.solvedChange();
        pos.unlockDoor(3);
      }
      if(pos.getCurrRoom() == 6 && o.equals("VASE")) {
        pos.solvedChange();
      }
      if(!exceptionChange(c, o)) {
        if(pos.getCurrRoom() == 15)
          return "The night table is locked.";
        return "You can't LOOK at that.";
      }
      else
        return pos.getRelLookText(o);
    }
    else if(c.equals("GET")) {
      if(!exceptionChange(c, o))
        return "You can't GET that.";
      if(pos.pickUp(o)) {
        String s = pos.getRelGetText(o);
        pos.removeGet(o);
        return s;
      }
      else
        return "You can't GET that.";
    }
    else if(c.equals("USE")) {
      //this first checks if the item in question is in your inventory and/or in the
      //room's useCue list.(if the first, checks the second, but if neither,
      //just the second) Then, brings up the appropriate useText and does the 
      //appropriate action. Also checks is any of the doors are used, which triggers
      //move. Actions triggered by correctUsage(String o).
      if(!exceptionChange(c, o))
        return "You can't USE that.";
      String s = correctUsage(o);
      if((o.equals("NORTH DOOR") || o.equals("RIGHT DOOR") || o.equals("SOUTH DOOR") ||
       o.equals("LEFT DOOR")) && pos.getSolved(pos.getCurrRoom()))
        return moveCheck(o);
      if((o.equals("NORTH DOOR") || o.equals("LEFT DOOR")) && 
         (pos.getCurrRoom() == 34 || pos.getCurrRoom() == 36))
        return moveCheck(o);
      if(!s.equals("You can't USE that.")) {
        return s;
      }
      if(moveChecker(o))//This is after initial check
        return moveCheck(o);
    }
    else if(c.equals("ITEMS"))
      return displayInventory();
    else if(c.equals("MAP"))
      return displayMap();
    else if(c.equals("QUIT"))
      System.exit(0);
    return errorMessage;
  }
  
  public boolean exceptionChange(String c, String o) {
    int room = pos.getCurrRoom();
    //the exception for 2 and 5 goes in moveCheck
    if(room == 6 && c.equals("GET") && o.equals("KNOB") && !pos.getSolved(6))
      return false;
    //puzzles are covered by initPuzzle
    else if(room == 15 && ((c.equals("GET") && o.equals("KEY")) || (c.equals("LOOK")
                            && (o.equals("PAPER") || o.equals("NIGHT TABLE")))) && 
                           !pos.getSolved(15))
      return false;
    else if(room == 16 && c.equals("GET") && o.equals("KEY") && !pos.getSolved(16))
      return false;
    //exception for 19 and 24 goes in moveCheck
    else if(room == 25 && c.equals("GET") && o.equals("KNOB") && !pos.getSolved(25))
      return false;
    //exception for 29, 32, 33, 35 go in moveCheck
    return true;
  }
  
  public void cycle() {
    //This executes one reading of the line and takes an appropriate action.
    input.commandMe();
    System.out.println(commandInterpret());
  }
  
  public void reset() {
    pos = new Position();
    System.out.println("You seek adventure and treasure; recently you've been given the opportunity to have both.  Rumors say that the mansion outside of town is haunted; but they've also said that it's full of treasure.  You decide to check it out.  You journey to the house and try the door.  It's unlocked, and you enter the foyer.");
    cycle();
  }
    
}