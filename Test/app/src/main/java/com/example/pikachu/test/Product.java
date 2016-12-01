package com.example.pikachu.test;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pikachu on 11/23/16.
 */
/*public class Product {
    String name;
    String SKU;
    Double price;
    String productUrl;
    String imageUrl;

    public Product(String name, String SKU, Double price,String productUrl, String imageUrl){
        this.name = name;
        this.SKU = SKU;
        this.price = price;
        this.productUrl = productUrl;
        this.imageUrl = imageUrl;
    }
}*/

/*public class Product implements Parcelable {
    String name;
    String SKU;
    Double price;
    String productUrl;
    Bitmap bitmap;

    public Product(String name, String SKU, Double price,String productUrl, Bitmap bitmap){
        this.name = name;
        this.SKU = SKU;
        this.price = price;
        this.productUrl = productUrl;
        this.bitmap = bitmap;
    }

    protected Product(Parcel in) {
        name = in.readString();
        SKU = in.readString();
        price = in.readByte() == 0x00 ? null : in.readDouble();
        productUrl = in.readString();
        bitmap = (Bitmap) in.readValue(Bitmap.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(SKU);
        if (price == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(price);
        }
        dest.writeString(productUrl);
        dest.writeValue(bitmap);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}*/

public class Product implements Parcelable {
    String name;
    String SKU;
    Double price;
    String productUrl;
    String imageUrl;

    public Product(String name, String SKU, Double price,String productUrl, String imageUrl){
        this.name = name;
        this.SKU = SKU;
        this.price = price;
        this.productUrl = productUrl;
        this.imageUrl = imageUrl;
    }

    protected Product(Parcel in) {
        name = in.readString();
        SKU = in.readString();
        price = in.readByte() == 0x00 ? null : in.readDouble();
        productUrl = in.readString();
        imageUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(SKU);
        if (price == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(price);
        }
        dest.writeString(productUrl);
        dest.writeString(imageUrl);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}