package com.example.routemetest.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.routemetest.MainActivity
import com.example.routemetest.R
import com.example.routemetest.databinding.ActivityMainBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import com.example.routemetest.databinding.ActivityMapsBinding
import com.example.routemetest.databinding.FragmentEnterPhoneNumberBinding.inflate
import com.example.routemetest.databinding.FragmentOrdersBinding
import com.example.routemetest.ui.fragments.OrdersFragment
import com.example.routemetest.utilities.replaceActivity
import com.example.routemetest.utilities.replaceFragment

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var  mBinding: ActivityMainBinding
    private lateinit var mOrder: FragmentOrdersBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        binding.mapBtnBack.setOnClickListener{ val intent = Intent(this@MapsActivity, MainActivity::class.java)
            startActivity(intent)}
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val minsk = LatLng(53.905255138551595, 27.555944843409836)
        mMap.addMarker(MarkerOptions().position(minsk).title("Минск"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(minsk, 10f))

        mMap.isTrafficEnabled= true
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
           ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),0)
            return
        }
        mMap.isMyLocationEnabled= true
    }
}