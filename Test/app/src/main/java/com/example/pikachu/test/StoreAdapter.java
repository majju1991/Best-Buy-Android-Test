package com.example.pikachu.test;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Pikachu on 11/26/16.
 */

public class StoreAdapter extends ArrayAdapter {
    ArrayList<Stores> storesArray;
    Context context;
    ViewHolder holder;

    public StoreAdapter(ArrayList<Stores> storesArray, Context context){
        super(context,R.layout.stores_row,storesArray);
        this.storesArray = storesArray;
        this.context = context;
    }

    public class ViewHolder{
        TextView name;
        TextView address;
        TextView hours;


        public ViewHolder(View row){
            name = (TextView) row.findViewById(R.id.store_name);
            address = (TextView) row.findViewById(R.id.store_address);
            hours = (TextView) row.findViewById(R.id.store_hours);
        }
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.stores_row,parent,false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        }else {
            holder = (ViewHolder) row.getTag();
        }
        String text = "Name: " + storesArray.get(position).name;
        holder.name.setText(text);

        text = "Address: " + storesArray.get(position).address + ", " + storesArray.get(position).city +", " + storesArray.get(position).region +", " + storesArray.get(position).country + ".";
        holder.address.setText(text);

        text = "Hours: " + storesArray.get(position).hours;
        holder.hours.setText(text);


        return row;
    }
}
