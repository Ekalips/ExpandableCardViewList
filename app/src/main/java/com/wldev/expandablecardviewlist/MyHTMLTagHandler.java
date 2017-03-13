package com.wldev.expandablecardviewlist;

import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import org.xml.sax.XMLReader;

/**
 * Created by wldev on 3/10/17.
 */

public class MyHTMLTagHandler implements Html.TagHandler {

    private static final String TAG = MyHTMLTagHandler.class.getSimpleName();
    private String listSymbol;


    public MyHTMLTagHandler(boolean useChecks) {
        if (useChecks)
            listSymbol = "✓";
        else
            listSymbol = "•";
    }

    private boolean first = true;
    private String parent = null;
    private int index = 1;

    @Override
    public void handleTag(boolean opening, String tag, Editable output,
                          XMLReader xmlReader) {

        SpannableStringBuilder builder = new SpannableStringBuilder();
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(ContextCompat.getColor(MyApplication.get(), R.color.colorPrimary));

        if (tag.equals("ul")) parent = "ul";
        else if (tag.equals("ol")) parent = "ol";
        if (tag.equals("li")) {
            if (parent.equals("ul")) {
                if (first) {
                    builder.append("\n\t").append(listSymbol).append(" ");
                    builder.setSpan(foregroundColorSpan, "\n\t".length(), "\n\t".length() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    output.append(builder);
//                    output.append("\n\t").append(listSymbol).append(" ");
                    first = false;
                } else {
                    first = true;
                }
            } else {
                if (first) {
                    builder.append("\n\t").append(String.valueOf(index)).append(". ");
                    output.append(builder);
                    first = false;
                    index++;
                } else {
                    first = true;
                }
            }
        }
    }
}