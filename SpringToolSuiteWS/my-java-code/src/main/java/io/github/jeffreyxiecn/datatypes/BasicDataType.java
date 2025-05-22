package io.github.jeffreyxiecn.datatypes;

import java.nio.ByteOrder;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class BasicDataType {

    public static void main(String[] args) {
        ByteOrder byteOrder = ByteOrder.nativeOrder();
        if (byteOrder.equals(ByteOrder.LITTLE_ENDIAN)) {
            System.out.println("This JVM is running on little_endian platform");
        } else {
            System.out.println("This JVM is running on big_endian platform");
        }

        int a = 15;
        System.out.println(Integer.toString(a));
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toOctalString(a));
        System.out.println(Integer.toHexString(a));
        System.out.println(String.valueOf(a));

        System.out.println(String.valueOf('a'));

        System.out.println(Integer.parseInt("-123"));
        System.out.println(Integer.valueOf("-123"));

        System.out.println("a".charAt(0));

        Integer aa = a;
        System.out.println(aa.toString());
        System.out.println(new Integer(a));
        System.out.println(Integer.valueOf(a));

        System.out.println(aa.intValue());

        long creditCardNumber = 1234_5678_9012_3456L;
        System.out.println("creditCardNumber:" + creditCardNumber);
        long socialSecurityNumber = 999_99_9999L;
        float pi = 3.14_15F;
        long hexBytes = 0xFF_EC_DE_5E;
        long hexWords = 0xCAFE_BABE;
        long maxLong = 0x7fff_ffff_ffff_ffffL;
        System.out.println("maxLong:" + maxLong); // 9223372036854775807
        long minLong = 0x8000_0000_0000_0000L;
        System.out.println("minLong:" + minLong); // -9223372036854775808
        System.out.println(Long.toHexString(Long.MIN_VALUE));
        byte nybbles = 0b0010_0101;
        System.out.println("nybbles:" + nybbles); // 37
        long bytes = 0b11010010_01101001_10010100_10010010;
        System.out.println("bytes:" + bytes); // -764832622

        Instant timestamp = Instant.now();
        System.out.println("timestamp:" + timestamp);
        System.out.println("timestamp (truncated to seconds):" + timestamp.truncatedTo(ChronoUnit.SECONDS));

        //timestamp = Instant.parse("2025-04-21T12:46:21.000+02:00");
        //timestamp = Instant.parse("2025-03-28T03:10:17+01:00");
        timestamp = Instant.parse("2025-04-21T12:46:21.567+02:00");
        System.out.println("timestamp:" + timestamp);
        System.out.println("timestamp (truncated to seconds):" + timestamp.truncatedTo(ChronoUnit.SECONDS));

    }
}
