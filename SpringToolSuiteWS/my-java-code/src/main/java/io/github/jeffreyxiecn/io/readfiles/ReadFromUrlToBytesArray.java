package io.github.jeffreyxiecn.io.readfiles;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.commons.io.IOUtils;

public class ReadFromUrlToBytesArray {

  public static void main(String[] args) throws IOException, URISyntaxException {
    //    Path path =
    //        Paths.get(
    //            new URL("https://i.imgur.com/xGmX4h3.jpg").toURI()); // note it's https instead of
    // http
    //    byte[] bytes = Files.readAllBytes(path);
    //    System.out.println("File size: " + bytes.length + " bytes");

    URL url = new URL("https://i.imgur.com/xGmX4h3.jpg");
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    // try (InputStream in = url.openStream()) {
    try (BufferedInputStream in = new BufferedInputStream(url.openStream())) {
      int bytesRead = 0;
      byte[] buffer = new byte[1024];
      while ((bytesRead = in.read(buffer)) != -1) {
        output.write(buffer, 0, bytesRead);
      }
    }

    byte[] bytes = output.toByteArray();
    System.out.println("File size: " + bytes.length + " bytes");

    // using library Apache commons-io
    bytes = IOUtils.toByteArray(new URL("https://i.imgur.com/xGmX4h3.jpg"));
    System.out.println("File size: " + bytes.length + " bytes");
  }
}
