package com.kidskart.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kidskart.R;
import com.kidskart.adapter.CategoryDetailAdapter;
import com.kidskart.callbacks.SwitchFragmentsCallback;
import com.kidskart.data.SubCategoryData;

import java.util.ArrayList;

/**
 * Created by Nilesh on 20/09/15.
 */
public class SubCategoryListFragment extends Fragment {

    ArrayList<SubCategoryData> arrSubcategoryData = new ArrayList<>();

    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    SwitchFragmentsCallback switchFragmentsCallback;
    ImageView imgBack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subcategory_fragment,container,false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        imgBack = (ImageView) view.findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragmentsCallback.popBackStack(true);
            }
        });

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        arrSubcategoryData.clear();
        for(int i = 0; i < 5; i ++){
            SubCategoryData data = new SubCategoryData();
            data.setTitle("Boys Mineral Red Sicillian Road Trip Graphic Item "+i);
            data.setDescription(getString(R.string.dummy_data_big));
            data.setPrice(i*i + 20);
            arrSubcategoryData.add(data);
        }

        mAdapter = new CategoryDetailAdapter(arrSubcategoryData,getActivity(),switchFragmentsCallback);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }



    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        switchFragmentsCallback = (SwitchFragmentsCallback) getActivity();
    }
}
