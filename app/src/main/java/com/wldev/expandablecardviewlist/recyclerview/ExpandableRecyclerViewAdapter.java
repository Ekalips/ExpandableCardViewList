package com.wldev.expandablecardviewlist.recyclerview;

import android.animation.ValueAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.wldev.expandablecardviewlist.R;
import com.wldev.expandablecardviewlist.data.ExpandStateItem;
import com.wldev.expandablecardviewlist.data.Item;
import com.wldev.expandablecardviewlist.databinding.RvItemBinding;
import com.wldev.expandablecardviewlist.extra.ClickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wldev on 3/10/17.
 */

public class ExpandableRecyclerViewAdapter extends RecyclerView.Adapter<BindingViewHolder<RvItemBinding>> {
    private static final String TAG = ExpandableRecyclerViewAdapter.class.getSimpleName();

    private static final float MAX_MARGIN = 16;
    private static final float MIN_MARGIN = 2;

    private ValueAnimator marginAnimator = ValueAnimator.ofFloat(MAX_MARGIN, MIN_MARGIN); // replace with dimens

    private final List<Item> data = new ArrayList<>();
    private final List<ExpandStateItem> states = new ArrayList<>();
    private boolean isListExpanded;

    public ExpandableRecyclerViewAdapter(List<Item> originData) {
        marginAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                for (ExpandStateItem item :
                        states) {
                    item.setMargin((float) animation.getAnimatedValue());
                }
            }
        });

        setData(originData);
    }

    @Override
    public BindingViewHolder<RvItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BindingViewHolder<>(R.layout.rv_item, parent);
    }

    @Override
    public void onBindViewHolder(final BindingViewHolder<RvItemBinding> holder, int position) {
        holder.getBinding().setItem(data.get(holder.getAdapterPosition()));
        holder.getBinding().executePendingBindings(); // We need to set our item here because view will need to know how to measure itself (which data to use, how many text lines and so on)


        states.get(holder.getAdapterPosition()).setFast(true);
        holder.getBinding().setState(states.get(holder.getAdapterPosition()));

        holder.getBinding().setIsLast(holder.getAdapterPosition() == data.size() - 1);
        holder.getBinding().setIsFirst(holder.getAdapterPosition() == 0);

        holder.getBinding().setOnClick(new ClickAdapter() {
            @Override
            public void onClick(View v) {
                states.get(holder.getAdapterPosition()).setFast(false);
                states.get(holder.getAdapterPosition()).setExpanded(!states.get(holder.getAdapterPosition()).isExpanded()); // just to keep it short
                invalidateExpandedState();
            }
        });

        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void invalidateExpandedState() {
        boolean isAnyExpanded = false;
        for (ExpandStateItem item :
                states) {
            isAnyExpanded = isAnyExpanded || item.isExpanded();
        }

        if (isListExpanded != isAnyExpanded) {
            if (!isAnyExpanded) {
                isListExpanded = false;
                marginAnimator.start();
            } else {
                isListExpanded = true;
                marginAnimator.reverse();
            }
        }
    }

    private void setData(List<Item> newData) {
        this.data.clear();
        this.states.clear();

        for (Item i :
                newData) {
            this.data.add(i);
            this.states.add(new ExpandStateItem(false)); // You can actually save expanded states here
        }
    }
}
