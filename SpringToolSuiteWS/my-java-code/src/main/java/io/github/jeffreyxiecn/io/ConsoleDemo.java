package io.github.jeffreyxiecn.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class ConsoleDemo {

  public static void main(String[] args) throws IOException {
    System.out.println("请输入任意个整数，用*结束：");
    ArrayList<Integer> list = new ArrayList<>();
    String line;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    while (!(line = br.readLine()).equals("*")) {
      list.add(Integer.valueOf(line));
    }

    Integer[] inputs = new Integer[list.size()];

    inputs = list.toArray(inputs);

    System.out.println("请输入排序方式（输入数字即可）：");
    System.out.println("1. 升序\t\t2.降序");
    line = br.readLine();
    if (line.equals("1")) {
      // TODO: 从小到大对inputs数组进行排序
      Arrays.sort(inputs, (a, b) -> (a - b));
    } else {
      // TODO: 从大到小对inputs数组进行排序
      Arrays.sort(inputs, (a, b) -> (b - a));
    }

    System.out.println("排序后的结果为：");
    // TODO: 打印排序后的结果
    Stream.of(inputs).forEach(System.out::println);
  }
}
