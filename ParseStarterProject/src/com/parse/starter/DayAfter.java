package com.parse.starter;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by kai on 16/7/15.
 */
public class DayAfter extends FragmentActivity {
    RowData ob;

    ArrayList<String> array_list_posts;
    ArrayList<RowData> rowDataList;
    ArrayAdapter<String> adapter;
    ListView listView;
    TextView tvslot;

    Boolean[] array = new Boolean[12];
    final String[] values = new String[] { "7am-8am","8am-9am","9am-10am","10am-11am","11am-12pm",
            "12pm-1pm","1pm-2pm","2pm-3pm","3pm-4pm","4pm-5pm","5pm-6pm","6pm-7pm"};
    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    // Get the date today using Calendar object.
    Date today = Calendar.getInstance().getTime();
    // Using DateFormat format method we can create a string
// representation of a date with the defined format.
    String reportDate = df.format(today);
    String outputDate="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.posts);

        listView = (ListView)findViewById(R.id.list);
        tvslot=(TextView)findViewById(R.id.tvslot);
        Arrays.fill(array, Boolean.FALSE);


        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();

        try {
            c.setTime(sdf.parse(reportDate));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }


        c.add(Calendar.DATE, 1);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
        outputDate = sdf1.format(c.getTime());



        final ParseUser currentUser = ParseUser.getCurrentUser();

        final String struser = currentUser.getUsername().toString();
        new Read().execute();

    }

    public class Read extends AsyncTask<String, Integer, String> {
        ProgressDialog pDialog;

        @Override
        protected String doInBackground(String... params) {

              //  final_json_array = get_entire_json();
                try {


                    rowDataList = new ArrayList<RowData>(12);
                    for(int i =0;i<12;i++){
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");

                    query.whereEqualTo("bookedSlots", values[i]);
                    query.whereEqualTo("bookedDate", outputDate);
                        final int finalI = i;
                        query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size() > 0) {
                                ob = new RowData();
                                ob.setSlot(values[finalI]);
                                ob.setTex("Busy");
                                Log.d(ob.getTex(), "GOT TEXT BOYSA ");
                                Log.d(ob.getSlot(), "GOT TEXT BOYSA ");
                                rowDataList.add(ob);

                                CustomArrayAdapter dataAdapter = new CustomArrayAdapter(DayAfter.this, R.id.label, rowDataList);
                                listView.setAdapter(dataAdapter);

                            } else {
                                ob = new RowData();
                                ob.setSlot(values[finalI]);
                                ob.setTex("available");
                                Log.d(ob.getTex(), "GOT TEXT BOYSA ");
                                Log.d(ob.getSlot(), "GOT TEXT BOYSA ");
                                rowDataList.add(ob);
                                CustomArrayAdapter dataAdapter = new CustomArrayAdapter(DayAfter.this, R.id.label, rowDataList);
                                listView.setAdapter(dataAdapter);


                            }
                        }
                    });

                }



                    CustomArrayAdapter dataAdapter = new CustomArrayAdapter(DayAfter.this, R.id.label, rowDataList);
                    listView.setAdapter(dataAdapter);






                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;




        }

        @Override
        protected void onPreExecute() {
            // Showing progress dialog before sending http request

            /*pDialog = new ProgressDialog(DayAfter.this);
            pDialog.setMessage("Please wait..");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
           // pDialog.show();*/
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //pDialog.dismiss();



        }
    }
}