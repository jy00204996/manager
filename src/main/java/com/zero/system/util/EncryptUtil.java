package com.zero.system.util;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.math.BigDecimal;

public class EncryptUtil {

    private static final String HASHALGORITHMNAME = "md5";
    private static final Integer HASHITERATIONS = 2;
    private static final String SALT = "994mkd02-2kj392=24949djd-29488d";
    private static int ENCRYPTION_TIME = 4;

    public EncryptUtil() {
    }

    public static String genCiphertex(String username, Object password) {
        SimpleHash simpleHash = new SimpleHash("md5", password, username, HASHITERATIONS);
        return simpleHash.toString();
    }

    public static String genCiphertex(String password, String slat) {
        SimpleHash simpleHash = new SimpleHash("md5", password, slat, HASHITERATIONS);
        return simpleHash.toString();
    }

    public static String md5(Object source) {
        SimpleHash simpleHash = new SimpleHash("md5", source);
        return simpleHash.toString();
    }

    public static String genCiphertex(String password) {
        SimpleHash simpleHash = new SimpleHash("md5", password, "994mkd02-2kj392=24949djd-29488d", HASHITERATIONS);
        return simpleHash.toString();
    }

    public static String encode(String souce) {
        for(int i = 0; i < ENCRYPTION_TIME; ++i) {
            souce = encBase64(souce);
        }

        return souce;
    }

    public static String decode(String souce) {
        for(int i = 0; i < ENCRYPTION_TIME; ++i) {
            souce = decBase64(souce);
        }

        return souce;
    }

    public static String encBase64(String souce) {
        return Base64.encodeToString(souce.getBytes());
    }

    public static String decBase64(String souce) {
        return Base64.decodeToString(souce);
    }

    public static void main(String[] args) {
        Double amount = 9.9999999454E7D;
        BigDecimal bigDecimal = BigDecimal.valueOf(amount);
        String base64 = encode(bigDecimal.toString());
        System.out.println("base64 = " + base64);
        String s = decode(base64);
        System.out.println("s = " + s);
        Double value = Double.valueOf(s);
        System.out.println("value = " + value);
    }

}
