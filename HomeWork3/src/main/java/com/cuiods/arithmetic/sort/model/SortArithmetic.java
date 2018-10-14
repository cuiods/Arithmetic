package com.cuiods.arithmetic.sort.model;

import java.util.concurrent.ThreadLocalRandom;

public class SortArithmetic {

    public void insertionSort(long[] numbers) {
        if (numbers.length < 2) return ;
        int length = numbers.length;
        for (int i = 1; i < length; i++) {
            long temp = numbers[i];
            int j = i-1;
            for (; j>=0 && (numbers[j] > temp) ;j--) {
                numbers[j+1] = numbers[j];
            }
            numbers[j+1] = temp;
        }
    }

    public void shellSort(long[] numbers) {
        int length = numbers.length, gap = length/2;
        while (gap > 0) {
            for (int i = gap; i < length; i++) {
                for (int j = i; j >= gap; j -= gap) {
                    if (numbers[j-gap] > numbers[j]) {
                        swap(numbers, j-gap, j);
                    } else break;
                }
            }
            gap = gap / 2;
        }
    }

    public void quickSort(long[] numbers) {
        quickSort(numbers,0,numbers.length);
    }

    public void mergeSort(long[] numbers) {
        mergeSort(numbers,0,numbers.length);
    }

    public void radixSort(long[] numbers) {}

    private void mergeSort(long[] numbers, int start, int end) {
        if (start < end - 1) {
            int middle = (start + end) / 2;
            mergeSort(numbers, start, middle);
            mergeSort(numbers, middle, end);
            merge(numbers, start, middle, end);
        }
    }

    private void merge(long[] numbers, int start, int middle, int end) {
        int l1 = middle - start;
        int l2 = end - middle;
        long[] temp1 = new long[l1];
        long[] temp2 = new long[l2];
        System.arraycopy(numbers,start,temp1,0,l1);
        System.arraycopy(numbers,middle,temp2,0,l2);

        int i = 0, j= 0, k = start;
        while (i < l1 && j < l2) {
            if (temp1[i] <= temp2[j]) numbers[k++] = temp1[i++];
            else numbers[k++] = temp2[j++];
        }
        while (i < l1) numbers[k++] = temp1[i++];
        while (j < l2) numbers[k++] = temp2[j++];
    }

    private void quickSort(long[] numbers, int start, int end) {
        if (start < end-1) {
            int index = partition(numbers, start, end);
            quickSort(numbers, start, index);
            quickSort(numbers, index+1, end);
        }
    }

    private int partition(long[] numbers, int start, int end) {
        int index = ThreadLocalRandom.current().nextInt(start, end);
        swap(numbers, index, end-1);
        long tag = numbers[end-1];
        int i = start-1;
        for (int j = start; j < end-1; j++) {
            if (numbers[j] <= tag) {
                i = i + 1;
                if (i != j)
                    swap(numbers, i, j);
            }
        }
        swap(numbers, i+1, end-1);
        return (i+1);
    }

    private static void swap(long[] numbers, int a, int b) {
        long temp = numbers[a];
        numbers[a] = numbers[b];
        numbers[b] = temp;
    }

    private String print(long[] numbers) {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (long num: numbers) result.append(num).append(",");
        result.append("]");
        return result.toString();
    }

}
