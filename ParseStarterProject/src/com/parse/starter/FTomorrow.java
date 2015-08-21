package com.parse.starter;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import it.carlom.stikkyheader.core.StikkyHeaderBuilder;
import it.carlom.stikkyheader.core.animator.AnimatorBuilder;
import it.carlom.stikkyheader.core.animator.HeaderStikkyAnimator;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


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

import it.carlom.stikkyheader.core.StikkyHeaderBuilder;
import it.carlom.stikkyheader.core.animator.AnimatorBuilder;
import it.carlom.stikkyheader.core.animator.HeaderStikkyAnimator;

public class FTomorrow extends Fragment {

    //Declarations
    RowData ob;
    ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
    ArrayList<RowData> rowDataList;


    ArrayList<String> array_list_posts;

    ArrayAdapter<String> adapter;

    TextView tvslot;
    LinearLayout containerLayout;
    Boolean[] array = new Boolean[12];
    final String[] values = new String[]{"07am-08am", "08am-09am", "09am-10am", "10am-11am", "11am-12pm",
            "12pm-1pm", "1pm-2pm", "2pm-3pm", "3pm-4pm", "4pm-5pm", "5pm-6pm", "6pm-7pm"};
    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    // Get the date today using Calendar object.
    Date today = Calendar.getInstance().getTime();
    // Using DateFormat format method we can create a string
// representation of a date with the defined format.
    String reportDate = df.format(today);
    String outputDate = "";
    TextView headerDate;
    CustomArrayAdapter dataAdapter;


    int yamaha, guitar6, fender, bass5, ejam, ejamMix, drummidi, drumidiediting, drumprogramming, greamping, editingsession, mastering, mixing;


    private ListView mListView;

    public FTomorrow() {
        // Required empty public constructor
    }

