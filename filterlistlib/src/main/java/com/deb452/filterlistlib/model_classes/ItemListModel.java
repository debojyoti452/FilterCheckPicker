package com.deb452.filterlistlib.model_classes;

import androidx.annotation.Keep;

import java.io.Serializable;

@Keep
public class ItemListModel implements Serializable {

    public String nameList;

    public ItemListModel() {
    }

    public ItemListModel(String nameList) {
        this.nameList = nameList;
    }

    public String getNameList() {
        return nameList;
    }

    public void setNameList(String nameList) {
        this.nameList = nameList;
    }
}
