package com.iview.rank;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cyuan on 2/2/2016.
 */
public class RegExIP {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named RegExIP. */
        Scanner scan = new Scanner(System.in);
        System.out.println("enter the number of lines: ");
        int numberOfLines = scan.nextInt();
        String ipv4PatternStr = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        String ipv6PatternStr = "^([0-9|a-f|A-F]{1,4}\\:){7}[0-9|a-f|A-F]{1,4}$";

        Pattern patternIpv4 = Pattern.compile(ipv4PatternStr);
        Pattern patternIpv6 = Pattern.compile(ipv6PatternStr);

        scan.nextLine(); // skip the newline char

        for (int i = 0; i < numberOfLines; i++) {
            String input = scan.nextLine();
            Matcher matcher = patternIpv4.matcher(input);
            boolean matches = matcher.matches();
            if (matches) {
                System.out.println("IPv4");
                continue;
            }
            matcher = patternIpv6.matcher(input);
            matches = matcher.matches();
            if (matches) {
                System.out.println("IPv6");
                continue;
            }
            System.out.println("Neither");
        }
    }

    /*Scanner scan = new Scanner(System.in);
    int numberOfLines = scan.nextInt();
    String ipv4PatternStr = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    String ipv6PatternStr = "^[0-9|a-f|A-F]{1,4}\\:[0-9|a-f|A-F]{1,4}\\:[0-9|a-f|A-F]{1,4}\\:[0-9|a-f|A-F]{1,4}\\:[0-9|a-f|A-F]{1,4}\\:[0-9|a-f|A-F]{1,4}\\:[0-9|a-f|A-F]{1,4}\\:[0-9|a-f|A-F]{1,4}$";

    Pattern patternIpv4 = Pattern.compile(ipv4PatternStr);
    Pattern patternIpv6 = Pattern.compile(ipv6PatternStr);

    scan.nextLine(); // skip the newline char

    for (int i = 0; i < numberOfLines; i++) {
        String input = scan.nextLine();
        Matcher matcher = patternIpv4.matcher(input);
        boolean matches = matcher.matches();
        if (matches) {
            System.out.println("IPv4");
            continue;
        }
        matcher = patternIpv6.matcher(input);
        matches = matcher.matches();
        if (matches) {
            System.out.println("IPv6");
            continue;
        }
        System.out.println("Neither");
    }*/
}
