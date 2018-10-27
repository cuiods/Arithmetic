package com.cuiods.arithmetic.sequence.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ReportObj {
    private final StringProperty num;
    private final StringProperty dpTime;
    private final StringProperty greedTime;

    public ReportObj(String num, String dpTime, String greedTime) {
        this.num = new SimpleStringProperty(num);
        this.dpTime = new SimpleStringProperty(dpTime);
        this.greedTime = new SimpleStringProperty(greedTime);
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

    public String getDpTime() {
        return dpTime.get();
    }

    public StringProperty dpTimeProperty() {
        return dpTime;
    }

    public void setDpTime(String dpTime) {
        this.dpTime.set(dpTime);
    }

    public String getGreedTime() {
        return greedTime.get();
    }

    public StringProperty greedTimeProperty() {
        return greedTime;
    }

    public void setGreedTime(String greedTime) {
        this.greedTime.set(greedTime);
    }
}
