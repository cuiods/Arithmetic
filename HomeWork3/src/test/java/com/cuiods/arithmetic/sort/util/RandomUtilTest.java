package com.cuiods.arithmetic.sort.util;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class RandomUtilTest {

    @Test
    public void generateUnsignedIntegers() {
        long[] result = RandomUtil.generateNumbers(300000000);
        System.out.println(result.length);
    }
}