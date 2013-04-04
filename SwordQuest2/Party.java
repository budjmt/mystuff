import java.io.*;
import java.util.*;

/*
  This class contains the party.
  
  Methods:
  Party()
  Party(String s)
  ArrayList<Member> getMembers()
  ArrayList<String> getNames()
  Member getMember(int i)
  void add(Member m)
  
*/

public class Party {
  
  private ArrayList<Member> party;
  
  public Party() {
    party = new ArrayList<Member>();
    party.add(new Member("Hero", ""));
  }
  
  public Party(String s) {
  }
  
  public ArrayList<Member> getMembers() {
    return party;
  }
  
  public ArrayList<String> getNames() {
    ArrayList<String> s = new ArrayList<String>();
    for(Member m : party)
      s.add(m.getName());
    return s;
  }
  
  public Member getMember(int i) {
    return party.get(i);
  }
  
  public void add(Member m) {
    party.add(m);
  }
    
}