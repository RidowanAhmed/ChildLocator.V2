package com.example.ridowanahmed.childlocator.Dashboard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ridowanahmed.childlocator.Map.ChildMap;
import com.example.ridowanahmed.childlocator.R;
import com.example.ridowanahmed.childlocator.Services.*;

public class ChildDashboard extends AppCompatActivity {
    private final String TAG = "ChildDashboard";
    private Button button_showLocation;
    private String phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.ridowanahmed.childlocator.R.layout.activity_child_dashboard);

        SharedPreferences mSharedPreferences = ChildDashboard.this.getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
        phoneNumber = mSharedPreferences.getString(getString(R.string.CHILD_GIVE_NUMBER), "");
        Log.e(TAG, "Phone is " + phoneNumber);

        button_showLocation = (Button) findViewById(R.id.button_showLocation);
        button_showLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChildDashboard.this, ChildMap.class));
            }
        });


        Intent intent = new Intent(ChildDashboard.this, MAP_Service.class);
        startService(intent);
        Log.e(TAG, "Service Started");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "Destroyed " + phoneNumber);
    }
}
