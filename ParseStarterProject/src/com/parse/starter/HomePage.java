package com.parse.starter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Chirag Shenoy on 28-Jul-15.
 */
public class HomePage extends Activity {

    FancyButton booknow;
    FancyButton tariff;
    FancyButton contactus;
    FancyButton bookedslots;
    String struser = "";

    FancyButton logout;

    TextView name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        String s = "";

        final FZTickerText ticker1 = (FZTickerText) findViewById(R.id.ticker1);

        final ArrayList<char[]> phrases = new ArrayList<char[]>();
//        phrases.add(new String("We have a jamming party coming next Sunday !").toCharArray());

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Message");
        query.whereNotEqualTo("message", "bobo");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                if (e == null) {

                    String s = "" + list.get(0).get("message");

                    if (s.length() < 60) {
                        s += "                                                                               ";
                    }
                    phrases.add(s.toCharArray());
                    ticker1.ANIMATION_DELAY = 75;
                    ticker1.setPhrases(phrases);
                    ticker1.animationStart();
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }

        });


        s = "                                                                                 ";


//        s = s + "We have a jamming party coming next Sunday !";


//        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");

        booknow = (FancyButton) findViewById(R.id.booknow);
        tariff = (FancyButton) findViewById(R.id.tariff);
        contactus = (FancyButton) findViewById(R.id.contactus);
        bookedslots = (FancyButton) findViewById(R.id.bookedslots);
        name = (TextView) findViewById(R.id.name);
        logout = (FancyButton) findViewById(R.id.logout);

//        name.setTypeface(typefaceSpan);


        try {
            ParseUser currentUser = ParseUser.getCurrentUser();
            struser = currentUser.getUsername().toString();
            name.setText("Welcome " + struser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (struser == "" || struser == null) {
            name.setText("Welcome");
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                finish();
            }
        });


        booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Welcome.class));
            }
        });

        tariff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Tariff.class));

            }
        });

        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ContactUs.class));

            }
        });

        bookedslots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable())
                    startActivity(new Intent(getApplicationContext(), BookedSlots.class));
                else {
                    Toast.makeText(
                            getApplicationContext(),
                            "Connected to the Internet to Proceed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
