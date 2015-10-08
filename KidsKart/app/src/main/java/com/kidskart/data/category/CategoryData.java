package com.kidskart.data.category;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by nilesing on 10/5/2015.
 */
public class CategoryData implements Serializable {

    private String category_name;

    private ArrayList<Sort> sort;

    private String productCount;

    private ArrayList<ProductList> productList;

    private String totalProductCount;

    private ArrayList<Filters> filters;



    public String getProductCount ()
    {
        return productCount;
    }

    public void setProductCount (String productCount)
    {
        this.productCount = productCount;
    }



    public String getTotalProductCount ()
    {
        return totalProductCount;
    }

    public void setTotalProductCount (String totalProductCount)
    {
        this.totalProductCount = totalProductCount;
    }

    public ArrayList<Sort> getSort() {
        return sort;
    }

    public void setSort(ArrayList<Sort> sort) {
        this.sort = sort;
    }

    public ArrayList<ProductList> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<ProductList> productList) {
        this.productList = productList;
    }

    public ArrayList<Filters> getFilters() {
        return filters;
    }

    public void setFilters(ArrayList<Filters> filters) {
        this.filters = filters;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [sort = "+sort+", productCount = "+productCount+", productList = "+productList+", totalProductCount = "+totalProductCount+", filters = "+filters+"]";
    }

}
