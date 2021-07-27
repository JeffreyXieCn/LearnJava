package io.github.jeffreyxiecn.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.util.Pair;

public class BasicCollection {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);

        System.out.println("Printing the map:" + map);

        Map<String, String> map2 = new HashMap<>();
        map2.put("One", "Un");
        map2.put("Two", "Deux");
        map2.put("Three", "Trois");

        System.out.println("Printing the map2:" + map2);

        Set<int[]> set1 = new HashSet<>();
        set1.add(new int[] { 1, 2 });

        Set<int[]> set2 = new HashSet<>();
        set2.add(new int[] { 1, 2 });
        System.out.println("set1.equals(set2): " + set1.equals(set2)); // false

        Set<String> set3 = new HashSet<>();
        set3.add(1 + "," + 2);

        Set<String> set4 = new HashSet<>();
        set4.add(1 + "," + 2);
        System.out.println("set3.equals(set4): " + set3.equals(set4)); // true

        Set<Pair<Integer, Integer>> set5 = new HashSet<>();
        set5.add(new Pair<>(1, 2));

        Set<Pair<Integer, Integer>> set6 = new HashSet<>();
        set6.add(new Pair<>(1, 2));
        System.out.println("set5.equals(set6): " + set5.equals(set6)); // true

        List<int[]> list1 = new ArrayList<>();
        list1.add(new int[] { 1, 2 });

        List<int[]> list2 = new ArrayList<>();
        list2.add(new int[] { 1, 2 });
        System.out.println("list1.equals(list2): " + list1.equals(list2)); // false
    }
}
