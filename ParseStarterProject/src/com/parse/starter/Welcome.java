package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;

import com.parse.ParseUser;

/**
 * Created by kai on 8/7/15.
 */
public class Welcome extends Activity {
    Button logout,Book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ParseUser currentUser = ParseUser.getCurrentUser();

        String struser = currentUser.getUsername().toString();

        TextView txtUser = (TextView) findViewById(R.id.txtuser);
        txtUser.setText("You are logged in as " + struser);

        final ListView list = (ListView) findViewById(R.id.lvdates);

        final String[] values = new String[] { "Tomorrow","DayAfter","Dayafter+1" }; // You have the necessary data to bind the list.

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values); // You have set     the previous array to an adapter that can be now setted to a list.

        list.setAdapter(adapter);

       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               if(position==0){
                   Intent intent = new Intent(Welcome.this,Tomorrow.class);
                   startActivity(intent);

               }


           }
       });

        logout = (Button) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                finish();
            }
        });
    }
}

