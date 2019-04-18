package com.example.womendemo;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.util.Log;
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
import android.widget.TextView;
import android.widget.Toast;

public class mainpage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,LocationListener {

    MediaPlayer player;
    String FirstNumber;
    String SecondNumber, ThirdNumber, FourthNumber, FifthNumber;
    String FirstMail, SecondMail, ThirdMail, FourthMail, FifthMail;
    TextView tv1, tv2;
    String text_1, text_2;
    LocationManager locationManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      //  Button redbtn = (Button) findViewById(R.id.redbtn);
        Button greenbtn = (Button) findViewById(R.id.greenbtn);
        Button orangebtn = (Button) findViewById(R.id.orengebtn);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        FirstMail = getIntent().getStringExtra("FirstMail");
        SecondMail = getIntent().getStringExtra("SecondMail");
        ThirdMail = getIntent().getStringExtra("ThirdMail");
        FourthMail = getIntent().getStringExtra("FourthMail");
        FifthMail = getIntent().getStringExtra("FifthMail");

        Log.e("Email Addresses", "" + FirstMail + "," + SecondMail + "," + ThirdMail + "," + FourthMail + "," + FifthMail);

        FirstNumber = getIntent().getStringExtra("First Number");
        SecondNumber = getIntent().getStringExtra("Second Number");
        ThirdNumber = getIntent().getStringExtra("Third Number");
        FourthNumber = getIntent().getStringExtra("Fourth Number");
        FifthNumber = getIntent().getStringExtra("Fifth Number");

//        redbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                player = MediaPlayer.create(mainpage.this,R.raw.siren);
//                player.start();
//                Toast.makeText(mainpage.this, "Pressed Red Panic Alert", Toast.LENGTH_SHORT).show();
//            }
//        });
//        greenbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                player = MediaPlayer.create(mainpage.this,R.raw.siren);
//                player.start();
//                Toast.makeText(mainpage.this, "Pressed Green Status Update", Toast.LENGTH_SHORT).show();
//            }
//        });
//        orangebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                player = MediaPlayer.create(mainpage.this,R.raw.siren);
//                player.start();
//                Toast.makeText(mainpage.this, "Pressed Orange Cautious", Toast.LENGTH_SHORT).show();
//            }
//        });

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
                Intent inten = new Intent(mainpage.this, Relativedetail.class);
                startActivity(inten);
                break;

            case  R.id.setting:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(mainpage.this, gensetting.class);
                startActivity(i);
                break;

            case R.id.help:
                    Intent intent = new Intent(mainpage.this,help.class);
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/panktisugandhi/Women-Safety/blob/master/help.txt"));
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
    public void call(View v) {

        final CharSequence[] options = {"Call Family Members", "Call Police", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(mainpage.this);
        builder.setTitle("Emergency Call To!");
        player = MediaPlayer.create(mainpage.this,R.raw.siren);
        player.start();
        Toast.makeText(mainpage.this, "Pressed Red Panic Alert", Toast.LENGTH_SHORT).show();

        builder.setIcon(R.drawable.siren);
        builder.setItems(options, new DialogInterface.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Call Family Members")) {

                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:8469797994"));
                    if (checkSelfPermission(Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(mainpage.this, "Plz grant phone Permission", Toast.LENGTH_SHORT).show();
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},0);
                        return;
                    }
                    startActivity(intent);

                } else if (options[item].equals("Call Police")) {

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:100"));
                    if (checkSelfPermission(Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(mainpage.this, "Plz grant phone Permission", Toast.LENGTH_SHORT).show();
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},0);
                        return;
                    }
                    startActivity(callIntent);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    public void message(View v) {

        player = MediaPlayer.create(mainpage.this,R.raw.siren);
        player.start();
        Toast.makeText(mainpage.this, "Pressed Green Status Update", Toast.LENGTH_SHORT).show();
                getLocation();

        //        text_1 = tv1.getText().toString();
//        text_2 = tv2.getText().toString();
//        Intent intent = getIntent();
//
////        SmsManager smsManager = SmsManager.getDefault();
////        smsManager.sendTextMessage("9974896992",null,"Hey! I am in Danger, My location is",null,null);
////        Toast.makeText(this, "Message Sent......!", Toast.LENGTH_SHORT).show();
//        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
//        smsIntent.setType("vnd.android-dir/mms-sms");
//        smsIntent.putExtra("address", "" + FirstNumber);
//        //smsIntent.putExtra("address","" + SecondNumber);
//        Log.e("Phone Numbers", "" + FirstNumber);
//        smsIntent.putExtra("sms_body", "Hey! I am in Danger, My location is  ");
//        startActivity(smsIntent);
    }

    public void sendemail(View v) {
       // Intent i = getIntent();
        text_1 = tv1.getText().toString();
        text_2 = tv2.getText().toString();
        player = MediaPlayer.create(mainpage.this,R.raw.siren);
        player.start();
        //Location location = null;
      //  String loc = "Hey! I am in Danger, My location is: http://maps.google.com/maps?saddr="+location.getLatitude()+","+location.getLongitude();
        Toast.makeText(mainpage.this, "Pressed Orange Cautious", Toast.LENGTH_SHORT).show();
        Intent intentemail = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
        intentemail.setType("text/email");
        Log.e("Mail Data", "" + FirstMail + "" + SecondMail + "" + ThirdMail + "" + FourthMail + "" + FifthMail);
        intentemail.putExtra(Intent.EXTRA_EMAIL, new String[]{FirstMail, SecondMail, ThirdMail, FourthMail, FifthMail});
        intentemail.putExtra(Intent.EXTRA_SUBJECT, "Please help me ");
        intentemail.putExtra(Intent.EXTRA_TEXT, "I am Unsafe.My Location is ");
        startActivity(intentemail);


        try {
            startActivity(Intent.createChooser(intentemail, "Choose an email client from..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(mainpage.this, "No email client installed.", Toast.LENGTH_LONG).show();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
  public void getLocation(){
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Please grant Location permission", Toast.LENGTH_SHORT).show();
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);

            return;
        }
        try {
            locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,500, (LocationListener) this);
        }catch (SecurityException e){
            Toast.makeText(this, "Unable to find location", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    //Methods of LocationListener
    @Override
    public void onLocationChanged(Location location) {
        //txtMsg.setText("I'm at: "+location.getLatitude()+","+location.getLongitude());

        //number = txtNum.getText().toString().trim();
        String loc = "Hey! I am in Danger, My location is: http://maps.google.com/maps?saddr="+location.getLatitude()+","+location.getLongitude();
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", "" + FirstNumber);
        //smsIntent.putExtra("address","" + SecondNumber);
        Log.e("Phone Numbers", "" + FirstNumber);
        smsIntent.putExtra("sms_body", loc);
        startActivity(smsIntent);
        //        SmsManager smsManager = SmsManager.getDefault();
//        smsManager.sendTextMessage(FirstNumber, null, loc, null, null);
        Toast.makeText(this, "Location Sent", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
