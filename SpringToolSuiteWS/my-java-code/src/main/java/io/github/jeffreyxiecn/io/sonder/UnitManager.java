package io.github.jeffreyxiecn.io.sonder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UnitManager {
  private List<Unit> units;

  public UnitManager() {
    units = new ArrayList<>();
  }

  public void addUnit(Unit unit) {
    units.add(unit);
  }

  public int getNumberOfUnits() {
    return units.size();
  }

  public Unit getUnit(int index) {
    return units.get(index);
  }

  public List<Unit> nearestSimilarUnits(Unit unit, int limit) {
    List<UnitWithDistance> unitWithDist = new ArrayList<>();
    for (Unit curUnit : units) {
      if (curUnit.getId() != unit.getId() && isSimilar(curUnit, unit)) {
        unitWithDist.add(new UnitWithDistance(curUnit, calcDistance(curUnit, unit)));
      }
    }

    unitWithDist.sort(Comparator.comparing(UnitWithDistance::getDistance));

    int counter = 0;
    List<Unit> result = new ArrayList<>();
    for (UnitWithDistance curUnitDist : unitWithDist) {
      result.add(curUnitDist.getUnit());
      counter++;
      if (counter == limit) {
        return result;
      }
    }

    return result;
  }

  public List<Unit> nearestSimilarUnitsWithMaxHeap(Unit unit, int limit) {
    List<UnitWithDistance> unitWithDist = new ArrayList<>();
    for (Unit curUnit : units) {
      if (curUnit.getId() != unit.getId() && isSimilar(curUnit, unit)) {
        unitWithDist.add(new UnitWithDistance(curUnit, calcDistance(curUnit, unit)));
      }
    }

    Heap<UnitWithDistance> heap = new MaxHeap<>();
    for (UnitWithDistance curUnitDist : unitWithDist) {
      if (heap.size() < limit) {
        heap.add(curUnitDist);
      } else if (curUnitDist.compareTo(heap.peek()) < 0) { // peek is O(1) operation
        // curUnitDist is closer than the max in the heap, so need to remove the max from the heap
        // (which is on the top), and add curUnitDist to the heap
        heap.poll(); // poll is O(log(HEAP_SIZE)) operation
        heap.add(curUnitDist); // add is O(log(HEAP_SIZE)) operation
      }
    }

    List<UnitWithDistance> resultsWithDist = heap.toList();
    Collections.sort(resultsWithDist); // sort is O(nlogn) operation, here n is HEAP_SIZE
    return resultsWithDist
        .stream()
        .map(curUnitDist -> curUnitDist.getUnit())
        .collect(Collectors.toList());
  }

  public static boolean isSimilar(Unit first, Unit second) {
    if (first.getBeds() == second.getBeds()) {
      return true;
    }
    return false;
  }

  public static double calcDistance(Unit first, Unit second) {
    return Math.sqrt(
        Math.pow(first.getLat() - second.getLat(), 2.0)
            + Math.pow(first.getLng() - second.getLng(), 2.0));
  }
}
