package com.parse.starter;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by kai on 16/7/15.
 */
public class Tomorrow extends FragmentActivity {
    RowData ob;
    CustomArrayAdapter dataadapter;

    int yamaha, guitar6, fender, bass5, ejam, ejamMix;
    ArrayList<RowData> rowDataList;
    ListView listView;
    TextView tvslot;

    Boolean[] array = new Boolean[12];

    final String[] values = new String[]{"07am-08am", "08am-09am", "09am-10am", "10am-11am", "11am-12pm",
            "12pm-1pm", "1pm-2pm", "2pm-3pm", "3pm-4pm", "4pm-5pm", "5pm-6pm", "6pm-7pm", "7pm-8pm", "8pm-9pm", "9pm-10pm"};


    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    // Get the date today using Calendar object.
    Date today = Calendar.getInstance().getTime();

    // Using DateFormat format method we can create a string
// representation of a date with the defined format.

    String reportDate = df.format(today);
    String outputDate = "";


    CheckBox cb;
    Button confirmBook;
    TextView date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(this);

        setContentView(R.layout.postsactivity);
        Bundle bundle = getIntent().getExtras();
        cb = (CheckBox) findViewById(R.id.check);
        yamaha = bundle.getInt("Yamaha");


        guitar6 = bundle.getInt("6Guitar");
        fender = bundle.getInt("FenderGuitar");
        bass5 = bundle.getInt("5Bass");
        ejam = bundle.getInt("Ejam");
        ejamMix = bundle.getInt("EjamSeparate");
        confirmBook = (Button) findViewById(R.id.confirmBook);
        listView = (ListView) findViewById(R.id.list);
        tvslot = (TextView) findViewById(R.id.tvslot);
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
        date = (TextView) findViewById(R.id.date);

        date.setText(outputDate);


        final ParseUser currentUser = ParseUser.getCurrentUser();
        final String struser = currentUser.getUsername().toString();

        confirmBook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final ParseObject[] p = new ParseObject[25];

                for (int i = 0; i < 25; i++) {
                    if (dataadapter.mCheckStates.get(i) == true) {

                        p[i] = new ParseObject("dbBand");
                        final int finalI = i;

                        Toast.makeText(getApplicationContext(),
                                "Slot Successfully Booked onfully Booked Slot on " + outputDate,
                                Toast.LENGTH_SHORT).show();
                        p[i].put("username", currentUser);
                        p[i].put("bookedDate", outputDate);
                        p[i].put("bookedSlots", values[finalI]);
                        p[i].put("yamaha", yamaha);
                        p[i].put("guitar6", guitar6);
                        p[i].put("fender", fender);
                        p[i].put("bass5", bass5);
                        p[i].put("ejam", ejam);
                        p[i].put("ejamMix", ejamMix);
                        Toast.makeText(Tomorrow.this, values[finalI], Toast.LENGTH_LONG).show();
                        p[i].saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {

                            }
                        });


                    }

                }



                finish();
                startActivity(new Intent(getApplicationContext(), BookedSlots.class));

            }

        });


        new Read().execute();


    }

    public class Read extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                rowDataList = new ArrayList<RowData>(15);
                for (int i = 0; i < 15; i++) {
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");

                    query.whereEqualTo("bookedSlots", values[i]);
                    query.whereEqualTo("bookedDate", outputDate);
                    final int finalI = i;
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size() > 0) {
                                ob = new RowData();
                                ob.setSlot(values[finalI]);
                                ob.setTex("Slot Busy");
                                rowDataList.add(ob);


                                CustomArrayAdapter dataAdapter = new CustomArrayAdapter(Tomorrow.this, R.id.label, rowDataList);
                                dataAdapter.sort(new Comparator<RowData>() {
                                    public int compare(RowData arg0, RowData arg1) {
                                        return arg0.getSlot().compareTo(arg1.getSlot());
                                    }
                                });


                                dataAdapter.notifyDataSetChanged();
                                listView.setAdapter(dataAdapter);

                            } else {
                                ob = new RowData();
                                ob.setSlot(values[finalI]);
                                ob.setTex("Slot Available");

                                rowDataList.add(ob);
                                dataadapter = new CustomArrayAdapter(Tomorrow.this, R.id.label, rowDataList);
                                dataadapter.sort(new Comparator<RowData>() {
                                    public int compare(RowData arg0, RowData arg1) {
                                        return arg0.getSlot().compareTo(arg1.getSlot());
                                    }
                                });


                                dataadapter.notifyDataSetChanged();
                                listView.setAdapter(dataadapter);


                            }
                        }
                    });

                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;


        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }


}