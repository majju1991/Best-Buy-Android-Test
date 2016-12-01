package com.example.pikachu.test;

import android.graphics.Bitmap;


/**
 * Created by Pikachu on 11/28/16.
 */

public class Product1 {

    String name;
    String SKU;
    Double price;
    String productUrl;
    Bitmap bitmap;

    public Product1(String name, String SKU, Double price,String productUrl, Bitmap bitmap){
        this.name = name;
        this.SKU = SKU;
        this.price = price;
        this.productUrl = productUrl;
        this.bitmap = bitmap;
    }
}

