/*---------------------------------------------------------------------------
CommandInput
This class reads the user's input in response to a situation.

Any user input is composed of two things:
[Command] [Object]
The user is instructed to do this; otherwise, an error message is returned.
CommandInput is regulated by the Position class, but only within the main engine.
(see BattleShip)
NOTE: This is responsible for KEYBOARD input, not the puzzle input.
That is managed in the Puzzle class and its subclasses.
*///--------------------------------------------------------------------------
import java.io.*;
import java.util.*;

public class CommandInput {
  
  private String readOut, cmd, obj;
  private String[] commands, objects;
  
  public CommandInput() {
    int comnum = 7;//comnum is how many commands there are
    readOut = "There is no readOut";
    cmd = "No command";
    obj = "No object";
    commands = new String[comnum];
    commands[0] = "LOOK";
    commands[1] = "GET";
    commands[2] = "USE";
    commands[3] = "SOLVE";
    commands[4] = "ITEMS";
    commands[5] = "MAP";
    commands[6] = "QUIT";
  }

  public void commandMe() {
    InputStreamReader in; 
    BufferedReader bin;
    in = new InputStreamReader(System.in);
    bin = new BufferedReader(in);
    String s = " ";
    try { 
      s = bin.readLine();
    }
    catch(IOException e) {
      System.out.println( "i/o error" );
    }
    //This is where the input reading ends and the interpretation begins.
    String[] input = new String[2];
    boolean b = false;
    for(int i = 0;i < s.length();i++) {
      if(s.charAt(i) == ' ')
        b = true;
    }
    if(b) {
      b = false;
      s.toUpperCase();
      input = s.split(" ", 2);
      for(int i = 0;i < commands.length;i++) {
        if(input[0].toUpperCase().equals(commands[i]))
          b = true;
      }
      if (b) {
        cmd = input[0];
        obj = input[1];
      }
      else {
      System.out.println("Invalid command. Try again.");
      this.commandMe();
      return;
      }
    }
    else {
      System.out.println("Invalid command. Try again.");
      this.commandMe();
    }
  }
  
  public String puzzleCommand() {
    InputStreamReader in; 
    BufferedReader bin;
    in = new InputStreamReader(System.in);
    bin = new BufferedReader(in);
    String s = " ";
    try { 
      s = bin.readLine();
    }
    catch(IOException e) {
      System.out.println( "i/o error" );
    }
    return s;
  }
  
  public String getCmd() {
    return cmd.toUpperCase();
  }
  
  public String getObj() {
    return obj.toUpperCase();
  }

  public int whatCommand(String s) {
    for(int i = 0;i < commands.length;i++) {
      if(s.equals(commands[i]))
        return i;
    }
    return -1;
  }
  
  public static void main(String[] args) {
    CommandInput c = new CommandInput();
    
    System.out.println(c.whatCommand(c.cmd));
    
    System.out.println("Write a command");
    c.commandMe();
    
    System.out.println(c.whatCommand(c.cmd));
  }
}