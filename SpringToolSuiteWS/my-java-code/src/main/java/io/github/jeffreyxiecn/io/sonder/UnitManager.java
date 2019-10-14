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
      if (curUnit.getId() != unit.getId()) {
        unitWithDist.add(new UnitWithDistance(curUnit, calcDistance(curUnit, unit)));
      }
    }

    unitWithDist.sort(Comparator.comparing(UnitWithDistance::getDistance));

    int counter = 0;
    List<Unit> result = new ArrayList<>();
    for (UnitWithDistance curUnitDist : unitWithDist) {
      if (isSimilar(curUnitDist, unit)) {
        result.add(curUnitDist.getUnit());
        counter++;
        if (counter == limit) {
          return result;
        }
      }
    }

    return result;
  }

  public List<Unit> nearestSimilarUnitsWithMaxHeap(Unit unit, int limit) {
    List<UnitWithDistance> unitWithDist = new ArrayList<>();
    for (Unit curUnit : units) {
      if (curUnit.getId() != unit.getId()) {
        unitWithDist.add(new UnitWithDistance(curUnit, calcDistance(curUnit, unit)));
      }
    }

    Heap<UnitWithDistance> heap = new MaxHeap<>();
    for (UnitWithDistance curUnitDist : unitWithDist) {
      if (isSimilar(curUnitDist, unit)) {
        if (heap.size() < limit) {
          heap.add(curUnitDist);
        } else if (curUnitDist.compareTo(heap.peek()) < 0) { // peek is O(1) operation
          // curUnitDist is closer than the max in the heap, so need to remove the max from the heap
          // (which is on the top), and add curUnitDist to the heap
          heap.poll(); // poll is O(log(HEAP_SIZE)) operation
          heap.add(curUnitDist); // add is O(log(HEAP_SIZE)) operation
        }
      }
    }

    List<UnitWithDistance> resultsWithDist = heap.toList();
    Collections.sort(resultsWithDist); // sort is O(nlogn) operation, here n is HEAP_SIZE
    return resultsWithDist
        .stream()
        .map(curUnitDist -> curUnitDist.getUnit())
        .collect(Collectors.toList());
  }

  private boolean isSimilar(UnitWithDistance curUnitDist, Unit unit) {
    if (curUnitDist.getUnit().getBeds() == unit.getBeds()) {
      return true;
    }
    return false;
  }

  private double calcDistance(Unit one, Unit two) {
    return Math.sqrt(
        Math.pow(one.getLat() - two.getLat(), 2.0) + Math.pow(one.getLng() - two.getLng(), 2.0));
  }
}
