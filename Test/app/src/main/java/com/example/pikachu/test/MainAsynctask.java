package com.example.pikachu.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
 * Created by Pikachu on 11/23/16.
 */

public class MainAsynctask extends AsyncTask<String,Integer,ArrayList<Product>> {
    private Context context;
    private String search;
    private String category_id;
    private String request_url;
    private ArrayList<Product> productResults;
    private ArrayList<Product1> product1Results;
    private String name;
    private String SKU;
    private Double price;
    private String productUrl;
    private String imageUrl;
    private Bitmap bitmap;

    public MainAsynctask(MainActivity mainActivity) {
        context = mainActivity;
        productResults = new ArrayList<>();
        product1Results = new ArrayList<>();
    }

    @Override
    protected ArrayList<Product> doInBackground(String... strings) {
        search = strings[0];
        category_id = strings[1];
        search = search.trim();
        search = search.replace(" ","&search=");
//        Log.d("Jsondata", "search: " +  search);

        if(category_id.equals("default")){
            if(search.equals("")){
                Log.d("Jsondata", "First IF Loop");
                request_url = "https://api.bestbuy.com/v1/products?apiKey=X6Ez6RSEUvpyHg8z6uCbWSUk&sort=name.asc&show=name,sku,regularPrice,mobileUrl,image&pageSize=25&format=json";
            }else{
                Log.d("Jsondata", "First Else Loop");
                request_url = "https://api.bestbuy.com/v1/products((search="+search+"))?apiKey=X6Ez6RSEUvpyHg8z6uCbWSUk&sort=name.asc&show=name,sku,regularPrice,mobileUrl,image&pageSize=25&format=json";
            }
        } else{

            if(search.equals("")){
                Log.d("Jsondata", "Second IF Loop");
                request_url = "https://api.bestbuy.com/v1/products((categoryPath.id="+ category_id+"))?apiKey=X6Ez6RSEUvpyHg8z6uCbWSUk&sort=name.asc&show=name,sku,regularPrice,mobileUrl,image&pageSize=25&format=json";

            } else{
                Log.d("Jsondata", "Second Else Loop");
                request_url = "https://api.bestbuy.com/v1/products((search=" + search+")&(categoryPath.id=" + category_id+ "))?apiKey=X6Ez6RSEUvpyHg8z6uCbWSUk&sort=name.asc&show=name,sku,regularPrice,mobileUrl,image&pageSize=25&format=json";
            }
        }
        Log.d("Jsondata", "URL " + request_url);

        try {
            URL url = new URL(request_url);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(),"UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while((line = bufferedReader.readLine()) != null ){
                stringBuilder.append(line);
            }

            String JSONString = stringBuilder.toString();
            Log.d("Jsondata",JSONString);
            JSONObject jsonObject = new JSONObject(JSONString);
            Log.d ("Jsondata", "Object " +  jsonObject);
            JSONArray products = jsonObject.getJSONArray("products");

            for(int i =0;i < products.length();i++){

                JSONObject singleProduct = products.getJSONObject(i);
                Product product;
                Product1 product1;

                name = "Name: " + singleProduct.getString("name");
                SKU = "SKU: " + singleProduct.getString("sku");
                price = singleProduct.getDouble("regularPrice");
                productUrl = singleProduct.getString("mobileUrl");
                imageUrl = singleProduct.getString("image");

                URL bitmapUrl = new URL(imageUrl);
                bitmap = BitmapFactory.decodeStream(bitmapUrl.openConnection().getInputStream());

                product = new Product(name,SKU,price,productUrl,imageUrl);
                product1 = new Product1(name,SKU,price,productUrl,bitmap);
                productResults.add(product);
                product1Results.add(product1);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return productResults;
    }

    @Override
    protected void onPostExecute(ArrayList<Product> products) {
        super.onPostExecute(products);
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.listView.setVisibility(View.VISIBLE);
        mainActivity.drawListView(products,product1Results);
//        Toast.makeText(context,"Product List Received ",Toast.LENGTH_LONG).show();
    }
}
