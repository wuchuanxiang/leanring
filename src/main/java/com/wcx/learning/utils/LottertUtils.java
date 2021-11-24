package com.wcx.learning.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class LottertUtils {
    static final char[] SEEDS = "123".toCharArray();

    public static String random(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size must > 0");
        }
        char[] result = new char[size];
        Random random = ThreadLocalRandom.current();
        int len = SEEDS.length;
        for (int i = 0; i < size; i++) {
            result[i] = SEEDS[random.nextInt(len)];
        }
        String returnString = new String(result);

        return returnString;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            String random = random(3);
            System.out.println(random);
        }
    }
}
