package io.github.jeffreyxiecn.algorithms.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class SortPrimeOrders {
  // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
  public List<String> prioritizedOrders(int numOrders, List<String> orderList) {
    // WRITE YOUR CODE HERE
    List<String> primeOrders = new ArrayList<>();
    List<String> nonPrimeOrders = new ArrayList<>();
    char firtCharAfterSpace;
    for (String order : orderList) {
      firtCharAfterSpace = order.charAt(order.indexOf(' ') + 1);
      if (firtCharAfterSpace > '0' && firtCharAfterSpace <= '9') {
        nonPrimeOrders.add(order);
      } else {
        primeOrders.add(order);
      }
    }

    Comparator<String> cmp =
        (str1, str2) -> {
          int indexOfSpace1 = str1.indexOf(' ');
          String orderMeta1 = str1.substring(indexOfSpace1 + 1);

          int indexOfSpace2 = str2.indexOf(' ');
          String orderMeta2 = str2.substring(indexOfSpace2 + 1);
          if (!orderMeta1.equals(orderMeta2)) {
            return orderMeta1.compareTo(orderMeta2);
          } else {
            String orderId1 = str1.substring(0, indexOfSpace1);
            String orderId2 = str2.substring(0, indexOfSpace2);
            return orderId1.compareTo(orderId2);
          }
        };

    primeOrders.sort(cmp);
    // Collections.sort(primeOrders, cmp);

    primeOrders.addAll(nonPrimeOrders);
    return primeOrders;
  }
  // METHOD SIGNATURE ENDS
}
