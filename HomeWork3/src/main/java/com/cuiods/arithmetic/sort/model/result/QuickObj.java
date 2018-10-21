package com.cuiods.arithmetic.sort.model.result;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class QuickObj {
    private final StringProperty num;
    private final StringProperty insert;
    private final StringProperty shell;
    private final StringProperty merge;

    public QuickObj(String num, String insert, String shell, String merge) {
        this.num = new SimpleStringProperty(num);
        this.insert = new SimpleStringProperty(insert);
        this.shell = new SimpleStringProperty(shell);
        this.merge = new SimpleStringProperty(merge);
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

    public String getMerge() {
        return merge.get();
    }

    public StringProperty mergeProperty() {
        return merge;
    }

    public void setMerge(String merge) {
        this.merge.set(merge);
    }
}
