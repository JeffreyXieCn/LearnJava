package io.github.jeffreyxiecn.algorithms.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualCubedNumbers {

  public static void main(String[] args) {
    //    long t1 = System.currentTimeMillis();
    //    printEqualCubedNumbers(1000);
    //    long t2 = System.currentTimeMillis();
    //    System.out.println("Time: " + (t2 - t1) + " milliseconds");

    long t3 = System.currentTimeMillis();
    printEqualCubedNumbers2(1000);
    long t4 = System.currentTimeMillis();
    System.out.println("Time: " + (t4 - t3) + " milliseconds");

    long t5 = System.currentTimeMillis();
    printEqualCubedNumbers3(1000);
    long t6 = System.currentTimeMillis();
    System.out.println("Time: " + (t6 - t5) + " milliseconds");

    /*    Counter: 3178
    Time: 29462 milliseconds
    Counter: 3178
    Time: 3961 milliseconds*/
  }

  public static void printEqualCubedNumbers(int n) {
    Map<Integer, Integer> cubed = new HashMap<>(n);
    for (int i = 1; i <= n; i++) {
      cubed.put((int) Math.pow(i, 3), i);
    }

    int sum;
    int target;
    int counter = 0;
    for (int a = 1; a < n; a++) {
      for (int b = a + 1; b <= n; b++) {
        sum = (int) Math.pow(a, 3) + (int) Math.pow(b, 3);
        for (int c = 1; c <= n; c++) {
          if (c != a && c != b) {
            target = sum - (int) Math.pow(c, 3);
            if (target <= 0) {
              break;
            }
            if (cubed.containsKey(target)) {
              // System.out.println(a + ", " + b + ", " + c + ", " + cubed.get(target));
              counter++;
              // break; //{[15, 930]   [198, 927]  [295, 920]}
            }
          }
        }
      }
    }

    // a < b, c < d; count (a,b,c,d); (a,b,d,c); (c, d, a, b); (c, d, b, a) only once
    counter = counter / 4;
    System.out.println("Counter: " + counter);
  }

  public static void printEqualCubedNumbers2(int n) {
    Map<Long, Integer> cubedMap = new HashMap<>(n);
    long[] cubed = new long[n + 1];
    long temp;
    for (int i = 1; i <= n; i++) {
      temp = (long) Math.pow(i, 3);
      cubedMap.put(temp, i);
      cubed[i] = temp;
    }

    long sum;
    long target;
    int counter = 0;
    for (int a = 1; a < n; a++) {
      for (int b = a + 1; b <= n; b++) {
        sum = cubed[a] + cubed[b];
        for (int c = 1; c <= n; c++) {
          if (c != a && c != b) {
            target = sum - cubed[c];
            if (target <= 0) {
              break;
            }
            if (cubedMap.containsKey(target)) {
              // System.out.println(a + ", " + b + ", " + c + ", " + cubedMap.get(target));
              counter++;
              // break; //{[15, 930]   [198, 927]  [295, 920]}
            }
          }
        }
      }
    }

    // a < b, c < d; count (a,b,c,d); (a,b,d,c); (c, d, a, b); (c, d, b, a) only once
    counter = counter / 4;

    System.out.println("Counter: " + counter);
  }

  public static void printEqualCubedNumbers3(int n) {
    // int mapSize = n * n / 2;
    int mapSize = n * n;
    Map<Long, List<Integer[]>> map = new HashMap<>(mapSize);

    long sum;
    List<Integer[]> temp;
    for (int c = 1; c < n; c++) {
      for (int d = c + 1; d <= n; d++) {
        sum = (int) Math.pow(c, 3) + (int) Math.pow(d, 3);
        if (map.containsKey(sum)) {
          map.get(sum).add(new Integer[] {c, d});
        } else {
          // The majority will have size 1, some has size 2, rarely size will be 3. Default is 10
          temp = new ArrayList<>(1);
          temp.add(new Integer[] {c, d});
          map.put(sum, temp);
        }
      }
    }

    int counter = 0;
    for (List<Integer[]> list : map.values()) {
      if (list.size() > 1) {
        //        if (list.size() > 2) {
        //          list.forEach(pair -> System.out.print(Arrays.toString(pair) + "\t"));
        //          System.out.println();
        //        }
        for (int i = 0; i < list.size() - 1; i++) { // {[15, 930]   [198, 927]  [295, 920]}
          for (int j = i + 1; j < list.size(); j++) {
            // System.out.println(list.get(i)[0] + ", " + list.get(i)[1] + ", " + list.get(j)[0] +
            // ", " + list.get(j)[1]);

            counter++;
          }
        }
      }
    }

    System.out.println("Counter: " + counter);
  }

  /*  [15, 930] [198, 927]  [295, 920]
  [22, 986]   [180, 984]  [692, 856]
  [11, 493]   [90, 492]   [346, 428]
  [167, 436]  [228, 423]  [255, 414]
  [300, 670]  [339, 661]  [510, 580]
  [70, 560]   [198, 552]  [315, 525]
  [334, 872]  [456, 846]  [510, 828]
  [111, 522]  [359, 460]  [408, 423]*/

  /*  public static void printEqualCubedNumbers3(int n) { //incorrect implementation
    Map<Long, Integer[]> map = new HashMap<>(n * n);

    long sum;
    Integer[] pair;
    int counter = 0;
    for (int c = 1; c < n; c++) {
      for (int d = c + 1; d <= n; d++) {
        sum = (int) Math.pow(c, 3) + (int) Math.pow(d, 3);
        if (map.containsKey(sum)) {
          pair = map.get(sum);
          // System.out.println(pair[0] + ", " + pair[1] + ", " + c + ", " + d);
          counter++;
        } else {
          map.put(sum, new Integer[] {c, d});
        }
      }
    }

    System.out.println("Counter: " + counter); //only 1593
  }*/
}
