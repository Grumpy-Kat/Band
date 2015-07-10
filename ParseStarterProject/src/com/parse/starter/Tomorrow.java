package com.parse.starter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kai on 10/7/15.
 */
public class Tomorrow extends Activity {

    int counter=0;
    Date  todaysDate = new Date();
    ParseObject gameScore = new ParseObject("dbBand");
    List<ParseObject> message = new ArrayList<ParseObject>();
    ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");

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
        try {
            message = query.find();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(position==0) {

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    query.whereEqualTo("bookedSlots", "7am-8am");
                    query.whereEqualTo("bookedDate", todaysDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size()>0) {
                                Log.d("score", "Retrieved " + scoreList.size() + " scores");
                                Toast.makeText(getApplicationContext(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", todaysDate);
                                gameScore.put("bookedSlots", "7am-8am");
                                gameScore.saveInBackground();

                            }
                        }
                    });







                }





                if(position==1){
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    query.whereEqualTo("bookedSlots", "8am-9am");
                    query.whereEqualTo("bookedDate", todaysDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size()>0) {
                                Toast.makeText(getApplicationContext(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", todaysDate);
                                gameScore.put("bookedSlots", "8am-9am");
                                gameScore.saveInBackground();

                            }
                        }
                    });

                }
                if(position==2){
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    query.whereEqualTo("bookedSlots", "9am-10am");
                    query.whereEqualTo("bookedDate", todaysDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size()>0) {
                                Toast.makeText(getApplicationContext(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", todaysDate);
                                gameScore.put("bookedSlots", "9am-10am");
                                gameScore.saveInBackground();

                            }
                        }
                    });


                }
            }
        });
    }

}
