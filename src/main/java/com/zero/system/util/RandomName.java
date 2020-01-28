package com.zero.system.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class RandomName {

    public RandomName() {
    }

    public static String getRandomChineseName(int len) {
        String ret = "";

        for(int i = 0; i < len; ++i) {
            String str = null;
            Random random = new Random();
            int hightPos = 176 + Math.abs(random.nextInt(39));
            int lowPos = 161 + Math.abs(random.nextInt(93));
            byte[] b = new byte[]{(new Integer(hightPos)).byteValue(), (new Integer(lowPos)).byteValue()};

            try {
                str = new String(b, "GBK");
            } catch (UnsupportedEncodingException var9) {
                var9.printStackTrace();
            }

            ret = ret + str;
        }

        return ret;
    }

    public static String getStringRandomEngishName(int length) {
        String val = "";
        Random random = new Random();

        for(int i = 0; i < length; ++i) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if ("char".equalsIgnoreCase(charOrNum)) {
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val = val + (char)(random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val = val + String.valueOf(random.nextInt(10));
            }
        }

        return val;
    }

    public static void main(String[] args) {
        String chineseName = getRandomChineseName(3);
        System.out.println("chineseName = " + chineseName);
    }


}
