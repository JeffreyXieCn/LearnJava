package io.github.jeffreyxiecn.io.sonder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
