package io.github.jeffreyxiecn.algorithms.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class OptimizeMemoryUtilization {
  // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
  List<List<Integer>> optimalUtilization(
      int deviceCapacity,
      List<List<Integer>> foregroundAppList,
      List<List<Integer>> backgroundAppList) {
    // WRITE YOUR CODE HERE
    List<List<Integer>> result;
    if (foregroundAppList.size() > backgroundAppList.size()) {
      sortListByMemUse(backgroundAppList);
      result = findPairs(deviceCapacity, foregroundAppList, backgroundAppList);

    } else {
      sortListByMemUse(foregroundAppList);
      List<List<Integer>> bgToFg = findPairs(deviceCapacity, backgroundAppList, foregroundAppList);
      result = new ArrayList<>();
      bgToFg.forEach(pair -> result.add(Arrays.asList(pair.get(1), pair.get(0))));
    }

    return result;
  }
  // METHOD SIGNATURE ENDS

  private List<List<Integer>> findPairs(
      int deviceCapacity, List<List<Integer>> nonSortedApps, List<List<Integer>> sortedApps) {

    List<List<Integer>> potentialApps; // it's possible that different apps use the same memory
    int maxUtil = -1;
    int currUtil;
    int maxBgAppMemUse = sortedApps.get(0).get(1);
    List<List<Integer>> result = new ArrayList<>();
    for (List<Integer> app : nonSortedApps) {
      if (app.get(1) > deviceCapacity || app.get(1) + maxBgAppMemUse < maxUtil) {
        // no need to find the pair, just skip
        continue;
      }

      // potentialApps have the same memory usage
      potentialApps = findAppsToMaxMemUtil(deviceCapacity, app.get(1), sortedApps);
      if (!potentialApps.isEmpty()) {
        currUtil = app.get(1) + potentialApps.get(0).get(1);
        if (currUtil > maxUtil) {
          // new max memory utilization found
          result.clear();
          maxUtil = currUtil;
          addPairToList(result, app.get(0), potentialApps);
        } else if (currUtil == maxUtil) {
          addPairToList(result, app.get(0), potentialApps);
        }
      }
    }

    return result;
  }

  private List<List<Integer>> findAppsToMaxMemUtil(
      int deviceCapacity, int memUse, List<List<Integer>> appList) {
    List<List<Integer>> result = new ArrayList<>();
    int maxMemUse = -1;
    int curMemUse;
    for (List<Integer> app : appList) {
      curMemUse = app.get(1) + memUse;
      if (curMemUse <= deviceCapacity) {
        if (maxMemUse == -1) {
          maxMemUse = curMemUse;
        }

        if (curMemUse == maxMemUse) {
          result.add(app);
        } else if (curMemUse < maxMemUse) {
          // since appList is sorted, no need to check the remaining
          break;
        }
      }
    }
    return result;
  }

  private void addPairToList(
      List<List<Integer>> result, int appId, List<List<Integer>> potentialPair) {
    potentialPair.forEach(pair -> result.add(Arrays.asList(appId, pair.get(0))));
  }

  private void sortListByMemUse(List<List<Integer>> list) {
    // sort the list in the descending order of memory usage
    Comparator<List<Integer>> cmp =
        (list1, list2) -> {
          return list2.get(1) - list1.get(1);
        };

    list.sort(cmp);
    // Collections.sort(list, cmp);
  }
}
