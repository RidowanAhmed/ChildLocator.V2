package com.example.ridowanahmed.childlocator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ridowanahmed.childlocator.Dashboard.ChildDashboard;
import com.example.ridowanahmed.childlocator.Dashboard.ParentDashboard;
import com.example.ridowanahmed.childlocator.Login.ChildLoginActivity;
import com.example.ridowanahmed.childlocator.Login.ParentLoginActivity;
import com.example.ridowanahmed.childlocator.Map.ChildMap;
import com.example.ridowanahmed.childlocator.Map.ParentMap;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void parentLoginBtn(View view){
        startActivity(new Intent(MainActivity.this,ParentLoginActivity.class));
    }

    public void childLoginBtn(View view){
        startActivity(new Intent(MainActivity.this,ChildLoginActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
