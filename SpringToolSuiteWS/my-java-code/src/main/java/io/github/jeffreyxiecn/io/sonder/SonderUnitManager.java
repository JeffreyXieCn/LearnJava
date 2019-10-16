package io.github.jeffreyxiecn.io.sonder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SonderUnitManager {
  // map from unitId to Unit
  private Map<Integer, Unit> units;

  // assume users don't want to see more than 200 nearby similar units
  public static final int MAX_LIMIT = 200;

  // map from unitId to list of nearby similar units, which is sorted from closest to farthest , and
  // the size of the list is capped at MAX_LIMIT
  private Map<Integer, List<UnitWithDistance>> nearbyUnits;

  public SonderUnitManager() {
    units = new HashMap<>();
    nearbyUnits = new HashMap<>();
  }

  public void addUnit(Unit unit) {
    // calculate distance from this new unit to existing similar units
    List<UnitWithDistance> similarUnitsWithDist = new ArrayList<>();

    double dist;
    for (Unit currUnit : units.values()) {
      if (isSimilar(unit, currUnit)) {
        dist = calcDistance(currUnit, unit);
        similarUnitsWithDist.add(new UnitWithDistance(currUnit, dist));

        // update the nearby units for currUnit by considering this new unit
        List<UnitWithDistance> currUnitNearbys = nearbyUnits.get(currUnit.getId());
        insertIntoOrderedList(currUnitNearbys, new UnitWithDistance(unit, dist));
      }
    }

    // add an entry for this new unit in nearbyUnits
    findNearbyUnits(unit, similarUnitsWithDist);

    units.put(unit.getId(), unit);
  }

  public int getNumberOfUnits() {
    return units.size();
  }

  public Unit getUnit(int unitId) {
    return units.get(unitId);
  }

  public List<Unit> nearestSimilarUnits(Unit unit, int limit) {
    if (limit < 1 || limit > MAX_LIMIT) {
      throw new IllegalArgumentException("The limit must be between 1 and " + MAX_LIMIT);
    }

    return nearbyUnits
        .get(unit.getId())
        .stream()
        .limit(limit)
        .map(UnitWithDistance::getUnit)
        .collect(Collectors.toList());
  }

  private boolean isSimilar(Unit first, Unit second) {
    if (first.getBeds() == second.getBeds()) {
      return true;
    }
    return false;
  }

  private double calcDistance(Unit first, Unit second) {
    return Math.sqrt(
        Math.pow(first.getLat() - second.getLat(), 2.0)
            + Math.pow(first.getLng() - second.getLng(), 2.0));
  }

  private void findNearbyUnits(Unit unit, List<UnitWithDistance> unitsWithDist) {
    List<UnitWithDistance> result = new ArrayList<>();
    for (UnitWithDistance currUnitWithDist : unitsWithDist) {
      insertIntoOrderedList(result, currUnitWithDist);
    }

    nearbyUnits.put(unit.getId(), result);
  }

  private void insertIntoOrderedList(
      List<UnitWithDistance> list, UnitWithDistance currUnitWithDist) {

    // note that binarySearch works by using compareTo method, not equals method, so if there is one
    // unit in the list having the same distance as currUnitWithDist, index will >= 0
    int insertionPoint = Collections.binarySearch(list, currUnitWithDist);
    if (insertionPoint < 0) {
      insertionPoint = -1 - insertionPoint;
    }
    if (list.size() < MAX_LIMIT) {
      list.add(insertionPoint, currUnitWithDist);
    } else {
      // the list is full
      if (insertionPoint < list.size()) {
        // and not all units in the list are closer than currUnitWithDist, so the farthest unit
        // should be removed before adding currUnitWithDist
        list.remove(MAX_LIMIT - 1);
        list.add(insertionPoint, currUnitWithDist);
      }
    }
  }
}
