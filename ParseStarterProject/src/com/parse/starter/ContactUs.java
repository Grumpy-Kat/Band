package com.parse.starter;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Chirag Shenoy on 28-Jul-15.
 */
public class ContactUs extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactus);

        FancyButton call = (FancyButton) findViewById(R.id.call);
        FancyButton mail = (FancyButton) findViewById(R.id.mail);
        FancyButton map = (FancyButton) findViewById(R.id.map);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:9035109000"));
                startActivity(callIntent);
            }
        });

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "progressivejuice@gmail.com", null));
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });


        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // check if Google Maps is supported on given device
                    String uri = String.format(Locale.ENGLISH, "geo:0,0?q=937,23rd Main, 4th T Block, Jayanagar ,bangalore");
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Google Maps is not installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        FancyButton facebookLoginBtn = (FancyButton) findViewById(R.id.fb);
//        facebookLoginBtn.setText("Like Us on Facebook");
//        facebookLoginBtn.setBackgroundColor(Color.parseColor("#3b5998"));
//        facebookLoginBtn.setFocusBackgroundColor(Color.parseColor("#5474b8"));
//        facebookLoginBtn.setTextSize(17);
//        facebookLoginBtn.setRadius(5);
//        facebookLoginBtn.setIconResource("\uf082");
//        facebookLoginBtn.setFontIconSize(30);
//
//        facebookLoginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent;
//                String pageId = "1623204697903495";
//                try {
//                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + pageId));
//                } catch (Exception e) {
//                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + pageId));
//                }
//                startActivity(intent);
//            }
//        });

    }
}
