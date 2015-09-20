package com.kidskart.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kidskart.R;
import com.kidskart.adapter.ProductDetailAdapter;
import com.kidskart.type.Type;

import java.util.ArrayList;

/**
 * Created by Nilesh on 20/09/15.
 */
public class ProductDetailFragment extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager mLayoutManager;

    ImageView imgMinus,imgPlus;
    TextView txtQuantity;

    ArrayList<Type> arrType = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_detail_fragment,container,false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_product_details);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new ProductDetailAdapter(this);
        mRecyclerView.setAdapter(adapter);

        imgMinus = (ImageView) view.findViewById(R.id.imgMinus);
        imgPlus = (ImageView) view.findViewById(R.id.imgPlus);
        txtQuantity = (TextView) view.findViewById(R.id.txtQuantity);

        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(txtQuantity.getText().toString());
                if(value >= 1){
                    value --;
                    txtQuantity.setText(String.valueOf(value));
                }
            }
        });

        imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(txtQuantity.getText().toString());
                txtQuantity.setText(String.valueOf(value + 1));
            }
        });


        return view;
    }
}
