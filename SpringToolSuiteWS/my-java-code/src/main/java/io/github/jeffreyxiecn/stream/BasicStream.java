package io.github.jeffreyxiecn.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BasicStream {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("One", "TWO", "three", "Three");
        Set<String> result = list.stream().map(String::toLowerCase).collect(Collectors.toSet());
        System.out.println("Resulting set is: " + result);

        int[][] logs = { { 1950, 1961 }, { 1960, 1971 }, { 1970, 1981 } };

        int minBirthYear = Arrays.stream(logs).min((log1, log2) -> log1[0] - log2[0]).orElseThrow()[0];
        // another way
        int maxBirthYear = Arrays.stream(logs).map(log -> log[0]).mapToInt(v -> v).max().orElseThrow();
        System.out.println("birth range: [" + minBirthYear + ", " + maxBirthYear + "]");
    }
}
