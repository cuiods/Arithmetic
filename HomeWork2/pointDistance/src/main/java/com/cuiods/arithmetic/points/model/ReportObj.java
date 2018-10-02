package com.cuiods.arithmetic.points.model;

import javafx.beans.property.*;

public class ReportObj {

    private final IntegerProperty num;
    private final StringProperty firstTime;
    private final StringProperty lastTime;

    public ReportObj(Integer num, String firstTime, String lastTime) {
        this.num = new SimpleIntegerProperty(num);
        this.firstTime = new SimpleStringProperty(firstTime);
        this.lastTime = new SimpleStringProperty(lastTime);
    }

    public int getNum() {
        return num.get();
    }

    public IntegerProperty numProperty() {
        return num;
    }

    public void setNum(int num) {
        this.num.set(num);
    }

    public String getFirstTime() {
        return firstTime.get();
    }

    public StringProperty firstTimeProperty() {
        return firstTime;
    }

    public void setFirstTime(String firstTime) {
        this.firstTime.set(firstTime);
    }

    public String getLastTime() {
        return lastTime.get();
    }

    public StringProperty lastTimeProperty() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime.set(lastTime);
    }
}
