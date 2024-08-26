package com.example.myproject2022_05_06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.google.android.gms.maps.GoogleMap;
import android.app.FragmentManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class mapLocation extends AppCompatActivity implements OnMapReadyCallback{

    GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_location);
        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady( GoogleMap map) {

        mMap = map;
        LatLng CHEONAN = new LatLng(36.833333, 127.178889);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(CHEONAN).title("상명대학교 천안 정문");
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(CHEONAN));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));

    }
}