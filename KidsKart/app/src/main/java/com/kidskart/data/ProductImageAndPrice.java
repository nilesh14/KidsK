package com.kidskart.data;

import com.kidskart.type.Type;
import com.kidskart.type.TypeConstants;

/**
 * Created by Nilesh on 20/09/15.
 */
public class ProductImageAndPrice implements Type {

    double price;

    @Override
    public int myType() {
        return TypeConstants.TYPE_PRODUCT_IMAGE_PRICE;
    }
}
