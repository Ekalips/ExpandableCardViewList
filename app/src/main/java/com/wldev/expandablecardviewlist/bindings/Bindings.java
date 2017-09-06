package com.wldev.expandablecardviewlist.bindings;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.wldev.expandablecardviewlist.extra.MyHTMLTagHandler;

/**
 * Created by wldev on 3/10/17.
 */

public class Bindings {
    private static final String TAG = Bindings.class.getSimpleName();

    @BindingAdapter("android:layout_height")
    public static void setLayoutHeight(View view, float height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) height;
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("android:layout_width")
    public static void setLayoutWidth(View view, float width) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (int) width;
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter({"layout_marginLeft"})
    public static void setMarginLeft(View layout, float margin) {
        if (layout.getLayoutParams() instanceof ViewGroup.MarginLayoutParams){
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) layout.getLayoutParams();
            params.leftMargin = (int) margin;
            layout.setLayoutParams(params);
        }
    }

    @BindingAdapter({"layout_marginRight"})
    public static void setMarginRight(View layout, float margin) {
        if (layout.getLayoutParams() instanceof ViewGroup.MarginLayoutParams){
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) layout.getLayoutParams();
            params.rightMargin = (int) margin;
            layout.setLayoutParams(params);
        }
    }

    @BindingAdapter({"layout_marginTop"})
    public static void setMarginTop(View layout, float margin) {
        if (layout.getLayoutParams() instanceof ViewGroup.MarginLayoutParams){
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) layout.getLayoutParams();
            params.topMargin = (int) margin;
            layout.setLayoutParams(params);
        }
    }

    @BindingAdapter({"layout_marginBottom"})
    public static void setMarginBottom(View layout, float margin) {
        if (layout.getLayoutParams() instanceof ViewGroup.MarginLayoutParams){
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) layout.getLayoutParams();
            params.bottomMargin = (int) margin;
            layout.setLayoutParams(params);
        }
    }

    @BindingAdapter({"onClick"})
    public static void setOnClick(View view, View.OnClickListener listener){
        view.setOnClickListener(listener);
    }

    @BindingAdapter({"backgroundTint"})
    public static void setBackhroundTint(View textView, String item) {
        Drawable drawable = textView.getBackground();
        drawable.setColorFilter(new
                PorterDuffColorFilter(Color.parseColor(item), PorterDuff.Mode.MULTIPLY));
        textView.setBackground(drawable);
    }

    @BindingAdapter({"html"})
    public static void setHTMLTextToTV(TextView textView, String text){
        setHTMLTextToTV(textView, text,false);
    }

    @BindingAdapter({"html","useChecks"})
    public static void setHTMLTextToTV(TextView textView, String text, boolean useChecks){
        Log.d(TAG, "setHTMLTextToTV: ");
        if (text == null || text.length()==0) {
            textView.setText("");
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(text,Html.FROM_HTML_MODE_LEGACY,null,new MyHTMLTagHandler(useChecks)));
        }
        else
            textView.setText(Html.fromHtml(text,null,new MyHTMLTagHandler(useChecks)));
    }


    @BindingAdapter({"animate", "animation", "fastAnimation"})
    public static void animateWithRes(ImageView imageView, boolean animate, Animation animation, boolean fastAnimation) {
        if (animate && imageView.getAnimation() != null) {
            if (fastAnimation) {
                animation.setDuration(0);
            }
            imageView.startAnimation(animation);
        }
    }

    @BindingAdapter({"reverse", "reverseAnimation", "fastReverse"})
    public static void reverseAnimationWithRes(ImageView imageView, boolean reverse, Animation animation, boolean fastAnimation) {
        if (reverse) {
            if (fastAnimation) {
                animation.setDuration(0);
            }
            imageView.startAnimation(animation);
        }
    }

}
