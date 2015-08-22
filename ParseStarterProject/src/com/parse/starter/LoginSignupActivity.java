package com.parse.starter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by kai on 8/7/15.
 */
public class LoginSignupActivity extends Activity {
    Button loginButton;
    Button signupButton;
    String usernametxt;
    String passwordtxt;
    EditText password;
    EditText username;
    EditText phone;
    String phonetxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        phone = (EditText) findViewById(R.id.phone);


        loginButton = (Button) findViewById(R.id.login);
        signupButton = (Button) findViewById(R.id.signup);


        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();

                if (isNetworkAvailable()) {

                    ParseUser.logInInBackground(usernametxt, passwordtxt,
                            new LogInCallback() {

                                @Override
                                public void done(ParseUser user, ParseException e) {
                                    if (user != null) {

                                        Intent intent = new Intent(
                                                LoginSignupActivity.this,
                                                HomePage.class);
                                        startActivity(intent);
                                        finish();

                                    } else {

                                        Toast.makeText(
                                                getApplicationContext(),
                                                "This user doesn't exist. Please Sign Up",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "Connected to the Internet to Proceed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        signupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();
                phonetxt = phone.getText().toString();


                if (isNetworkAvailable()) {

                    if (usernametxt.equals("") && passwordtxt.equals("")) {

                        Toast.makeText(getApplicationContext(),
                                "Please complete the sign up form",
                                Toast.LENGTH_SHORT).show();

                    } else if (phonetxt.equals("") || phonetxt.length() < 8 || phonetxt.length() > 11) {
                        Toast.makeText(getApplicationContext(),
                                "Please enter valid phone number",
                                Toast.LENGTH_SHORT).show();
                    } else {

                        ParseUser user = new ParseUser();
                        user.setUsername(usernametxt);
                        user.setPassword(passwordtxt);
                        user.put("phone", phonetxt);
                        user.signUpInBackground(new SignUpCallback() {

                            @Override
                            public void done(ParseException e) {
                                if (e == null) {

                                    Toast.makeText(getApplicationContext(),
                                            "Successfully Signed up! Please Click on Login",
                                            Toast.LENGTH_SHORT).show();

                                } else {

                                    Toast.makeText(getApplicationContext(),
                                            "Sign up error", Toast.LENGTH_SHORT)
                                            .show();

                                }
                            }
                        });

                    }
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "Connected to the Internet to Proceed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
