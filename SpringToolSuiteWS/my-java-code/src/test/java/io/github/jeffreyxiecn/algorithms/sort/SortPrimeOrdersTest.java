package io.github.jeffreyxiecn.algorithms.sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortPrimeOrdersTest {

  @BeforeEach
  void setUp() throws Exception {}

  @Test
  void test() {
    String order1 = "zld 93 12";
    String order2 = "fp kindle book";
    String order3 = "10a echo show";
    String order4 = "17g 12 25 6";
    String order5 = "ab1 kindle book";
    String order6 = "125 echo dot second generation";
    List<String> orders = Arrays.asList(order1, order2, order3, order4, order5, order6);
    List<String> expected = Arrays.asList(order6, order3, order5, order2, order1, order4);
    List<String> actual = new SortPrimeOrders().prioritizedOrders(orders.size(), orders);
    assertEquals(expected, actual);
  }
}
