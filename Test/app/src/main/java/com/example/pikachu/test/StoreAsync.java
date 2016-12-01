package com.example.pikachu.test;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Pikachu on 11/26/16.
 */

public class StoreAsync extends AsyncTask<String,Integer,ArrayList<Stores>> {
    Context context;
    ArrayList<Stores> resultArray;
    String zipcode;
    String requestUrl;
    String name;
    String address;
    String city;
    String region;
    String country;
    String hours;
    Stores single;


    public StoreAsync(Context context){
        this.context = context;
        resultArray = new ArrayList<>();
    }

    @Override
    protected ArrayList<Stores> doInBackground(String... strings) {
        zipcode = strings[0];


        requestUrl = "https://api.bestbuy.com/v1/stores((area("+zipcode+",25)))?apiKey=X6Ez6RSEUvpyHg8z6uCbWSUk&show=name,address,city,region,country,hoursAmPm&pageSize=25&format=json";
       /* Log.d("JsonMessage","Link: " + requestUrl);*/

        try {
            URL url = new URL(requestUrl);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));

            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }

            String jsonString = stringBuilder.toString();

            JSONObject jsonObject = new JSONObject(jsonString);
            /*Log.d("JsonMessage", "JSOBJECT: " + jsonObject);*/
            JSONArray stores = jsonObject.getJSONArray("stores");
            /*Log.d("JsonMessage", "StoreSize: " + stores.length());*/

            for(int i=0;i<stores.length();i++)
            {
                JSONObject singleStore = stores.getJSONObject(i);

                name = singleStore.getString("name");
                address = singleStore.getString("address");
                city = singleStore.getString("city");
                region = singleStore.getString("region");
                country = singleStore.getString("country");
                hours = singleStore.getString("hoursAmPm");

                single = new Stores(name,address,city,region,country,hours);

                resultArray.add(single);
            }

            /*Log.d("JsonMessage", "Result array: " + resultArray);*/
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultArray;
    }

    @Override
    protected void onPostExecute(ArrayList<Stores> stores) {
        super.onPostExecute(stores);
        StoreActivity storeActivity = (StoreActivity) context;
        storeActivity.listViewDraw(stores);
    }
}
