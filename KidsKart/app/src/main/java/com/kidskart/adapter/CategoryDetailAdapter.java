package com.kidskart.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kidskart.MainActivity;
import com.kidskart.R;
import com.kidskart.callbacks.SwitchFragmentsCallback;
import com.kidskart.data.SubCategoryData;
import com.kidskart.fragment.ProductDetailFragment;
import com.kidskart.fragment.SubCategoryListFragment;

import java.util.ArrayList;

/**
 * Created by Nilesh on 19/09/15.
 */
public class CategoryDetailAdapter extends RecyclerView.Adapter<CategoryDetailAdapter.ViewHolder> {

    ArrayList<SubCategoryData> arrSubcategory;
    Context context;
    SwitchFragmentsCallback switchFragmentsCallback;
    public CategoryDetailAdapter(ArrayList<SubCategoryData> arrSubcategory, Context context,SwitchFragmentsCallback switchFragmentsCallback){
        this.arrSubcategory = arrSubcategory;
        this.context = context;
        this.switchFragmentsCallback = switchFragmentsCallback;
    }

    @Override
    public CategoryDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.subcategory_cell, viewGroup, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        if(arrSubcategory != null){
            SubCategoryData data = arrSubcategory.get(i);
            viewHolder.txtProductName.setText(data.getTitle());
            viewHolder.txtDescription.setText(data.getDescription());
            viewHolder.txtPrice.setText("Rs. "+String.valueOf(data.getPrice()));
            viewHolder.card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switchFragmentsCallback.switchFragmentAddToBackStack(new ProductDetailFragment(), MainActivity.FRAG_PRODUCT_DETAIL,null,true);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return arrSubcategory.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtProductName,txtPrice,txtDescription;
        CardView card_view;
        public ViewHolder(View view) {
            super(view);
            txtDescription = (TextView) view.findViewById(R.id.txtDescription);
            txtPrice = (TextView) view.findViewById(R.id.txtPrice);
            txtProductName = (TextView) view.findViewById(R.id.txtProductName);
            card_view = (CardView) view.findViewById(R.id.card_view);
        }
    }
}
