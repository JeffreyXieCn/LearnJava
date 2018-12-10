package io.github.jeffreyxiecn.array;

import java.util.ArrayList;
import java.util.List;

public class ArrayInJava {

  public static void main(String[] args) {
    List<Integer> li = new ArrayList<>();
    List<String> ls = new ArrayList<>();
    System.out.println(li.getClass()); // class java.util.ArrayList
    System.out.println(ls.getClass()); // class java.util.ArrayList
    List<String>[] lsArr = new List[10];
    System.out.println(lsArr.getClass()); // class [Ljava.util.List;
    List<String>[] lsArr2 = new ArrayList[10];
    System.out.println(lsArr2.getClass()); // class [Ljava.util.ArrayList;
    String[] strArr = new String[10]; // class [Ljava.lang.String;
    System.out.println(strArr.getClass());
    String[][] strArr2 = new String[10][20];
    System.out.println(strArr2.getClass()); // class [[Ljava.lang.String;
    // List<String>[] liArr2 = new ArrayList<>()[10];
    // ArrayList<String>[] alArr3 = new ArrayList<String>[10]; //Cannot create a generic array of
    // ArrayList<String>

  }
}
