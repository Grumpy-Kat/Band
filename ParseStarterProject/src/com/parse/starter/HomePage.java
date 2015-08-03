package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Chirag Shenoy on 28-Jul-15.
 */
public class HomePage extends Activity {

    Button booknow;
    Button tariff;
    Button contactus;
    Button bookedslots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        booknow = (Button) findViewById(R.id.booknow);
        tariff = (Button) findViewById(R.id.tariff);
        contactus = (Button) findViewById(R.id.contactus);
        bookedslots = (Button) findViewById(R.id.bookedslots);

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
