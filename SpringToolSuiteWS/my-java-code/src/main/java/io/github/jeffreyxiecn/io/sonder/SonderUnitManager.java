package io.github.jeffreyxiecn.io.sonder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SonderUnitManager {
  // map from unitId to Unit
  private Map<Integer, Unit> units;

  // map from unitId to nearby similar units
  private Map<Integer, List<UnitWithDistance>> nearbyUnits;

  // assume users don't want to see more than 30 nearly similar units
  public static final int MAX_LIMIT = 30;

  public SonderUnitManager() {
    units = new HashMap<>();
    nearbyUnits = new HashMap<>();
  }

  public void addUnit(Unit unit) {
    units.put(unit.getId(), unit);

    // calc distance from this unit to existing units

    // add an entry for this unit in nearbyUnits

    // update the existing mappings in nearbyUnits with this UnitWithDistance with binary search
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
