package com.developer.yoshi1125hisa.healthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void sleep(View v){
        Intent intent = new Intent(this,SleepActivity.class);
        startActivity(intent);
    }
    public void walk(View v){
        Intent intent = new Intent(this,HealthActivity.class);
        startActivity(intent);
    }
    public void logout(View v){
        Intent intent = new Intent(this,LogoutActivity.class);
        startActivity(intent);
    }
}
