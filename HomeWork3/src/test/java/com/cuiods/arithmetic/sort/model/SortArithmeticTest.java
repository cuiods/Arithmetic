package com.cuiods.arithmetic.sort.model;

import com.cuiods.arithmetic.sort.util.RandomUtil;
import org.junit.Test;

import static org.junit.Assert.*;

public class SortArithmeticTest {

    @Test
    public void insertionSort() {
        SortArithmetic arithmetic = new SortArithmetic();
        long[] nums = RandomUtil.generateNumbers(10);
        arithmetic.insertionSort(nums);
        for (long num : nums) System.out.println(num + ",");
    }

    @Test
    public void shellSort() {
        SortArithmetic arithmetic = new SortArithmetic();
        long[] nums = RandomUtil.generateNumbers(100000000);
        arithmetic.shellSort(nums);
    }

    @Test
    public void quickSort() {
        SortArithmetic arithmetic = new SortArithmetic();
        long[] nums = RandomUtil.generateNumbers(100000000);
        arithmetic.quickSort(nums);
    }

    @Test
    public void mergeSort() {
        SortArithmetic arithmetic = new SortArithmetic();
        long[] nums = RandomUtil.generateNumbers(100000000);
        arithmetic.mergeSort(nums);
    }

    @Test
    public void radixSort() {
        SortArithmetic arithmetic = new SortArithmetic();
        long[] nums = RandomUtil.generateNumbers(100000000);
        arithmetic.radixSort(nums);
    }

    @Test
    public void originSort() {
        SortArithmetic arithmetic = new SortArithmetic();
        long[] nums = RandomUtil.generateNumbers(100000000);
        arithmetic.originSort(nums);
    }
}