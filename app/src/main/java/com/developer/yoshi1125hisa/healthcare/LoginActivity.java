package com.developer.yoshi1125hisa.healthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void move(View v){
        Intent intent = new Intent(this,BankActivity.class);
        startActivity(intent);
    }

    public void move2(View v){
        Intent intent = new Intent(this,HealthActivity.class);
        startActivity(intent);
    }
}
