package com.kidskart.data.category;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by nilesing on 10/5/2015.
 */
public class Filters implements Serializable{

    boolean isSelected;
    private String id;

    private ArrayList<Values> values;

    private String name;

    private ArrayList<String> priceValues;

    private String code;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public ArrayList<Values> getValues() {
        return values;
    }

    public void setValues(ArrayList<Values> values) {
        this.values = values;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public ArrayList<String> getPriceValues() {
        return priceValues;
    }

    public void setPriceValues(ArrayList<String> priceValues) {
        this.priceValues = priceValues;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", values = "+values+", name = "+name+", priceValues = "+priceValues+", code = "+code+"]";
    }
}
