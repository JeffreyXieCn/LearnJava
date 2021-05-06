package io.github.jeffreyxiecn.exception;

import java.io.IOException;
import org.slf4j.Logger;

public class TestException {
  private static final Logger log = org.slf4j.LoggerFactory.getLogger(TestException.class);

  public static int assignment() {
    int number = 1;
    try {
      number = 3;
      if (true) {
        throw new Exception("Test Exception");
      }
      number = 2;
    } catch (Exception ex) {
      return number;
    } finally {
      number = 4;
    }
    return number;
  }

  public static void main(String[] args) {
    System.out.println(assignment()); // 3

    Parent p1 = new Parent();
    try {
      p1.msg();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      System.out.println("Catched IOException from Parent");
    }

    Parent p2 = new Child2();
    try {
      p2.msg();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      System.out.println("Catched IOException from Child2");
    }

    Exception e = new IllegalArgumentException("Need string, but found integer");
    log.info("An exception happened: {}", e);
    System.out.println("A log after the log.info()");

    int a = 5;
    int b = 0;
    try {
      int c = a / b;
    } catch (ArithmeticException ae) {
      System.out.println("Catched RuntimeException");
    }
  }
}

class Parent {
  public void msg() throws IOException {
    System.out.println("Parent msg()");
    throw new IOException("IOException in Parent");
  }
}

// class Child1 extends Parent {
//  @Override
//  public void msg() throws IOException, InterruptedException { // compilation error
//    System.out.println("Child msg()");
//  }
// }

class Child2 extends Parent {
  private static final Logger log = org.slf4j.LoggerFactory.getLogger(TestException.class);

  @Override
  public void msg() {
    System.out.println("Child msg()");

    Exception e = new IllegalArgumentException("Need string, but found integer");
    log.info("An exception happened: {}", e);
    System.out.println("A log after the log.info()");
  }
}
