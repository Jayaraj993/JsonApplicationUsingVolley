package com.emdsys.android.jsonapplication.JsonExpandableListViewVolley;

import java.util.ArrayList;

/**
 * Created by EMD029 on 8/26/2015.
 */
public class Group {
    private String name;
    private ArrayList<Child> Items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Child> getItems() {
        return Items;
    }

    public void setItems(ArrayList<Child> items) {
        Items = items;
    }
}
