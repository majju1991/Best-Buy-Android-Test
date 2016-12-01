package com.example.pikachu.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    String category_id;
    String search;
    EditText editText;
    MainAsynctask mainAsynctask;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        editText = (EditText) findViewById(R.id.editText1);
        listView = (ListView) findViewById(R.id.product_listview);
        spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0: {
//                        Toast.makeText(MainActivity.this, "default", Toast.LENGTH_LONG).show();
                        category_id = "default";
                        break;
                    }
                    case 1: {
//                        Toast.makeText(MainActivity.this, "Clicked " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                        category_id = "pcmcat209400050001";
                        break;
                    }
                    case 2: {
//                        Toast.makeText(MainActivity.this, "Clicked " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                        category_id="abcat0501000";
                        break;
                    }
                    case 3: {
//                        Toast.makeText(MainActivity.this, "Clicked " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                        category_id="abcat0401000";
                        break;
                    }
                    case 4: {
//                        Toast.makeText(MainActivity.this, "Clicked " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                        category_id="pcmcat242800050021";
                        break;
                    }
                    case 5: {
//                        Toast.makeText(MainActivity.this, "Clicked " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                        category_id="abcat0204000";
                        break;
                    }
                    case 6: {
//                        Toast.makeText(MainActivity.this, "Clicked " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                        category_id="pcmcat241600050001";
                        break;
                    }
                    case 7: {
//                        Toast.makeText(MainActivity.this, "Clicked " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                        category_id="abcat0502000";
                        break;
                    }
                    case 8: {
//                        Toast.makeText(MainActivity.this, "Clicked " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                        category_id="pcmcat295700050012";
                        break;
                    }
                    case 9: {
//                        Toast.makeText(MainActivity.this, "Clicked " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                        category_id="abcat0101000";
                        break;
                    }
                    case 10: {
//                        Toast.makeText(MainActivity.this, "Clicked " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                        category_id="pcmcat300300050002";
                        break;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.store_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this,StoreActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    public void searchBestBuy(View view) {
        listView.setVisibility(View.GONE);
        search = editText.getText().toString();
        Toast.makeText(this," Search: " + search + " Category ID : " + category_id,Toast.LENGTH_SHORT).show();
        mainAsynctask = new MainAsynctask(this);
        mainAsynctask.execute(search,category_id);

    }


    public void drawListView(final ArrayList<Product> products, final ArrayList<Product1> product1s){


        CustomAdapter adapter = new CustomAdapter(this,product1s);
        listView.setAdapter(adapter);
        Toast.makeText(this,"Adapter Set",Toast.LENGTH_SHORT).show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,BuyProductActivity.class);
                intent.putExtra("result",products.get(i));
                startActivity(intent);
            }
        });
    }

}
