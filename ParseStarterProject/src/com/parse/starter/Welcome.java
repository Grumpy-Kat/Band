package com.parse.starter;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kai on 8/7/15.
 */
public class Welcome extends FragmentActivity {
    Button logout, MyBookedSlots;
    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    // Get the date today using Calendar object.
    Date today = Calendar.getInstance().getTime();
    // Using DateFormat format method we can create a string
// representation of a date with the defined format.
    String reportDate = df.format(today);
    String outputDate = "";
    String outputDate2 = "";
    String outputDate3 = "";


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LinearLayout lin = (LinearLayout) findViewById(R.id.mL);
        lin.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ParseUser currentUser = ParseUser.getCurrentUser();

        String struser = currentUser.getUsername().toString();

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();


        try {
            c.setTime(sdf.parse(reportDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        c.add(Calendar.DATE, 1);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
        outputDate = sdf1.format(c.getTime());


        c.add(Calendar.DATE, 1);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
        outputDate2 = sdf2.format(c.getTime());


        c.add(Calendar.DATE, 1);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        SimpleDateFormat sdf3 = new SimpleDateFormat("MM/dd/yyyy");
        outputDate3 = sdf3.format(c.getTime());

        FragmentManager fm = getFragmentManager();
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (getFragmentManager().getBackStackEntryCount() == 0) finish();
                LinearLayout lin = (LinearLayout) findViewById(R.id.mL);
                lin.setVisibility(View.VISIBLE);
            }
        });


        TextView txtUser = (TextView) findViewById(R.id.txtuser);
        txtUser.setText("Welcome " + struser);

        final ListView list = (ListView) findViewById(R.id.lvdates);

        // MyBookedSlots = (Button)findViewById(R.id.BookedSlot);

        final String[] values = new String[]{outputDate, outputDate2, outputDate3}; // You have the necessary data to bind the list.

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.myrow, android.R.id.text1, values); // You have set     the previous array to an adapter that can be now setted to a list.

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                  /* Intent intent = new Intent(Welcome.this,Tomorrow.class);
                   startActivity(intent);*/
                    if (savedInstanceState == null) {
                        Fragment fragment = null;
                        fragment = new FTomorrow();
                        loadFragment(fragment);

                    }

                }
                if (position == 1) {
                   /*Intent intent = new Intent(Welcome.this,DayAfter.class);
                   startActivity(intent);*/
                    if (savedInstanceState == null) {
                        Fragment fragment = null;
                        fragment = new ParallaxStikkyFragment();
                        loadFragment(fragment);

                    }


                }
                if (position == 2) {
                   /*Intent intent = new Intent(Welcome.this,ThirdDay.class);
                   startActivity(intent);*/
                    if (savedInstanceState == null) {
                        Fragment fragment = null;
                        fragment = new FThirdDay();
                        loadFragment(fragment);

                    }

                }

            }

        });


//        MyBookedSlots.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(Welcome.this,BookedSlots.class);
//                startActivity(i);
//            }
//        });
//
//
//        logout = (Button) findViewById(R.id.logout);
//
//        logout.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                ParseUser.logOut();
//                finish();
//            }
//        });
    }


    public void loadFragment(final Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.letssee, fragment, fragment.getClass().getName())
                .addToBackStack(fragment.getClass().getName())
                .commit();
        LinearLayout lin = (LinearLayout) findViewById(R.id.mL);
        lin.setVisibility(View.INVISIBLE);


    }


}

