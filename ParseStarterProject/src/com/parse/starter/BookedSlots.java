package com.parse.starter;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by kai on 17/7/15.
 */
public class BookedSlots extends FragmentActivity {
    ListView listView;
    RowData ob;
    ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
    final String[] values = new String[]{"07am-08am", "08am-09am", "09am-10am", "10am-11am", "11am-12pm",
            "12pm-1pm", "1pm-2pm", "2pm-3pm", "3pm-4pm", "4pm-5pm", "5pm-6pm", "6pm-7pm"};

    ArrayList<String> array_list_posts;
    ArrayList<RowData> rowDataList;
    final ParseUser currentUser = ParseUser.getCurrentUser();
    int count0 = 0, count1 = 0, count2 = 0, count3 = 0;
    int ansToday, ansTomorrow, ansDayAfter, ansThird;
    int yamaha, guitar6, fender, bass5, ejam, ejamMix;


    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    // Get the date today using Calendar object.
    Date today = Calendar.getInstance().getTime();
    // Using DateFormat format method we can create a string
// representation of a date with the defined format.
    String reportDate = df.format(today);
    String outputDate = "";
    String outputDateT = "";
    String outputDate2 = "";
    String outputDate3 = "";


    int amt = 300;
    TextView tvtoday, tvtomorrow, tvdayafter, tvthirdday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts);


        tvtoday = (TextView) findViewById(R.id.today);
        tvtomorrow = (TextView) findViewById(R.id.tomorrow);
        tvdayafter = (TextView) findViewById(R.id.dayafter);
        tvthirdday = (TextView) findViewById(R.id.thirdday);


        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();


        try {
            c.setTime(sdf.parse(reportDate));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        c.add(Calendar.DATE, 0);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        SimpleDateFormat sdfT = new SimpleDateFormat("MM/dd/yyyy");
        outputDateT = sdfT.format(c.getTime());

        c.add(Calendar.DATE, 1);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
        outputDate = sdf1.format(c.getTime());


        c.add(Calendar.DATE, 1);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
        outputDate2 = sdf2.format(c.getTime());


        c.add(Calendar.DATE, 1);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        SimpleDateFormat sdf3 = new SimpleDateFormat("MM/dd/yyyy");
        outputDate3 = sdf3.format(c.getTime());
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(this);
        listView = (ListView) findViewById(R.id.list);

        //final String struser = currentUser.getUsername().toString();
        new Read().execute();

        //delete
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView label = (TextView) view.findViewById(R.id.label);
                TextView slot = (TextView) view.findViewById(R.id.tvslot);

                String slabel = label.getText().toString();
                String sslot = slot.getText().toString();

                ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                query.whereEqualTo("bookedSlots", sslot);
                query.whereEqualTo("bookedDate", slabel);

                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(final List<ParseObject> scoreList, ParseException e) {
                        if (scoreList.size() > 0) {
                            try {

                                dialogBuilder
                                        .withTitle("Confirmation")                                  //.withTitle(null)  no title
                                        .withTitleColor("#FFFFFF")                                  //def
                                        .withDividerColor("#11000000")                              //def
                                        .withMessage("Are you sure you want to delete this booked slot ?")                     //.withMessage(null)  no Msg
                                        .withMessageColor("#FF000000")                              //def  | withMessageColor(int resid)
                                        .withDialogColor("#FFD4D60C")                               //def  | withDialogColor(int resid)
                                        .withDuration(700)                                          //def
                                        .withButton1Text("Delete")                                      //def gone
                                        .withButton2Text("Back")                                  //def gone
                                        .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                                        .setButton1Click(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(v.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                                                try {
                                                    scoreList.get(0).delete();


                                                  /*  if (count0 == 1) {
                                                        tvthirdday.setText("Todays amount 0");
                                                    }

                                                    if (count1 == 1) {
                                                        tvthirdday.setText("Tomorrow amount 0");
                                                    }

                                                    if (count2 == 1) {
                                                        tvthirdday.setText("Day after amount 0");
                                                    }

                                                    if (count3 == 1) {
                                                        tvthirdday.setText("Third day amount 0");
                                                    }*/
                                                    finish();
                                                    startActivity(getIntent());


                                                    //new Read().execute();

                                                    dialogBuilder.dismiss();
                                                } catch (ParseException e1) {
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


                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }


                        } else {


                        }
                    }
                });

            }
        });
    }

    public class Read extends AsyncTask<String, Integer, String> {
        ProgressDialog pDialog;

        @Override
        protected String doInBackground(String... params) {

            //  final_json_array = get_entire_json();
            try {


                rowDataList = new ArrayList<RowData>(12);


                //retrieve
                ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");

                query.whereEqualTo("username", currentUser);


                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> scoreList, ParseException e) {
                        if (scoreList.size() > 0) {

                            String[] array = new String[scoreList.size()];
                            String[] array1 = new String[scoreList.size()];
                            int index = 0;
                            for (ParseObject value : scoreList) {

                                ob = new RowData();
                                //to access the properties of the Deals object.
                                //to access the properties of the Deals object.
                                ob.setSlot(value.getString("bookedSlots"));
                                ob.setTex(value.getString("bookedDate"));
                                yamaha = value.getInt("yamaha");
                                guitar6 = value.getInt("guitar6");
                                fender = value.getInt("fender");
                                bass5 = value.getInt("bass5");
                                ejam = value.getInt("ejam");
                                ejamMix = value.getInt("ejamMix");

                                Log.d(ob.getTex(), "GOT TEXT BOYSA ");
                                Log.d(ob.getSlot(), "GOT TEXT BOYSA ");

                                if (ob.getTex().compareTo(outputDateT) < 0) {
                                    continue;
                                }
                                rowDataList.add(ob);

                                //today guitar6, fender, bass5, ejam, ejamMix;
                                if (ob.getTex().compareTo(outputDateT) == 0) {
                                    count0++;
                                    if (count0 >= 4) {
                                        if (count0 == 4) {
                                            ansToday -= 300 * 3;
                                            amt = 250 * 4;
                                        }
                                        if (count0 > 4) {
                                            amt = 250;

                                        }
                                        ansToday += amt + yamaha + guitar6 + fender + bass5 + ejam + ejamMix;

                                    } else {
                                        ansToday += yamaha + guitar6 + fender + bass5 + ejam + ejamMix;
                                    }

                                    if (count0 <= 3) {
                                        ansToday += 300;
                                    }

                                    tvtoday.setText("Today's Amount " + ansToday);

                                }

                                //Tomorrow
                                if (ob.getTex().compareTo(outputDate) == 0) {
                                    count1++;

                                    if (count1 >= 4) {
                                        if (count1 == 4) {
                                            ansTomorrow -= 300 * 3;
                                            amt = 250 * 4;
                                        }
                                        if (count1 > 4) {
                                            amt = 250;

                                        }
                                        ansTomorrow += amt + yamaha + guitar6 + fender + bass5 + ejam + ejamMix;

                                    } else {
                                        ansTomorrow += yamaha + guitar6 + fender + bass5 + ejam + ejamMix;
                                    }

                                    if (count1 <= 3) {
                                        ansTomorrow += 300;
                                    }


                                    tvtomorrow.setText("Tomorrow's Amount " + ansTomorrow);


                                }

                                //Day aftyer
                                if (ob.getTex().compareTo(outputDate2) == 0) {
                                    count2++;

                                    if (count2 >= 4) {
                                        if (count2 == 4) {
                                            ansDayAfter -= 300 * 3;
                                            amt = 250 * 4;
                                        }
                                        if (count2 > 4) {
                                            amt = 250;

                                        }
                                        ansDayAfter += amt + yamaha + guitar6 + fender + bass5 + ejam + ejamMix;

                                    } else {
                                        ansDayAfter += yamaha + guitar6 + fender + bass5 + ejam + ejamMix;
                                    }

                                    if (count2 <= 3) {
                                        ansDayAfter += 300;
                                    }
                                    tvdayafter.setText("Day After Tomorrow's Amount - " + ansDayAfter);

                                }
                                //third
                                if (ob.getTex().compareTo(outputDate3) == 0) {
                                    count3++;


                                    if (count3 >= 4) {
                                        if (count3 == 4) {
                                            ansThird -= 300 * 3;
                                            amt = 250 * 4;
                                        }
                                        if (count3 > 4) {
                                            amt = 250;

                                        }
                                        ansThird += amt + yamaha + guitar6 + fender + bass5 + ejam + ejamMix;

                                    } else {
                                        ansThird += yamaha + guitar6 + fender + bass5 + ejam + ejamMix;
                                    }

                                    if (count3 <= 3) {
                                        ansThird += 300;
                                    }
                                }
                                tvthirdday.setText("Third day's Amount - " + ansThird);

                            }


                            CustomArrayAdapter dataAdapter = new CustomArrayAdapter(BookedSlots.this, R.id.label, rowDataList);
                            dataAdapter.sort(new Comparator<RowData>() {
                                public int compare(RowData arg0, RowData arg1) {
                                    return arg0.getTex().compareTo(arg1.getTex());
                                }
                            });


                            dataAdapter.notifyDataSetChanged();
                            listView.setAdapter(dataAdapter);

                        } else {
                            ob = new RowData();
                            ob.setSlot("No booked slots");
                            ob.setTex("");
                            Log.d(ob.getTex(), "GOT TEXT BOYSA ");
                            Log.d(ob.getSlot(), "GOT TEXT BOYSA ");
                            rowDataList.add(ob);
                            CustomArrayAdapter dataAdapter = new CustomArrayAdapter(BookedSlots.this, R.id.label, rowDataList);
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
