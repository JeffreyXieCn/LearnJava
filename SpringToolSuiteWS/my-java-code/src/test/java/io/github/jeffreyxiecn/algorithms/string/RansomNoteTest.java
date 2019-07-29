package io.github.jeffreyxiecn.algorithms.string;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class RansomNoteTest {
  private RansomNote mangazine;

  @Before
  public void setUp() {
    mangazine = new RansomNote("I wish I can fly, I wish I can swim. I wish my dreams come true");
  }

  @Test
  public void givenMagazine_whenRansomNoteCanBeFormed_thenReturnTrue() {
    String ransomNote = "I wish fly come true";
    assertTrue(mangazine.canForm(ransomNote));
  }

  @Test
  public void givenMagazine_whenRansomNoteHasNewWord_thenReturnFalse() {
    String ransomNote = "I wish my dream job come true";
    assertFalse(mangazine.canForm(ransomNote));
  }

  @Test
  public void givenMagazine_whenRansomNoteWordAppearMoreFrequently_thenReturnFalse() {
    String ransomNote = "I wish I can swim, I wish I can fly, I wish I can come";
    assertFalse(mangazine.canForm(ransomNote));
  }

  @Test
  public void givenMagazine_whenRansomNoteIsEmpty_thenReturnTrue() {
    String ransomNote = "";
    System.out.println(String.format("Empty string has %d words", ransomNote.split("\\W+").length));
    assertTrue(mangazine.canForm(ransomNote));
  }

  @Test
  public void givenMagazine_whenRansomNoteAreWhiteSpaces_thenReturnTrue() {
    String ransomNote = "    \t   \n";
    assertTrue(mangazine.canForm(ransomNote));
  }
}
