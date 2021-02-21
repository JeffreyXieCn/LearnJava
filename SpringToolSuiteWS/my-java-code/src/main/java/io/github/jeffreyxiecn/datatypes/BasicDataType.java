package io.github.jeffreyxiecn.datatypes;

import java.nio.ByteOrder;

public class BasicDataType {

  public static void main(String[] args) {
    ByteOrder byteOrder = ByteOrder.nativeOrder();
    if (byteOrder.equals(ByteOrder.LITTLE_ENDIAN)) {
      System.out.println("This JVM is running on little_endian platform");
    } else {
      System.out.println("This JVM is running on big_endian platform");
    }

    int a = 15;
    System.out.println(Integer.toString(a));
    System.out.println(Integer.toBinaryString(a));
    System.out.println(Integer.toOctalString(a));
    System.out.println(Integer.toHexString(a));
    System.out.println(String.valueOf(a));

    System.out.println(String.valueOf('a'));

    System.out.println(Integer.parseInt("-123"));
    System.out.println(Integer.valueOf("-123"));

    System.out.println("a".charAt(0));

    Integer aa = a;
    System.out.println(aa.toString());
    System.out.println(new Integer(a));
    System.out.println(Integer.valueOf(a));

    System.out.println(aa.intValue());
  }
}
