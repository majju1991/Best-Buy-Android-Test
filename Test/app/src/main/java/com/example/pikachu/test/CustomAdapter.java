package com.example.pikachu.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Pikachu on 11/23/16.
 */

public class CustomAdapter extends ArrayAdapter {
    Context context;
    ArrayList<Product1> product1s;
    ViewHolder holder;

    public CustomAdapter(MainActivity mainActivity, ArrayList<Product1> product1s) {

        super(mainActivity, R.layout.product_list_row, product1s);
        context = mainActivity;
        this.product1s = product1s;
    }

    public class ViewHolder {
        ImageView imageView;
        TextView nameView;
        TextView SKUView;
        TextView priceView;

        public ViewHolder(View row) {
            imageView = (ImageView) row.findViewById(R.id.product_imageview1);
            nameView = (TextView) row.findViewById(R.id.product_name_view1);
            SKUView = (TextView) row.findViewById(R.id.product_sku_view2);
            priceView = (TextView) row.findViewById(R.id.product_price_view2);
        }

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.product_list_row, parent, false);
            holder = new ViewHolder(row);
            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }
        Product1 singleProduct1 = product1s.get(position);
        String value = "Name: " + singleProduct1.name;
        holder.nameView.setText(value);
        value = "SKU: " + singleProduct1.SKU;
        holder.SKUView.setText(value);
        value = "Price: " + singleProduct1.price;
        holder.priceView.setText(value);

        holder.imageView.setImageBitmap(singleProduct1.bitmap);


        return row;
    }
}