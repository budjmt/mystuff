import java.io.*;
import java.util.*;

public class Item {
  
  private ArrayList<String> list;
  
  public Item() {
    list = new ArrayList<String>();
  }
  
  public void add(String s) {
    list.add(s);
  }
  
  public String remove(int i) {
    return list.remove(i);
  }
  
  public int size() {
    return list.size();
  }
  
  public String get(int i) {
    return list.get(i);
  }
  
}