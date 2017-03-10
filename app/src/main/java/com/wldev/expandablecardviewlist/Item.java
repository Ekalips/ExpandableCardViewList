package com.wldev.expandablecardviewlist;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by wldev on 3/10/17.
 */

public class Item extends BaseObservable {
    public static final int INIT_MARGIN = 2;
    public static final int FINAL_MARGIN = 16;

    @Bindable
    private
    int margin = 2;
    @Bindable
    private
    boolean expanded;

    private String title;
    private String desrc;

    Item(String title, String desrc) {
        this.title = title;
        this.desrc = desrc;
    }

    public int getMargin() {
        return margin;
    }


    public boolean isExpanded() {
        return expanded;
    }

    public void setMargin(int margin) {
        this.margin = margin;
        notifyPropertyChanged(BR.margin);
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
        notifyPropertyChanged(BR.expanded);
    }

    public String getTitle() {
        return title;
    }

    public String getDesrc() {
        return desrc;
    }
}