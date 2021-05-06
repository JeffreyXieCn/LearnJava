package io.github.jeffreyxiecn.io.readfiles;

import java.io.InputStream;
import java.util.Scanner;

/** Hello world! */
public class ReadFromStdinWithScanner {
  public static void main(String[] args) {
    // To read from stdin:
    InputStream source = System.in;

    /*
    Or, to read from a file:
    InputStream source = new FileInputStream(filename);

    Or, to read from a network stream:
    InputStream source = socket.getInputStream();
    */

    Scanner in = new Scanner(source);
    // while (in.hasNext()) {
    while (in.hasNextLine()) {
      // String input = in.next(); // Use in.nextLine() for line-by-line reading
      String input = in.nextLine();

      // Process the input here. For example, you could print it out:
      System.out.println(input);
      if (input.equals("")) { // Just press ENTER key will break out of the loop
        break;
      }
    }
  }
}
