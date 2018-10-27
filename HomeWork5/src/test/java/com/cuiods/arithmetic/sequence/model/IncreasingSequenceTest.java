package com.cuiods.arithmetic.sequence.model;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class IncreasingSequenceTest {

    @Test
    public void dpIncreasingSequence() {
        int[] numbers = new int[15];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = ThreadLocalRandom.current().nextInt(50);
        }
        print(numbers);
        int[] test1 = new IncreasingSequence().dpIncreasingSequence(numbers);
        int[] test2 = new IncreasingSequence().greedyIncreasingSequence(numbers);
        print(test1);
        print(test2);
    }

    @Test
    public void greedyIncreasingSequence() {
        int[] numbers = new int[15];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = ThreadLocalRandom.current().nextInt(50);
        }
        print(numbers);
        int[] test2 = new IncreasingSequence().greedyIncreasingSequence(numbers);
        print(test2);
    }

    private void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num + ",");
        }
        System.out.println();
    }
}