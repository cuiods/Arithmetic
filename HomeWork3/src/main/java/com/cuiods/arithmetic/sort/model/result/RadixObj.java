package com.cuiods.arithmetic.sort.model.result;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RadixObj {

    private final StringProperty num;
    private final StringProperty time;

    public RadixObj(String num, String time) {
        this.num = new SimpleStringProperty(num);
        this.time = new SimpleStringProperty(time);
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

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }
}
