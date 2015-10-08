package com.kidskart.data.category;

import java.io.Serializable;

/**
 * Created by nilesing on 10/5/2015.
 */
public class ProductList implements Serializable {

    private String price;

    private String image_url;

    private String description;

    private String name;

    private String product_url;

    private String sku;

    private String entity_id;

    private String special_price;

    private String brand_logo;

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getImage_url ()
    {
        return image_url;
    }

    public void setImage_url (String image_url)
    {
        this.image_url = image_url;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getProduct_url ()
    {
        return product_url;
    }

    public void setProduct_url (String product_url)
    {
        this.product_url = product_url;
    }

    public String getSku ()
    {
        return sku;
    }

    public void setSku (String sku)
    {
        this.sku = sku;
    }

    public String getEntity_id ()
    {
        return entity_id;
    }

    public void setEntity_id (String entity_id)
    {
        this.entity_id = entity_id;
    }

    public String getSpecial_price ()
    {
        return special_price;
    }

    public void setSpecial_price (String special_price)
    {
        this.special_price = special_price;
    }

    public String getBrand_logo ()
    {
        return brand_logo;
    }

    public void setBrand_logo (String brand_logo)
    {
        this.brand_logo = brand_logo;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [price = "+price+", image_url = "+image_url+", description = "+description+", name = "+name+", product_url = "+product_url+", sku = "+sku+", entity_id = "+entity_id+", special_price = "+special_price+", brand_logo = "+brand_logo+"]";
    }
}
