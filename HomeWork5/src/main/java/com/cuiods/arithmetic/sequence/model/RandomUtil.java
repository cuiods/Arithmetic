package com.cuiods.arithmetic.sequence.model;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

    public static int[] generateNumbers(int size) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
        }
        return result;
    }
}
