package com.deb452.filterlistlib.model_classes;

import androidx.annotation.Keep;

import java.io.Serializable;

@Keep
public class ItemListModel implements Serializable {

    public int id;
    public String nameList;

    public ItemListModel() {
    }

    public ItemListModel(int id, String nameList) {
        this.id = id;
        this.nameList = nameList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameList() {
        return nameList;
    }

    public void setNameList(String nameList) {
        this.nameList = nameList;
    }
}
