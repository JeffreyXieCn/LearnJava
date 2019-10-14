package io.github.jeffreyxiecn.io.sonder;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

class UnitManagerTest {
  private static UnitManager um;
  public static int LIMIT = 10;
  private static Random random;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    ObjectMapper om = new ObjectMapper();
    List<Unit> units =
        om.readValue(
            new File("src/test/resources/units_small.json"), new TypeReference<List<Unit>>() {});

    System.out.println("Number of units in the JSON file:" + units.size());

    um = new UnitManager();
    units.forEach(um::addUnit);

    System.out.println("Number of units added to UnitManager:" + um.getNumberOfUnits());

    random = new Random();
  }

  @RepeatedTest(10)
  void whenBothMethodsFindTheSameUnits_thenCorrect(RepetitionInfo repInfo) {
    System.out.println(
        String.format(
            "\n\n====Run %d of %d====",
            repInfo.getCurrentRepetition(), repInfo.getTotalRepetitions()));

    int index = random.nextInt(um.getNumberOfUnits());
    Unit unit = um.getUnit(index);
    System.out.println(
        "Searching for " + LIMIT + " nearest similar " + "units around unit: " + unit.toString());

    List<Unit> resultWithTotalSort = um.nearestSimilarUnits(unit, LIMIT);
    System.out.println(
        "resultWithTotalSort:" + resultWithTotalSort.size() + "\n" + resultWithTotalSort);
    List<Unit> resultWithMaxHeap = um.nearestSimilarUnitsWithMaxHeap(unit, LIMIT);
    System.out.println("resultWithMaxHeap:" + resultWithMaxHeap.size() + "\n" + resultWithMaxHeap);

    // Can't make below assertion, although the sort algorithms used in UnitManager are stable,
    // MaxHeap is used to produce resultWithMaxHeap, and MaxHeap, by definition, maintains partial
    // ordering. Also the MaxHeap is constantly modified as closer unit is discovered
    // assertEquals(resultWithTotalSort, resultWithMaxHeap);

    // We can assert the two results have the same size, and contents are the same regardless of
    // order
    assertEquals(resultWithTotalSort.size(), resultWithMaxHeap.size());
    assertThat(resultWithTotalSort, containsInAnyOrder(resultWithMaxHeap.toArray()));

    // Or we can extract the unit ids from the result, sort them, and assert the sorted lists are
    // the same
    List<Integer> unitIdWithTotalSort =
        resultWithTotalSort.stream().map(Unit::getId).sorted().collect(Collectors.toList());

    List<Integer> unitIdWithMaxHeap =
        resultWithMaxHeap.stream().map(Unit::getId).sorted().collect(Collectors.toList());

    assertEquals(unitIdWithTotalSort, unitIdWithMaxHeap);
  }
}
