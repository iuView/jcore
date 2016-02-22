package com.iview.rank;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cyuan on 2/2/2016.
 */
public class BigDecimalExp {
    public static void main(String[] args) {
        //Input
        Scanner sc= new Scanner(System.in);
        int n=sc.nextInt();
        String []s=new String[n+2];
        BigDecimal[] sint = new BigDecimal[n];
        for(int i=0;i<n;i++)
        {
            s[i]=sc.next();
            sint[i] = new BigDecimal(s[i]);
        }

        //Write your code here
        Arrays.sort(sint, new Comparator<BigDecimal>() {
            @Override
            public int compare(BigDecimal bigDecimal, BigDecimal t1) {
                return bigDecimal.compareTo(t1);
            }
        });

        //Output
        for(int i=0;i<n;i++)
        {
            System.out.println(sint[i]);
        }
    }
}
