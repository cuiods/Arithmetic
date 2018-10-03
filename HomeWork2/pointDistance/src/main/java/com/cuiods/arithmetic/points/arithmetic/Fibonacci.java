package com.cuiods.arithmetic.points.arithmetic;

import java.math.BigInteger;

public class Fibonacci {

    /**
     * 简单递归实现斐波那契数列
     * @param num
     * @return
     */
    public BigInteger recursiveFibonacci(int num) {
        if (num == 0) return BigInteger.ZERO;
        if (num == 1) return BigInteger.ONE;
        return recursiveFibonacci(num-1).add(recursiveFibonacci(num-2));
    }

    /**
     * 线性加法实现斐波那契数列
     * @param num
     * @return
     */
    public BigInteger bottomUpFibonacci(int num) {
        if (num == 0) return BigInteger.ZERO;
        if (num == 1) return BigInteger.ONE;
        BigInteger n1 = BigInteger.ZERO;
        BigInteger n2 = BigInteger.ONE;
        BigInteger res = BigInteger.ZERO;
        for (int i = 0; i < num-1; i++) {
            res = n1.add(n2);
            n1 = n2;
            n2 = res;
        }
        return res;
    }

    /**
     * 矩阵乘法实现斐波那契数列
     * @param num
     * @return
     */
    public BigInteger matrixFibonacci(int num) {
        if (num == 0) return BigInteger.ZERO;
        if (num == 1) return BigInteger.ONE;
        return addMatrix(num-1)[0][0];
    }

    private BigInteger[][] addMatrix(int num) {
        BigInteger[][] origin = {{BigInteger.ONE,BigInteger.ONE},{BigInteger.ONE,BigInteger.ZERO}};
        if (num <= 1) return origin;
        BigInteger[][] a = addMatrix(num/2);
        BigInteger[][] b = addMatrix(num-num/2);
        BigInteger[][] result = new BigInteger[2][2];
        result[0][0] = a[0][0].multiply(b[0][0]).add(a[0][1].multiply(b[1][0]));
        result[0][1] = a[0][0].multiply(b[0][1]).add(a[0][1].multiply(b[1][1]));
        result[1][0] = a[1][0].multiply(b[0][0]).add(a[1][1].multiply(b[1][0]));
        result[1][1] = a[1][0].multiply(b[0][1]).add(a[1][1].multiply(b[1][1]));
        return result;
    }

    /**
     * 公式法
     * @param num
     * @return
     */
    public long formulaFibonacci(int num) {
        double n1 = (1 + Math.sqrt(5)) / 2;
        double n2 = (1 - Math.sqrt(5)) / 2;
        double result = ( Math.pow(n1, num) - Math.pow(n2, num) ) / Math.sqrt(5);
        return Math.round(result);
    }
}
