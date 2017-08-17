package com.example.ridowanahmed.childlocator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.ridowanahmed.childlocator.Dashboard.ChildDashboard;
import com.example.ridowanahmed.childlocator.Dashboard.ParentDashboard;
import com.example.ridowanahmed.childlocator.Login.ChildLoginActivity;
import com.example.ridowanahmed.childlocator.Login.ParentLoginActivity;
import com.example.ridowanahmed.childlocator.Map.ChildMap;
import com.example.ridowanahmed.childlocator.Map.ParentMap;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private FirebaseAuth mFirebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAuth = FirebaseAuth.getInstance();
        Log.e(TAG, "Auth " + mFirebaseAuth.toString());

    }
    public void parentLoginBtn(View view){
        SharedPreferences mSharedPreferences = MainActivity.this.getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
        final String phoneNumber = mSharedPreferences.getString(getString(R.string.PARENT_GIVE_NUMBER), "");
        if(phoneNumber.length() == 11) {
            startActivity(new Intent(MainActivity.this,ParentDashboard.class));
            return;
        } else if (mFirebaseAuth.getCurrentUser() != null){
            mFirebaseAuth.signOut();
            Log.e(TAG, "Starting ParenLoginActivity");
        }
        startActivity(new Intent(MainActivity.this,ParentLoginActivity.class));
    }

    public void childLoginBtn(View view){
        SharedPreferences mSharedPreferences = MainActivity.this.getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
        final String phoneNumber = mSharedPreferences.getString(getString(R.string.CHILD_GIVE_NUMBER), "");
        if(phoneNumber.length() == 11) {
            startActivity(new Intent(MainActivity.this,ChildDashboard.class));
            return;
        } else if (mFirebaseAuth.getCurrentUser() != null){
            mFirebaseAuth.signOut();
            Log.e(TAG, "Starting ChildLoginActivity");
        }
        startActivity(new Intent(MainActivity.this,ChildLoginActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
