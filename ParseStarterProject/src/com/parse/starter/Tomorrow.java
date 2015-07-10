package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

/**
 * Created by kai on 10/7/15.
 */
public class Tomorrow extends Activity {

    Date  todaysDate = new Date();
    ParseObject gameScore = new ParseObject("dbBand");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tomorrow);

        final ParseUser currentUser = ParseUser.getCurrentUser();

        final String struser = currentUser.getUsername().toString();
        final ListView list = (ListView) findViewById(R.id.lvtomorrow);

        final String[] values = new String[] { "slot 1","slot 2","slot3" }; // You have the necessary data to bind the list.

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values); // You have set     the previous array to an adapter that can be now setted to a list.

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(position==0){

                    gameScore.put("username", currentUser);
                    gameScore.put("bookedDate", todaysDate);
                    gameScore.put("bookedSlots", "7am-8am");
                    gameScore.saveInBackground();


                }
                if(position==1){


                    gameScore.put("username", currentUser);
                    gameScore.put("bookedDate", todaysDate);
                    gameScore.put("bookedSlots", "8am-9am");
                    gameScore.saveInBackground();

                }
                if(position==2){
                    gameScore.put("username", currentUser);
                    gameScore.put("bookedDate", todaysDate);
                    gameScore.put("bookedSlots", "9am-10eam");
                    gameScore.saveInBackground();
                }


            }
        });
    }
}
