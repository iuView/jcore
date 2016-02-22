package com.iview.rank;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cyuan on 2/2/2016.
 */
public class RegExTags {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named RegExIP. */
        Scanner scan = new Scanner(System.in);
        //System.out.println("enter the number of lines: ");
        int numberOfLines = scan.nextInt();

        String tags = "(\\<)(\\w+)(\\/?)(\\>?)";

        Pattern patternTags = Pattern.compile(tags);

        scan.nextLine(); // skip the newline char

        List<String> items = new ArrayList<>();

        for (int i = 0; i < numberOfLines; i++) {
            String input = scan.nextLine();
            Matcher matcher = patternTags.matcher(input);
            while (matcher.find()) {
                //System.out.println(matcher.group(2));
                if (!items.contains(matcher.group(2)))
                    items.add(matcher.group(2));
            }
        }

        Collections.sort(items);


        System.out.println(items.toString().replace(", ", ";").replace("[", "").replace("]", ""));
    }
}
