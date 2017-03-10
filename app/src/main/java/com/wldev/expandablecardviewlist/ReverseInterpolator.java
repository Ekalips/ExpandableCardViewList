package com.wldev.expandablecardviewlist;

/**
 * Created by wldev on 3/10/17.
 */

import android.view.animation.Interpolator;
public class ReverseInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float paramFloat) {
        return Math.abs(paramFloat -1f);
    }
}