package com.kidskart.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kidskart.MainActivity;
import com.kidskart.R;
import com.kidskart.application.KidsKartApp;
import com.kidskart.callbacks.SwitchFragmentsCallback;
import com.kidskart.data.category.CategoryData;
import com.kidskart.data.category.ProductList;
import com.kidskart.fragment.ProductDetailFragment;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Nilesh on 19/09/15.
 */
public class CategoryDetailAdapter extends RecyclerView.Adapter<CategoryDetailAdapter.ViewHolder> {

    //ArrayList<SubCategoryData> arrSubcategory;
    Context context;
    SwitchFragmentsCallback switchFragmentsCallback;
    CategoryData categoryData;
    public CategoryDetailAdapter(CategoryData categoryData, Context context,SwitchFragmentsCallback switchFragmentsCallback){
        this.categoryData = categoryData;
        this.context = context;
        this.switchFragmentsCallback = switchFragmentsCallback;
    }

    @Override
    public CategoryDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.categorylist_cell, viewGroup, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        if (categoryData != null) {
            ProductList productList = categoryData.getProductList().get(i);
            viewHolder.txtProductName.setText(productList.getName());
            viewHolder.txtPrice.setText("Rs. "+productList.getPrice());
            viewHolder.txtDescription.setText(productList.getDescription());
            ImageLoader.getInstance().displayImage(productList.getImage_url(), viewHolder.imgProductImage, KidsKartApp.options);
            ImageLoader.getInstance().displayImage(productList.getBrand_logo(), viewHolder.imgBrandImage, KidsKartApp.brandImageOptions);
            viewHolder.card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switchFragmentsCallback.switchFragmentAddToBackStack(new ProductDetailFragment(), MainActivity.FRAG_PRODUCT_DETAIL,null,true);
                }
            });
        }
        /*if(categoryData != null){

        ImageLoader.getInstance().displayImage(url, imgPic, FMCApplication.options);
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
        }*/
    }

    @Override
    public int getItemCount() {
        if (categoryData != null) {
            if(!TextUtils.isEmpty(categoryData.getTotalProductCount())){
                try {
                    return Integer.parseInt(categoryData.getTotalProductCount());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return 0;
                }
            }

        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtProductName,txtPrice,txtDescription;
        CardView card_view;
        ImageView imgProductImage,imgBrandImage;
        public ViewHolder(View view) {
            super(view);
            txtDescription = (TextView) view.findViewById(R.id.txtDescription);
            txtPrice = (TextView) view.findViewById(R.id.txtPrice);
            txtProductName = (TextView) view.findViewById(R.id.txtProductName);
            card_view = (CardView) view.findViewById(R.id.card_view);
            imgProductImage = (ImageView) view.findViewById(R.id.imgProductImage);
            imgBrandImage = (ImageView) view.findViewById(R.id.imgBrandImage);
        }
    }
}
