package com.kidskart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kidskart.R;
import com.kidskart.data.category.Filters;
import com.kidskart.fragment.FiltersFragment;

import java.util.ArrayList;

/**
 * Created by Nilesh on 08/10/15.
 */
public class FilterNameAdapter extends BaseAdapter {

    FiltersFragment filtersFragment;
    ArrayList<Filters> arrayFilters;
    LayoutInflater inflater;
    Context context;
    public FilterNameAdapter(ArrayList<Filters> arrayFilters,Context context,FiltersFragment filtersFragment) {
        this.arrayFilters = arrayFilters;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.filtersFragment = filtersFragment;
    }

    @Override
    public int getCount() {
        return arrayFilters.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayFilters.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.filter_cell, parent, false);
            holder = new ViewHolder();

            holder.txtFilterName = (TextView) view.findViewById(R.id.txtFilterName);
            // holder.progressBar = (ProgressBar) view.findViewById(R.id.progress);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final Filters filters = arrayFilters.get(position);

        if(filters != null){
            holder.txtFilterName.setText(filters.getName());

            holder.txtFilterName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    resetSelectedState();
                    arrayFilters.get(position).setIsSelected(true);
                    filtersFragment.setNewFilterValues(filters);
                    notifyDataSetChanged();
                }
            });

            if(filters.isSelected()){
                holder.txtFilterName.setBackgroundResource(android.R.color.white);
                holder.txtFilterName.setTextColor(context.getResources().getColor(R.color.list_filter_bg_color));
            }else{
                holder.txtFilterName.setBackgroundResource(android.R.color.transparent);
                holder.txtFilterName.setTextColor(context.getResources().getColor(android.R.color.white));
            }
        }



        return view;
    }

    private void resetSelectedState(){
        for (Filters filters : arrayFilters) {
            filters.setIsSelected(false);
        }
    }

    private class ViewHolder{

        TextView txtFilterName;
    }
}
