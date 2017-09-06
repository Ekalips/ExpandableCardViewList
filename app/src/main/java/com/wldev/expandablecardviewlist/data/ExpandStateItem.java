package com.wldev.expandablecardviewlist.data;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.wldev.expandablecardviewlist.BR;


/**
 * Created by wldev on 7/7/17.
 * Purpose:
 */

public class ExpandStateItem extends BaseObservable {

    @Bindable
    private boolean expanded;

    @Bindable
    private float margin;

    @Bindable
    private boolean fast;

    public ExpandStateItem(boolean expanded) {
        this.expanded = expanded;
    }

    public ExpandStateItem(boolean expanded, float margin) {
        this.expanded = expanded;
        this.margin = margin;
    }

    public boolean isFast() {
        return fast;
    }

    public void setFast(boolean fast) {
        this.fast = fast;
        notifyPropertyChanged(BR.fast);
    }

    public boolean isExpanded() {
        return expanded;
    }

    public float getMargin() {
        return margin;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
        notifyPropertyChanged(BR.expanded);
    }

    public void setMargin(float margin) {
        this.margin = margin;
        notifyPropertyChanged(BR.margin);
    }
}
