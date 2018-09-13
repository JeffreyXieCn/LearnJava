package io.github.jeffreyxiecn.oop;

import java.util.ArrayList;

/** @author ewenxie */
public class GetRealClassType {

  /** @param args input parameters */
  public static void main(String[] args) {
    ArrayList al = new ArrayList();
    al.add(new Integer(37));
    al.add(new String("Hello World"));
    al.add(new MyTestIOException());
    al.add(new Double(5.37));
    System.out.println(al.get(1).getClass().getCanonicalName());
    System.out.println(al.get(2).getClass().getCanonicalName());
  }
}
