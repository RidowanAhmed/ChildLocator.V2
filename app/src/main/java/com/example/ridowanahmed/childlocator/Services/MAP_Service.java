package com.example.ridowanahmed.childlocator.Services;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.ridowanahmed.childlocator.Model.ChildInformation;
import com.example.ridowanahmed.childlocator.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


//Location Callback
public class MAP_Service extends Service implements ConnectionCallbacks, OnConnectionFailedListener {
    private static final String TAG = "MAP_Services";
    private static final int LOCATION_INTERVAL = 1000;
    private static final float LOCATION_DISTANCE = 0.3f;

    private SharedPreferences mSharedPreferences;
    private DatabaseReference databaseReference;
    private String childName, phoneNumber;

    private class locationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            saveData(location);
        }
    }
    GoogleApiClient client = null;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mSharedPreferences = MAP_Service.this.getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
        childName = mSharedPreferences.getString(getString(R.string.CHILD_NAME), "");
        phoneNumber = mSharedPreferences.getString(getString(R.string.CHILD_GIVE_NUMBER), "");
        databaseReference = FirebaseDatabase.getInstance().getReference(phoneNumber).child(childName);

        client = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        client.connect();

        return START_STICKY;


    }

    @Override
    public void onDestroy() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onConnected(Bundle arg0) {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(LOCATION_INTERVAL);
        mLocationRequest.setSmallestDisplacement(LOCATION_DISTANCE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(client, mLocationRequest, new locationListener());

    }

    @Override
    public void onConnectionSuspended(int arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onConnectionFailed(ConnectionResult arg0) {
    }
    private void saveData(Location location) {
        if(databaseReference == null) {
            return;
        }
        ChildInformation childInformation = new ChildInformation(childName, location.getLatitude(), location.getLongitude(), location.getTime());
        Log.e(TAG, childInformation.toString());
        databaseReference.setValue(childInformation);
    }
}