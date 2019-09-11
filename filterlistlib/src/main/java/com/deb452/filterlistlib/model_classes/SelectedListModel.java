package com.deb452.filterlistlib.model_classes;

import androidx.annotation.Keep;

import java.io.Serializable;

@Keep
public class SelectedListModel implements Serializable {

    public int id;
    public String name;
    public boolean isSelected;

    public SelectedListModel() {
    }

    public SelectedListModel(int id, String name, boolean isSelected) {
        this.id = id;
        this.name = name;
        this.isSelected = isSelected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

