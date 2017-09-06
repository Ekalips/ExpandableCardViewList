package com.wldev.expandablecardviewlist.data;

/**
 * Created by wldev on 3/10/17.
 */

public class Item {

    private String title;
    private String desc;

    public Item(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }


    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}