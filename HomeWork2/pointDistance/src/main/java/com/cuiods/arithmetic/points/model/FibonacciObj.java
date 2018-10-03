package com.cuiods.arithmetic.points.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FibonacciObj {

    private final StringProperty num;
    private final StringProperty time1;
    private final StringProperty time2;
    private final StringProperty time3;
    private final StringProperty time4;
    private final StringProperty trueValue;
    private final StringProperty floatValue;

    public FibonacciObj(String num, String time1, String time2, String time3,
                        String time4, String trueValue, String floatValue) {
        this.num = new SimpleStringProperty(num);
        this.time1 = new SimpleStringProperty(time1);
        this.time2 = new SimpleStringProperty(time2);
        this.time3 = new SimpleStringProperty(time3);
        this.time4 = new SimpleStringProperty(time4);
        this.trueValue = new SimpleStringProperty(trueValue);
        this.floatValue = new SimpleStringProperty(floatValue);
    }

    public String getNum() {
        return num.get();
    }

    public StringProperty numProperty() {
        return num;
    }

    public void setNum(String num) {
        this.num.set(num);
    }

    public String getTime1() {
        return time1.get();
    }

    public StringProperty time1Property() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1.set(time1);
    }

    public String getTime2() {
        return time2.get();
    }

    public StringProperty time2Property() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2.set(time2);
    }

    public String getTime3() {
        return time3.get();
    }

    public StringProperty time3Property() {
        return time3;
    }

    public void setTime3(String time3) {
        this.time3.set(time3);
    }

    public String getTime4() {
        return time4.get();
    }

    public StringProperty time4Property() {
        return time4;
    }

    public void setTime4(String time4) {
        this.time4.set(time4);
    }

    public String getTrueValue() {
        return trueValue.get();
    }

    public StringProperty trueValueProperty() {
        return trueValue;
    }

    public void setTrueValue(String trueValue) {
        this.trueValue.set(trueValue);
    }

    public String getFloatValue() {
        return floatValue.get();
    }

    public StringProperty floatValueProperty() {
        return floatValue;
    }

    public void setFloatValue(String floatValue) {
        this.floatValue.set(floatValue);
    }
}
