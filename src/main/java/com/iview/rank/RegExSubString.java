package com.iview.rank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cyuan on 2/2/2016.
 */
public class RegExSubString {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named RegExIP. */
        Scanner scan = new Scanner(System.in);
        //System.out.println("enter the number of lines: ");
        int numberOfLines = scan.nextInt();

        String tags;

        Pattern patternTags;

        scan.nextLine(); // skip the newline char

        List<String> lines = new ArrayList<>();

        for (int i = 0; i < numberOfLines; i++) {
            String input = scan.nextLine();
            lines.add(input);
        }

        // lines now have all the lines

        int numberOfSearch = scan.nextInt();
        scan.nextLine();

        List<Pattern> items = new ArrayList<>();

        for (int i = 0; i < numberOfSearch; i++) {
            String input = scan.nextLine();
            tags = "(\\W|\\b)" + input + "(\\W|\\b)";
            patternTags = Pattern.compile(tags);
            items.add(patternTags);
        }

        // items now have the stuff we need to search


        for (Pattern item: items) {
            int count = 0;
            for (String line: lines) {
                Matcher matcher = item.matcher(line);
                // check how to see how many matches
                while (matcher.find()) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
