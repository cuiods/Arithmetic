package com.cuiods.arithmetic.sort.model.result;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MergeObj {

    private final StringProperty num;
    private final StringProperty insert;
    private final StringProperty shell;
    private final StringProperty quick;

    public MergeObj(String num, String insert, String shell, String quick) {
        this.num = new SimpleStringProperty(num);
        this.insert = new SimpleStringProperty(insert);
        this.shell = new SimpleStringProperty(shell);
        this.quick = new SimpleStringProperty(quick);
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

    public String getInsert() {
        return insert.get();
    }

    public StringProperty insertProperty() {
        return insert;
    }

    public void setInsert(String insert) {
        this.insert.set(insert);
    }

    public String getShell() {
        return shell.get();
    }

    public StringProperty shellProperty() {
        return shell;
    }

    public void setShell(String shell) {
        this.shell.set(shell);
    }

    public String getQuick() {
        return quick.get();
    }

    public StringProperty quickProperty() {
        return quick;
    }

    public void setQuick(String quick) {
        this.quick.set(quick);
    }
}
