package com.cuiods.arithmetic.sort.model;

import com.cuiods.arithmetic.sort.util.RandomUtil;
import com.cuiods.arithmetic.sort.util.SortMethod;
import org.junit.Test;

import static org.junit.Assert.*;

public class SortArithmeticTest {

    @Test
    public void insertionSort1() {
        SortArithmetic arithmetic = new SortArithmetic();
        long[] nums = RandomUtil.generateNumbers(10);
        arithmetic.insertionSort(nums);
        for (long num : nums) System.out.println(num + ",");
    }

    @Test
    public void shellSort1() {
        SortArithmetic arithmetic = new SortArithmetic();
        long[] nums = RandomUtil.generateNumbers(200000000);
        long time1 = System.nanoTime();
        arithmetic.shellSort(nums);
        long time2 = System.nanoTime();
        System.out.println((time2-time1)/1000000.0);
    }

    @Test
    public void quickSort1() {
        SortArithmetic arithmetic = new SortArithmetic();
        long[] nums = RandomUtil.generateNumbers(10);
        arithmetic.quickSort(nums);
        for (long num : nums) System.out.println(num + ",");
    }

    @Test
    public void quickSort2() {
        SortArithmetic arithmetic = new SortArithmetic();
        long[] nums = RandomUtil.generateNumbers(1000000000);
        long time1 = System.nanoTime();
        arithmetic.quickSort(nums,2);
        long time2 = System.nanoTime();
        System.out.println((time2-time1)/1000000.0);
    }

    @Test
    public void quickSort3() {
        SortArithmetic arithmetic = new SortArithmetic();
        long[] nums = RandomUtil.generateNumbers(20);
        arithmetic.quickSort(nums,2, SortMethod.SHELL);
        for (long num : nums) System.out.println(num + ",");
    }

    @Test
    public void mergeSort1() {
        SortArithmetic arithmetic = new SortArithmetic();
        long[] nums = RandomUtil.generateNumbers(10);
        arithmetic.mergeSort(nums);
        for (long num : nums) System.out.println(num + ",");
    }

    @Test
    public void mergeSort2() {
        SortArithmetic arithmetic = new SortArithmetic();
        long[] nums = RandomUtil.generateNumbers(1000000000);
        long time1 = System.nanoTime();
        arithmetic.mergeSort(nums,2);
        long time2 = System.nanoTime();
        System.out.println((time2-time1)/1000000.0);
    }

    @Test
    public void mergeSort3() {
        SortArithmetic arithmetic = new SortArithmetic();
        long[] nums = RandomUtil.generateNumbers(20);
        arithmetic.mergeSort(nums, 2, SortMethod.QUICK);
        for (long num : nums) System.out.println(num + ",");
    }

    @Test
    public void radixSort1() {
        SortArithmetic arithmetic = new SortArithmetic();
        long[] nums = RandomUtil.generateNumbers(20);
        arithmetic.radixSort(nums);
        for (long num : nums) System.out.println(num + ",");
    }

    @Test
    public void radixSort2() {
        SortArithmetic arithmetic = new SortArithmetic();
        long[] nums = RandomUtil.generateNumbers(1000000000);
        long time1 = System.nanoTime();
        arithmetic.radixSort(nums,8);
        long time2 = System.nanoTime();
        System.out.println((time2-time1)/1000000.0);
    }

    @Test
    public void originSort1() {
        SortArithmetic arithmetic = new SortArithmetic();
        long[] nums = RandomUtil.generateNumbers(20);
        arithmetic.originSort(nums);
        for (long num : nums) System.out.println(num + ",");
    }
}