    @Override
    public void onStop() {
        super.onStop();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_parallax, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Bundle bundle = getArguments();
        yamaha = bundle.getInt("Yamaha");
        Toast.makeText(getActivity(), " " + yamaha, Toast.LENGTH_LONG).show();


        guitar6 = bundle.getInt("6Guitar");
        fender = bundle.getInt("FenderGuitar");
        bass5 = bundle.getInt("5Bass");
        ejam = bundle.getInt("Ejam");
        ejamMix = bundle.getInt("EjamSeparate");
//        drummidi = bundle.getInt("Yamaha");
//        drumidiediting = bundle.getInt("Yamaha");
//        drumprogramming = bundle.getInt("Yamaha");
//        greamping = bundle.getInt("Yamaha");
//        editingsession = bundle.getInt("Yamaha");
//        mastering = bundle.getInt("Yamaha");
//        mixing = bundle.getInt("Yamaha");

        mListView = (ListView) view.findViewById(R.id.lv1);


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


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {




                if (position == 1) {
                    final ParseObject gameScore = new ParseObject("dbBand");

                    query.whereEqualTo("bookedSlots", "07am-08am");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size() > 0) {
                                Log.d("score", "Retrieved " + scoreList.size() + " scores");
                                Toast.makeText(getActivity(),
                                        "Slot is busy" + position,
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getActivity(),
                                        "Success" + position,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "07am-08am");


                                gameScore.put("yamaha", yamaha);
                                gameScore.put("guitar6", guitar6);
                                gameScore.put("fender", fender);
                                gameScore.put("bass5",  bass5);
                                gameScore.put("ejam", ejam);
                                gameScore.put("ejamMix", ejamMix);

                                gameScore.saveInBackground();

                                new Read().execute();

                            }
                        }
                    });
                }


                if (position == 2) {
                    //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    final ParseObject gameScore = new ParseObject("dbBand");
                    query.whereEqualTo("bookedSlots", "08am-09am");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size() > 0) {
                                Toast.makeText(getActivity(),
                                        "Slot is busy" + position,
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getActivity(),
                                        "Success" + outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "08am-09am");
                                gameScore.saveInBackground();
                                new Read().execute();


                            }
                        }
                    });

                }
                if (position == 3) {
                    //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    final ParseObject gameScore = new ParseObject("dbBand");
                    query.whereEqualTo("bookedSlots", "09am-10am");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size() > 0) {
                                Toast.makeText(getActivity(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getActivity(),
                                        "Success" + outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "09am-10am");
                                gameScore.saveInBackground();
                                new Read().execute();

                            }
                        }
                    });


                }
                if (position == 4) {
                    //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    final ParseObject gameScore = new ParseObject("dbBand");
                    query.whereEqualTo("bookedSlots", "10am-11am");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size() > 0) {
                                Toast.makeText(getActivity(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getActivity(),
                                        "Success" + outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "10am-11am");
                                gameScore.saveInBackground();
                                new Read().execute();


                            }
                        }
                    });

                }
                if (position == 5) {
                    //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    final ParseObject gameScore = new ParseObject("dbBand");
                    query.whereEqualTo("bookedSlots", "11am-12pm");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size() > 0) {
                                Toast.makeText(getActivity(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getActivity(),
                                        "Success" + outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "11am-12pm");
                                gameScore.saveInBackground();
                                new Read().execute();


                            }
                        }
                    });

                }
                if (position == 6) {
                    //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    final ParseObject gameScore = new ParseObject("dbBand");
                    query.whereEqualTo("bookedSlots", "12pm-1pm");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size() > 0) {
                                Toast.makeText(getActivity(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getActivity(),
                                        "Success" + outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "12pm-1pm");
                                gameScore.saveInBackground();
                                new Read().execute();

                            }
                        }
                    });

                }

                if (position == 7) {
                    //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    final ParseObject gameScore = new ParseObject("dbBand");
                    query.whereEqualTo("bookedSlots", "1pm-2pm");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size() > 0) {
                                Toast.makeText(getActivity(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getActivity(),
                                        "Success" + outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "1pm-2pm");
                                gameScore.saveInBackground();
                                new Read().execute();


                            }
                        }
                    });

                }

                if (position == 8) {
                    //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    final ParseObject gameScore = new ParseObject("dbBand");
                    query.whereEqualTo("bookedSlots", "2pm-3pm");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size() > 0) {
                                Toast.makeText(getActivity(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getActivity(),
                                        "Success" + outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "2pm-3pm");
                                gameScore.saveInBackground();
                                new Read().execute();


                            }
                        }
                    });

                }

                if (position == 9) {
                    //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    final ParseObject gameScore = new ParseObject("dbBand");
                    query.whereEqualTo("bookedSlots", "3pm-4pm");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size() > 0) {
                                Toast.makeText(getActivity(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getActivity(),
                                        "Success" + outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "3pm-4pm");
                                gameScore.saveInBackground();
                                new Read().execute();


                            }
                        }
                    });

                }
                if (position == 10) {
                    //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    final ParseObject gameScore = new ParseObject("dbBand");
                    query.whereEqualTo("bookedSlots", "4pm-5pm");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size() > 0) {
                                Toast.makeText(getActivity(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getActivity(),
                                        "Success" + outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "4pm-5pm");
                                gameScore.saveInBackground();
                                new Read().execute();

                            }
                        }
                    });

                }

                if (position == 11) {
                    //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    final ParseObject gameScore = new ParseObject("dbBand");
                    query.whereEqualTo("bookedSlots", "5pm-6pm");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size() > 0) {
                                Toast.makeText(getActivity(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getActivity(),
                                        "Success" + outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "5pm-6pm");
                                gameScore.saveInBackground();
                                new Read().execute();


                            }
                        }
                    });

                }
                if (position == 12) {
                    //  ParseQuery<ParseObject> query = ParseQuery.getQuery("dbBand");
                    final ParseObject gameScore = new ParseObject("dbBand");
                    query.whereEqualTo("bookedSlots", "6pm-7pm");
                    query.whereEqualTo("bookedDate", outputDate);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (scoreList.size() > 0) {
                                Toast.makeText(getActivity(),
                                        "Slot is busy",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getActivity(),
                                        "Success" + outputDate,
                                        Toast.LENGTH_SHORT).show();
                                gameScore.put("username", currentUser);
                                gameScore.put("bookedDate", outputDate);
                                gameScore.put("bookedSlots", "6pm-7pm");
                                gameScore.saveInBackground();
                                new Read().execute();


                            }
                        }
                    });

                }


            }

        });


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        StikkyHeaderBuilder.stickTo(mListView)
                .setHeader(R.id.h1, (ViewGroup) getView())
                .minHeightHeader(80)
                .animator(new ParallaxStikkyAnimator())
                .build();


    }


    private class ParallaxStikkyAnimator extends HeaderStikkyAnimator {

        @Override
        public AnimatorBuilder getAnimatorBuilder() {
            View mHeader_image = getHeader().findViewById(R.id.header_image);

            return AnimatorBuilder.create().applyVerticalParallax(mHeader_image);
        }
    }

    public class Read extends AsyncTask<String, Integer, String> {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("ASYNC", "GAWT IN POST");
        }

        @Override
        protected String doInBackground(String... params) {

            //  final_json_array = get_entire_json();
            try {


                rowDataList = new ArrayList<RowData>(12);
                for (int i = 0; i < 12; i++) {
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


                                CustomArrayAdapter dataAdapter = new CustomArrayAdapter(getActivity(), R.id.label, rowDataList);
                                dataAdapter.sort(new Comparator<RowData>() {
                                    public int compare(RowData arg0, RowData arg1) {
                                        return arg0.getSlot().compareTo(arg1.getSlot());
                                    }
                                });


                                dataAdapter.notifyDataSetChanged();
                                mListView.setAdapter(dataAdapter);


                            } else {
                                ob = new RowData();
                                ob.setSlot(values[finalI]);
                                ob.setTex("available");
                                Log.d(ob.getTex(), "GOT TEXT BOYSA ");
                                Log.d(ob.getSlot(), "GOT TEXT BOYSA ");
                                rowDataList.add(ob);
                                CustomArrayAdapter dataAdapter = new CustomArrayAdapter(getActivity(), R.id.label, rowDataList);
                                dataAdapter.sort(new Comparator<RowData>() {
                                    public int compare(RowData arg0, RowData arg1) {
                                        return arg0.getSlot().compareTo(arg1.getSlot());
                                    }
                                });


                                dataAdapter.notifyDataSetChanged();
                                mListView.setAdapter(dataAdapter);


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


    }

}
