package io.github.jeffreyxiecn.string;
// package whatever; // don't place package name!

// hello
// Given a list of sports players, each player has name and score.
// Sort by score descendingly. If the scores are the same, sort by name in the reverse alphabetic
// order.

// Input
// 3
// Jack 20
// Martin 30
// Sean 20

// Output
// Martin 30
// Sean 20
// Jack 20

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Player {
  private String name;
  private int score;

  public Player(String name, int score) {
    this.name = name;
    this.score = score;
  }

  public String getName() {
    return name;
  }

  public int getScore() {
    return score;
  }

  @Override
  public String toString() {
    return name + " " + score;
  }

  public static void main(String[] args) {
    System.out.println("Hello Java");
    List<Player> players =
        Arrays.asList(new Player("Jack", 20), new Player("Martin", 30), new Player("Sean", 20));
    System.out.println(players);

    players.sort(
        Comparator.comparing(Player::getScore)
            .reversed()
            .thenComparing(Comparator.comparing(Player::getName).reversed()));

    /*Collections.sort(
    players,
    (a, b) -> {
      if (a.getScore() == b.getScore()) {
        return b.getName().compareTo(a.getName());
      } else {
        return b.getScore() - a.getScore();
      }
    });*/
    System.out.println(players);
  }
}
