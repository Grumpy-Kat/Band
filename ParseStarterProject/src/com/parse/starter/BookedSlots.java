package com.parse.starter;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by kai on 17/7/15.
 */
public class BookedSlots extends FragmentActivity {
    ListView listView;
    RowData ob;
    ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
    final String[] values = new String[] { "07am-08am","08am-09am","09am-10am","10am-11am","11am-12pm",
            "12pm-1pm","1pm-2pm","2pm-3pm","3pm-4pm","4pm-5pm","5pm-6pm","6pm-7pm"};

    ArrayList<String> array_list_posts;
    ArrayList<RowData> rowDataList;
    final ParseUser currentUser = ParseUser.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts);
        listView = (ListView)findViewById(R.id.list);

        //final String struser = currentUser.getUsername().toString();
        new Read().execute();
    }

    public class Read extends AsyncTask<String, Integer, String> {
        ProgressDialog pDialog;

        @Override
        protected String doInBackground(String... params) {

            //  final_json_array = get_entire_json();
            try {


                rowDataList = new ArrayList<RowData>(12);

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");

                    query.whereEqualTo("username", currentUser );


                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size()>0) {

                                String[] array = new String[scoreList.size()];
                                String[] array1 = new String[scoreList.size()];
                                int index = 0;
                                for (ParseObject value : scoreList) {

                                    ob = new RowData();
                                    //to access the properties of the Deals object.
                                    //to access the properties of the Deals object.
                                    ob.setSlot(value.getString("bookedSlots"));
                                    ob.setTex(value.getString("bookedDate"));
                                    Log.d(ob.getTex(), "GOT TEXT BOYSA ");
                                    Log.d(ob.getSlot(), "GOT TEXT BOYSA ");
                                    rowDataList.add(ob);
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
