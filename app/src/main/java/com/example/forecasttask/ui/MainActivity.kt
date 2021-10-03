package com.example.forecasttask.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.forecasttask.R
import com.example.forecasttask.utils.GeneralMethods
import com.example.forecasttask.viewModel.HomeViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.OnCompleteListener
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  private lateinit var generalMethods: GeneralMethods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      generalMethods=GeneralMethods(this)


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d("TAG", "testTag onRequestPermissionsResult:")
        if (requestCode == 44) {
            if (grantResults.isEmpty()) {
                Log.d(
                    "TAG",
                    "testTag onRequestPermissionsResult:User interaction was cancelled"
                )
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                generalMethods.currentLocation
                Log.d(
                    "TAG",
                    "testTag onRequestPermissionsResult:Permission granted"
                )
                Toast.makeText(this, " Location Access", Toast.LENGTH_SHORT).show()
            } else {
                generalMethods.addDefaultCity()
                Log.d("TAG", "testTag onRequestPermissionsResult:Permission denied")
            }

        }

    }
}