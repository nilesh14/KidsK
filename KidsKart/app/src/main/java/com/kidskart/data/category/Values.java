package com.kidskart.data.category;

import java.io.Serializable;

/**
 * Created by nilesing on 10/5/2015.
 */
public class Values implements Serializable {

    boolean isSelected;
    private String option_label;

    private String option_id;

    public String getOption_label ()
    {
        return option_label;
    }

    public void setOption_label (String option_label)
    {
        this.option_label = option_label;
    }

    public String getOption_id ()
    {
        return option_id;
    }

    public void setOption_id (String option_id)
    {
        this.option_id = option_id;
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
        return "ClassPojo [option_label = "+option_label+", option_id = "+option_id+"]";
    }
}
