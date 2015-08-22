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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by kai on 16/7/15.
 */
public class ThirdDay extends FragmentActivity {
    RowData ob;
    ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
    int yamaha, guitar6, fender, bass5, ejam, ejamMix;
    ArrayList<String> array_list_posts;
    ArrayList<RowData> rowDataList;
    ArrayAdapter<String> adapter;
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

    TextView date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(this);

        setContentView(R.layout.postsactivity);
        Bundle bundle = getIntent().getExtras();
        yamaha = bundle.getInt("Yamaha");

        guitar6 = bundle.getInt("6Guitar");
        fender = bundle.getInt("FenderGuitar");
        bass5 = bundle.getInt("5Bass");
        ejam = bundle.getInt("Ejam");
        ejamMix = bundle.getInt("EjamSeparate");


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


        c.add(Calendar.DATE, 3);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
        outputDate = sdf1.format(c.getTime());
        date = (TextView) findViewById(R.id.date);

        date.setText(outputDate);


        final ParseUser currentUser = ParseUser.getCurrentUser();
        final String struser = currentUser.getUsername().toString();
        new Read().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                dialogBuilder
                        .withTitle("Confirmation")                                  //.withTitle(null)  no title
                        .withTitleColor("#FFFFFF")                                  //def
                        .withDividerColor("#11000000")                              //def
                        .withMessage("Are you sure you want to book this slot ?")                     //.withMessage(null)  no Msg
                        .withMessageColor("#FF000000")                              //def  | withMessageColor(int resid)
                        .withDialogColor("#FFD4D60C")                               //def  | withDialogColor(int resid)
                        .withDuration(700)                                          //def
                        .withButton1Text("Yes")                                      //def gone
                        .withButton2Text("Back")                                  //def gone
                        .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                        .setButton1Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                Toast.makeText(v.getContext(), "Booked slot!", Toast.LENGTH_SHORT).show();
                                try {
                                    if (position == 0) {
                                        final ParseObject gameScore = new ParseObject("dbBand");

                                        query.whereEqualTo("bookedSlots", "07am-08am");
                                        query.whereEqualTo("bookedDate", outputDate);
                                        query.findInBackground(new FindCallback<ParseObject>() {
                                            public void done(List<ParseObject> scoreList, ParseException e) {
                                                if (scoreList.size() > 0) {
                                                    Log.d("score", "Retrieved " + scoreList.size() + " scores");
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot is busy",
                                                            Toast.LENGTH_SHORT).show();

                                                } else {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Successfully Booked Slot on " + outputDate,
                                                            Toast.LENGTH_SHORT).show();
                                                    gameScore.put("username", currentUser);
                                                    gameScore.put("bookedDate", outputDate);
                                                    gameScore.put("bookedSlots", "07am-08am");
                                                    gameScore.put("yamaha", yamaha);
                                                    gameScore.put("guitar6", guitar6);
                                                    gameScore.put("fender", fender);
                                                    gameScore.put("bass5", bass5);
                                                    gameScore.put("ejam", ejam);
                                                    gameScore.put("ejamMix", ejamMix);
                                                    gameScore.saveInBackground();

                                                    finish();
                                                    startActivity(getIntent());

                                                }
                                            }
                                        });
                                    }


                                    if (position == 1) {
                                        //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                                        final ParseObject gameScore = new ParseObject("dbBand");
                                        query.whereEqualTo("bookedSlots", "08am-09am");
                                        query.whereEqualTo("bookedDate", outputDate);
                                        query.findInBackground(new FindCallback<ParseObject>() {
                                            public void done(List<ParseObject> scoreList, ParseException e) {
                                                if (scoreList.size() > 0) {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot is busy",
                                                            Toast.LENGTH_SHORT).show();

                                                } else {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot Successfully Booked on " + outputDate,
                                                            Toast.LENGTH_SHORT).show();
                                                    gameScore.put("username", currentUser);
                                                    gameScore.put("bookedDate", outputDate);
                                                    gameScore.put("bookedSlots", "08am-09am");
                                                    gameScore.put("yamaha", yamaha);
                                                    gameScore.put("guitar6", guitar6);
                                                    gameScore.put("fender", fender);
                                                    gameScore.put("bass5", bass5);
                                                    gameScore.put("ejam", ejam);
                                                    gameScore.put("ejamMix", ejamMix);
                                                    gameScore.saveInBackground();
                                                    finish();
                                                    startActivity(getIntent());

                                                }
                                            }
                                        });

                                    }
                                    if (position == 2) {
                                        //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                                        final ParseObject gameScore = new ParseObject("dbBand");
                                        query.whereEqualTo("bookedSlots", "09am-10am");
                                        query.whereEqualTo("bookedDate", outputDate);
                                        query.findInBackground(new FindCallback<ParseObject>() {
                                            public void done(List<ParseObject> scoreList, ParseException e) {
                                                if (scoreList.size() > 0) {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot is busy",
                                                            Toast.LENGTH_SHORT).show();

                                                } else {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot Successfully Booked on " + outputDate,
                                                            Toast.LENGTH_SHORT).show();
                                                    gameScore.put("username", currentUser);
                                                    gameScore.put("bookedDate", outputDate);
                                                    gameScore.put("bookedSlots", "09am-10am");
                                                    gameScore.put("yamaha", yamaha);
                                                    gameScore.put("guitar6", guitar6);
                                                    gameScore.put("fender", fender);
                                                    gameScore.put("bass5", bass5);
                                                    gameScore.put("ejam", ejam);
                                                    gameScore.put("ejamMix", ejamMix);
                                                    gameScore.saveInBackground();
                                                    finish();
                                                    startActivity(getIntent());

                                                }
                                            }
                                        });


                                    }
                                    if (position == 3) {
                                        //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                                        final ParseObject gameScore = new ParseObject("dbBand");
                                        query.whereEqualTo("bookedSlots", "10am-11am");
                                        query.whereEqualTo("bookedDate", outputDate);
                                        query.findInBackground(new FindCallback<ParseObject>() {
                                            public void done(List<ParseObject> scoreList, ParseException e) {
                                                if (scoreList.size() > 0) {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot is busy",
                                                            Toast.LENGTH_SHORT).show();

                                                } else {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot Successfully Booked on " + outputDate,
                                                            Toast.LENGTH_SHORT).show();
                                                    gameScore.put("username", currentUser);
                                                    gameScore.put("bookedDate", outputDate);
                                                    gameScore.put("bookedSlots", "10am-11am");
                                                    gameScore.put("yamaha", yamaha);
                                                    gameScore.put("guitar6", guitar6);
                                                    gameScore.put("fender", fender);
                                                    gameScore.put("bass5", bass5);
                                                    gameScore.put("ejam", ejam);
                                                    gameScore.put("ejamMix", ejamMix);
                                                    gameScore.saveInBackground();
                                                    finish();
                                                    startActivity(getIntent());

                                                }
                                            }
                                        });

                                    }
                                    if (position == 4) {
                                        //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                                        final ParseObject gameScore = new ParseObject("dbBand");
                                        query.whereEqualTo("bookedSlots", "11am-12pm");
                                        query.whereEqualTo("bookedDate", outputDate);
                                        query.findInBackground(new FindCallback<ParseObject>() {
                                            public void done(List<ParseObject> scoreList, ParseException e) {
                                                if (scoreList.size() > 0) {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot is busy",
                                                            Toast.LENGTH_SHORT).show();

                                                } else {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot Successfully Booked on " + outputDate,
                                                            Toast.LENGTH_SHORT).show();
                                                    gameScore.put("username", currentUser);
                                                    gameScore.put("bookedDate", outputDate);
                                                    gameScore.put("bookedSlots", "11am-12pm");
                                                    gameScore.put("yamaha", yamaha);
                                                    gameScore.put("guitar6", guitar6);
                                                    gameScore.put("fender", fender);
                                                    gameScore.put("bass5", bass5);
                                                    gameScore.put("ejam", ejam);
                                                    gameScore.put("ejamMix", ejamMix);
                                                    gameScore.saveInBackground();
                                                    finish();
                                                    startActivity(getIntent());

                                                }
                                            }
                                        });

                                    }
                                    if (position == 5) {
                                        //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                                        final ParseObject gameScore = new ParseObject("dbBand");
                                        query.whereEqualTo("bookedSlots", "12pm-1pm");
                                        query.whereEqualTo("bookedDate", outputDate);
                                        query.findInBackground(new FindCallback<ParseObject>() {
                                            public void done(List<ParseObject> scoreList, ParseException e) {
                                                if (scoreList.size() > 0) {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot is busy",
                                                            Toast.LENGTH_SHORT).show();

                                                } else {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot Successfully Booked on " + outputDate,
                                                            Toast.LENGTH_SHORT).show();
                                                    gameScore.put("username", currentUser);
                                                    gameScore.put("bookedDate", outputDate);
                                                    gameScore.put("bookedSlots", "12pm-1pm");
                                                    gameScore.put("yamaha", yamaha);
                                                    gameScore.put("guitar6", guitar6);
                                                    gameScore.put("fender", fender);
                                                    gameScore.put("bass5", bass5);
                                                    gameScore.put("ejam", ejam);
                                                    gameScore.put("ejamMix", ejamMix);
                                                    gameScore.saveInBackground();
                                                    finish();
                                                    startActivity(getIntent());

                                                }
                                            }
                                        });

                                    }

                                    if (position == 6) {
                                        //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                                        final ParseObject gameScore = new ParseObject("dbBand");
                                        query.whereEqualTo("bookedSlots", "1pm-2pm");
                                        query.whereEqualTo("bookedDate", outputDate);
                                        query.findInBackground(new FindCallback<ParseObject>() {
                                            public void done(List<ParseObject> scoreList, ParseException e) {
                                                if (scoreList.size() > 0) {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot is busy",
                                                            Toast.LENGTH_SHORT).show();

                                                } else {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot Successfully Booked on " + outputDate,
                                                            Toast.LENGTH_SHORT).show();
                                                    gameScore.put("username", currentUser);
                                                    gameScore.put("bookedDate", outputDate);
                                                    gameScore.put("bookedSlots", "1pm-2pm");
                                                    gameScore.put("yamaha", yamaha);
                                                    gameScore.put("guitar6", guitar6);
                                                    gameScore.put("fender", fender);
                                                    gameScore.put("bass5", bass5);
                                                    gameScore.put("ejam", ejam);
                                                    gameScore.put("ejamMix", ejamMix);
                                                    gameScore.saveInBackground();
                                                    finish();
                                                    startActivity(getIntent());

                                                }
                                            }
                                        });

                                    }

                                    if (position == 7) {
                                        //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                                        final ParseObject gameScore = new ParseObject("dbBand");
                                        query.whereEqualTo("bookedSlots", "2pm-3pm");
                                        query.whereEqualTo("bookedDate", outputDate);
                                        query.findInBackground(new FindCallback<ParseObject>() {
                                            public void done(List<ParseObject> scoreList, ParseException e) {
                                                if (scoreList.size() > 0) {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot is busy",
                                                            Toast.LENGTH_SHORT).show();

                                                } else {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot Successfully Booked on " + outputDate,
                                                            Toast.LENGTH_SHORT).show();
                                                    gameScore.put("username", currentUser);
                                                    gameScore.put("bookedDate", outputDate);
                                                    gameScore.put("bookedSlots", "2pm-3pm");
                                                    gameScore.put("yamaha", yamaha);
                                                    gameScore.put("guitar6", guitar6);
                                                    gameScore.put("fender", fender);
                                                    gameScore.put("bass5", bass5);
                                                    gameScore.put("ejam", ejam);
                                                    gameScore.put("ejamMix", ejamMix);
                                                    gameScore.saveInBackground();
                                                    finish();
                                                    startActivity(getIntent());

                                                }
                                            }
                                        });

                                    }

                                    if (position == 8) {
                                        //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                                        final ParseObject gameScore = new ParseObject("dbBand");
                                        query.whereEqualTo("bookedSlots", "3pm-4pm");
                                        query.whereEqualTo("bookedDate", outputDate);
                                        query.findInBackground(new FindCallback<ParseObject>() {
                                            public void done(List<ParseObject> scoreList, ParseException e) {
                                                if (scoreList.size() > 0) {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot is busy",
                                                            Toast.LENGTH_SHORT).show();

                                                } else {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot Successfully Booked on " + outputDate,
                                                            Toast.LENGTH_SHORT).show();
                                                    gameScore.put("username", currentUser);
                                                    gameScore.put("bookedDate", outputDate);
                                                    gameScore.put("bookedSlots", "3pm-4pm");
                                                    gameScore.put("yamaha", yamaha);
                                                    gameScore.put("guitar6", guitar6);
                                                    gameScore.put("fender", fender);
                                                    gameScore.put("bass5", bass5);
                                                    gameScore.put("ejam", ejam);
                                                    gameScore.put("ejamMix", ejamMix);
                                                    gameScore.saveInBackground();
                                                    finish();
                                                    startActivity(getIntent());

                                                }
                                            }
                                        });

                                    }
                                    if (position == 9) {
                                        //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                                        final ParseObject gameScore = new ParseObject("dbBand");
                                        query.whereEqualTo("bookedSlots", "4pm-5pm");
                                        query.whereEqualTo("bookedDate", outputDate);
                                        query.findInBackground(new FindCallback<ParseObject>() {
                                            public void done(List<ParseObject> scoreList, ParseException e) {
                                                if (scoreList.size() > 0) {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot is busy",
                                                            Toast.LENGTH_SHORT).show();

                                                } else {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot Successfully Booked on " + outputDate,
                                                            Toast.LENGTH_SHORT).show();
                                                    gameScore.put("username", currentUser);
                                                    gameScore.put("bookedDate", outputDate);
                                                    gameScore.put("bookedSlots", "4pm-5pm");
                                                    gameScore.put("yamaha", yamaha);
                                                    gameScore.put("guitar6", guitar6);
                                                    gameScore.put("fender", fender);
                                                    gameScore.put("bass5", bass5);
                                                    gameScore.put("ejam", ejam);
                                                    gameScore.put("ejamMix", ejamMix);
                                                    gameScore.saveInBackground();
                                                    finish();
                                                    startActivity(getIntent());

                                                }
                                            }
                                        });

                                    }

                                    if (position == 10) {
                                        //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                                        final ParseObject gameScore = new ParseObject("dbBand");
                                        query.whereEqualTo("bookedSlots", "5pm-6pm");
                                        query.whereEqualTo("bookedDate", outputDate);
                                        query.findInBackground(new FindCallback<ParseObject>() {
                                            public void done(List<ParseObject> scoreList, ParseException e) {
                                                if (scoreList.size() > 0) {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot is busy",
                                                            Toast.LENGTH_SHORT).show();

                                                } else {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot Successfully Booked on " + outputDate,
                                                            Toast.LENGTH_SHORT).show();
                                                    gameScore.put("username", currentUser);
                                                    gameScore.put("bookedDate", outputDate);
                                                    gameScore.put("bookedSlots", "5pm-6pm");
                                                    gameScore.put("yamaha", yamaha);
                                                    gameScore.put("guitar6", guitar6);
                                                    gameScore.put("fender", fender);
                                                    gameScore.put("bass5", bass5);
                                                    gameScore.put("ejam", ejam);
                                                    gameScore.put("ejamMix", ejamMix);
                                                    gameScore.saveInBackground();
                                                    finish();
                                                    startActivity(getIntent());

                                                }
                                            }
                                        });

                                    }
                                    if (position == 11) {
                                        //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                                        final ParseObject gameScore = new ParseObject("dbBand");
                                        query.whereEqualTo("bookedSlots", "6pm-7pm");
                                        query.whereEqualTo("bookedDate", outputDate);
                                        query.findInBackground(new FindCallback<ParseObject>() {
                                            public void done(List<ParseObject> scoreList, ParseException e) {
                                                if (scoreList.size() > 0) {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot is busy",
                                                            Toast.LENGTH_SHORT).show();

                                                } else {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot Successfully Booked on " + outputDate,
                                                            Toast.LENGTH_SHORT).show();
                                                    gameScore.put("username", currentUser);
                                                    gameScore.put("bookedDate", outputDate);
                                                    gameScore.put("bookedSlots", "6pm-7pm");
                                                    gameScore.put("yamaha", yamaha);
                                                    gameScore.put("guitar6", guitar6);
                                                    gameScore.put("fender", fender);
                                                    gameScore.put("bass5", bass5);
                                                    gameScore.put("ejam", ejam);
                                                    gameScore.put("ejamMix", ejamMix);
                                                    gameScore.saveInBackground();
                                                    finish();
                                                    startActivity(getIntent());

                                                }
                                            }
                                        });

                                    }

                                    if (position == 12) {
                                        //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                                        final ParseObject gameScore = new ParseObject("dbBand");
                                        query.whereEqualTo("bookedSlots", "7pm-8pm");
                                        query.whereEqualTo("bookedDate", outputDate);
                                        query.findInBackground(new FindCallback<ParseObject>() {
                                            public void done(List<ParseObject> scoreList, ParseException e) {
                                                if (scoreList.size() > 0) {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot is busy",
                                                            Toast.LENGTH_SHORT).show();

                                                } else {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot Successfully Booked on " + outputDate,
                                                            Toast.LENGTH_SHORT).show();
                                                    gameScore.put("username", currentUser);
                                                    gameScore.put("bookedDate", outputDate);
                                                    gameScore.put("bookedSlots", "7pm-8pm");
                                                    gameScore.put("yamaha", yamaha);
                                                    gameScore.put("guitar6", guitar6);
                                                    gameScore.put("fender", fender);
                                                    gameScore.put("bass5", bass5);
                                                    gameScore.put("ejam", ejam);
                                                    gameScore.put("ejamMix", ejamMix);
                                                    gameScore.saveInBackground();
                                                    finish();
                                                    startActivity(getIntent());

                                                }
                                            }
                                        });


                                    }

                                    if (position == 13) {
                                        //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                                        final ParseObject gameScore = new ParseObject("dbBand");
                                        query.whereEqualTo("bookedSlots", "8pm-9pm");
                                        query.whereEqualTo("bookedDate", outputDate);
                                        query.findInBackground(new FindCallback<ParseObject>() {
                                            public void done(List<ParseObject> scoreList, ParseException e) {
                                                if (scoreList.size() > 0) {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot is busy",
                                                            Toast.LENGTH_SHORT).show();

                                                } else {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot Successfully Booked on " + outputDate,
                                                            Toast.LENGTH_SHORT).show();
                                                    gameScore.put("username", currentUser);
                                                    gameScore.put("bookedDate", outputDate);
                                                    gameScore.put("bookedSlots", "8pm-9pm");
                                                    gameScore.put("yamaha", yamaha);
                                                    gameScore.put("guitar6", guitar6);
                                                    gameScore.put("fender", fender);
                                                    gameScore.put("bass5", bass5);
                                                    gameScore.put("ejam", ejam);
                                                    gameScore.put("ejamMix", ejamMix);
                                                    gameScore.saveInBackground();
                                                    finish();
                                                    startActivity(getIntent());

                                                }
                                            }
                                        });


                                    }

                                    if (position == 14) {
                                        //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                                        final ParseObject gameScore = new ParseObject("dbBand");
                                        query.whereEqualTo("bookedSlots", "9pm-10pm");
                                        query.whereEqualTo("bookedDate", outputDate);
                                        query.findInBackground(new FindCallback<ParseObject>() {
                                            public void done(List<ParseObject> scoreList, ParseException e) {
                                                if (scoreList.size() > 0) {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot is busy",
                                                            Toast.LENGTH_SHORT).show();

                                                } else {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Slot Successfully Booked on " + outputDate,
                                                            Toast.LENGTH_SHORT).show();
                                                    gameScore.put("username", currentUser);
                                                    gameScore.put("bookedDate", outputDate);
                                                    gameScore.put("bookedSlots", "9pm-10pm");
                                                    gameScore.put("yamaha", yamaha);
                                                    gameScore.put("guitar6", guitar6);
                                                    gameScore.put("fender", fender);
                                                    gameScore.put("bass5", bass5);
                                                    gameScore.put("ejam", ejam);
                                                    gameScore.put("ejamMix", ejamMix);
                                                    gameScore.saveInBackground();
                                                    finish();
                                                    startActivity(getIntent());

                                                }
                                            }
                                        });


                                    }

                                    dialogBuilder.dismiss();
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }

                            }
                        })
                        .setButton2Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                                Toast.makeText(v.getContext(), "Back", Toast.LENGTH_SHORT).show();
                                dialogBuilder.dismiss();
                            }
                        })
                        .show();


            }
        });

    }

    public class Read extends AsyncTask<String, Integer, String> {
        ProgressDialog pDialog;

        @Override
        protected String doInBackground(String... params) {

            //  final_json_array = get_entire_json();
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


                                CustomArrayAdapter dataAdapter = new CustomArrayAdapter(ThirdDay.this, R.id.label, rowDataList);
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
                                CustomArrayAdapter dataAdapter = new CustomArrayAdapter(ThirdDay.this, R.id.label, rowDataList);
                                dataAdapter.sort(new Comparator<RowData>() {
                                    public int compare(RowData arg0, RowData arg1) {
                                        return arg0.getSlot().compareTo(arg1.getSlot());
                                    }
                                });


                                dataAdapter.notifyDataSetChanged();
                                listView.setAdapter(dataAdapter);


                            }
                        }
                    });

                }



                    /*CustomArrayAdapter dataAdapter = new CustomArrayAdapter(DayAfter.this, R.id.label, rowDataList);
                    listView.setAdapter(dataAdapter);
*/


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