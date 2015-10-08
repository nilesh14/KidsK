package com.kidskart.data.category;

import java.io.Serializable;

/**
 * Created by nilesing on 10/5/2015.
 */
public class Sort implements Serializable {

    private String id;

    private String sort_by;

    private String direction;

    private String name;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getSort_by ()
    {
        return sort_by;
    }

    public void setSort_by (String sort_by)
    {
        this.sort_by = sort_by;
    }

    public String getDirection ()
    {
        return direction;
    }

    public void setDirection (String direction)
    {
        this.direction = direction;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", sort_by = "+sort_by+", direction = "+direction+", name = "+name+"]";
    }
}
