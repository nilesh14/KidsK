package com.kidskart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.kidskart.R;
import com.kidskart.data.category.Filters;
import com.kidskart.data.category.Values;

import java.util.ArrayList;

/**
 * Created by Nilesh on 08/10/15.
 */
public class FilterValueAdapter extends BaseAdapter {

    ArrayList<Values> arrayValues;
    LayoutInflater inflater;
    Context context;
    public FilterValueAdapter(ArrayList<Values> arrayValues, Context context) {
        this.arrayValues = arrayValues;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayValues.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayValues.get(i);
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
            view = inflater.inflate(R.layout.filtervalues_cell, parent, false);
            holder = new ViewHolder();

            holder.radioFilterValues = (RadioButton) view.findViewById(R.id.radioFilterValues);
            holder.txtQuantity = (TextView) view.findViewById(R.id.txtQuantity);
            // holder.progressBar = (ProgressBar) view.findViewById(R.id.progress);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Values values = arrayValues.get(position);

        if(values != null){
            holder.radioFilterValues.setText(values.getOption_label());

            if(values.isSelected()){
                holder.radioFilterValues.setChecked(true);
            }else{
                holder.radioFilterValues.setChecked(false);
            }
            holder.radioFilterValues.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    resetSelectedState();
                    arrayValues.get(position).setIsSelected(true);
                    notifyDataSetChanged();
                }
            });
        }



        return view;
    }

    private void resetSelectedState(){
        for (Values filters : arrayValues) {
            filters.setIsSelected(false);
        }
    }

    private class ViewHolder{
        RadioButton radioFilterValues;
        TextView txtQuantity;
    }
}
