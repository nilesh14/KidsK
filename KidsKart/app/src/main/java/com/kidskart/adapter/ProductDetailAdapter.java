package com.kidskart.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kidskart.R;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by Nilesh on 20/09/15.
 */
public class ProductDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    Fragment fragment;

    public ProductDetailAdapter(Fragment fragment){
        this.fragment = fragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_image_slide_fragment, parent, false);
        return new ProductImagePriceViewHolder(view);
        //return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


    }


    @Override
    public int getItemCount() {
        return 1;
    }

    public class ProductImagePriceViewHolder extends RecyclerView.ViewHolder{

        private ViewPager mPager;

        /**
         * The pager adapter, which provides the pages to the view pager widget.
         */
        private PagerAdapter mPagerAdapter;
        CirclePageIndicator circle;
        TextView txtReviews,txtOldPrice;
        public ProductImagePriceViewHolder(View itemView) {
            super(itemView);
            mPager = (ViewPager) itemView.findViewById(R.id.pager);
            circle = (CirclePageIndicator) itemView.findViewById(R.id.indicator);
            mPagerAdapter = new ScreenSlidePagerAdapter(fragment.getChildFragmentManager());
            mPager.setAdapter(mPagerAdapter);

            txtOldPrice = (TextView) itemView.findViewById(R.id.txtOldPrice);
            SpannableString cost = new SpannableString(txtOldPrice.getText().toString());
            cost.setSpan(new StrikethroughSpan(),0,cost.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            txtOldPrice.setText(cost);

            txtReviews = (TextView) itemView.findViewById(R.id.txtReviews);
            SpannableString string = new SpannableString(txtReviews.getText().toString());
            string.setSpan(new ForegroundColorSpan(Color.BLUE),11,15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            txtReviews.setText(string);

            circle.setViewPager(mPager);
        }
    }
}
