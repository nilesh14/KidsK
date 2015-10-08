package com.kidskart;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kidskart.adapter.CategoryDetailAdapter;
import com.kidskart.data.SubCategoryData;

import java.util.ArrayList;

/**
 * Created by Nilesh on 19/09/15.
 */
public class SubCategoryListActivity extends Activity {

    ArrayList<SubCategoryData> arrSubcategoryData = new ArrayList<>();

    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.categorylist_fragment);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        for(int i = 0; i < 5; i ++){
            SubCategoryData data = new SubCategoryData();
            data.setTitle("Boys Mineral Red Sicillian Road Trip Graphic Item "+i);
            data.setDescription(getString(R.string.dummy_data_big));
            data.setPrice(i*i + 20);
            arrSubcategoryData.add(data);
        }

        mAdapter = new CategoryDetailAdapter(arrSubcategoryData,SubCategoryListActivity.this);
        mRecyclerView.setAdapter(mAdapter);

        getActionBar().setTitle("");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setIcon(R.drawable.logo);*/

    }
}
