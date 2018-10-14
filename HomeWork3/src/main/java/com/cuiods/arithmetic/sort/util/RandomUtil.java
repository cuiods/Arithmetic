package com.cuiods.arithmetic.sort.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

    public static long[] generateNumbers(int size) {
        long[] result = new long[size];
        for (int i = 0; i < size; i++) {
            result[i] = ThreadLocalRandom.current().nextLong(4294967296L);
        }
        return result;
    }
}
