package com.example.scotiabankplus;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FarmaciasLocationActivity extends AppCompatActivity implements OnMapReadyCallback {
    protected LocationManager locationManager;
    private GoogleMap mMap;
    private LatLng currentLocation;

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            if (mMap!=null) {
                currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                System.out.println("onLocationChanged" + location);
                mMap.addMarker(new MarkerOptions().position(currentLocation).title("Chile"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle b) {
            System.out.println("onStatusChanged" + s);
        }

        @Override
        public void onProviderEnabled(String s) {
            System.out.println("onProviderEnabled" + s);
        }

        @Override
        public void onProviderDisabled(String s) {
            System.out.println("onProviderDisabled" + s);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("FarmaciasLocationActivity onCreate");

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100,
                    100, mLocationListener);
            System.out.println("locationManager"+locationManager);
        } catch (SecurityException e) {
            System.out.println("locationManager"+e.getMessage());
        }
        setContentView(R.layout.farmacia_location);
    }
    
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (currentLocation != null) {
            //seattle coordinates
            mMap.addMarker(new MarkerOptions().position(currentLocation).title("Chile"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
        }
    }
}
