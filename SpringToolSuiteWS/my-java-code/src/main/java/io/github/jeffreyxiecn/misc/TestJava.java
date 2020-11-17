package io.github.jeffreyxiecn.misc;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** */

/** @author ewenxie */
public class TestJava {

  protected static String timestamp = null;

  public static Vector<String> localComponentInfoVector;
  // private final static Pattern HOST_PATTERN = Pattern.compile("^(.*)-[bot]$"); //valid to MiO
  private static final Pattern HOST_PATTERN =
      Pattern.compile("^(.*?)\\..*"); // use this (non-greedy) pattern since the beginning of vMIO

  private static SimpleDateFormat formatter = null;

  static {
    formatter = new SimpleDateFormat("yyyyMMddHHmm");
    formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
  }

  protected static String getNowTimestamp() {
    if (timestamp == null) {
      Date now = new Date();
      timestamp = formatter.format(now);
    }
    return timestamp;
  }

  enum ActionType {
    START("start"),
    CANCEL("cancel");

    private final String action;

    ActionType(String action) {
      this.action = action;
    }

    String action() {
      return action;
    }
  }

  public static void main(String[] args) throws UnknownHostException {
    String[] strArr = new String[] {"one", "two", "three"};
    System.out.println("Print string array contents:" + Arrays.toString(strArr));

    System.out.println("=========================================");

    System.out.println(minimizeVersion("MIO.LV.VMIO.SNAPSHOT"));
    System.out.println(replaceSnapshotWithTimestamp("PVMIOSNAPSHOT."));
    System.out.println(removeIllegalCharacters("PVMIO201412172136"));

    TestJava tj = new TestJava();
    if (localComponentInfoVector == null) {
      System.out.println("localComponentInfoVector is null");
    } else {
      System.out.println("localComponentInfoVector is not null");
    }

    // System.out.println("HostName: " + InetAddress.getByName("172.16.33.2").getHostName());
    // System.out.println("normalizeHost: " + tj.normalizeHost("172.16.33.2"));

    Matcher m = HOST_PATTERN.matcher("mms-tn-00.traffic.vmio.ericsson.com");
    if (m.matches()) {
      System.out.println("matched hostname:" + m.group(1));
    }

    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HH_mm_ss_SSS");

    try {
      Date triggerDate = formatter.parse("20150619_21_04_21_000");
      System.out.println(triggerDate.toString());
      System.out.println(formatter.format(triggerDate));
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    System.out.println("Compare a string with an enum: " + "START".equals(ActionType.START));
    System.out.println(
        "Compare a string with an enum: " + "start".equals(ActionType.START.action()));

    long triggerDate = 1445687731000L;
    long currentTime = 1437738931621L;

    long delayLimit = currentTime + 92 * 86400000L;
    System.out.println("Delay limit:" + delayLimit);

    if (triggerDate > currentTime + 92 * 86400000) {
      System.out.println("Beyond limitation!");
    } else {
      System.out.println("Within limitation");
    }

    if (triggerDate > delayLimit) {
      System.out.println("Beyond limitation!");
    } else {
      System.out.println("Within limitation");
    }

    Date trigDate = new Date(triggerDate);
    Date dateLimit = new Date(currentTime + 92 * 86400000L);
    if (trigDate.compareTo(dateLimit) > 0) {
      System.out.println("Beyond limitation!");
    } else {
      System.out.println("Within limitation");
    }

    // Pattern address_range_regexp = Pattern.compile("[00000000-9999999999]");
    Pattern addressRangeRegexp = Pattern.compile("\\+?[0-9]{8,10}");
    String[] testStrArr =
        new String[] {
          "5142984598",
          "+5142984598",
          "+15142984598",
          "00000000",
          "000000001",
          "0000000001",
          "9999999999",
          "+9999999999",
          "12345678",
          "1234567",
          "0000000",
          "0000000000",
          "99999999900"
        };
    for (String element : testStrArr) {
      System.out.print(element + ":");
      if (addressRangeRegexp.matcher(element).matches()) {
        System.out.println("Match!");
      } else {
        System.out.println("Not match!");
      }
    }

    String[] addressElem = "1.1.1234567890".split("[.]");

    for (String addr : addressElem) {
      System.out.println(addr);
    }
    try {
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("obj.out"));
      oos.writeObject("This is a Java String serialized to a file");
      oos.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    System.out.println("address_range is: " + "\\\\+?[0-9]{8,15}");

    Date myDate = new Date(1449858032246L);
    System.out.println("Date is:" + myDate.toString());

    System.out.println(
        "Print French message: "
            + "Victor Hugo est né le 26 Février 1802 à Besancon en France. Poète, romancier");

    System.out.println("Print Chinese message: " + "测试Java编码");

    try {
      String aaa = "ABC Farmacéutica Corporation";
      String bbb = "ABC FarmacÃ©utica Corporation";

      aaa = URLEncoder.encode(aaa, "UTF-8");
      String bbb1 = URLDecoder.decode(bbb, "UTF-8");

      System.out.println("aaa   " + aaa);
      System.out.println("bbb1   " + bbb1);

      String bbb2 = URLDecoder.decode(new String(bbb.getBytes("ISO-8859-1"), "UTF-8"), "UTF-8");
      System.out.println("bbb2   " + bbb2);

    } catch (Exception e) {
      System.out.println(e);
    }

    Pattern chineseRegexp = Pattern.compile(".*欢迎.*");
    if (chineseRegexp.matcher("我欢迎你").matches()) {
      System.out.println("中文匹配成功");
    }

    Pattern arabicRegexp = Pattern.compile(".*رسائل جديدة.*");
    if (arabicRegexp
        .matcher("أهلا Matthew. لديك 1 رسائل جديدة من Jeffrey! لقرائتها، إذهب إلىm.refunite.org")
        .matches()) {
      System.out.println("Arabic matched");
    }

    if (arabicRegexp
        .matcher(
            "\"أهلا Matthew. لديك 1 رسائل جديدة من Jeffrey! لقرائتها، إذهب إلىm.refunite.org\"")
        .matches()) {
      System.out.println("Arabic matched again");
    }

    String origStr = "Original String";
    changeStr(origStr);
    System.out.println(origStr);

    final LocalDate now = LocalDate.now();
    System.out.println(now);
    final StringBuilder sb = new StringBuilder("Try change me");
    sb.append(", append some text");
    System.out.println(sb);
    // sb = new StringBuilder("Try to reference another object"); //compile time error

    Integer[][] ints = {{1, 2, 3}, {null}, {7, 8, 9}};
    // java.lang.ArrayIndexOutOfBoundsException
    // System.out.println("value = " + ints[1][1].intValue());

    assert ((3 + 5) == 8);
    // assert ((3 + 5) == 7);

    StringBuilder strBld = new StringBuilder("abc");
    Map<Integer, StringBuilder> map = new HashMap<>();
    map.put(1, strBld);
    StringBuilder val = map.get(1);
    StringBuilder val2 = map.get(1);
    val.append("de");
    System.out.println("val2:" + val2);
    val2.append("fg");
    System.out.println("val:" + val);

    List<Integer> listToSearch = new ArrayList<>();
    System.out.println(
        "Binary search result for an empty list: " + Collections.binarySearch(listToSearch, 1));

    String productNumber = "ABC 103        3846";
    System.out.println(
        "product number without white spaces:" + productNumber.replaceAll("\\s", ""));

    // List<int> listInt = new ArrayList<>(); //Can't use primitive types as type parameter
    List<int[]> listIntArray = new ArrayList<>(); // Can use primitive types as type parameter
    listIntArray.add(new int[] {1, 2});
    listIntArray.add(new int[] {3, 4});

    List<Integer> listInteger = new ArrayList<>(); // Allowed
    List<Integer[]> listIntegerArray = new ArrayList<>(); // Allowed

    int[] input = {10, 8, 99, 7, 1, 5, 88, 9};
    System.out.println(
        "input.getClass() = "
            + input.getClass().getCanonicalName()); // input.getClass().getCanonicalName() = int[]
    System.out.println(
        "input.getClass() = " + input.getClass().getName()); // input.getClass().getName() = [I

    List<Integer> list = Arrays.asList(1, 2, 3);
    // list.get(0) = new Integer(list.get(0) + 2);
    // list.set(0, new Integer(list.get(0) + 4));
    list.set(0, list.get(0) + 4);
    System.out.println("list.get(0)=" + list.get(0));

    String strVar = null;
    strVar.equals("mystring");
    System.out.println("When NPE happens, the application won't keep running");
  }

  public static void changeStr(String str) {
    str = "Changed inside a method";
  }

  public static String minimizeVersion(String originalString) {
    String[] tokens = originalString.split("\\.");
    StringBuilder result = new StringBuilder();
    if (tokens.length >= 3) {
      result.append(tokens[2]);
    }
    // Now the rest
    for (int i = 3; i < tokens.length; i++) {
      result.append(tokens[i]);
      result.append('.');
    }
    return result.toString();
  }

  protected static String replaceSnapshotWithTimestamp(String originalString) {
    StringBuilder sb = new StringBuilder();
    int indexOfSnapshot = -1;
    if ((indexOfSnapshot = originalString.toUpperCase().indexOf("SNAPSHOT")) != -1) {
      String timestampAsString = getNowTimestamp();
      String beforeSnapshot = originalString.substring(0, indexOfSnapshot);
      sb.append(beforeSnapshot);
      sb.append(timestampAsString);
    }
    return sb.toString();
  }

  protected static String removeIllegalCharacters(String originalString) {
    String newString = "";
    for (int i = 0; i < originalString.length(); i++) {
      char c = originalString.charAt(i);
      if ((c >= 'a') && (c <= 'z') || (c >= 'A') && (c <= 'Z') || (c >= '0') && (c <= '9')) {
        newString += c;
      }
    }
    return newString;
  }

  public String normalizeHost(String myHost) {

    String normalizedHost = null;
    try {
      // Transform into a host name
      normalizedHost = InetAddress.getByName(myHost).getHostName();

      // Normalize by removing the suffix -[bot], if present
      Matcher m = HOST_PATTERN.matcher(normalizedHost);
      if (m.matches()) {
        normalizedHost = m.group(1);
      }
    } catch (UnknownHostException e) {
      normalizedHost = myHost;
    }

    return normalizedHost;
  }

  public static void testGoogleFormatter() {
    System.out.println("Test Google Java Code Style Formatter profile and implementation");
  }
}
