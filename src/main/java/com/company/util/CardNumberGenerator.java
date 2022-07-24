package com.company.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CardNumberGenerator {
    public long generateRandom(int length) {
        Random random = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }

    public String maskCCNumber(String ccnum){
        long starttime = System.currentTimeMillis();
        int total = ccnum.length();
        int startlen=4,endlen = 4;
        int masklen = total-(startlen + endlen) ;
        StringBuffer maskedbuf = new StringBuffer(ccnum.substring(0,startlen));
        for(int i=0;i<masklen;i++) {
            maskedbuf.append('*');
        }
        maskedbuf.append(ccnum.substring(startlen+masklen, total));
        String masked = maskedbuf.toString();
        long endtime = System.currentTimeMillis();
        System.out.println("maskCCNumber:="+masked+" of :"+masked.length()+" size");
        System.out.println("using StringBuffer="+ (endtime-starttime)+" millis");
        return masked;
    }

}
