package com.example.pikachu.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BuyProductActivity extends AppCompatActivity {
    ImageView imageView;
    TextView name;
    TextView price;
    TextView sku;
    WebView webView;
    Thread thread;
    String imageurl;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_product);
//        Toast.makeText(this,"HI",Toast.LENGTH_SHORT).show();

        imageView = (ImageView) findViewById(R.id.imageView);
        webView = (WebView) findViewById(R.id.webview);
        webView.setVisibility(View.GONE);
        name = (TextView) findViewById(R.id.buyproduct_name);
        price = (TextView) findViewById(R.id.buyproduct_price);
        sku = (TextView) findViewById(R.id.buyproduct_sku);
        String text;

        Product product = getIntent().getParcelableExtra("result");
        imageurl = product.imageUrl;
        thread = new Thread(new Mythread(imageurl));
        thread.start();

        webView.setWebViewClient( new WebViewClient());
        webView.loadUrl(product.productUrl);

        text = "Name: " + product.name;
        name.setText(text);

        text = "SKU: " + product.SKU;
        sku.setText(text);

        text = "Price " + product.price;
        price.setText(text);

        webView.setVisibility(View.VISIBLE);

    }


    public class Mythread implements Runnable{
        String imageurl;
        URL url;
        Bitmap bitmap;

        public Mythread(String imageurl){
            this.imageurl = imageurl;
        }

        @Override
        public void run() {
            try {
                url = new URL(imageurl);
                bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                BuyProductActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);

                    }
                });

            }


        }
    }
}
