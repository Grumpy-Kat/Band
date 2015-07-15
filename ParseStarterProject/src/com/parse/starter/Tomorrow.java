package com.parse.starter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by kai on 10/7/15.
 */
public class Tomorrow extends Activity {

    int counter=0;


    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    // Get the date today using Calendar object.
    Date today = Calendar.getInstance().getTime();
    // Using DateFormat format method we can create a string
// representation of a date with the defined format.
    String reportDate = df.format(today);











    List<ParseObject> message = new ArrayList<ParseObject>();
    ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tomorrow);
       // ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();

        try {
            c.setTime(sdf.parse(reportDate));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }


        c.add(Calendar.DATE, 1);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
        final String outputDate = sdf1.format(c.getTime());



        final ParseUser currentUser = ParseUser.getCurrentUser();

        final String struser = currentUser.getUsername().toString();
        final ListView list = (ListView) findViewById(R.id.lvtomorrow);

        final String[] values = new String[] { "7am-8am Slot","8am-9am Slot","9am-10am Slot","10am-11am Slot","11am-12pm Slot",
        "12pm-1pm Slot","1pm-2pm Slot","2pm-3pm Slot","3pm-4pm Slot","4pm-5pm Slot","5pm-6pm Slot","6pm-7pm Slot"}; // You have the necessary data to bind the list.

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
                   final ParseObject gameScore = new ParseObject("dbBand");

                    query.whereEqualTo("bookedSlots", "7am-8am");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size()>0) {
                                Log.d("score", "Retrieved " + scoreList.size() + " scores");
                                Toast.makeText(getApplicationContext(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Success"+outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "7am-8am");
                                gameScore.saveInBackground();

                            }
                        }
                    });
                }





                if(position==1){
                  //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    final ParseObject gameScore = new ParseObject("dbBand");
                    query.whereEqualTo("bookedSlots", "8am-9am");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size()>0) {
                                Toast.makeText(getApplicationContext(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Success"+outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "8am-9am");
                                gameScore.saveInBackground();

                            }
                        }
                    });

                }
                if(position==2){
                  //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                   final ParseObject gameScore = new ParseObject("dbBand");
                    query.whereEqualTo("bookedSlots", "9am-10am");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size()>0) {
                                Toast.makeText(getApplicationContext(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Success"+outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "9am-10am");
                                gameScore.saveInBackground();

                            }
                        }
                    });


                }
                if(position==3){
                    //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    final ParseObject gameScore = new ParseObject("dbBand");
                    query.whereEqualTo("bookedSlots", "10am-11am");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size()>0) {
                                Toast.makeText(getApplicationContext(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Success"+outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "10am-11am");
                                gameScore.saveInBackground();

                            }
                        }
                    });

                }
                if(position==4){
                    //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    final ParseObject gameScore = new ParseObject("dbBand");
                    query.whereEqualTo("bookedSlots", "11am-12pm");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size()>0) {
                                Toast.makeText(getApplicationContext(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Success"+outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "11am-12pm");
                                gameScore.saveInBackground();

                            }
                        }
                    });

                }
                if(position==5){
                    //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    final ParseObject gameScore = new ParseObject("dbBand");
                    query.whereEqualTo("bookedSlots", "12pm-1pm");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size()>0) {
                                Toast.makeText(getApplicationContext(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Success"+outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "12pm-1pm");
                                gameScore.saveInBackground();

                            }
                        }
                    });

                }

                if(position==6){
                    //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    final ParseObject gameScore = new ParseObject("dbBand");
                    query.whereEqualTo("bookedSlots", "1pm-2pm");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size()>0) {
                                Toast.makeText(getApplicationContext(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Success"+outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "1pm-2pm");
                                gameScore.saveInBackground();

                            }
                        }
                    });

                }

                if(position==7){
                    //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    final ParseObject gameScore = new ParseObject("dbBand");
                    query.whereEqualTo("bookedSlots", "2pm-3pm");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size()>0) {
                                Toast.makeText(getApplicationContext(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Success"+outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "2pm-3pm");
                                gameScore.saveInBackground();

                            }
                        }
                    });

                }

                if(position==8){
                    //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    final ParseObject gameScore = new ParseObject("dbBand");
                    query.whereEqualTo("bookedSlots", "3pm-4pm");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size()>0) {
                                Toast.makeText(getApplicationContext(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Success"+outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "3pm-4pm");
                                gameScore.saveInBackground();

                            }
                        }
                    });

                }
                if(position==9){
                    //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    final ParseObject gameScore = new ParseObject("dbBand");
                    query.whereEqualTo("bookedSlots", "4pm-5pm");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size()>0) {
                                Toast.makeText(getApplicationContext(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Success"+outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "4pm-5pm");
                                gameScore.saveInBackground();

                            }
                        }
                    });

                }

                if(position==10){
                    //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    final ParseObject gameScore = new ParseObject("dbBand");
                    query.whereEqualTo("bookedSlots", "5pm-6pm");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size()>0) {
                                Toast.makeText(getApplicationContext(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Success"+outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "5pm-6pm");
                                gameScore.saveInBackground();

                            }
                        }
                    });

                }
                if(position==11){
                    //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    final ParseObject gameScore = new ParseObject("dbBand");
                    query.whereEqualTo("bookedSlots", "6pm-7pm");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size()>0) {
                                Toast.makeText(getApplicationContext(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Success"+outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "6pm-7pm");
                                gameScore.saveInBackground();

                            }
                        }
                    });

                }
            }
        });
    }

}
