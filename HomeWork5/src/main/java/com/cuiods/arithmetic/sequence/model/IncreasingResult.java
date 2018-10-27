package com.cuiods.arithmetic.sequence.model;

public class IncreasingResult {
    private double time;
    private int[] result;

    public IncreasingResult(double time, int[] result) {
        this.time = time;
        this.result = result;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int[] getResult() {
        return result;
    }

    public void setResult(int[] result) {
        this.result = result;
    }
}
