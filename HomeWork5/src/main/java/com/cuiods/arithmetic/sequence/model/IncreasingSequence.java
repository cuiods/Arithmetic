package com.cuiods.arithmetic.sequence.model;


public class IncreasingSequence {

    public int[] dpIncreasingSequence(final int[] numbers) {
        if (numbers==null || numbers.length <= 1)
            return numbers;
        int[] tags = new int[numbers.length];
        int[] maxLen = new int[numbers.length];
        maxLen[0] = 1;
        tags[0] = -1;
        int maxIndex = 0;
        int maxLength = 1;
        for (int i = 1; i < numbers.length; i++) {
            maxLen[i] = 1;
            tags[i] = -1;
            for (int j = 0; j < i; j++) {
                if (numbers[j] < numbers[i]) {
                    int length = maxLen[j]+1;
                    if (length > maxLen[i]) {
                        maxLen[i] = length;
                        tags[i] = j;
                        if (length > maxLength) {
                            maxIndex = i;
                            maxLength = length;
                        }
                    }
                };
            }
        }
        int[] result = new int[maxLength];
        while (maxIndex>=0) {
            result[--maxLength] = numbers[maxIndex];
            maxIndex = tags[maxIndex];
        }
        return result;
    }

    public int[] greedyIncreasingSequence(final int[] numbers) {
        if (numbers==null || numbers.length <= 1)
            return numbers;
        int[] minTailIndex = new int[numbers.length];
        int[] tags = new int[numbers.length];
        minTailIndex[0] = 0;
        tags[0] = 1;
        int maxLen = 1;
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > numbers[minTailIndex[maxLen-1]]) {
                minTailIndex[maxLen++] = i;
                tags[i] = maxLen;
            }
            else {
                int replaceIndex = binarySearch(numbers, minTailIndex, 0,maxLen-1,numbers[i]);
                tags[i] = tags[minTailIndex[replaceIndex]];
                minTailIndex[replaceIndex] = i;
            }
        }
        int[] result = new int[maxLen];
        int currentMax = Integer.MAX_VALUE;
        for (int i = maxLen-1, tempIndex = minTailIndex[maxLen-1]; i >= 0 && tempIndex>=0; tempIndex--) {
            if (numbers[tempIndex] < currentMax && tags[tempIndex] == i+1) {
                result[i--] = numbers[tempIndex];
                currentMax = numbers[tempIndex];
            }
        }
        return result;
    }

    private int binarySearch(int[] numbers,int[] tailIndex, int start, int end, int num) {
        if (start == end) return start;
        int middle = (start+end)/2;
        if (numbers[tailIndex[middle]] == num)
            return middle;
        else if (numbers[tailIndex[middle]] > num)
            return binarySearch(numbers, tailIndex, start, middle, num);
        else
            return binarySearch(numbers, tailIndex, middle+1, end, num);
    }

}
