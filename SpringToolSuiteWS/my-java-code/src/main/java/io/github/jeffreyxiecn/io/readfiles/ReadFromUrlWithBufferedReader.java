package io.github.jeffreyxiecn.io.readfiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ReadFromUrlWithBufferedReader {

  public static void main(String[] args) throws IOException {
    URL url = new URL("https://i.imgur.com/xGmX4h3.jpg"); // note it's https instead of http
    // URL url = new URL("https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html");
    // URL url = new URL("https://www.oracle.com/index.html");
    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
    String inputLine;
    while ((inputLine = in.readLine()) != null) {
      System.out.println(inputLine);
    }
    in.close();
  }
}
