package com.example.pikachu.test;

import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StoreActivity extends AppCompatActivity implements com.google.android.gms.location.LocationListener,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    EditText zipcode;
    String zip;
    StoreAsync storeAsync;
    ListView listView;
    GoogleApiClient googleApiClient;
    LocationRequest locationRequest;
    Switch aSwitch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_layout);
        zipcode = (EditText) findViewById(R.id.zip_code);
        listView = (ListView) findViewById(R.id.stores_listview);
        listView.setVisibility(View.GONE);
        aSwitch = (Switch) findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    listView.setVisibility(View.VISIBLE);

                }else{
                    listView.setVisibility(View.GONE);
                }
            }
        });


    }

    public void getCurrentLocation(View view) {
        googleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API)
                .addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();

        googleApiClient.connect();


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(1000);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,locationRequest,this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    public void onLocationChanged(Location location) {
        if(location == null){
            Toast.makeText(this,"Could not get Location",Toast.LENGTH_SHORT).show();
        }else {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<android.location.Address> list = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                android.location.Address address = list.get(0);
                zipcode.setText(address.getPostalCode());

                LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void searchStores(View view) {
        zip = zipcode.getText().toString();
        storeAsync = new StoreAsync(this);
        storeAsync.execute(zip);
    }


    public void listViewDraw(ArrayList<Stores> resultArray){


        Toast.makeText(this,"drawListview",Toast.LENGTH_SHORT).show();

        Log.d("JsonMessage", "DrawListArray " + resultArray);

        StoreAdapter storeAdapter = new StoreAdapter(resultArray, this);
        listView.setAdapter(storeAdapter);
    }


}
