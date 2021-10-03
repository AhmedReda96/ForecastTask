package com.example.forecasttask.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.forecasttask.viewModel.HomeViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;


public class GeneralMethods {
    private Context context;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private List<Address> addresses;
    private Geocoder geocoder;
    private boolean locationFlag = false;
    private Location location;
    String city = "";
    private HomeViewModel homeViewModel;

    public GeneralMethods(Context context) {
        this.context = context;
        geocoder = new Geocoder(context, new Locale("en"));
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        homeViewModel = new
                ViewModelProvider((ViewModelStoreOwner) context).get(HomeViewModel.class);
        homeViewModel.init(context);
    }


    public void addDefaultCity(){
        homeViewModel.insertCityToRoom("London",true);

    }


    public Boolean getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                location = task.getResult();
                if (location != null) {
                    Log.d(
                            "TAG",
                            "testTag getCurrentLocation:location != null" + "\n"
                    );
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();

                    Log.d(
                            "TAG",
                            "testTag getCurrentLocationSP:" + "\n" + latitude + "\n" + longitude
                    );

                    try {

                        addresses = geocoder.getFromLocation(latitude, longitude, 1);
                        if (addresses != null && addresses.size() > 0) {
                            String cityName = addresses.get(0).getAddressLine(0);
                            homeViewModel.insertCityToRoom(cityName,true);

                            Log.d(
                                    "TAG",
                                    "testTag getCurrentLocationSP:" + city
                            );
                        }
                    } catch (IOException e) {
                        Log.d("TAG", "testTag getLocationAddress: error: " + e.getMessage());

                        e.printStackTrace();
                    }

                    locationFlag = true;
                } else {
                    locationFlag = false;
                    Log.d(
                            "TAG",
                            "testTag getCurrentLocation:location == null"
                    );
                }
            }
        });
        return true;
    }


}



