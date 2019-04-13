package com.example.womendemo;


import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class mainpage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button redbtn = (Button) findViewById(R.id.redbtn);
        Button greenbtn = (Button) findViewById(R.id.greenbtn);
        Button orangebtn = (Button) findViewById(R.id.orengebtn);

        redbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player = MediaPlayer.create(mainpage.this,R.raw.siren);
                player.start();
                Toast.makeText(mainpage.this, "Pressed Red Panic Alert", Toast.LENGTH_SHORT).show();
            }
        });
        greenbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player = MediaPlayer.create(mainpage.this,R.raw.siren);
                player.start();
                Toast.makeText(mainpage.this, "Pressed Green Status Update", Toast.LENGTH_SHORT).show();
            }
        });
        orangebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player = MediaPlayer.create(mainpage.this,R.raw.siren);
                player.start();
                Toast.makeText(mainpage.this, "Pressed Orange Cautious", Toast.LENGTH_SHORT).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainpage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            //noinspection SimplifiableIfStatement
            case R.id.siren: {
                player = MediaPlayer.create(this,R.raw.siren);
                player.setLooping(true);
                player.start();
            break;
            }
            case R.id.setting: {
                Intent i = new Intent(this, gensetting.class);
                startActivity(i);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.personal:
                Toast.makeText(this, "Personal details", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(mainpage.this,personal_detail.class);
                startActivity(intent1);
                break;

            case R.id.relative:
                Toast.makeText(this, "Relative details", Toast.LENGTH_SHORT).show();
                Intent inten = new Intent(mainpage.this,Relative_detail.class);
                startActivity(inten);
                break;

            case  R.id.setting:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(mainpage.this, gensetting.class);
                startActivity(i);
                break;

            case R.id.help:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/panktisugandhi/Women-Safety/blob/master/help.txt"));
                startActivity(intent);
                break;

            case R.id.feedback:
                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL, new String[]{"panktisugandhi143@gmail.com"});
                Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                Email.putExtra(Intent.EXTRA_TEXT, "Dear ...," + "");
                startActivity(Intent.createChooser(Email, "Send Feedback:"));
                break;

            case  R.id.nav_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            case R.id.rate:
                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/panktisugandhi"));
                startActivity(in);
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
