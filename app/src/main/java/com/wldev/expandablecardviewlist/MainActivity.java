package com.wldev.expandablecardviewlist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.wldev.expandablecardviewlist.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<Item> arrayList = new ArrayList<>();
        arrayList.add(new Item("Freelancer", getString(R.string.first_plan)));
        arrayList.add(new Item("Startup", getString(R.string.second_plan)));
        arrayList.add(new Item("Agency", getString(R.string.third_plan)));
        arrayList.add(new Item("Corporate", getString(R.string.full_plan)));
        binding.recyclerView.setAdapter(new ExpandableRecyclerViewAdapter(arrayList));
    }
}
