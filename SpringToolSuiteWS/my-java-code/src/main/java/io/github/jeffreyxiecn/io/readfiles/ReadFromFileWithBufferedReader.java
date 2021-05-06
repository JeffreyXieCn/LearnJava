package io.github.jeffreyxiecn.io.readfiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReadFromFileWithBufferedReader {

  public static void main(String[] args) throws FileNotFoundException {
    // To read from stdin:
    // Reader reader = new InputStreamReader(System.in);

    Reader reader = new FileReader("src/main/resources/readfiles/input.txt");
    /*
    Or, to read from a file:
    Reader reader = new FileReader(filename);

    Or, to read from a network stream:
    Reader reader = new InputStreamReader(socket.getInputStream());
    */

    try {
      BufferedReader inp = new BufferedReader(reader);
      String line;
      while ((line = inp.readLine()) != null) {
        // Process the input here. For example, you can print it out.
        System.out.println(line);
      }
    } catch (IOException e) {
      // There was an input error.
    }
  }
}
