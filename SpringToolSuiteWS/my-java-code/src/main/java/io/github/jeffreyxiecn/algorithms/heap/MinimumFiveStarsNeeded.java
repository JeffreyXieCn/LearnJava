package io.github.jeffreyxiecn.algorithms.heap;

import java.util.List;
import java.util.PriorityQueue;

public class MinimumFiveStarsNeeded {

  /*
   * To minimize the number of five stars needed to reach the threshold, we need to give that five
   * star to the product that will produce maximum gain, which is calculated as:
   * (fiveStars + 1)/(total+1) - (fiveStars)/(total)
   *
   * We define class RatingGain to represent the current product rating and the gain that will be
   * produced with one more fiveStar.
   *
   * We use a max heap pq to store all the current RatingGains. As long as the rating hasn't reached
   * the threshold, we remove the root from pq, add root's gain to the rating, then add one fiveStar
   * to that root and calculate the next gain, then put that root back to the pq.
   *
   * Let n be the number of products, then:
   * Space complexity is O(n), because we need to maintain a max heap of size n.
   *
   * Time complexity: each time we add a new five star, we need to maintain the max heap, whose time
   * complexity is O(logn), but how many new five stars are needed really depending on three factors:
   *  - the threshold t (such as 0.77)
   *  - the number of products n
   *  - the current rating for each product
   *  In worst case, let's say each product has m total ratings, and 0 fiveStar, so the current
   *  average rating is 0. Adding the first batch n five stars will add 1/((m+1)*n) to the average
   *  rating, the second batch n five stars will add m/((m+1)*(m+2)*n), which is less than the
   *  first batch's contribution, and the third batch will contribute even less...
   *  So the total number of five stars needed is more than t*(m+1)*n.
   *  And the time complexity is proximately O(t*m*n*logn)
   *
   */
  public static int minFiveStarsNeeded(List<List<Integer>> productRatings, int threshold) {
    int n = productRatings.size();
    float curRating = 0;

    // use maxHeap so that each time the root is the product for which one more five star will gain
    // most. Set the heap's initial capacity to n because we know that's what is needed, this can
    // avoid the cost of resizing to increase capacity
    PriorityQueue<RatingGain> pq =
        new PriorityQueue<>(
            n,
            (a, b) -> {
              return b.compareTo(a);
            });
    for (List<Integer> prodRate : productRatings) {
      curRating += prodRate.get(0) * 100.0f / prodRate.get(1);
      pq.add(new RatingGain(prodRate.get(0), prodRate.get(1)));
    }

    // avoid division whenever possible since division may lose precision
    // curRating = (curRating / n) * 100;
    // scale both the curRating and threshold by n times to further avoid division
    int thresholdScaled = threshold * n;

    int result = 0;
    // System.out.println("\ncurRating:" + curRating);
    while (curRating < thresholdScaled) {
      RatingGain root = pq.remove();
      curRating += root.gain;
      // System.out.println("curRating:" + curRating);
      result++;
      pq.add(root.next());
    }

    return result;
  }
}

class RatingGain implements Comparable<RatingGain> {
  int fiveStars;
  int total;
  float gain; // gain by adding one more fiveStar

  public RatingGain(int fiveStars, int total) {
    this.fiveStars = fiveStars;
    this.total = total;
    gain = calGain();
  }

  public RatingGain next() {
    fiveStars++;
    total++;
    gain = calGain();
    return this;
  }

  private float calGain() {
    // avoid division whenever possible since division may lose precision
    return (total * (fiveStars + 1) - fiveStars * (total + 1)) * 100.0f / (total * (total + 1));
  }

  @Override
  public int compareTo(RatingGain that) {
    float delta = this.gain - that.gain;
    if (delta == 0) {
      return 0;
    } else if (delta > 0) {
      return 1;
    } else {
      return -1;
    }
  }
}
