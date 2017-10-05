package suntime.swindroid.suncalculatorp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import suntime.swindroid.suncalculatorp.R;
import suntime.swindroid.suncalculatorp.util.Const;

/**
 * Created by Hoang on 10/05/2017.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private int mapType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getTypeMap();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void getTypeMap() {
        Intent intent = getIntent();
        mapType = intent.getIntExtra(Const.KEY_TYPE_MAP, -1);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        switch (mapType) {
            case Const.MAP_TYPE_MORE_LOCATION:
                LatLng sydney = new LatLng(-33.86785, 151.20732);
                mMap.addMarker(new MarkerOptions().position(sydney).title("Sydney"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(-34.88333, 150.6)).title("Nowra"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(-34.43333, 150.88333)).title("Wollongong"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 8));
                break;
            case Const.MAP_TYPE_MY_LOCATION:
                LatLng sydneyA = new LatLng(-33.86785, 151.20732);
                mMap.addMarker(new MarkerOptions().position(sydneyA).title("Sydney"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydneyA, 13));
                break;
        }
    }
}
