package com.example.womendemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class help extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),mainpage.class));
    }
}
