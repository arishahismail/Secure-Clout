package client.springbootvuejs.domain;

import java.util.*;

public class OTP {
    public char[] temp_pass() {
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        int len = 8;

        String values = Capital_chars + Small_chars + numbers;

        // Using random method
        Random rndm_method = new Random();

        char[] temppass = new char[len];

        for (int i = 0; i < len; i++) {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            temppass[i] = values.charAt(rndm_method.nextInt(values.length()));

        }
        return temppass;
    }

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}