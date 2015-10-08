package com.kidskart.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.kidskart.R;
import com.kidskart.adapter.FilterNameAdapter;
import com.kidskart.adapter.FilterValueAdapter;
import com.kidskart.callbacks.SwitchFragmentsCallback;
import com.kidskart.data.category.Filters;
import com.kidskart.data.category.Values;

import java.util.ArrayList;

/**
 * Created by Nilesh on 08/10/15.
 */
public class FiltersFragment extends Fragment {

    ListView listFilter,listFilterValues;
    FilterNameAdapter filterNameAdapter;
    FilterValueAdapter filterValueAdapter;
    ArrayList<Filters> arrayFilters;
    ArrayList<Values> arrayValues;
    ImageView imgBack;
    SwitchFragmentsCallback switchFragmentsCallback;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.filter_layout,container,false);
        listFilter = (ListView) view.findViewById(R.id.listFilter);
        listFilterValues = (ListView) view.findViewById(R.id.listFilterValues);
        arrayValues = new ArrayList<>();
        imgBack = (ImageView) view.findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchFragmentsCallback != null) {
                    switchFragmentsCallback.popBackStack(true);
                }
            }
        });

        Bundle bundle = getArguments();

        if (bundle != null) {
            arrayFilters = (ArrayList<Filters>) bundle.getSerializable("filters");
        }

        if (arrayFilters != null) {
            if(arrayFilters.size() > 0){
                arrayFilters.get(0).setIsSelected(true);
                arrayValues = arrayFilters.get(0).getValues();
            }
            filterNameAdapter = new FilterNameAdapter(arrayFilters,getActivity(),this);
            listFilter.setAdapter(filterNameAdapter);


            filterValueAdapter = new FilterValueAdapter(arrayValues,getActivity());
            listFilterValues.setAdapter(filterValueAdapter);
        }

        return view;
    }

    public void setNewFilterValues(Filters filters){
        arrayValues = filters.getValues();
        filterValueAdapter = new FilterValueAdapter(arrayValues,getActivity());
        listFilterValues.setAdapter(filterValueAdapter);
        filterValueAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        switchFragmentsCallback = (SwitchFragmentsCallback) activity;
    }
}
