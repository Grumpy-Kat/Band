package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;

import java.util.ArrayList;

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


        FZTickerText ticker1 = (FZTickerText) findViewById(R.id.ticker1);

        ArrayList<char[]> phrases = new ArrayList<char[]>();
//        phrases.add(new String("We have a jamming party coming next Sunday !").toCharArray());
        String s = new String();
        s = "                                                                                    ";
        s = s + "We have a jamming party coming next Sunday !";
        phrases.add(s.toCharArray());


        ticker1.ANIMATION_DELAY = 75;
        ticker1.setPhrases(phrases);
        ticker1.animationStart();

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
                startActivity(new Intent(getApplicationContext(), BookedSlots.class));
            }
        });


    }

}
