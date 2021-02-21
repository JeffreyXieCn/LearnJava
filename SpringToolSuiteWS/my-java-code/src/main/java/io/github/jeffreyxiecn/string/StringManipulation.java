package io.github.jeffreyxiecn.string;

import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class StringManipulation {

  public enum BodyType {
    NOBODY,
    JSON,
    MULTIPART,
    FORMDATA
  }

  public static void main(String[] args) {
    String token1 = "abcd1234";
    String[] tokens1 = token1.split(" ");
    System.out.println("tokens1:" + tokens1.length); // 1

    String token2 = "1.2.3";
    String[] tokens2 = token2.split(".");
    System.out.println("tokens2:" + tokens2.length); // 0

    String[] tokens3 = token2.split("\\.");
    System.out.println("tokens3:" + tokens3.length); // 3

    List<String> setTokens =
        Collections.list(new StringTokenizer("1.2.3", "."))
            .stream()
            .map(token -> (String) token)
            .collect(Collectors.toList());

    System.out.println("setTokens:" + setTokens);

    BodyType bodyType = BodyType.MULTIPART;
    System.out.println("Body Type: " + bodyType);
    StringBuilder sb = new StringBuilder("mystr");
    System.out.println("mystr:" + sb);

    System.out.println(new Child());
  }
}

class Parent {

  public String getBodyAsString() {
    return "Parent body string";
  }

  @Override
  public String toString() {
    return "Body:" + getBodyAsString();
  }
}

class Child extends Parent {

  @Override
  public String getBodyAsString() {
    return "Child body string";
  }
}
