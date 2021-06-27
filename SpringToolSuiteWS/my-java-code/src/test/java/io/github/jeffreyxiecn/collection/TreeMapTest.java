package io.github.jeffreyxiecn.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TreeMapTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {}

    @Test
    public void givenTreeMap_whenOrdersEntriesNaturally_thenCorrect() {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(3, "val");
        map.put(2, "val");
        map.put(1, "val");
        map.put(5, "val");
        map.put(4, "val");

        assertEquals("[1, 2, 3, 4, 5]", map.keySet().toString());
    }

    @Test
    public void givenTreeMap_whenOrdersEntriesNaturally_thenCorrect2() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("c", "val");
        map.put("b", "val");
        map.put("a", "val");
        map.put("e", "val");
        map.put("d", "val");

        assertEquals("[a, b, c, d, e]", map.keySet().toString());
    }

    @Test
    public void givenTreeMap_whenProvideCustomComparator_thenCorrect() {
        TreeMap<String, String> map = new TreeMap<>(Comparator.comparing(String::length));
        map.put("three", "tois");
        map.put("one", "un");
        map.put("four", "quart");
        String prevValue = map.put("two", "deux"); // the key is considered exist ("one") due to our comparator by
                                                   // length, so its value "un" is replaced with "deux"

        assertEquals("un", prevValue);
        assertEquals(3, map.size());
        assertEquals("[one, four, three]", map.keySet().toString());
        assertEquals("deux", map.get("one"));
        assertEquals("deux", map.get("xxx"));
        assertEquals("deux", map.get("yyy"));
    }

    @Test
    public void givenTreeMap_whenOrdersEntriesByComparator_thenCorrect() {
        TreeMap<Integer, String> map = new TreeMap<>(Comparator.reverseOrder());
        map.put(3, "val");
        map.put(2, "val");
        map.put(1, "val");
        map.put(5, "val");
        map.put(4, "val");

        assertEquals("[5, 4, 3, 2, 1]", map.keySet().toString());
    }

    @Test
    public void givenTreeMap_whenPerformsQueries_thenCorrect() {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(3, "val");
        map.put(2, "val");
        map.put(1, "val");
        map.put(5, "val");
        map.put(4, "val");

        Integer highestKey = map.lastKey();
        Integer lowestKey = map.firstKey();
        Set<Integer> keysLessThan3 = map.headMap(3).keySet();
        Set<Integer> keysGreaterThanEqTo3 = map.tailMap(3).keySet();

        assertEquals(Integer.valueOf(5), highestKey);
        assertEquals(Integer.valueOf(1), lowestKey);
        assertEquals("[1, 2]", keysLessThan3.toString());
        assertEquals("[3, 4, 5]", keysGreaterThanEqTo3.toString());
    }

}
