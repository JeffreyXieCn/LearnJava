package io.github.jeffreyxiecn.algorithms.string;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicString0205 {
  public boolean isIsomorphic(String s, String t) {
    return isIsomorphicHelper(s, t) && isIsomorphicHelper(t, s);
  }

  private boolean isIsomorphicHelper(String s, String t) {
    char[] charsS = s.toCharArray();
    char[] charsT = t.toCharArray();
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < charsS.length; i++) {
      if (map.containsKey(charsS[i])) {
        // in s, char at i and at map.get(chars[i]) are the same
        if (charsT[map.get(charsS[i])] != charsT[i]) { // check whether the same is true in charsT
          return false;
        }
      } else {
        map.put(charsS[i], i); // the index that this char appears for the first time
      }
    }
    return true;
  }

  public boolean isIsomorphic2(String s, String t) {
    char[] charsS = s.toCharArray();
    char[] charsT = t.toCharArray();
    Map<Character, Integer> mapS = new HashMap<>();
    Map<Character, Integer> mapT = new HashMap<>();
    for (int i = 0; i < charsS.length; i++) {
      if (mapS.containsKey(charsS[i])) {
        // in s, char at i and mapS.get(charsS[i]) are the same
        if (charsT[mapS.get(charsS[i])] != charsT[i]) {
          return false;
        }
      } else {
        mapS.put(charsS[i], i);
      }

      if (mapT.containsKey(charsT[i])) {
        // in t, char at i and mapT.get(charsT[i]) are the same
        if (charsS[mapT.get(charsT[i])] != charsS[i]) {
          return false;
        }
      } else {
        mapT.put(charsT[i], i);
      }
    }
    return true;
  }

  public boolean isIsomorphic3(String s, String t) {
    char[] charsS = s.toCharArray();
    char[] charsT = t.toCharArray();
    Map<Character, Integer> mapS = new HashMap<>();
    Map<Character, Integer> mapT = new HashMap<>();
    for (int i = 0; i < charsS.length; i++) {
      if (mapS.containsKey(charsS[i])) {
        // in s, char at i and mapS.get(charsS[i]) are the same
        if (charsT[mapS.get(charsS[i])] != charsT[i]) {
          return false;
        }
      } else {
        mapS.put(charsS[i], i);
      }
    }

    for (int i = 0; i < charsT.length; i++) {
      if (mapT.containsKey(charsT[i])) {
        // in t, char at i and mapT.get(charsT[i]) are the same
        if (charsS[mapT.get(charsT[i])] != charsS[i]) {
          return false;
        }
      } else {
        mapT.put(charsT[i], i);
      }
    }
    return true;
  }

  public boolean isIsomorphic4(String s, String t) {
    char[] charsS = s.toCharArray();
    char[] charsT = t.toCharArray();
    Map<Character, Character> map = new HashMap<>();
    for (int i = 0; i < charsS.length; i++) {
      if (map.containsKey(charsS[i])) {
        // this char has appeared before
        if (charsT[i] != map.get(charsS[i])) {
          return false;
        }
      } else {
        if (map.containsValue(charsT[i])) {
          return false; // two characters can't map to the same character
        }
        map.put(charsS[i], charsT[i]);
      }
    }
    return true;
  }

  public boolean isIsomorphic5(String s, String t) {
    int[] sCharLastAppearAt = new int[256];
    int[] tCharLastAppearAt = new int[256];
    int n = s.length();
    for (int i = 0; i < n; ++i) {
      if (sCharLastAppearAt[s.charAt(i)] != tCharLastAppearAt[t.charAt(i)]) {
        // s.charAt(i) should map to t.charAt(i), so the position they appear last time should be
        // the same, otherwise, return false
        return false;
      }
      sCharLastAppearAt[s.charAt(i)] = i + 1;
      tCharLastAppearAt[t.charAt(i)] = i + 1;
    }
    return true;
  }
}
