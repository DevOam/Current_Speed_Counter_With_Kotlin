package com.example.testvitesse

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.Manifest
import androidx.core.content.ContextCompat
import androidx.core.app.ActivityCompat

private val MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
private val MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1

class MainActivity : AppCompatActivity(), LocationListener {

    private lateinit var speedTextView: TextView
    private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
        }


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION)
        }
        speedTextView = findViewById(R.id.speed_text_view)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
        } catch (e: SecurityException) {
            // Handle permissions exception
        }
    }

    override fun onLocationChanged(location: Location) {
        val speed = location.speed
        val speedInKmh = (speed * 3.6).toString()
        speedTextView.text = "$speedInKmh km/h"
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        // Handle status change
    }
}


