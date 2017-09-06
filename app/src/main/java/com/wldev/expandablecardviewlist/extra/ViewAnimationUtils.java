package com.wldev.expandablecardviewlist.extra;

import android.databinding.BindingAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by wldev on 3/10/17.
 */

public class ViewAnimationUtils {

    public static void expand(final View v, final AnimationEndCallback callback, boolean fast) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targtetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targtetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        int duration = 0;

        if (!fast) {
            duration = (int) (targtetHeight / v.getContext().getResources().getDisplayMetrics().density);
        }

        a.setDuration(duration);
        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (callback != null)
                    callback.onAnimationEnded();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.startAnimation(a);
    }

    public static void collapse(final View v, final AnimationEndCallback callback, boolean fast) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        int duration = 0;
        if (!fast) {
            duration = (int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density);
        }

        a.setDuration(duration);

        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (callback != null)
                    callback.onAnimationEnded();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.startAnimation(a);
    }

    interface AnimationEndCallback {
        void onAnimationEnded();
    }

    @BindingAdapter({"expand", "fastExpand"})
    public static void expandView(View view, boolean expand, boolean fast) {
        if (expand) {
            expand(view, null, fast);
        } else if (view.getHeight() != 0) {
            collapse(view, null, fast);
        }
    }

    @BindingAdapter({"collapse", "fastCollapse"})
    public static void collapseView(View view, boolean collapse, boolean fast) {
        if (collapse && view.getHeight() != 0)
            collapse(view, null, fast);
    }
}