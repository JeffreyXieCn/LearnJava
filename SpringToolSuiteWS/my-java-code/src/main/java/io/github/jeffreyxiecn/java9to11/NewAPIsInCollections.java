/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson AB 2020
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package io.github.jeffreyxiecn.java9to11;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewAPIsInCollections {
    public static void main(String[] args) {
        List<String> tokens = List.of("1.2.3".split("\\."));
        System.out.println("Tokens.length = " + tokens.size());
        System.out.println("Tokens = " + tokens);

        String str = "Jeffrey / ${designation} / ${url:productNumber} / ${url:productVersion}";
        Pattern PLACEHOLDER_EXPRESSION = Pattern.compile("\\$\\{(.+?)\\}");
        Matcher matcher = PLACEHOLDER_EXPRESSION.matcher(str);

        // System.out.println(matcher.matches()); // false

        while (matcher.find()) {
            System.out.println(matcher.group()); // ${designation}, ${url:productNumber}, ${url:productVersion}
            System.out.println(matcher.group(1)); // designation, url:productNumber, url:productVersion
        }

        String placeHolder = "${url:productNumber}";
        System.out.println(placeHolder.substring(placeHolder.indexOf(':') + 1, placeHolder.length() - 1)); // productNumber


    }
}
