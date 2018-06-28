package com.developer.yoshi1125hisa.healthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class BankActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);


    }

    public void pull(View v){
        Toast.makeText(this, "引き出しが完了しました！", Toast.LENGTH_SHORT).show();
    Intent intent = new Intent(this,ProfileActivity.class);
    startActivity(intent);

    }
}
